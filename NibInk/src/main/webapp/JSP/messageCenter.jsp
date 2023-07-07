<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.model.MessageManager" %>
<%@ page import="com.model.Message" %>
<%@ page import="com.model.DAOCustomer" %>
<%@ page import="com.model.Customer" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <script src="https://kit.fontawesome.com/391827d54c.js" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="/NibInk/CSS/messageCenter.css">
  <title>Centro Messaggi</title>
  
  <script>
  var name;
  var email;
  function openChat(conversationId, element) {
	closeMessageForm();
	var chatBoxes = document.querySelectorAll('.chat-box');
	var clickedChatBox = document.getElementById("cb-" + element);
	var input = document.querySelector('.chatbox-input input[type="text"]');
	var button = document.getElementById("check");
	if(clickedChatBox.classList.contains("closed")) {
		input.placeholder = "Conversazione terminata";
		input.readOnly = true;
		button.style.display = "none";
	}
	if(!clickedChatBox.classList.contains("closed")) {
		input.placeholder = "Scrivi un messaggio";
		input.readOnly = false;
		button.style.display = "flex";
	}
	chatBoxes.forEach(function(chatBox) {
		if(chatBox === clickedChatBox ) {
			chatBox.classList.add('active');
			textHead = chatBox.querySelector('.text-head');
			name = textHead.querySelector('h4').textContent.trim();
			//email = textHead.querySelector('p').textContent.trim();
		} else {
			chatBox.classList.remove('active');
		}
	});

    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
      if (xhr.readyState === XMLHttpRequest.DONE) {
        if (xhr.status === 200) {
          // Request successful, update the right container with the messages
          var messages = JSON.parse(xhr.responseText);
          updateRightContainer(messages);
        } else {
          // Handle error case
          console.error('Failed to retrieve messages');
        }
      }
    };
    xhr.open('GET', '/NibInk/retrieveMessage?id=' + conversationId);
    xhr.send();
  }

  function updateRightContainer(messages) {
    // Update the right container with the retrieved messages
    var chatContainer = document.querySelector('.chat-container');
    chatContainer.innerHTML = '';

    var header = document.querySelector('.right-container .header');
    var imgText = header.querySelector('.img-text');
    var nameElement = imgText.querySelector('h4');
    var emailElement = imgText.querySelector('span');
    var textField = document.getElementById('conversationId');
    textField.value = messages[0].id;
    nameElement.textContent = name;
    //emailElement.textContent = email;
    messages.forEach(function(message) {
      var messageBox = createMessageBox(message);
      chatContainer.appendChild(messageBox);
    });
  }

  function createMessageBox(message) {
    // Create and return a message box element
    // Use the message data to construct the HTML structure
    // Example:
    var messageBox = document.createElement('div');
    messageBox.className = 'message-box';

    var content = document.createElement('p');
    content.textContent = message.text;


    var timestamp = document.createElement('span');
    timestamp.textContent = message.sent.substring(11,16);

    messageBox.appendChild(content);
    messageBox.appendChild(timestamp);

    if(message.userId == '0') {
    	messageBox.classList.add('my-message');
    }
    else messageBox.classList.add('friend-message');
    return messageBox;
  }

	function openMessageForm() {
	  var messageFormContainer = document.getElementById('messageFormContainer');
	  messageFormContainer.style.display = 'flex';
	}

	function closeMessageForm() {
	  var messageFormContainer = document.getElementById('messageFormContainer');
	  messageFormContainer.style.display = 'none';
	}
	
	function markAsSolved() {
	  var id = conversationIdInput.value;
	  
	  var xhr = new XMLHttpRequest();
	    xhr.onreadystatechange = function() {
	      if (xhr.readyState === XMLHttpRequest.DONE) {
	        if (xhr.status === 200) {
	        	location.reload();
	        } else {
	          // Handle error case
	          console.error('Failed to mark messages');
	        }
	      }
	    };
	    xhr.open('GET', '/NibInk/closeConversation?id=' + id);
	    xhr.send();
	}
</script>
</head>
<body>
  <div class="background-green"></div>


  <div class="main-container">
    <div class="left-container">

<!--header -->
      <div class="header">
        <div class="user-img">
          <img class="dp" src="https://www.codewithfaraz.com/InstaPic.png" alt="">
        </div>
        <div class="nav-icons">
          <li><i class="fa-solid fa-message" onclick="openMessageForm()">
 </i></li>
        </div>
      </div>

<!--search-container -->
      <div class="search-container">
        <div class="input">
<i class="fa-solid fa-magnifying-glass"></i>
          <input type="text" placeholder="Inizia una nuova chat   "></div>
     <i class="fa-sharp fa-solid fa-bars-filter"></i>
      </div>


<!--chats (conversazioni sulla sinistra)-->
      <div class="chat-list">
      	<%MessageManager mm = new MessageManager(); 
      	int i=0;
      	for(Message message : mm.getAllConversationsHeaders()) { 
      		String add = "";
       		if(message.getStatus().equals("closed")) { 
       			add = "closed";%>
		 <% } %>
        <div class="chat-box" id="cb-<%=i%>"  onclick="openChat('<%=message.getId() %>', '<%=i%>')">
          <div class="img-box">
            <img class="img-cover" src="/NibInk/images/userIcon.png" alt="">
          </div>
          <div class="chat-details">
            <div class="text-head">
            <%  int id = message.getUserId();
	        	String userName = "Non Registrato";
	        	String email = message.getUserEmail();
	        	if(id == 0)
	        		userName = "Admin";
            	if(id > 0){
            		DAOCustomer db = new DAOCustomer();
            		Customer cust = db.getCustomerById(id);
            		userName = cust.getName() + " " + cust.getSurname();
            		email = cust.getEmail();
            	} %>
              <h4><%=userName %></h4>
              <p style="hidden"><%=email %></p>
            </div>
            <div class="text-message">
              <p><%=message.getSubject() %></p>
              <b><%=mm.getUnreadMessagesCount(message.getId(), "admin") %></b>
            </div>
          </div>
        </div>
        <script>
        	if("<%=add%>" =="closed") {
   				var toClose = document.getElementById("cb-<%=i%>");
   				toClose.classList.add("closed");
        	}
   		</script>
        <% i++;} %>
      </div>

    </div>

    <div class="right-container">
<!--header (la persona con cui chatti) -->
      <div class="header">
        <div class="img-text">
          <div class="user-img">
          <img class="dp" src="/NibInk/images/userIcon.png" alt="">
        </div>
          <h4>Invia Messaggio<br><span>email</span></h4>
        </div>
        <div class="nav-icons">
          <li><i></i></li>
          <li id="check">Segna come risolto<i onclick="markAsSolved()"class="fa-solid fa-circle-check"></i></li>
        </div>
      </div>

<!--chat-container la conversazione aperta -->
      <div class="chat-container">
      </div>
      
      <div id="messageFormContainer">
  		<h3>Invia un nuovo messaggio a:</h3>
  		<form id="messageForm" method="post" action="/NibInk/sendMessage">
	
	    	<label for="email">Email:</label>
	    	<input type="email" id="email" name="email" required><br>
	
	    	<label for="subject">Oggetto:</label>
	    	<input type="text" id="subject" name="subject" required><br>
	
	    	<label for="message">Messaggio:</label><br>
	    	<textarea id="message" name="text" rows="4" cols="50" required></textarea><br>
	
	    	<button type="submit">Invia</button>
	    	<button type="button" onclick="closeMessageForm()">Chiudi</button>
  		</form>
	</div>
  		<div class="chatbox-input">
        <input type="text" placeholder="Scrivi un messaggio">
		<input type="hidden" id="conversationId" value="">
      </div>
	</div>	
      </div>

<!--input-bottom -->
      
      <script>
      const messageInput = document.querySelector('.chatbox-input input');
	  const conversationIdInput = document.getElementById('conversationId');
      
	  function sendMessage() {
        const message = messageInput.value;
        const conversationId = conversationIdInput.value;
        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
          if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
              // Message sent successfully, do something
              console.log('Message sent successfully');
              // Clear the input field after sending the message
              messageInput.value = '';
              var xhr2 = new XMLHttpRequest();
              xhr2.onreadystatechange = function() {
                if (xhr2.readyState === XMLHttpRequest.DONE) {
                  if (xhr2.status === 200) {
                    // Request successful, update the right container with the messages
                    var messages = JSON.parse(xhr2.responseText);
                    updateRightContainer(messages);
                  } else {
                    // Handle error case
                    console.error('Failed to retrieve messages');
                  }
                }
              };
              xhr2.open('GET', '/NibInk/retrieveMessage?id=' + conversationId);
              xhr2.send(); 
              
            } else {
              // Error sending message, handle it appropriately
              console.error('Failed to send message');
            }
          }
        };
        xhr.open('POST', '/NibInk/sendMessage');
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        const params = 'conversationId=' + encodeURIComponent(conversationId) + '&text=' + encodeURIComponent(message);
        xhr.send(params);
        
      }

      // Add event listener to the input field or trigger the sendMessage() function when needed
      messageInput.addEventListener('keydown', function(event) {
        if (event.key === 'Enter') {
          event.preventDefault(); // Prevent the default form submission behavior
          if (messageInput.value.trim() !== '') {
              sendMessage();
            }
        }
      });
      </script>

</body>
</html>