/**
 * Created by Administrator on 2017/11/12 0012.
 */
/*这是原版js 不用任何框架的*/

var ws;
function init() {
    ws=new WebSocket("ws://localhost/my-websocket");
}

ws.onopen=function () {
   // ws.send("we are ok");
    console.info("okkkok");
}

ws.onmessage=function (msg) {
    console.info(msg);

}

