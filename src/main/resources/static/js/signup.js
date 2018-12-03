
$(function () {
    //check valid when blurs
    $('.field').blur(function () {
        if (validator.form[$(this).attr('id')].isValid($(this).val())) {
            $(this).parent().find('.error').text('');
        } else {
            $(this).parent().find('.error').text(validator.getError(this.name));
        }
    });

    $('#submit').click(function () {
        $('.field').blur();
        if (!validator.isValid()) return false;
    });


    // //reset
    // $('#reset').click(function (event) {
    //     console.log('reset');
    //     $('#username').val('');
    //     $('#id').val('');
    //     $('#phone').val('');
    //     $('#email').val('');
    //     $('#autoFill').val('no');
    //     $('#autoFill').change();
    //     $('.error').text('');
    // });
});