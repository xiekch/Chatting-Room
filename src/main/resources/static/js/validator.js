var validator = {
    form: {
        username: {
            status: false,
            error: 'Username only contains 5~15 alphanumeric or underscore characters, and begins with a letter',
            isValid: function (data) {
                return this.status = /^\w{5,15}$/.test(data);
            }
        },
        password: {
            status: false,
            error: 'Password can be 8 digits and cannot begin with 0',
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

    // isFieldValid: function (filedName, data) {
    //     // console.log(filedName);
    //     // var func=filedName[0].toUpperCase()+filedName.substr(1,filedName.length-1);
    //     return this.form[filedName].isValid(data);
    // },

    getError: function (filedName) {
        return this.form[filedName].error;
    },

}
