'use strict';

const PORT = 3001;

const app = require('./config/app');

app.listen(PORT, () => console.log(`Server runninig at http://localhost:${PORT}`));