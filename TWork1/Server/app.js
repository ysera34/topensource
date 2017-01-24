const express = require('express');
const http = require('http');

var app = express();

app.use(function(req, res, next) {
    console.log('client request');
    
    var jsonObject = { 
        data : [
            {
                month : "1월",
                count : 100,
                sales : 80
            },
            {
                month : "2월",
                count : 80,
                sales : 120
            },
            {
                month : "3월",
                count : 90,
                sales : 50
            },
            {
                month : "4월",
                count : 40,
                sales : 20
            },
            {
                month : "5월",
                count : 70,
                sales : 30
            },
            {
                month : "6월",
                count : 100,
                sales : 80
            },
            {
                month : "7월",
                count : 100,
                sales : 80
            },
            {
                month : "8월",
                count : 80,
                sales : 120
            },
            {
                month : "9월",
                count : 90,
                sales : 50
            },
            {
                month : "10월",
                count : 40,
                sales : 20
            },
            {
                month : "11월",
                count : 70,
                sales : 30
            },
            {
                month : "12월",
                count : 100,
                sales : 80
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