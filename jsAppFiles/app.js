// Full-Stack Final Project
const express = require('express'); 
const requestHandler = require('./requestHandler');
const app = express(); 
const port = 3000; 

const url = "http://alpha-meme-maker.herokuapp.com/1"; 

app.get('/', (req, res) => {
  requestHandler.make_API_call(url)
  .then(response => {
    res.json(response); 
  })
  .catch(error => {
    res.send(error)
  })
})

app.listen(port, () => console.log('App listening on port 3000')); 