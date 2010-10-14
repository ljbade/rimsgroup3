// JavaScript file for calling functions via AJAX
//
// the sendRequest function is called by the form in the jsp page
// Parameters are: method - only GET methods are accepted
//                 url - url of server function being called

function createRequestObject(){
   var req;
   try {
      // Firefox, Opera, Safari
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

//Make the XMLHttpRequest Object
var http = createRequestObject();
function sendRequest(method, url, doi){
	// check doi textbox was not empty
	/*if(doi.indexOf(" ") != -1) {
		var progress = document.getElementById('ajax_response');
		var html = "<br />A DOI does not include spaces...";
		progress.innerHTML = html;
	} else */
		if(doi.length < 1) {
		var progress = document.getElementById('ajax_response');
		var html = "<br />A DOI needs to be entered...";
		progress.innerHTML = html;
	} else {
	  // start  progress display
	  var progress = document.getElementById('ajax_response');
	  var empty = "";
	  progress.innerHTML = empty;
	  var html = "<br /><img src='ajax-loader.gif' /><br /> Search in progress. Please wait...";
	  progress.innerHTML = html;
	
	  // send AJAX request
	  if(method == 'get' || method == "GET"){
	     url = url + "?search=" + doi;
	     http.open(method,url,true);
	     http.onreadystatechange = handleResponse;
	     http.send(null);
	  }
	}
}

//response handler for doiRequest method
function handleResponse(){
  if(http.readyState == 4 && http.status == 200){
     var response = http.responseText;
     if(response.indexOf("DOI found") != -1) {
    	 var progress = document.getElementById('ajax_response');
         progress.innerHTML = "DOI has already been processed.";
     } else if (response.indexOf("DOI not found") != -1) {
    	 var progress = document.getElementById('ajax_response');
         progress.innerHTML = "That DOI could not be located.";
     } else if(response.indexOf("error") != -1) {
    	 window.location = "index.jsp";
	} 
     else if(response.indexOf("OK") != -1){
      //stop progress display
      var progress = document.getElementById('ajax_response');
      progress.innerHTML = "Search complete, redirecting...";

      // redirect to results page for relevant type
      if(response.indexOf("journal") != -1) {
    	  window.location = "results.jsp";
      }else if (response.indexOf("book") != -1) {
    	  window.location = "bookResults.jsp";
      } else if ((response.indexOf("conference") != -1) ) {
    	  window.location = "conferenceResults.jsp";
      }
     }
  }
}

// ajax calling function for screenscaping class
function getAbstract(method, url, resource){
	// start  progress display
	  var progress = document.getElementById('abstract_progress');
	  var html = "<br /><img src='ajax-loader.gif' /><br /> Locating article abstract. Please wait..."
	  progress.innerHTML = html;
	  // send AJAX request
	  if(method == 'get' || method == "GET"){
	     url = url + "?resource=" + resource;
	     http.open(method,url,true);
	     http.onreadystatechange = handleAbstractResponse;
	     http.send(null);
	  }
}



// advancedRequest AJAX methods
// method = get, url is the servlet to be called
// journal, lname, article and year relate to search criteria entered into the form
function sendAdvancedRequest(method, url, lname, journal, article, year){
	  // start  progress display
	  var progress = document.getElementById('ajax_response2');
	  var empty = "";
	  progress.innerHTML = empty;
	  if(advancedValid()){
		  var html = "<br /><img src='ajax-loader.gif' /><br /> Search in progress. Please wait...";
		  progress.innerHTML = html;

		  // send AJAX request
		  if(method == 'get' || method == "GET"){
			  var name = "?lName=" + lname;
			  var journalTitle = "&journalTitle=" + journal;
			  var articleTitle = "&articleTitle=" + article;
			  var date = "&year=" + year;
		     url = url + name + journalTitle + articleTitle + date;
		     http.open(method,url,true);
		     http.onreadystatechange = handleAdvancedResponse;
		     http.send(null);
		  }
	  }
	  else{
		  var html = "<br />Please fill in all fields";
		  progress.innerHTML = html;
	  }
	  
}

function advancedValid(){
	var textField = document.getElementById('lName');
	var errorMessage = "";
	var valid = true;
	
	if(textField.value == ""){
		errorMessage += "Last Name can't be empty\n";
		valid = false;
	}
	
	textField = document.getElementById('journalTitle');
	if(textField.value == ""){
		errorMessage += "Journal title can't be empty\n";
		valid = false;
	}
	
	textField = document.getElementById('articleTitle');
	if(textField.value == ""){
		errorMessage += "Article title can't be empty\n";
		valid = false;
	}
	
	textField = document.getElementById('year');
	if(textField.value == ""){
		errorMessage += "Publication year can't be empty\n";
		valid = false;
	}
	
	if(!valid){
		alert(errorMessage);
	}
	
	return valid;
}

function handleAdvancedResponse(){
	  if(http.readyState == 4 && http.status == 200){
	     var response = http.responseText;
	     if(response.indexOf("search failed") != -1) {
	    	 var progress = document.getElementById('ajax_response2');
	         progress.innerHTML = "Advanced search found no matches.";
	     }
	     else {
	      //stop progress display
	      var progress = document.getElementById('ajax_response2');
	      progress.innerHTML = "Search complete, redirecting...";

	      // redirect to results
	      window.location = "results.jsp";

	     }
	  }
	}
