$(document).ready(function() {
      $("#userResearch").on("change keyup", function(){
		  canISearchByUser();  
	  });
	  $("#startDate").on("change keyup", function() {
		  checkDates();
	  });
	  $("#endDate").on("change keyup", function() {
		  checkDates();
	  });
})
    
function canISearchByUser() {
	var input = document.getElementById("userResearch");
	var button = document.getElementById("userButton");
	if(input.value==='') {
		button.disabled = true;
	}
	else {
		button.disabled = false;
	}
}

function checkDates() {
  var startDateID = document.getElementById("startDate");
  var endDateID = document.getElementById("endDate");
  var dateButton = document.getElementById("dateButton");
  
  var startDate = new Date(startDateID.value);
  var endDate = new Date(endDateID.value);

  if (startDate <= endDate) {
    dateButton.disabled = false;
  } else {
    dateButton.disabled = true;
  }
}
//funzione AJAX per il caricamento di tutti gli ordini dal più recente
function showAllOrders() {
    $.ajax({
      url: "/NibInk/getOrders",
      method: "GET",
      data: {mode : "all"},
      dataType: "json", 
      success: function (response) {
    	  showOrders(response);
    	},
      error: function (xhr, status, error) {
        console.log("AJAX Error:", error);
      }
    });
}

//funzione AJAX per il caricamento di tutti gli ordini di un utente

function showSingleUserOrders() {
    var input = document.getElementById("userResearch");
    var id = input.value;
    $.ajax({
      url: "/NibInk/getOrders",
      method: "GET",
      data: {userId : id},
      dataType: "json", 
      success: function (response) {
    	  showOrders(response);
    	},
      error: function (xhr, status, error) {
        console.log("AJAX Error:", error);
      }
    });
}

function showOrderByDate() {
	var startDateID = document.getElementById("startDate");
  	var endDateID = document.getElementById("endDate");
  	var startDate = startDateID.value;
  	var endDate = endDateID.value;
	$.ajax({
      url: "/NibInk/getOrders",
      method: "GET",
      data: {mode : "byDate", "sd" : startDate, "ed" : endDate},
      dataType: "json", 
      success: function (response) {
    	  showOrders(response);
    	},
      error: function (xhr, status, error) {
        console.log("AJAX Error:", error);
      }
    });
}

//funzione per chiudere la tabella generata da showOrder
function togglePopup() {
	  var container = document.getElementById("container");
	  container.style.display = "none";
}
  	
  	//questa funzione mostra tutti gli ordini ricevuti dalla response
function showOrders(response) {
	  var container = document.getElementById("container");
	
	  // Clear any existing addresses
	  container.innerHTML = "";
	
	  // Create the HTML table structure
	  var table = document.createElement("table");
	  var thead = document.createElement("thead");
	  var tbody = document.createElement("tbody");
	
	  // Create the table header row
	  var headerRow = document.createElement("tr");
	  for (var key in response[0]) {
	    if (response[0].hasOwnProperty(key)) {
	      var th = document.createElement("th");
	      th.textContent = key;
	      headerRow.appendChild(th);
	    }
	  }
	  thead.appendChild(headerRow);
	  table.appendChild(thead);
	
	  // Create the table body rows
	  response.forEach(function (order) {
	    var row = document.createElement("tr");
	    for (var key in order) {
	      if (order.hasOwnProperty(key)) {
	        var cell = document.createElement("td");
	        if(key ==="status") {	//se è la colonna status
	        	var select = document.createElement("select");
	        	select.name = "status";
	        	var option1 = document.createElement("option");
	        	option1.value = "pending";
	            option1.text = "Pending";
	
	        	var option2 = document.createElement("option");
	        	option2.value = "confirmed";
	        	option2.text = "Confirmed";
	        	
	        	var option3 = document.createElement("option");
	        	option3.value = "canceled";
	        	option3.text = "Canceled";
	        	
	        	var option4 = document.createElement("option");
	        	option4.value = "shipped";
	        	option4.text = "Shipped";
	        	
	        	var option5 = document.createElement("option");
	        	option5.value = "delivered";
	        	option5.text = "Delivered";
	        	
	        	var option6 = document.createElement("option");
	        	option6.value = "toBeReturned";
	        	option6.text = "ToBeReturned";
	        	
	        	var option7 = document.createElement("option");
	        	option7.value = "refund";
	        	option7.text = "Refund";
	        	
	        	select.appendChild(option1);
	        	select.appendChild(option2);
	        	select.appendChild(option3);
	        	select.appendChild(option4);
	        	select.appendChild(option5);
	        	select.appendChild(option6);
	        	select.appendChild(option7);
	        	
	        	select.value = order[key];
	        	cell.appendChild(select);
	        	
	        	select.addEventListener('change', function() {	//da qui comincio a controllare se la Select viene modificata
					var currentStatus = order[key];
					var newStatus = select.value;
					if(currentStatus !== newStatus) {
						var button = document.createElement("button");
						button.textContent = "Aggiorna";
						button.addEventListener('click', function() {
							var orderId = order['id'];
							//sendAjaxRequest(orderId, newStatus);		//da fare se riesco a far funzionare sto cazzo di bottone
						});
					} else {	//la select è ritornata allo stato di partenza, non devo aggiornare
						var button = cell.querySelector('button');
						if(button) {
							button.remove();
						}
					}
				});
	        }
	        else {	//non è la colonna status
		        cell.textContent = order[key];
	        }
	        row.appendChild(cell);
	      }
	    }
	    tbody.appendChild(row);
	  });
	  table.appendChild(tbody);
	
	  // Append the table to the container
	  container.appendChild(table);
	  var backButton = $("<button>").text("Indietro").click(function() {
		  togglePopup(); });
	  backButton.appendTo(table);
	  container.style.display = "block";
}