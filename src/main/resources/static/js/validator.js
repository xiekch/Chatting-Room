var validator = {
    form: {
        username: {
            status: false,
            error: 'Username only contains 6~18 alphanumeric or underscore characters, and begins with a letter',
            isValid: function (data) {
                return this.status = /^[a-zA-Z]\w{5,17}$/.test(data);
            }
        },
        id: {
            status: false,
            error: 'Id can be 8 digits and cannot begin with 0',
            isValid: function (data) {
                return this.status = /^[1-9]\d{7}$/.test(data);
            }
        },
        phone: {
            status: false,
            error: 'Phone can be 11 digits and cannot begin with 0',
            isValid: function (data) {
                return this.status = /^[1-9]\d{10}$/.test(data);
            }
        },
        email: {
            status: false,
            error: 'Email is invalid',
            isValid: function (data) {
                return this.status = /^[\w\-]+@([\w\-]+\.)+[\w]{2,4}$/.test(data);
            }
        }
    },

    isValid: function () {
        return this.form.username.status && this.form.id.status && this.form.phone.status && this.form.email.status;
    },

    isFieldValid: function (filedName, data) {
        // console.log(filedName);
        // var func=filedName[0].toUpperCase()+filedName.substr(1,filedName.length-1);
        return this.form[filedName].isValid(data);
    },

    getError: function (filedName) {
        return this.form[filedName].error;
    },


    isFieldUnique: function (data, user, attr) {
        // console.log('data:\n' + JSON.stringify(data));
        // console.log('user:\n' + JSON.stringify(user));
        for (var key in data) {
            if (data.hasOwnProperty(key) && data[key][attr] == user[attr]) return false;
        }

        return true;
    }
}

if (typeof module == 'object') {
    module.exports = validator;
}