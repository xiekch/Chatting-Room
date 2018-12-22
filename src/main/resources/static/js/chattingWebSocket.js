$(function () {
    var socket = new SockJS('/websocket');
    var stompClient = Stomp.over(socket);
    var roomName = location.search.substr(1).match('[^&]*' + 'roomName' + '=([^&]*)')[1];
    var userName = $('h2').text().match(/\w+$/)[0];
    stompClient.connect('', '', function (frame) {
        console.log('Connected: ' + frame);
        //用户聊天订阅
        //用userName作为id
        stompClient.subscribe('/userChat/' + roomName, function (data) {
            //只接受非自己的消息，左侧显示
            if (data.headers.subscription !== userName) {
                var displayMessage = $("<div class='message'>" + $('#message').val() + "</div>");
                $('#content').append(displayMessage);
                $('#content').scrollTop($('#content').prop('scrollHeight')); //收g消息后滚动到底
            }
        }, {
            id: userName
        });
    });

    $('#submit').click(function () {
        if ($('#message').val() !== '') {
            stompClient.send('/app/' + roomName, {sourceUser: userName}, $('#message').val());
            //发出消息右侧显示
            var displayMessage = $("<div class='message'>" + $('#message').val() + "</div>").css('align-self', 'flex-end');
            $('#content').append(displayMessage);
            $('#content').scrollTop($('#content').prop('scrollHeight')); //发消息后滚动到底
            $('#message').val('');
        }
    })
});