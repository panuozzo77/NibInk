function showTextBox(){
	var textBox = document.getElementById("textBox");
	var options = document.getElementById("options");
	var optionButton = document.getElementById("optionButton");
	var textButton = document.getElementById("textButton");
	var addButton = document.getElementById("addButton");
	var removeButton = document.getElementById("removeButton");
	var undoButton = document.getElementById("undoButton");
	
	removeButton.style.display="none";
	undoButton.style.display="inline";
	addButton.style.display="inline";
	optionButton.style.display="inline";
	textButton.style.display="none";
	textBox.style.display="inline";
	options.style.display="none";
}

function disableSecondHalf(){
	var div = document.getElementById("secondHalf");
	div.classList.replace("secondHalfForm", "disabled")
}

function enableSecondHalf(){
	var div = document.getElementById("secondHalf");
	div.classList.replace("disabled", "secondHalfForm")
}

function showOptions(){
	var textBox = document.getElementById("textBox");
	var options = document.getElementById("options");
	var optionButton = document.getElementById("optionButton");
	var textButton = document.getElementById("textButton");
	var addButton = document.getElementById("addButton");
	var removeButton = document.getElementById("removeButton");
	var undoButton = document.getElementById("undoButton");
	
	removeButton.style.display="inline";
	undoButton.style.display="none";
	addButton.style.display="none";
	textButton.style.display="inline";
	textBox.style.display="none";
	options.style.display="inline";
	optionButton.style.display="none";
	
}

function handleKeyPress(event) {
    if (event.keyCode === 13) {
    	event.preventDefault();
        return false;
    }
}

function addOption() {
	  var optionValue = document.getElementById("textBox").value;

	  if (optionValue !== "") {
	    var newOption = document.createElement("option");
	    newOption.value=optionValue;
	    newOption.text=optionValue;
	    document.getElementById("options").add(newOption);
	    document.getElementById("textBox").value = "";
	  }
	}

function removeOption(){
	var options = document.getElementById("options");
	var selectedIndex = options.selectedIndex;
	if(selectedIndex!==null) {
		options.remove(selectedIndex);
		}
}

function undoOption(){
	var options = document.getElementById("options");
	var lastOption = options.lastElementChild;
	if (lastOption !== null) {
		options.removeChild(lastOption);
		}
}

function saveSizes(){
	var sizes = [];
	var selectInput = document.getElementById("options");
	var output = document.getElementById("optionValues");
    for(var i=0;i<selectInput.options.length;i++){
        sizes.push(selectInput.options[i].value);
    }
    output.value=sizes;
}