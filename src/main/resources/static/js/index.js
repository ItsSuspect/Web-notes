const elements = document.getElementsByClassName("inputs");

$(document).ready(function() {
    // Функция обработчика отправки формы
    function handleSubmit(event) {
        // Отменяем стандартное поведение отправки формы (перезагрузка страницы)
        event.preventDefault();

        // Получение данных формы
        const formData = $(this).serialize();

        // AJAX-запрос
        $.ajax({
            type: 'POST', // Или 'GET', в зависимости от вашего случая
            url: '/', // Укажите URL для обработки данных на сервере
            data: formData,
        });
    }

    // Добавляем обработчик события "submit" для каждой формы
    $('.forms').submit(handleSubmit);

    // Добавляем обработчик события "blur" для всех полей ввода в формах
    $('.forms input').blur(function() {
        // Получаем родительскую форму для текущего поля ввода
        const form = $(this).closest('.forms');

        // Вызываем обработчик отправки формы только для этой формы
        handleSubmit.call(form[0], new Event('submit'));
    });

    $('.inputs').on('input', function() {
        var $form = $(this).closest('.forms');
        var $inputFields = $form.find('.inputs');

        var emptyIndex = -1;
        var nonEmptyFields = [];
        $inputFields.each(function(index, element) {
            if ($(element).val() === '' && emptyIndex === -1) {
                emptyIndex = index;
            }
            if ($(element).val() !== '') {
                nonEmptyFields.push($(element).val());
            }
        });

        if (emptyIndex !== -1) {
            for (var i = 0; i < $inputFields.length; i++) {
                if (i < nonEmptyFields.length) {
                    $inputFields.eq(i).val(nonEmptyFields[i]);
                } else {
                    $inputFields.eq(i).val('');
                }
            }
        }
    });

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

        // Проверка и коррекция положения модального окна, если оно выходит за границы экрана
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
});

function fieldsMonday(id) {
    let nullPoint = -1;
    for (let i = 0; i < 10; i++) {
        if (elements.item(i).value==="") {
            nullPoint = i;
            break;
        }
    }

    if (elements.item(id).value==="") elements.item(nullPoint).focus();
}

function fieldsTuesday(id) {
    let nullPoint = -1;
    for (let i = 10; i < 20; i++) {
        if (elements.item(i).value==="") {
            nullPoint = i;
            break;
        }
    }

    if (elements.item(id).value==="") elements.item(nullPoint).focus();
}

function fieldsWednesday(id) {
    let nullPoint = -1;
    for (let i = 20; i < 30; i++) {
        if (elements.item(i).value==="") {
            nullPoint = i;
            break;
        }
    }

    if (elements.item(id).value==="") elements.item(nullPoint).focus();
}

function fieldsThursday(id) {
    let nullPoint = -1;
    for (let i = 30; i < 40; i++) {
        if (elements.item(i).value==="") {
            nullPoint = i;
            break;
        }
    }

    if (elements.item(id).value==="") elements.item(nullPoint).focus();
}

function fieldsFriday(id) {
    let nullPoint = -1;
    for (let i = 40; i < 50; i++) {
        if (elements.item(i).value==="") {
            nullPoint = i;
            break;
        }
    }

    if (elements.item(id).value==="") elements.item(nullPoint).focus();
}

function fieldsSaturday(id) {
    let nullPoint = -1;
    for (let i = 50; i < 54; i++) {
        if (elements.item(i).value==="") {
            nullPoint = i;
            break;
        }
    }

    if (elements.item(id).value==="") elements.item(nullPoint).focus();
}

function fieldsSunday(id) {
    let nullPoint = -1;
    for (let i = 54; i < 58; i++) {
        if (elements.item(i).value==="") {
            nullPoint = i;
            break;
        }
    }

    if (elements.item(id).value==="") elements.item(nullPoint).focus();
}

function fieldsSomeday(id) {
    let nullPoint = -1;
    for (let i = 58; i < 61; i++) {
        if (elements.item(i).value==="") {
            nullPoint = i;
            break;
        }
    }

    if (elements.item(id).value==="") elements.item(nullPoint).focus();
}