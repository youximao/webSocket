function say(str) {


    var words = new SpeechSynthesisUtterance(str);
    window.speechSynthesis.speak(words);

}
function show(i) {
    console.log(i);
}
function init(str) {

    console.log(str)
    var html1="";



    jQuery.getJSON("./getword",function (data) {
        console.log(data);
        html1+="<table>"
        var len=data.length;
        for(var i=0;i<len;i++){
           /* 'show("+i  +")'>"+*/
           var x="'"+data[i].word+"'";
            html1+="<tr onclick= say("+x+")><td>"+data[i].word+"</td><td>"+data[i].info+"</td></tr>"

        }

        html1+="</table>"
        $("#myword").html(html1);
    })


}