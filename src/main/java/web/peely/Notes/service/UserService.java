package web.peely.Notes.service;

import org.springframework.transaction.annotation.Transactional;
import web.peely.Notes.entity.Note;
import web.peely.Notes.entity.Role;
import web.peely.Notes.entity.User;
import web.peely.Notes.repository.NoteRepository;
import web.peely.Notes.repository.RoleRepository;
import web.peely.Notes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    NoteRepository noteRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public Long findIdByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).getId();
    }

    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        String[] dates = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday", "Someday"};
        for (int i = 0; i < 8; i++) {
            Note note = new Note();
            note.setUsername(user.getUsername());
            note.setDate(dates[i]);
            noteRepository.save(note);
        }
        return true;
    }

    @Transactional
    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            noteRepository.deleteByUsername(userRepository.findById(userId).get().getUsername());
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<User> usergtList(Long idMin) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }

    public List<Note> getNote (String username) {
        return noteRepository.findByUsernameOrderById(username);
    }

    public void updateNote (Note note) {
        noteRepository.save(note);
    }

    public boolean updatePassword(User user, String password, String newPassword) {
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return true;
        }

        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        userRepository.save(user);
        return false;
    }

    public void updateProfile(User user) {
        userRepository.save(user);
    }
}