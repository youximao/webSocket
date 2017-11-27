var stompClient = null;
function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
    $('#response').html();
}
function connect() {
    var socket = new SockJS('/my-websocket'); //1
    stompClient = Stomp.over(socket);//2
    stompClient.connect({}, function(frame) {//3
        setConnected(true);
        console.log('开始进行连接Connected: ' + frame);
        stompClient.subscribe('/topic/send', function(respnose){ //4
            showResponse(respnose);
        });
    });
}
function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}
function sendName() {
    var name = $('#name').val();
    //var name="jijiasjdfa";
    // stompClient.send("/test", {}, JSON.stringify({ 'name': name }));//5
    stompClient.send("/test", {}, name);
}
function showResponse(message) {
    console.log(message);
    var response = $("#response");
    response.html(message);
}