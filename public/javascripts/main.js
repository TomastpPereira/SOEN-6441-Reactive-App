const socket = new WebSocket("wss://localhost:9000");

socket.addEventListener("open", (event) => {
    document.getElementById("status").innerHTML = "Connected";
})

socket.addEventListener("close", (event) => {
    document.getElementById("status").innerHTML = "Disconnected";
})