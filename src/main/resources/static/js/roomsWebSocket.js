'use strict'

$(function () {
    var socket = new SockJS('/websocket');
    var stompClient = Stomp.over(socket);
    stompClient.connect('', '', function (frame) {
        console.log('Connected: ' + frame);
        //即时增删房间
        stompClient.subscribe('/rooms/', function (data) {
            console.log(JSON.stringify(data.body));
            var operate = JSON.parse(data.body);
            if (operate.hasOwnProperty('create')) {
                var room = $('<div class="rest" ' + 'id="' + operate.create + '"><output>' + operate.create + '</output>' +
                    '<form action="change/room/join" method="post"><input type="hidden" name="roomName" value="' + operate.create + '" />' +
                    '<input class="joinButton" type="submit" value="join" /></div></form></div>');
                $('#rest').append(room);
            } else if (operate.hasOwnProperty('delete')) {
                $('#' + operate.delete).remove();
            }
        });
    });

});