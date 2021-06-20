'use strict';

var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');


var stompClient = null;
var username = null;

var colors = [
	'#2196F3', '#32c787', '#00BCD4', '#ff5652',
	'#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function connect(event) {
	// username = document.querySelector('#name').value.trim();

	// if(username) {
	//     usernamePage.classList.add('hidden');
	//     chatPage.classList.remove('hidden');

	//     var socket = new SockJS('/ws');
	//     stompClient = Stomp.over(socket);

	//     stompClient.connect({}, onConnected, onError);
	// }
	console.log('All assets are loaded');
	var socket = new SockJS('/ws');
	stompClient = Stomp.over(socket);

	stompClient.connect({}, onConnected, onError);
	event.preventDefault();
}


function onConnected() {
	// Subscribe to the Public Topic
	stompClient.subscribe('/topic/public/' + roomId, onMessageReceived);

	// Tell your username to the server
	stompClient.send(`/app/chat.addUser/${roomId}`,
		{},
		JSON.stringify({ sender: userName, type: 'JOIN' })
	)

	// connectingElement.classList.add('hidden');
}


function onError(error) {
	connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
	connectingElement.style.color = 'red';
}


function sendMessage(event) {
	var messageContent = messageInput.value.trim();

	if (messageContent && stompClient) {
		var chatMessage = {
			sender: userName,
			content: messageInput.value,
			type: 'CHAT',
			roomId: roomId
		};

		stompClient.send(`/app/chat.sendMessage/${roomId}`, {}, JSON.stringify(chatMessage));
		messageInput.value = '';
	}
	event.preventDefault();
}


function onMessageReceived(payload) {
	var message = JSON.parse(payload.body);

	console.log(message);

	var messageElement = document.createElement('li');

	if (message.type === 'CHAT') {
		messageElement.classList.add('chat-message');

		var avatarElement = document.createElement('i');
		var avatarText = document.createTextNode(message.sender[0]);
		avatarElement.appendChild(avatarText);
		avatarElement.style['background-color'] = getAvatarColor(message.sender);

		messageElement.appendChild(avatarElement);

		var usernameElement = document.createElement('span');
		var usernameText = document.createTextNode(message.sender);
		usernameElement.appendChild(usernameText);
		messageElement.appendChild(usernameElement);

		var textElement = document.createElement('p');
		var messageText = document.createTextNode(message.content);
		textElement.appendChild(messageText);

		messageElement.appendChild(textElement);
		let msgElement = '';
		if (userName === message.sender) {
			msgElement = `
	        <div class="direct-chat-msg right">
	            <div class="direct-chat-infos clearfix">
	                <span class="direct-chat-name float-right">${message.sender}</span> <span class="direct-chat-timestamp float-left">19-06-2021 09:21</span>
	            </div>
	            <img class="direct-chat-img" src="/dist/img/user1-128x128.jpg" alt="message user image">
	            <div class="direct-chat-text">${message.content}</div>
	        </div>
	        
	        `;
		} else {
			msgElement = `
        		<div class="direct-chat-msg">
					<div class="direct-chat-infos clearfix">
						<span class="direct-chat-name float-left">${message.sender}</span> <span class="direct-chat-timestamp float-right">19-06-2021 08:58</span>
					</div>
					<img class="direct-chat-img" src="/dist/img/user1-128x128.jpg" alt="message user image">
					<div class="direct-chat-text">${message.content}</div>
				</div>
        	
        	`;
		}

		messageArea.insertAdjacentHTML('beforeend', msgElement);
		messageArea.scrollTop = messageArea.scrollHeight;



	}


}


function getAvatarColor(messageSender) {
	var hash = 0;
	for (var i = 0; i < messageSender.length; i++) {
		hash = 31 * hash + messageSender.charCodeAt(i);
	}

	var index = Math.abs(hash % colors.length);
	return colors[index];
}

window.addEventListener('load', connect, true);
messageForm.addEventListener('submit', sendMessage, true)