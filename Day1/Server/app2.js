const express = require('express');
const http = require('http');

var app = express();

app.use(function(req, res, next) {
    console.log('client request');
    
    var person = {
        name : "aoa",
        age : 20,
        mobile : '111-1112'
    };
    var output = JSON.stringify(person);
    
    res.writeHead(200, {'Content-Type':'text/html;charset=utf8'});
    res.end(output);
});

http.createServer(app).listen(3000, function() {
    console.log('server start');
});