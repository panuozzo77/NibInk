var newItems;
var firstShownNewItem = 0;
var firstHiddenNewItem;

$(document).ready(function startUp(){
	newItems=$("#newItemsNumber").val();
	firstHiddenNewItem=$("#firstHiddenItem").val();
})

function showNext(){
	var classes=["Hidden", "cardContainer"];
	
	$("#item"+firstShownNewItem).toggleClass(classes);
	$("#item"+firstHiddenNewItem).toggleClass(classes);
	firstHiddenNewItem++;
	firstShownNewItem++;
	
	if(firstHiddenNewItem==newItems){
		firstHiddenNewItem=0;
	}
	if(firstShownNewItem==newItems){
		firstShownNewItem=0;
	}
	
	let item = $("#prevBtn").next().prop('outerHTML');
		$("#prevBtn").next().remove();
		$("#nextBtn").before(item);
}

function showPrev(){
	var classes=["Hidden", "cardContainer"];
	
	firstHiddenNewItem--;
	firstShownNewItem--;
	if(firstHiddenNewItem==-1){
		firstHiddenNewItem=newItems-1;
	}
	if(firstShownNewItem==-1){
		firstShownNewItem=newItems-1;
	}
	
	$("#item"+firstShownNewItem).toggleClass(classes);
	$("#item"+firstHiddenNewItem).toggleClass(classes);
	
	let item = $("#nextBtn").prev().prop('outerHTML');
		$("#nextBtn").prev().remove();
		$("#prevBtn").after(item);
}







	