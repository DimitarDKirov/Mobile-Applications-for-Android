module.exports = function (models) {
    let User = models.User;
    return {
        findById(id) {
            return User.findById(id);
        },
        findByUsername(username) {
            return User.findOne({ username });
        },
        createUser(user) {

            // hash the password so it isn't stored in plain text
            const salt = hashing.generateSalt(),
                passHash = hashing.hashPassword(salt, user.password);

            const newUser = {
                username: user.username,
                roles: user.roles,
                salt,
                passHash
            };

            return User.create(newUser);
        }
    }
}