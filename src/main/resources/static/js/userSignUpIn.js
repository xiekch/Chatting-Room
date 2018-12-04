
$(function () {
    var validator = {
        form: {
            username: {
                status: false,
                error: 'User\'s name can be 5~15 alphanumeric or underscore characters',
                isValid: function (data) {
                    return this.status = /^\w{5,15}$/.test(data);
                }
            },
            password: {
                status: false,
                error: 'Password can be 5~15 alphanumeric or underscore characters',
                isValid: function (data) {
                    return this.status = /^\w{5,15}$/.test(data);
                }
            }
            // email: {
            //     status: false,
            //     error: 'Email is invalid',
            //     isValid: function (data) {
            //         return this.status = /^[\w\-]+@([\w\-]+\.)+[\w]{2,4}$/.test(data);
            //     }
            // }
        },

        isValid: function () {
            return this.form.username.status && this.form.password.status;
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