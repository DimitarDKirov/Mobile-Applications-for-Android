'use strict';

module.exports = function(app, data){
    const router = require('express').Router(),
    authController = require('../controller/auth-controller')(data);

    router
        .post('/login', authController.loginLocal)
        .post('/register', authController.register);

    app.use('/user', router);
};