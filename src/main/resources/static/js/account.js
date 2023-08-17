$(document).ready(function() {
    var accountIcon = $(".account");
    var accountModal = $("#accountModal");

    accountIcon.click(function(event) {
        event.stopPropagation(); // Остановим событие, чтобы избежать перенаправления

        var iconRect = accountIcon[0].getBoundingClientRect();
        var iconWidth = iconRect.width;
        var iconHeight = iconRect.height;
        var iconTop = iconRect.top;
        var iconLeft = iconRect.left;

        accountModal.css({
            "top": iconTop + iconHeight + "px",
            "left": iconLeft + "px",
            "display": (accountModal.css("display") === "block") ? "none" : "block"
        });

        var modalRect = accountModal[0].getBoundingClientRect();
        var windowWidth = $(window).width();

        if (modalRect.right > windowWidth) {
            accountModal.css("left", (windowWidth - modalRect.width) + "px");
        }
    });

    // Скрыть модальное окно при клике в любом другом месте на странице
    $(document).click(function(event) {
        if (!$(event.target).closest(accountModal).length && !$(event.target).is(accountIcon)) {
            accountModal.css("display", "none");
        }
    });

    // Скрываем форму с изменением пароля при загрузке страницы
    $("#passwordFormContainer").hide();

    // При нажатии на кнопку "Изменить пароль"
    $("#showPasswordFormButton").click(function() {
        $("#profileFormContainer").hide();
        $("#passwordFormContainer").show();
        $("#error").empty();
        $("#success").empty();
    });

    // При нажатии на кнопку "Отменить"
    $("#showProfileFormButton").click(function() {
        $("#passwordFormContainer").hide();
        $("#profileFormContainer").show();
        $("#error").empty();
        $("#success").empty();
    });
});