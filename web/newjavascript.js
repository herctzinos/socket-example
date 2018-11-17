/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



var socket=  new WebSocket("ws://localhost:8080/Socket/hello");
socket.onmessage = onMessage; 

function onMessage(event) {
    var newMessage = event.data;
    var chats = document.getElementById("content");
    var newHtml = chats.innerHTML+"</br>"+newMessage;
    document.getElementById("content").innerHTML=newHtml;
}

function say(name, text){
    var msg = {
    type: "message",
    text: text,
    name: name,
    date: new Date(Date.now()).toLocaleString() //https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Date/toLocaleString
  };

//Convert a JavaScript object into a string with JSON.stringify().
    socket.send(JSON.stringify(msg));
}

function formSubmit(){
   var name= document.getElementById("name").value;
    var text = document.getElementById("message").value;
    
    say(name, text);
   
}



//function register(){
//      var name = document.getElementById("name").value; 
//}
