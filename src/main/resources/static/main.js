import uploadFiles from './file-upload-service.js';

var app = new Vue({
  el: '#app',
  data: {
    title: "",
    images: []
  },
  methods: {
    filesChange(fileList) {
      // handle file changes
      const formData = new FormData();

      if (!fileList.length) return;

      // append the files to FormData
      Array.from(Array(fileList.length).keys())
        .map(x => {
          formData.append("files", fileList[x], fileList[x].name);
        });

      uploadFiles(formData).then(images => {
        this.images = images;
      }).catch(console.warn);
    },
    submitform() {
      fetch('http://localhost:8070/api/auctions/', {
        method: 'POST',
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          title: this.title,
          description: "Bla bla",
          startingBid: 200,
          images: this.images
        })
      }).then(response => {
        console.log(response);
      });
    }
  }
})

let ws;
connect();

function connect() {
  ws = new WebSocket('ws://localhost:8070/name');
  ws.onmessage = (e) => {
    showSomething(e.data);
  }
  ws.onopen = (e) => {
    sendSomething();
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
  console.log("Disconnected");
}

function sendSomething() {
  ws.send(JSON.stringify({ firstname: "Hello World!" }));
}

function showSomething(message) {
  document.querySelector("#messages").innerText += message + "\n";
}

function transformRequest(jsonData = {}) {
  return Object.entries(jsonData)
    .map(x => `${encodeURIComponent(x[0])}=${encodeURIComponent(x[1])}`)
    .join('&');
}

function login() {
  fetch('/login', {
    method: "POST",
    body: transformRequest({ username: "root", password: "password" }),
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
  })
    .then(function (response) {
      let successfulLogin = !response.url.includes("error");
      console.log("the login result is:", successfulLogin);
    });
}