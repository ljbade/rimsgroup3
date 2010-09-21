// JavaScript Document
var counter = 1;
function addNew(authorValue) {
	// Get the main Div in which all the other divs will be added
	var mainContainer = document.getElementById('authorDiv');
	// Create a new div for holding text and button input elements
	var newDiv = document.createElement('div');
	newDiv.id = counter;

	// Create a new text input
	var newText = document.createElement('input');
	newText.type = "input";
	newText.id = "authors" + counter;
	newText.setAttribute("value",authorValue);
	newDiv.innerHTML+= "<label for=\"author + counter\">Author:</label>";
	newDiv.appendChild(newText);
	
	var newText = document.createElement('input');
	newText.type = "input";
	newText.id = "affiliation" + counter;
	newDiv.innerHTML+= "<label for=\"affiliation + counter\">Affiliation:</label>";
	newDiv.appendChild(newText);
	
	var newText = document.createElement('input');
	newText.type = "input";
	newText.id = "id" + counter;
	newDiv.innerHTML+= "<label for=\"id + counter\">Unit:</label>";
	newDiv.appendChild(newText);

	var newMoveUpBtn = document.createElement('input');
	newMoveUpBtn.type = "button";
	newMoveUpBtn.value = "Move Up";

	// Append new button input to the newDiv
	//newDiv.appendChild(newDelButton);
	newDiv.appendChild(newMoveUpBtn);
	
	// Append newDiv input to the mainContainer div
	mainContainer.appendChild(newDiv);
	counter++;
	
	newMoveUpBtn.onclick = function() {
			var thisDiv = newMoveUpBtn.parentNode.id;
			
			var temp = document.getElementById("authors" + thisDiv).value;
			document.getElementById("authors" + thisDiv).value = document.getElementById("authors" + (thisDiv - 1)).value;
			document.getElementById("authors" + (thisDiv - 1)).value = temp;
			
			var temp = document.getElementById("affiliation" + thisDiv).value;
			document.getElementById("affiliation" + thisDiv).value = document.getElementById("affiliation" + (thisDiv - 1)).value;
			document.getElementById("affiliation" + (thisDiv - 1)).value = temp;
			
			var temp = document.getElementById("id" + thisDiv).value;
			document.getElementById("id" + thisDiv).value = document.getElementById("id" + (thisDiv - 1)).value;
			document.getElementById("id" + (thisDiv - 1)).value = temp;
	};
}
function deleteIt(){
	var mainContainer = document.getElementById('authorDiv');
	mainContainer.removeChild(document.getElementById(counter-1));
	if(counter>1){
		counter--;
	}
}

function dynamicAdd(authorString){
	var authorString = "Kevin,Joe,Bob,Tom";
	var myAuthors = authorString.split(",");
	
	for(var i=0;i<myAuthors.length;i++){
		addNew(myAuthors[i]);
	}	
}

/* count number of characters in abstract text */
function wordCount(count) {
	var label = document.getElementById('wordcount');
	label.innerHTML = "Character count: " + count.length;
}