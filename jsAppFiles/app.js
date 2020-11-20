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
 /* Http.onreadystatechange = function () {
    var status = Http.status;
    if (status == 200) {
      callback(null, Http.response);
    } else {
      callback(status);
    }
  };
  Http.send();
};

getdata(url, function (err, data) {
  if (err != null) {
    console.log("Error"); 
  } else {
    for (i = 0; i < data.length; i++) {
        var title = data[i].name;
        console.log(title); 
        var text = (i+1) + ". " + id + ": " + title; 
        var res = document.getElementById("results"); 
        const li = document.createElement("ol"); 
        li.innerHTML = text; 
        res.append(li);        
    }
  }

  console.log(data.message)
})

*/
