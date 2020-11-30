//Full Stack Final Project
//Authors: Stephanie Beagle and Danford Compton 

const express = require('express'); 
const requestHandler = require('./requestHandler');
const request = require('request');
const fs = require('fs'); 
const path = require('path'); 
const router = express.Router();
const port = 3000; 
const app = express();
const url = "https://api.imgflip.com/get_memes";

// Code for using Pug. It will set off some errors it's just copy/pasted. 

app.set("view engine", "pug");
app.set("views", path.join(__dirname, "views")); 

app.get('/', (req, res) => {
  requestHandler.make_API_call(url) //see requestHandler.js 
  .then(response => {
  // res.json(response); // this prints the raw json data to the browser if you need that for testing 
   var length = response.data.memes.length; //24
   writeHomepage(length, response.data.memes);
  // This is for using pug 
   res.render('index'); 
   })
})

// Edit page redirect 
app.get('/edit/*/*', (req,res) => {
  // todo: url parsing 
  var imgurl; // need to retrieve imgurl from parsing code 
  writeEditPage(imgurl); 
  res.render('index1'); 
})
//app.use("/", router); // more Pug stuff 
app.listen(port, () => console.log('App listening on port 3000'));

// takes the array of data from the JSON body & the length of the array as arguments
// and writes homepage.html with the corresponding data  
function writeHomepage(length, data) {

 // var html = '<!DOCTYPE html><html><head><link rel="stylesheet" href="homepage.css"><script>src="index.js"</script><meta charset="utf-8"/></head><body><table class = "grid" id = "table"><h1 class = "title">Meme Maker</h1><tr>'; //head of html file
  var pugtxt = "doctype html\nhtml\n\thead\n\t\tstyle\n\t\t\tinclude homepage.css\n\t\tscript.\n\t\t\tsrc=\"index.js\"\n\tbody\n\t\tdiv.header\n\t\t\th1.title Meme Maker\n\t\t\th2.text To get started, select an image from the gallery below!\n\t\ttable#table.grid\n\t\t\ttbody\n\t\t\t\ttr";
  for(var i = 0; i < length; i++) {
    if(i%4 === 0 && i!=0){ // Makes a row of 4, replace 4 with anything you want 
      //html += '</tr><tr>'; 
      pugtxt += "\n\t\t\t\ttr"
    }
    //html += '<td class = "meme_box"><img src="' + data[i].url + '"></td>';
    pugtxt += "\n\t\t\t\t\ttd.meme_box"; 
    pugtxt += "\n\t\t\t\t\t\timg(src='" + data[i].url + "')";
  }
  //html += '</tr>';
  //html += '</table>'; 
  //html += '</body></html>';
  //console.log(html); // just for testing if you want to 
  //write pugtxt to the index.pug file, it will overwrite the previous html on each run
  fs.writeFile(path.join(__dirname + 'views/index.pug'), pugtxt, err => {
   if(err) {
     console.error(err);
     return; 
   }
  })
} 

// Write pug file for edit page 
function writeEditPage(imgurl) {
var pugtxt = "doctype html\nhtml\n\thead\n\t\tstyle\n\t\t\tinclude homepage.css\n\t\tscript.\n\t\t\tsrc=\"index.js\"\n\tbody\n\t\th1.title Meme Maker";
//pugtxt += "\n\t\tdiv.container\n\t\t\img(src=\"" + imgurl + "\")";
fs.writeFile(path.join(__dirname + '/views/index1.pug'), pugtxt, err => {
   if(err) {
     console.error(err);
     return; 
   }
  })
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

