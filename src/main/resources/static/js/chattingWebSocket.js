$(function () {
    var socket = new SockJS('/websocket');
    var stompClient = Stomp.over(socket);
    var roomName=location.search.substr(1).match('[^&]*' + 'roomName'+ '=([^&]*)')[1];
    var userName = $('h2').text().match(/\w+$/)[0];
    stompClient.connect('', '', function (frame) {
        console.log('Connected: ' + frame);
        //用户聊天订阅
        //用userName作为id
        stompClient.subscribe('/userChat/'+roomName, function (data) {
            $("#content").text($('#content').text()+data.body);
        }, {id: userName});
    });

    $('#submit').click(function () {
        stompClient.send('/app/' + roomName, {},$('#message').val());
        $('#message').val('');
    })
});
