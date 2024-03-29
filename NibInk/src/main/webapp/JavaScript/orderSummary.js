$(document).ready(function startUp(){
	generateRadioValue();
})

function generateRadioValue(){
	var n=$("#shippingMethodsNumber").val();
	
	for(let i=0; i<n; i++){
		let radio="#sr"+i.toString();
		let value="#sPrice"+i.toString();
		$(radio).val($(value).text());
	}
}

function addShipPrice(){
	var sPrice=$('input[name="shippingRadio"]:checked').val();
	var priceSpan=$("<span>Spedizione</span><span>"+sPrice+"  +</span>");
	sPrice.replace("€", "");
	$("#selectedShippingCost").empty();
	$("#selectedShippingCost").append(priceSpan);
	getTotal();	
}

function getTotal(){
	var tPrice=$("#itemTotalPrice").text();
	var sPrice=$('input[name="shippingRadio"]:checked').val();
	var totalPrice = sum(tPrice, sPrice);
	
	var priceSpan=$("<span>Costo totale:</span><span>"+totalPrice+" €</span>");
	$("#totalCost").empty();
	$("#totalCost").append(priceSpan);
	showBuyBtn();
}
function sum(x1, x2){
	x1=makeFloat(x1);
	x2=makeFloat(x2);
	var tot=x1+x2;
	tot=tot.toFixed(2);
	return tot;
}

function makeFloat(input){
	input=input.replace("€", "");
	input=parseFloat(input.replace(",", "."));
	return input;
}

function showBuyBtn(){
	var classes=["Hidden", "btns"];
	if($("#placeOrderBtn").hasClass("Hidden")){
		$("#placeOrderBtn").toggleClass(classes);
	}
}



function placeOrder() {
	if (checkEmail()) {
		$("#placeOrderBtn").attr("disabled", true);
		$("#placeOrderBtn").val("Ordine inviato!");
	    var email = $("#emailCustomer").val();
	    
	    var shippingMethod = $('input[name="shippingRadio"]:checked').data("smethod");
	    var shippingPrice = $('input[name="shippingRadio"]:checked').data("sprice");
		sendAjaxRequest(email, shippingMethod, shippingPrice).then((resolve) => {
	  		var redirectUrl = resolve.trim();
	  		if (redirectUrl.startsWith("/NibInk")) {
	    		window.location.href = redirectUrl;
	  		} else {
				console.error("Invalid redirect URL: " + redirectUrl);
	  		}
		}).catch(function(error) {
	  		console.error("AJAX request failed:", error);
		});
  }
}

function sendAjaxRequest(email, shippingMethod, shippingPrice){
	return new Promise((resolve, reject) => {
      $.ajax({
        url: "/NibInk/placeOrder",
        type: "POST",
        data: {
          email: email,
          shippingMethod: shippingMethod,
          shippingPrice: shippingPrice
        },
        dataType: "text",
        success: resolve,
        error: reject
      }).done(resolve).fail(reject);
    });
}

function checkEmail(){
	var emailTest = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var email = $("#emailCustomer");
	if(emailTest.test(email.val())){
		showError();
		return true;
	}else{
		showError();
		return false;
	}
}

function showError(){
	var emailTest = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var email = $("#emailCustomer");
	if(emailTest.test(email.val())){
		$("#emailCustomer").removeClass("Error");
	}else{
		$("#emailCustomer").addClass("Error");
	}
}
