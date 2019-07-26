module.exports = {
    userApi: function (url) {
        return 'http://8db5acdd.ngrok.io/user/' + url;
    },
    recordApi: function (url) {
        return 'http://8db5acdd.ngrok.io/record/' + url;
    },
    ruleApi: function (url) {
        return 'http://8db5acdd.ngrok.io/rule/' + url;
    },
    petApi: function (url) {
        return 'http://8db5acdd.ngrok.io/pet/' + url;
    }
};
