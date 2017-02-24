'use strict';

const express = require('express'),
    session = require('express-session'),
    cookieParser = require('cookie-parser'),
    bodyParser = require('body-parser');

const config = require('../app/data-config');
const data = require('../../data')(config);

const app = express();

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(cookieParser());
app.use(session({ secret: 'contact app' }));

require('../passport/')(app, data);
require('../../routing/users-router')(app);
require('../../routing/contacts-router')(app);

module.exports = app;