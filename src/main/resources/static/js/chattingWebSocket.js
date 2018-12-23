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
        stompClient.subscribe('/userChat/' + roomName, function (data) {
            var message = JSON.parse(data.body);
            //间隔大于2分钟则显示时间
            var newTime = new Date(message.time);
            if (newTime - time > 120000) {
                var timeMatch = /\d{2}:\d{2}/;
                var displayTime = $("<div class='time'>" + newTime.toTimeString().match(timeMatch)[0] + "</div>");
                $('#content').append(displayTime);
            }
            time = newTime;

            var html;
            if (message.user !== userName) {
                //别人的消息
                html = "<div class='received'>" +
                    "<div class='receivedUser'>" + message.user + "</div>" +
                    "<div class='receivedMessage'>" + message.text + "</div>";
            } else {
                //自己的消息
                html = "<div class='sent'>" + message.text + "</div>";
            }
            var displayMessage = $(html);
            $('#content').append(displayMessage);
            $('#content').scrollTop($('#content').prop('scrollHeight')); //收到消息后滚动到底
        });
    });

    $('#submit').click(function () {
        if ($('#message').val() !== '') {
            var newTime = new Date();
            var message = {
                user: userName,
                time: newTime.toJSON(),
                text: $('#message').val()
            }
            stompClient.send('/app/' + roomName, {}, JSON.stringify(message));
            $('#message').val('');
        }
    });
});