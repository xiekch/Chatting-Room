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
                var room = $('<div id="' + operate.create + '" class="rest"><output>' + operate.create + '</output><input class="joinButton" type="submit" name="'
                    + operate.create + '" value="join"></div>');
                //$('<div id="'+operate.create+'" class="participated"><output>'+operate.create+'</output><div><a href="/room?roomName='+operate.create+'&amp;user=">'+'<button class="enterButton">enter</button></a>'+'<input class="quitButton" type="submit" name="'+operate.create+'" value="quit"></form></div></div>');
                $('#rest form').append(room);
            } else if (operate.hasOwnProperty('delete')) {
                $('#' + operate.delete).remove();
            }
        });
    });

});