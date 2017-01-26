const express = require('express');
const http = require('http');

const static = require('serve-static');
const path = require('path');

var app = express();

app.use('/public', static(path.join(__dirname, 'public')));

app.use(function(req, res, next) {
    console.log('client request');
    
    var jsonObject = { 
        data : [
            {
                month : "1월",
                count : 100,
                sales : 80,
                url : "http://192.168.43.186:3000/public/1.png"
            },
            {
                month : "2월",
                count : 80,
                sales : 120,
                url : "http://192.168.43.186:3000/public/2.png"
            },
            {
                month : "3월",
                count : 90,
                sales : 50,
                url : "http://192.168.43.186:3000/public/3.png"
            },
            {
                month : "4월",
                count : 40,
                sales : 20,
                url : "http://192.168.43.186:3000/public/1.png"
            },
            {
                month : "5월",
                count : 70,
                sales : 30,
                url : "http://192.168.43.186:3000/public/2.png"
            },
            {
                month : "6월",
                count : 100,
                sales : 80,
                url : "http://192.168.43.186:3000/public/3.png"
            },
            {
                month : "7월",
                count : 100,
                sales : 80,
                url : "http://192.168.43.186:3000/public/1.png"
            },
            {
                month : "8월",
                count : 80,
                sales : 120,
                url : "http://192.168.43.186:3000/public/2.png"
            },
            {
                month : "9월",
                count : 90,
                sales : 50,
                url : "http://192.168.43.186:3000/public/3.png"
            },
            {
                month : "10월",
                count : 40,
                sales : 20,
                url : "http://192.168.43.186:3000/public/1.png"
            },
            {
                month : "11월",
                count : 70,
                sales : 30,
                url : "http://192.168.43.186:3000/public/2.png"
            },
            {
                month : "12월",
                count : 100,
                sales : 80,
                url : "http://192.168.43.186:3000/public/3.png"
            }
        ]
    };
                      
    var output = JSON.stringify(jsonObject);
    
    res.writeHead(200, {'Content-Type':'text/html;charset=utf8'});
    res.end(output);
});

http.createServer(app).listen(3000, function() {
    console.log('server start');
});