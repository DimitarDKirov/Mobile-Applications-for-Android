'use strict';

const router = require('express').Router(),
    createAuthController = require('../controller/auth-controller'),
        data = require('../data'),
    passport = require('passport');

const authController = createAuthController(data);



module.exports = app => {
    router
        .post('/login', authController.loginLocal)
        .post('/register', authController.register);

    app.use(router);
};