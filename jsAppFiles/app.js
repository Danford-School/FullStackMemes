//Full Stack Final Project
//Authors: Stephanie Beagle and Danford Compton 

const express = require('express'); 
const requestHandler = require('./requestHandler');
const request = require('request');
const fs = require('fs'); 
//const port = 3000; 
const app = express();
const url = "http://alpha-meme-maker.herokuapp.com/1"; 
const javaCaller = require('java-caller'); // node java caller 
const java = new javaCaller({
  classPath:'',//path
  mainClass:'',//has a specific formatting regarding the src package see documentation for java-caller
  rootPath: '', //might not even be necessary 
  minimumJavaVersion: 10 // or whatever idk this also isn't necessary
});
//const path = require('path');

// I copied the next line from the npm website  
//const {status, stdout, stderr} = java.run(JAVA_ARGUMENTS,JAVA_CALLER_RUN_OPTIONS);

function invokeJavaGet() {

  // to be continued here 
}

app.get('/', (req, res) => {
  requestHandler.make_API_call(url) //see requestHandler.js 
  .then(response => {
  // res.json(response); // this prints the raw json data to the browser if you need that for testing 
   var length = response.data.length; //24
   writeHtml (length, response.data); 
})

app.listen(port, () => console.log('App listening on port 3000'));

// takes the array of data from the JSON body & the length of the array as arguments
// and writes homepage.html with the corresponding data  
function writeHTML (length, data) {
  var html = '<!DOCTYPE html><html><head><meta charset="utf-8"/></head><body><table class = "grid" id = "table"><tr>'; //head of html file
  for(var i = 0; i < length; i++) {
    if(i%5 === 0 && i!=0){ // Makes a row of 5, replace 5 with anything you want 
      html += '</tr><tr>'; 
    }
    html += '<td class = "meme_box"><img src="' + data[i].image + '"></td>';
  }
  //console.log(html); // just for testing if you wan to 
  html += '</tr>'; 
  html += '</table>'; 
  html += '</body></html>';
  //write html to the homepage.html file, it will overwrite the previous html on each run
  fs.writeFile(path.join(__dirname + '/homepage.html'), html, err => {
   if(err) {
     console.error(err);
     return; 
   }
}
//when fed a search term and the array of names with urls, this should send back an array of names and urls that match
var searchMemes = function (theData, searchParameter) { 
  var results;   
  for(var i = 0; i < theData.length; i++) { 
    if(theData[i]['name'].includes(searchParameter) ) { 
        results.push(theData[i]); 
    }
  }
  return results; 
}

//when fed the path of a specific image (with path) this will download said image to your computer. 
const download = (url, path, callback) => { 
  request.head(url, (err, res, body) => {
    request(url) 
      .pipe(fs.createWriteStream(path))
      .on('close', callback)
  })
}

