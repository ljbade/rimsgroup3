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
  // start  progress display
  var progress = document.getElementById('ajax_response');
  var html = "<br /><img src='ajax-loader.gif' /><br /> Search in progress. Please wait..."
  progress.innerHTML = html;

  // send AJAX request
  if(method == 'get' || method == "GET"){
     url = url + "?search=" + doi;
     http.open(method,url,true);
     http.onreadystatechange = handleResponse;
     http.send(null);
  }
}

// ajax function for advanced search
// takes method (get), url (AdvancedReques servlet) and the fields from the advanced search form
function sendAdvancedRequest(method, url, fName, lName, jTitle, aTitle, year){
  // start  progress display
  var progress = document.getElementById('ajax_response2');
  var html = "<br /><img src='ajax-loader.gif' /><br /> Search in progress. Please wait..."
  progress.innerHTML = html;

  // send AJAX request
  if(method == 'get' || method == "GET"){
     url = url + "?aulast=" + lName + "&title=" + journalTitle + "&articleTitle=" + aTitle + "&year=" + year;
     http.open(method,url,true);
     http.onreadystatechange = handleResponse;
     http.send(null);
  }
}

function handleResponse(){
  if(http.readyState == 4 && http.status == 200){
     var response = http.responseText;
     if(response){
      //stop progress display
      var progress = document.getElementById('ajax_response');
      progress.innerHTML = "Search complete, redirecting...";

      // redirect to results
      window.location = "results.jsp"

     }
  }

function displayProgress() {
    
}
}



