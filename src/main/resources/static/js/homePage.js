let searchedUserName = "";
let loggedInName = "";
const SYSTEM = "SYSTEM";
const USER = "USER";
var stompClient = null;
var name = null;
let socket = null;

window.onload = function() {
    console.log('onload get called');
    getLoggedInName();
    connect();
};

function connect() {
        console.log('inside connect');
        socket = new SockJS('http://localhost:8081/socket1');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            // Subscribe to a destination (topic or queue)
            stompClient.subscribe('/topic/receive', function (message) {
                console.log('Received message: ' + JSON.parse(message.body).content);
                console.log('Received message hr: ' + JSON.parse(message.body).hr);
                console.log('Received message min: ' + JSON.parse(message.body).min);
                showGreeting(JSON.parse(message.body).senderName, JSON.parse(message.body).content,JSON.parse(message.body).hr,JSON.parse(message.body).min);
            });
            setConnected(true);
        });
    }

function setConnected(connected) {
        //$("#connect").prop("disabled", connected);
        //$("#disconnect").prop("disabled", !connected);
        if (connected) {
           // $("#conversation").show();
        }
        else {
         //   $("#conversation").hide();
        }
       // $("#greetings").html("");
    }

function showGreeting(name, message,hr,min) {
        var AMPM = 'AM'
        if(hr>12){
        hr = hr - 12
        AMPM = 'PM';
       }
        //document.getElementById('audioSource').src = '/audio/notification.mp3';
        //document.getElementById('myAudio').play();
         var userChatBox = document.getElementById('userChatBox');
         var div = document.createElement("div");
         div.className="row";
         div.innerHTML = '<b>'+message+'</b>';
         userChatBox.appendChild(div);
       // $("#greetings").append("<tr><td><b>" + name + " : "+"</b>" + message + "</td>"+"<td>"+hr+":"+min+" "+AMPM+"</td></tr>");
    }

function sendMsg(sender){
        var senderName = loggedInName;
        var receiverName = document.getElementById('modalUserName').textContent;
        console.log("-------- reciever name is : ------------"+receiverName);
        console.log("-------- sender name is : ------------"+senderName);
        var content = document.getElementById('msgToSend').value;
        var currentDate = new Date();
        var hours = currentDate.getHours();
        var minutes = currentDate.getMinutes();
        var seconds = currentDate.getSeconds();
        var data =
        {
            "senderName":senderName,
            "recieverName":receiverName,
            "content":content,
            "hr":hours,
            "min":minutes
        };

        fetch('http://localhost:8081/saveMessage',{
        method : 'POST',
        mode : 'cors',
        body: JSON.stringify(data),
            headers:{
            "Content-Type": "application/json"}
          })
        .then(response => {
                if (response.ok) {
                    stompClient.send('/app/sendMessage', {}, JSON.stringify({ 'senderName': senderName, 'content': content,'hr':hours,'min':minutes }));
                    return response.json(); // Return the JSON-parsed response
                } else {
                    throw new Error("Message not saved");
                }
            })
        .then(data => {
                console.log(data);
            })
            .catch(error => {
                console.error("Error:", error);
                alert("Error occurred: " + error.message);
            });
    }

function getUser(){
    var userNameToBeSearched = document.getElementById("userName").value;
    var url = 'http://localhost:8081/getUserByUserName/'+userNameToBeSearched;
    console.log(url);
    fetch(url,{
        method: "GET",
        mode: "cors"
    })
    .then(response => {
        if (response.ok) {
            return response.json(); // Return the JSON-parsed response
        } else {
            throw new Error("No such user with userName : "+userNameToBeSearched);
        }
    })
    .then(data => {
        console.log(data.userName);
        searchedUserName = data.userName;
        showSearchUserResult();
         // Log the data received
        // Process your data here, like updating UI, etc.
        // window.location.href = "/URL of WebsocketApp"; // Place the URL of WebsocketAPP
    })
    .catch(error => {
        console.error("Error:", error);
        alert("Error occurred: " + error.message);
    });
}

//this function will add searched users to page
function showSearchUserResult(){
    var usersTable = document.getElementById('usersTable');
    var tr = document.createElement('tr');
    usersTable.appendChild(tr);

    var td1 = document.createElement('td');
    td1.textContent="";
    tr.appendChild(td1);

    var td2 = document.createElement('td');
    td2.textContent=searchedUserName;
    tr.appendChild(td2);

    var td3 = document.createElement('td');
    td3.className='fas fa-comments';
    td3.setAttribute("onClick","showModal()");
    tr.appendChild(td3);
}

 function showModal(){
    var modal = new bootstrap.Modal(document.getElementById('exampleModal'));
    modal.show();
    console.log("model shown");
    console.log(searchedUserName);

    var modalUserName = document.getElementById('modalUserName');
    modalUserName.textContent = searchedUserName;

    fetchMessagesInModel();
    }

function getLoggedInName(){
    var url = 'http://localhost:8081/getLoggedInUserName';
    console.log(url);
    fetch(url,{
        method: "GET",
        mode: "cors"
    })
    .then(response => {
        if (response.ok) {
            console.log(response);
            return response.json(); // Return the JSON-parsed response
        } else {
            throw new Error("No such user with userName : "+userNameToBeSearched);
        }
    })
    .then(data => {
        loggedInName = data.name;
        console.log(data.name);
        loadLoggedInName(data.name);
        console.log(loggedInName);
    })
    .catch(error => {
        console.error("Error:", error);
        alert("Error occurred: " + error.message);
    });
}

function loadLoggedInName(name){
    console.log(loggedInName);
    document.getElementById('welcomeTag').textContent = "welcome  "+name;
}

function fetchMessagesInModel()
{
    console.log('load messages in model is called ');
    data =
    {
        "senderName" : loggedInName,
        "recieverName" : document.getElementById('modalUserName').textContent
    };

    fetch('http://localhost:8081/getMessages',{
        method: "POST",
        mode: "cors",
        body: JSON.stringify(data),
        headers : {"Content-Type": "application/json"}
    })
    .then(response=>response.json())
    .then(data=>{
            loadMessagesInModel(data);
            })
}

function loadMessagesInModel(msgList){
    var userChatBox = document.getElementById('userChatBox');
    let i = 0;
//    while (i < msgList.length) {
//         console.log(msgList[i].content);
//         var div = document.createElement("div");
//         div.className="row right-aligned";
//         div.textContent = msgList[i].content;
//         userChatBox.appendChild(div);
//         i++;
//    }

while (i < msgList.length) {
        console.log(msgList[i]);
        var div = document.createElement("div");
        if(msgList[i].senderName==loggedInName){
            div.className="row justify-content-end"; // Use Bootstrap's justify-content-end class
        }
        else{
            div.className="row justify-content-start"; // Use Bootstrap's justify-content-start class
        }
        var innerDiv = document.createElement("div");
        innerDiv.className = "col-auto"; // Use Bootstrap's col-auto class to make the width auto-adjust
        innerDiv.textContent = msgList[i].content;
        div.appendChild(innerDiv);
        userChatBox.appendChild(div);
        i++;
    }
}