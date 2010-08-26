// JavaScript Document
var counter = 1;
function addNew() {
	// Get the main Div in which all the other divs will be added
	var mainContainer = document.getElementById('mainContainer');
	// Create a new div for holding text and button input elements
	var newDiv = document.createElement('div');
	newDiv.id = counter;
	// Create a new text input
	var newText = document.createElement('input');
	newText.type = "input";
	newText.id = "author" + counter;
	newDiv.innerHTML+= "<label for=\"author + counter\">Author:</label>";
	newDiv.appendChild(newText);
	
	var newText = document.createElement('input');
	newText.type = "input";
	newText.id = "staffID" + counter;
	newDiv.innerHTML+= "<label for=\"staffID + counter\">Staff ID:</label>";
	newDiv.appendChild(newText);
	
	var newText = document.createElement('input');
	newText.type = "input";
	newText.id = "unit" + counter;
	newDiv.innerHTML+= "<label for=\"unit + counter\">Unit:</label>";
	newDiv.appendChild(newText);
	
	var newText = document.createElement('input');
	newText.type = "input";
	newText.id = "mailCode" + counter;
	newDiv.innerHTML+= "<label for=\"mailCode + counter\">Mail Code:</label>";
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
			
			var temp = document.getElementById("author" + thisDiv).value;
			document.getElementById("author" + thisDiv).value = document.getElementById("author" + (thisDiv - 1)).value;
			document.getElementById("author" + (thisDiv - 1)).value = temp;
			
			var temp = document.getElementById("staffID" + thisDiv).value;
			document.getElementById("staffID" + thisDiv).value = document.getElementById("staffID" + (thisDiv - 1)).value;
			document.getElementById("staffID" + (thisDiv - 1)).value = temp;
			
			var temp = document.getElementById("unit" + thisDiv).value;
			document.getElementById("unit" + thisDiv).value = document.getElementById("unit" + (thisDiv - 1)).value;
			document.getElementById("unit" + (thisDiv - 1)).value = temp;
			
			var temp = document.getElementById("mailCode" + thisDiv).value;
			document.getElementById("mailCode" + thisDiv).value = document.getElementById("mailCode" + (thisDiv - 1)).value;
			document.getElementById("mailCode" + (thisDiv - 1)).value = temp;

	}
}
function deleteIt(){
	var mainContainer = document.getElementById('mainContainer');
	mainContainer.removeChild(document.getElementById(counter-1));
	if(counter>1){
		counter--;
	}
}

var cnt;
function wordCount(count) {
	var words = count.split(/\s/);
	cnt = words.length;
	var label = document.getElementById('wordcount');
	label.innerHTML = "Word count: " + cnt;
}