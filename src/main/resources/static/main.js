var app = new Vue({
  el: '#app',
  data: {
    message: 'Hello Vue!'
  }
})

let ws;
let isConnected = false;
connect();

function connect() {
	ws = new WebSocket('ws://localhost:8070/name');
	ws.onmessage = (e) => {
		showSomething(e.data);
	}
	ws.onopen = (e) => {
	    sendSomething();
	    isConnected = true;
	};

	ws.onclose = (e) => {
	    console.log("Closing websocket...");
	};

    console.log("Connecting...");
}

function disconnect() {
    if (ws != null) {
        ws.close();
    }
	isConnected = false;
    console.log("Disconnected");
}

function sendSomething() {
    ws.send(JSON.stringify({firstname: "Hello World!" }));
}

function showSomething(message) {
    document.querySelector("#messages").innerText += message + "\n";
}

function transformRequest(jsonData = {}){
  return Object.entries(jsonData)
    .map(x => `${encodeURIComponent(x[0])}=${encodeURIComponent(x[1])}`)
    .join('&');
}

function login(){
  fetch('/login', {
    method: "POST",
    body: transformRequest({username: "root", password: "password"}),
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
  })
  .then(function(response) {
    let successfulLogin = !response.url.includes("error");
    console.log("the login result is:", successfulLogin);
  });
}