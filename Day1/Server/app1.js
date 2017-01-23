const express = require('express');
const http = require('http');

var app = express();

app.use(function(req, res, next) {
    console.log('client request');
    
    res.writeHead(200, {'Content-Type':'text/html;charset=utf8'});
    res.end('<h1>response server</h1>');
});

http.createServer(app).listen(3000, function() {
    console.log('server start');
});