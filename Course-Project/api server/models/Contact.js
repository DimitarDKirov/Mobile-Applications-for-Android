'use strict';

const mongoose = require('mongoose');

const pasteSchema = new mongoose.Schema({
    name:{
        type: String,
        minlength: 3,
        required: true
    },
    phone:{
        type: String,
        required: true
    },
    notes:{
        type: String
    }
});

mongoose.model('Contact', pasteSchema);

module.exports = mongoose.model('Contact');