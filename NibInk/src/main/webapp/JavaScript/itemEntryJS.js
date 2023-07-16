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

function openPopUp() {
	var popup = document.getElementById("popup");
	popup.style.display = "flex";
}

function closePopUp() {
	var popup = document.getElementById("popup");
	popup.style.display = "none";
}

document.getElementById("closePopup").addEventListener("click", function() {
  var popup = document.getElementById("popup");
  popup.style.display = "none";
});

function deleteImage(imageUrl) {
  fetch('/NibInk/deleteImage', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
    },
    body: 'imageUrl=' + imageUrl,
  })
    .then(function(response) {
      if (response.ok) {
        // Image deleted successfully
        console.log('Image deleted.');
      } else {
        // Error occurred while deleting the image
        console.log('Failed to delete image.');
      }
    })
    .catch(function(error) {
      console.log('Error occurred:', error);
    });
    showUploadedPhotos();
}

function displayImages(images) {
  var thumbnailPreview = document.getElementById("thumbnailPreview");
  var photosPreview = document.getElementById("photosPreview");

  thumbnailPreview.innerHTML = "";
  photosPreview.innerHTML = "";

  images.forEach(function (image) {
    var img = document.createElement("img");
    img.src = image;
    img.alt = "Preview";

    var deleteButton = document.createElement("button");
    deleteButton.textContent = "Elimina";
    deleteButton.addEventListener("click", function () {
      deleteImage(image);
      thumbnailPreview.removeChild(img);
      photosPreview.removeChild(img);
    });

    var container = document.createElement("div");
    container.appendChild(img);
    container.appendChild(deleteButton);

    if (image.includes("thumbnail")) {
      thumbnailPreview.appendChild(container);
    } else {
      photosPreview.appendChild(container);
    }
  });
}


function showUploadedPhotos() {
	openPopUp();
    var url = new URL(window.location.href);
    var id = url.searchParams.get('id');
    var photosPreview = document.getElementById("photosPreview");

    // Clear the existing preview
    photosPreview.innerHTML = "";

    // Make AJAX request to fetch the image URLs
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/NibInk/getImages?id=" + id, true);

    xhr.onload = function () {
        if (xhr.status === 200) {
            var response = JSON.parse(xhr.responseText);
            var imageUrls = response.images;
            displayImages(imageUrls);
        }
    };

    xhr.send();
}

document.addEventListener("DOMContentLoaded", function() {
    var showPopupButton = document.getElementById("showPopup");

    // Add the click event listener to the button
    showPopupButton.addEventListener("click", function() {
        showUploadedPhotos();
    });
});