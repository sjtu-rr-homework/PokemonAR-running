module.exports = {
    _login: false,
    doLogin: function () {
        this._login = true;
    },
    exitLogin: function () {
        this._login = false;
    },
    isLogin: function () {
        return this._login;
    }
};
