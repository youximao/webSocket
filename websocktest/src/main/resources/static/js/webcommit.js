var vm=new Vue({
    el:"#app",
    data: {
        isconnect:"未连接",
        stompClient:null,
        user:[],
        chatinfo:[],
        usernum:'',
        nowchat:'',
        nowuser:'',
        chattext:''
    },
    created : function () {

        var self = this;
        self.getdata();
        self.connect()
    },
    methods : {
        changechatuser: function (name) {
            var self=this;
            self.nowchat=name;
        },
        getdata:function () {
            var self = this;
            jQuery.getJSON("./ws/getuser",function (data){
                self.user=data.user;
                self.usernum=data.num;
                self.nowuser=data.nowuser;
            })
            jQuery.getJSON("./ws/regist",function (data){
                self.user=data.user;
                self.usernum=data.num;
            })
        },
        setConnected : function (connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
    $('#response').html();

    },
        connect :function () {
    var socket = new SockJS('/my-websocket'); //1
             var self = this;
    self.stompClient = Stomp.over(socket);//2
            console.log("-----");
            self.stompClient.connect({}, function(frame) {//3
       // self.setConnected(true);

        console.log('开始进行连接Connected: ' + frame);
                self.stompClient.subscribe('/topic/send', function(respnose){ //4
                    console.log("||||||||");
                    self.showResponse(respnose);
        });
                self.stompClient.subscribe('/user/topic/say', function(respnose){ //4
                    console.log("||||||||");
                    self.showSay(respnose);
        });

    });
       self.isconnect="已经连接";
    },
        disconnect:function () {
           /* var self=this;
    if (self.stompClient != null) {
        self.stompClient.disconnect();
    }
    //self.setConnected(false);
    console.log("Disconnected");*/

    },
        sendName: function () {
    var name = $('#name').val();
    //var name="jijiasjdfa";
    // stompClient.send("/test", {}, JSON.stringify({ 'name': name }));//5
    //stompClient.send("/test", {}, name);
            var self=this;
            self.stompClient.send("/chat",{},self.nowchat+"/"+name);
            self.chattext=self.chattext+"||"+name;


            self.chatinfo.push({"name":self.nowuser,"info":name});
    },
        showResponse :function (message) {
    console.log(message);
    var response = $("#response");
    response.html(message.body);

    },

        showSay: function(mes) {
    var response = $("#response");
    response.html(mes);
    console.log(mes);
    var self=this;
    self.chattext=self.chattext+"---"+mes.body;
    var str=mes.body.split("/");
    self.chatinfo.push({"name":str[0],"info":str[1]});


    },
        sayAll : function () {
            jQuery.getJSON("./ws/sendAll",function (data){
                console.log(data);
            })
        }
    },

});
