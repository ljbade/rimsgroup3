// JavaScript Document
var counter = 1;

function setCount(count){
	counter = count+1;
}

function addNew() {
	// Get the main Div in which all the other divs will be added
	var mainContainer = document.getElementById('authorDiv');
	// Create a new div for holding text and button input elements
	var newDiv = document.createElement('div');
	newDiv.id = counter;

	// Create a new text input
	var newText = document.createElement('input');
	newText.type = "text";
	newText.name = "fName" + counter;
	newText.setAttribute("size","15");
	newDiv.innerHTML+= "<label>First Name:</label>";
	newDiv.appendChild(newText);
	
	var newText = document.createElement('input');
	newText.type = "text";
	newText.name = "mName" + counter;
	newText.setAttribute("size","15");
	newDiv.innerHTML+= "<label>M. Name:</label>";
	newDiv.appendChild(newText);
	
	var newText = document.createElement('input');
	newText.type = "text";
	newText.name = "lName" + counter;
	newText.setAttribute("size","15");
	newDiv.innerHTML+= "<label>Last Name:</label>";
	newDiv.appendChild(newText);
	
	var newText = document.createElement('input');
	newText.type = "text";
	newText.name = "affiliation" + counter;
	newText.setAttribute("size","15");
	newDiv.innerHTML+= "<label>Affiliation:</label>";
	newDiv.appendChild(newText);
	
	var newText = document.createElement('input');
	newText.type = "text";
	newText.name = "id" + counter;
	newText.setAttribute("size","15");
	newDiv.innerHTML+= "<label>ID Number:</label>";
	newDiv.appendChild(newText);

	var newMoveUpBtn = document.createElement('input');
	newMoveUpBtn.type = "button";
	newMoveUpBtn.value = "Move Up";
	newMoveUpBtn.setAttribute("onClick","moveUp()");

	// Append new button input to the newDiv
	//newDiv.appendChild(newDelButton);
	newDiv.appendChild(newMoveUpBtn);
	
	// Append newDiv input to the mainContainer div
	mainContainer.appendChild(newDiv);
	counter++;
}

function moveUp() {
	var thisDiv = this.parentNode.id;
	
	var temp = document.getElementById("fName" + thisDiv).value;
	document.getElementById("fName" + thisDiv).value = document.getElementById("fName" + (thisDiv - 1)).value;
	document.getElementById("fName" + (thisDiv - 1)).value = temp;
	
	var temp = document.getElementById("mName" + thisDiv).value;
	document.getElementById("mName" + thisDiv).value = document.getElementById("mName" + (thisDiv - 1)).value;
	document.getElementById("mName" + (thisDiv - 1)).value = temp;
	
	var temp = document.getElementById("lName" + thisDiv).value;
	document.getElementById("lName" + thisDiv).value = document.getElementById("lName" + (thisDiv - 1)).value;
	document.getElementById("lName" + (thisDiv - 1)).value = temp;
	
	var temp = document.getElementById("affiliation" + thisDiv).value;
	document.getElementById("affiliation" + thisDiv).value = document.getElementById("affiliation" + (thisDiv - 1)).value;
	document.getElementById("affiliation" + (thisDiv - 1)).value = temp;
	
	var temp = document.getElementById("id" + thisDiv).value;
	document.getElementById("id" + thisDiv).value = document.getElementById("id" + (thisDiv - 1)).value;
	document.getElementById("id" + (thisDiv - 1)).value = temp;
}

function deleteIt(){
	var mainContainer = document.getElementById('authorDiv');
	mainContainer.removeChild(document.getElementById(counter-1));
	if(counter>1){
		counter--;
	}
}


/* count number of characters in abstract text */
function wordCount(count) {
	var label = document.getElementById('wordcount');
	label.innerHTML = "Character count: " + count.length;
}