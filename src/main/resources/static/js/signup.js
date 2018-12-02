//js for signup.hbs
$(function () {
    //check valid when blurs
    $('.field').blur(function () {
        if (validator.isFieldValid(this.name, $(this).val())) {
            $(this).parent().find('.error').text('');
        } else {
            $(this).parent().find('.error').text(validator.getError(this.name));
        }
    });

    $('#submit').click(function () {
        $('.field').blur();
        if (!validator.isValid()) return false;
    });

    //auto fill the form for test
    $('#autoFill').change(function () {
        switch ($(this).val()) {
            case 'no'://not fill
                $('#username').val('');
                $('#id').val('');
                $('#phone').val('');
                $('#email').val('');
                break;
            case 'test1'://xiaoming
                $('#username').val('xiaoming');
                $('#id').val('12345678');
                $('#phone').val('12345678901');
                $('#email').val('xiaoming@qq.com');
                break;
            case 'test2'://xiaohong
                $('#username').val('xiaohong');
                $('#id').val('82345678');
                $('#phone').val('92345678901');
                $('#email').val('xiaohong@qq.com');
                break;
            case 'test3'://name wrong
                $('#username').val('_xiaofang');
                $('#id').val('17000001');
                $('#phone').val('12345678902');
                $('#email').val('xiaofang@qq.com');
                break;
            case 'test4'://id wrong
                $('#username').val('xiaoliu');
                $('#id').val('1700000');
                $('#phone').val('12345678903');
                $('#email').val('xiaoliu@qq.com');
                break;
            case 'test5'://phone wrong
                $('#username').val('xiaowang');
                $('#id').val('17000005');
                $('#phone').val('1234567890');
                $('#email').val('xiaowang@qq.com');
                break;
            case 'test6'://email wrong
                $('#username').val('xiaolin');
                $('#id').val('17000006');
                $('#phone').val('12345678905');
                $('#email').val('xiaolin@qq');
                break;
        }

        if ($(this).val() != 'no') $('.field').blur();
    });

    //reset
    $('#reset').click(function (event) {
        console.log('reset');
        $('#username').val('');
        $('#id').val('');
        $('#phone').val('');
        $('#email').val('');
        $('#autoFill').val('no');
        $('#autoFill').change();
        $('.error').text('');
        // event.stopPropagation();
    });
});