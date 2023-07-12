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
	$("#placeOrderBtn").toggleClass(classes);
}