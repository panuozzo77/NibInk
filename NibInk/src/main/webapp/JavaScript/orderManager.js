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

  // Clear any existing orders
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
    var buttonAdded = false;
    for (var key in order) {
      if (order.hasOwnProperty(key)) {
        var cell = document.createElement("td");
        if (key === "status") { // If it's the status column
          var select = document.createElement("select");
          select.name = "status";
          var options = [
            { value: "pending", text: "Pending" },
            { value: "confirmed", text: "Confirmed" },
            { value: "canceled", text: "Canceled" },
            { value: "shipped", text: "Shipped" },
            { value: "delivered", text: "Delivered" },
            { value: "toBeReturned", text: "To Be Returned" },
            { value: "refund", text: "Refund" }
          ];

          options.forEach(function (option) {
            var optionElement = document.createElement("option");
            optionElement.value = option.value;
            optionElement.text = option.text;
            select.appendChild(optionElement);
          });

          select.value = order[key];
          cell.appendChild(select);

          select.addEventListener('change', function () {
            var currentStatus = order[key];
            var newStatus = select.options[select.selectedIndex].value;
            if (currentStatus !== newStatus && !buttonAdded) {
              var button = document.createElement("button");
              button.textContent = "Aggiorna";
              button.addEventListener('click', function () {
                var orderId = order['id']; 
                sendAjaxRequest(orderId, newStatus);
              });
              cell.appendChild(button); // Append the button to the cell
              buttonAdded = true;
            } else {
              var button = cell.querySelector('button');
              if (button) {
                button.remove();
                buttonAdded = false;
              }
            }
          });
        } else {
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
  var backButton = $("<button>").text("Indietro").click(function () {
    togglePopup();
  });
  backButton.appendTo(table);
  container.style.display = "block";
}

function sendAjaxRequest(orderId, newStatus) {
	$.ajax({
  url: '/getOrders',
  method: 'POST',
  data: {
    parameter1: orderId,
    parameter2: newStatus,
  },
  success: function(response) {
    console.log(response);
  },
  error: function(error) {
    console.error(error);
  }
});
}