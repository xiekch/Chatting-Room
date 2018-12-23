'use strict'

$(function () {
    var socket = new SockJS('/websocket');
    var stompClient = Stomp.over(socket);
    var roomName = location.search.substr(1).match('[^&]*' + 'roomName' + '=([^&]*)')[1];
    var userName = $('h2').text().match(/\w+$/)[0];
    var time = new Date().setTime(0);
    stompClient.connect('', '', function (frame) {
        console.log('Connected: ' + frame);
        //用户聊天订阅
        //用userName作为id

        stompClient.subscribe('/userChat/' + roomName, function (data) {
            //只接受非自己的消息，左侧显示
            var message = JSON.parse(data.body);
            if (message.userName !== userName) {
                //间隔大于2分钟则显示时间
                var newTime = message.date;
                if (newTime - time > 120000) {
                    var timeMatch = /\d{2}:\d{2}/;
                    var displayTime = $("<div class='time'>" + new Date(newTime).toTimeString().match(timeMatch) + "</div>");
                    $('#content').append(displayTime);
                }
                time = newTime;

                var html = "<div class='received'>" +
                    "<div class='receivedUser'>" + message.userName + "</div>" +
                    "<div class='receivedMessage'>" + message.message + "</div>";
                var displayMessage = $(html);
                $('#content').append(displayMessage);
                $('#content').scrollTop($('#content').prop('scrollHeight')); //收g消息后滚动到底
            }
            var displayMessage = $(html);
            $('#content').append(displayMessage);
            $('#content').scrollTop($('#content').prop('scrollHeight')); //收到消息后滚动到底
        });

    });

    $('#submit').click(function () {
        if ($('#message').val() !== '') {
            stompClient.send('/app/' + roomName, {}, JSON.stringify({
                userName: userName,
                date: new Date().getTime(),
                message: $('#message').val(),
                roomName: roomName
            }));
        }
    });
});