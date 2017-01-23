const fs = require('fs');

fs.readFile('./test1.js', 'utf8', function(err, data) {
    console.log(data);
});