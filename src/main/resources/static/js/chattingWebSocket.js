'use strict'

$(function () {
    var socket = new SockJS('/websocket');
    var stompClient = Stomp.over(socket);
    var roomName = location.search.substr(1).match('[^&]*' + 'roomName' + '=([^&]*)')[1];
    var userName = $('h2').text().match(/\w+$/)[0];
    var time = new Date(0);
    stompClient.connect('', '', function (frame) {
        console.log('Connected: ' + frame);
        //用户聊天订阅
        stompClient.subscribe('/userChat/' + roomName, function (data) {
            var message = JSON.parse(data.body);
            //分钟不一样则显示时间
            var newTime = new Date(message.date);
            var lastTimeStr = time.toTimeString().match(/\d{2}:\d{2}/)[0];
            var newTimeStr = newTime.toTimeString().match(/\d{2}:\d{2}/)[0];
            if (newTimeStr !== lastTimeStr) {             
                var displayTime = $("<div class='time'>" + newTime.toTimeString().match(/\d{2}:\d{2}/)[0] + "</div>");
                $('#content').append(displayTime);
            }
            time = newTime;

            var html;
            if (message.userName !== userName) {
                //别人的消息
                html = "<div class='received'>" +
                    "<div class='receivedUser'>" + message.userName + "</div>" +
                    "<div class='receivedMessage'>" + message.message + "</div>";
            } else {
                //自己的消息
                html = "<div class='sent'>" + message.message + "</div>";
            }
            var displayMessage = $(html);
            $('#content').append(displayMessage);
            $('#content').scrollTop($('#content').prop('scrollHeight')); //收到消息后滚动到底
        });
    });

    $('#submit').click(function () {
        if ($('#message').val() !== '') {
            var message = {
                userName: userName,
                date: new Date().getTime(),
                message: $('#message').val(),
                roomName: roomName
            };
            stompClient.send('/app/' + roomName, {}, JSON.stringify(message));
            $('#message').val('');
        }
    });
});