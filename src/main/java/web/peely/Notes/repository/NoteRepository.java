package web.peely.Notes.repository;

import web.peely.Notes.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUsernameOrderById(String username);
    void deleteByUsername(String username);
}