/**
 * Created by Niek on 05/04/14.
 */
var wsUri = "ws://localhost:8080/kwetter/websocket";
var websocket;

function init() {
    websocket = new WebSocket(wsUri);
    websocket.onmessage = function(evt) {
        onMessage(evt);
    };

}
function onMessage(evt) {
    updateTweets();
}


$(document).ready(function(){
    init();
});

function postTweet(){
    var content = $("#txtPostNewTweet").val();
    $("#txtPostNewTweet").val("");
    var $divError = $("#PostFail");

    if(content != null && content != "" && content.length < 140){
        websocket.send(content);
        $divError.hide();
        updateTweets();
    } else {
        $divError.show();
    }
}