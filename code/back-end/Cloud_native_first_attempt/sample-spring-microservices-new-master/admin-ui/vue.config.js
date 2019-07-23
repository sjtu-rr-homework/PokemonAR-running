module.exports = {
    devServer: {
        open: false, //浏览器自动打开页面
        host: "0.0.0.0", //如果是真机测试，就使用这个IP
        port: 8001,
        https: false,
        hotOnly: false, //热更新（webpack已实现了，这里false即可）
        proxy: {
            //配置跨域
            '/ruleadmin_api': {
                target: 'http://202.120.40.8:30751/rule',
                ws: true,
                changOrigin: true,
                pathRewrite: {
                    '^/ruleadmin_api': '/'
                }
            },
            '/user_api': {
                target: 'http://202.120.40.8:30751/user',
                ws: true,
                changOrigin: true,
                pathRewrite: {
                    '^/user_api': '/'
                }
            },
            '/record_api': {
                target: 'http://202.120.40.8:30751/record',
                ws: true,
                changOrigin: true,
                pathRewrite: {
                    '^/record_api': '/'
                }
            },
            '/pet_api': {
                target: 'http://202.120.40.8:30751/pet',
                ws: true,
                changOrigin: true,
                pathRewrite: {
                    '^/pet_api': '/'
                }
            }
        }
    }
};
