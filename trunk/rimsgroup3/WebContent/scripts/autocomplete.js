// JavaScript file for calling functions via AJAX
//
// the sendRequest function is called by the form in the results.jsp page

var isIE;
var completeTable;
var publisherField;
var autorow;
var http;

function initRequest() {
	http = createRequestObject();
	publisherField = document.getElementById("publisher");
	var menu = document.getElementById("auto-row");
	autorow = document.getElementById("menu-popup");
	autorow.style.top = getElementY(menu);
	completeTable = document.getElementById("completeTable");
	completeTable.setAttribute("bordercolor", "white");
}

function getElementY(element){
	var targetTop = 0;
	if (element.offsetParent)
	{
		while (element.offsetParent) 
		{
		targetTop += element.offsetTop;
		element = element.offsetParent;
		}
	} else if (element.y) {
	targetTop += element.y;
	}
	return targetTop;
	}

function createRequestObject(){
   var req;
   try {
      // Firefox, Opera, Safari
	   isIE = false;
      req = new XMLHttpRequest();
      
      
     }
     catch (e) {
     // Internet Explorer
    	 
    	 
      try {
      //For IE 6
    	  
      req = new ActiveXObject("Msxml2.XMLHTTP");
      
	 
     } catch (e) {
     try {
      //For IE 5
      req = new ActiveXObject("Microsoft.XMLHTTP");
     } catch (e) {
      alert('Your browser is not IE 5 or higher, or Firefox or Safari or Opera');
     }
  }
}
return req;
}
 /*function AJAXInteraction(url) {

	http=GetXmlHttpObject();
	http.onreadystatechange = processRequest;
	http.open("GET", url, true);
	http.send(null);

	}*/


function doCompletion() {
	initRequest();
    if (publisherField.value == "") 
    {
        clearTable();
    }
    else 
    {
        var url = "AutoComplete?action=complete&id=" + 
                escape(publisherField.value);
        
        
        http.open("GET", url, true);
        http.send(null);
        http.onreadystatechange = processRequest;
           
        
    }
} 
function processRequest () {
	 if (http.readyState == 4 && http.status == 200) 
     {
     	parseMessages(http.responseXML);
     } 
 	else if (http.status == 204)
     {
 		clearTable();
     } 
	}



function parseMessages(responseXML) {
    clearTable();
   
    var publishers = responseXML.getElementsByTagName("publishers")[0];
    if (publishers.childNodes.length > 0) 
    {
        completeTable.setAttribute("bordercolor", "black");
        completeTable.setAttribute("border", "1");
    } 
    else 
    {
        clearTable();
    }
 
    for (loop = 0; loop < publishers.childNodes.length; loop++) 
    {
        var publisher = publishers.childNodes[loop];
        var name = publisher.getElementsByTagName("name")[0];
        appendPublisher(name.childNodes[0].nodeValue);     
    }
} 

function clearTable()
	{
		if (completeTable) 
		{
			completeTable.setAttribute("bordercolor", "white");
	        completeTable.setAttribute("border", "0");
			completeTable.style.visible = false;
			for (loop = completeTable.childNodes.length -1; loop >= 0 ; loop--) 
				{
					completeTable.removeChild(completeTable.childNodes[ loop]);
				}
			
		}
	}

function appendPublisher(name) {

	var row;
	var nameCell;
		row = completeTable.insertRow(completeTable.rows.length);
		nameCell = row.insertCell(0);
		row.className = "popupRow";
		nameCell.setAttribute("bgcolor", "#FFFAFA");
		nameCell.setAttribute("width", "60%");

		var linkElement = document.createElement("a");
		linkElement.className = "popupItem";
		linkElement.setAttribute("href","javascript:enter('"+name+"')");
		linkElement.appendChild(document.createTextNode(name));
		nameCell.appendChild(linkElement); 
}

function enter(name){

	document.getElementById("publisher").value=name;
	clearTable();
	}













