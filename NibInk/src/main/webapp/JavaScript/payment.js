$(document).ready(function createExpDates(){
	for(let i=1; i<13; i++){
		$("#expMonth").append($("<option>").text(i));
	}
	var year=new Date();
	year=year.getFullYear();
	for(let j=0; j<11; j++){
		$("#expYear").append($("<option>").text(year+j));
	}
	$("#inputBAddr").addClass("inputBAddrHidden");
});

function toggleBillingAddr(){
	var classes=["inputBAddrHidden", "inputBAddrShow"];
	$("#inputBAddr").toggleClass(classes);
};