module.exports = {
    userApi: function (url) {
        return 'http://202.120.40.8:30751/user/' + url;
    },
    recordApi: function (url) {
        return 'http://202.120.40.8:30751/record/' + url;
    },
    ruleApi: function (url) {
        return 'http://202.120.40.8:30751/rule/' + url;
    },
    petApi: function (url) {
        return 'http://202.120.40.8:30751/pet/' + url;
    }
};
