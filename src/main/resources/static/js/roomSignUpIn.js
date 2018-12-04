$(function () {
    var validator = {
        form: {
            roomname: {
                status: false,
                error: 'Room\'s name can be 5~15 alphanumeric or underscore characters',
                isValid: function (data) {
                    return this.status = /^\w{5,15}$/.test(data);
                }
            }
        },

        isValid: function () {
            return this.form.username.status;
        },

        getError: function (filedName) {
            return this.form[filedName].error;
        }
    }

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
});