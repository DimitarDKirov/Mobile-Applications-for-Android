'use strict';

const User = require('../models/User'),
    Contact = require('../models/Contact'),
    mongoose = require('mongoose'),
    fs = require("fs"),
    path = require("path"),
    config = require('../config/app/data-config');

module.exports = function (config) {
    const connectionString = config.connectionString;
    mongoose.Promise = global.Promise;
    mongoose.connect(config.connectionString);

    let models = { User, Contact };
    let data = {};

    fs.readdirSync("./data")
        .filter(x => x.includes("-data"))
        .forEach(file => {
            let dataModule =
                require(path.join(__dirname, file))(models);

            Object.keys(dataModule)
                .forEach(key => {
                    data[key] = dataModule[key];
                });
        });

    return data;
};