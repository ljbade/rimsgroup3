// JavaScript Document
var counter = 1;
var max = 1;

function setCount(count){
	counter = count;
	counter++;
	max = counter;
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
	newText.id = "fName" + counter;
	newText.setAttribute("size","15");
	newDiv.innerHTML+= "<label>First Name:&nbsp;</label>";
	newDiv.appendChild(newText);
	
	var newText = document.createElement('input');
	newText.type = "text";
	newText.name = "mName" + counter;
	newText.id = "mName" + counter;
	newText.setAttribute("size","15");
	newDiv.innerHTML+= "<label>&nbsp;M. Name:&nbsp;</label>";
	newDiv.appendChild(newText);
	
	var newText = document.createElement('input');
	newText.type = "text";
	newText.name = "lName" + counter;
	newText.name = "lName" + counter;
	newText.setAttribute("size","15");
	newDiv.innerHTML+= "<label>&nbsp;Last Name:&nbsp;</label>";
	newDiv.appendChild(newText);
	
	var newText = document.createElement('input');
	newText.type = "text";
	newText.name = "affiliation" + counter;
	newText.id = "affiliation" + counter;
	newText.setAttribute("size","15");
	newDiv.innerHTML+= "<label>&nbsp;Affiliation:&nbsp;</label>";
	newDiv.appendChild(newText);
	
	var newText = document.createElement('input');
	newText.type = "text";
	newText.name = "id" + counter;
	newText.id = "id" + counter;
	newText.setAttribute("size","15");
	newDiv.innerHTML+= "<label>&nbsp;ID Number:&nbsp;</label>";
	newDiv.appendChild(newText);
	
	var radio = document.createElement('input');
	radio.type = "radio";
	radio.name = "submitter";
	radio.id = "submitter" + counter;
	newDiv.innerHTML+= "&nbsp;";
	newDiv.appendChild(radio);
	
	// Append newDiv input to the mainContainer div
	mainContainer.appendChild(newDiv);
	counter++;
	
	// increment value in hidden field so print script knows total number of author rows
	var rowCount = document.getElementById('hidden').value;
	rowCount++;
	document.getElementById('hidden').value = rowCount;
	
	alignSubmitter();
}

function deleteIt(){
	if(counter>max){
		var mainContainer = document.getElementById('authorDiv');
		mainContainer.removeChild(document.getElementById(counter-1));
		counter--;
	}
	// decrement value in hidden field so print script knows total number of author rows
	var rowCount = document.getElementById('hidden').value;
	rowCount--;
	document.getElementById('hidden').value = rowCount;
}


/* count number of characters in abstract text */
function wordCount(count) {
	var label = document.getElementById('wordcount');
	label.innerHTML = "Character count: " + count.length;
}

// parse special characters in article title
function parse() {
	var title = document.getElementById('articleTitle').value;
	while(title.indexOf("[sub") != -1) {
	  title = title.replace("[sub", "<sub>");
	  title = title.replace("]", "</sub>");
	}
	while(title.indexOf("[sup") != -1) {
		  title = title.replace("[sup", "<sup>");
		  title = title.replace("]", "</sup>");
		}
	document.getElementById('articleTitle').value = title;
	document.getElementById('htmlTitle').innerHTML = title;
}

function alignSubmitter(){
	var submitRadio = document.getElementById('submitter1');
	var submitLabel = document.getElementById('submitterLabel');
	
	var radioTop = submitRadio.offsetTop;
	var radioLeft = submitRadio.offsetLeft;
	submitLabel.style.position = 'absolute';
	submitLabel.style.top = radioTop - 30;
	submitLabel.style.left = radioLeft - 20;
}
