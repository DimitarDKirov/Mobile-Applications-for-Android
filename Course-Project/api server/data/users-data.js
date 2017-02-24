const hashing = require('../utils/hashing');

module.exports = function (models) {
    let User = models.User;
    return {
        findUserById(id) {
            return User.findById(id);
        },
        findUserByUsername(username) {
            return User.findOne({ username });
        },
        createUser(user) {

            // hash the password so it isn't stored in plain text
            const salt = hashing.generateSalt(),
                passHash = hashing.hashPassword(salt, user.password);

            const newUser = {
                username: user.username,
                phone: user.phone,
                salt,
                passHash
            };

            return User.create(newUser);
        }
    }
}