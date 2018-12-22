$(function () {
    var socket = new SockJS('/websocket');
    var stompClient = Stomp.over(socket);
    var roomName=location.search.substr(1).match('[^&]*' + 'roomName'+ '=([^&]*)')[1];
    stompClient.connect('', '', function (frame) {
        console.log('Connected: ' + frame);
        //用户聊天订阅
        stompClient.subscribe('/userChat/'+roomName, function (data) {
            $("#content").text($('#content').text()+data.body);
        });
    });

    $('#submit').click(function () {
        stompClient.send('/app/' + roomName, {},$('#message').val());
    })
});
