<%@ page  language="java" pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="main.css" type="text/css"/>
<title>RIMS Assistant - Results</title>
		<script type="text/javascript">
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
</script>
</head>

<body>
<div class="results">
<h3>Results</h3>
<jsp:useBean id="publication" class="nz.ac.massey.rimsgroup3.metadata.bean.Journal" scope="session" ></jsp:useBean>

<form name="resultsForm" action="confirmation.jsp" method="post" onSubmit="" >
<div id="mainContainer">
	<div><label for="author0">Author:</label><input type="text" id="author0">
       	 <label for="staffID0">Staff ID:</label><input type="text" id="staffID0">
       	 <label for="unit0">Unit:</label><input type="text" id="unit0">
      	 <label for="mailCode0">Mail Code:</label><input type="text" id="mailCode0">
       	 <input type="button" value="Add" onClick="addNew()">
         <input type="button" value="Delete" onClick="deleteIt()">
    </div>
</div>
<div class="secondSet" >
<table class="resultTable" align="center">
  <tr>
<td colspan="3">Journal Contribution:</td>
</tr>
<tr>
<td><input type="radio" name="contribution" value="review article"/>Review Article</td>
<td><input type="radio" name="contribution" value="full article"/>Full Article in Personal Publication</td>
<td><input type="radio" name="contribution" value="journal editor"/>Journal Editor</td>
</tr>
<tr>
<td><input type="radio" name="contribution" value="full article in journal"/>Full Article in Journal</td>
<td><input type="radio" name="contribution" value="misc"/>Editorial, Brief Communication, Letter or Note</td>
<td><input type="radio" name="contribution" value="other"/>Other</td>
</tr>
</table>
<table class="resultTable" cellpadding="5px" align="center">
<tr>
<td>Research or Professsional/Community</td><td>Quality Assured?</td><td>Confidential?</td>
</tr>
<tr>
<td>Research (PBRF)       <input type="radio" name="research" value="research"/> <br/>
    Professional/Community<input type="radio" name="research" value="professional"/>
</td>
<td>
	Yes<input type="radio" name="qa" value="yes"/> <br/>
    No <input type="radio" name="qa" value="no"/>
</td>
<td>
	Yes<input type="radio" name="confidential" value="yes"/> <br/>
    No <input type="radio" name="confidential" value="no"/>
</td>
</tr>
</table>
</div>

<div class="thirdSet">
<table align="left">
<tr>
<td><label for="authors">Authors:</label></td>
<td><input type="text" name="authors" size="40" value="<c:out value="${publication.authorsString}" />" /></td>
</tr>
<tr>
<td><label for="articleTitle">Article/Output Title:</label></td>
<td><input type="text" name="articleTitle" size="40" value="<c:out value="${publication.articleTitle}" />" /></td>
</tr>
<tr>
<td><label for="journalTitle">Journal/Publication Title:</label></td>
<td><input type="text" name="journalTitle" size="40" value="<c:out value="${publication.journalTitle}" />" /></td>
</tr>
<tr>
<td><label for="year">Publication Year:</label></td>
<td><input type="text" name="year" size="40" value="<c:out value="${publication.year}" />" /> </td>
</tr>
<tr>
<td><label for="publisher">Publisher:</label></td>
<td><input type="text" name="publisher" size="40" /></td>
</tr>
<tr>
<td><label for="issn">ISSN:</label></td>
<td><input type="text" name="issn" size="40" value="<c:out value="${publication.issn}" />" /></td>
</tr>
<tr>
<td><label for="volume">Volume/Number:</label></td>
<td><input type="text" name="volume" size="40" value="<c:out value="${publication.volume}"/>" /></td>
</tr>
<tr>
<td><label for="pageNum">Page numbers:</label></td>
<td><input type="text" name="pageNum" size="40" value="<c:out value="${publication.startPage}-${publication.endPage}" />" /> </td>
</tr>
<tr>
<td><label for="url">URL Address:</label></td>
<td><input type="text" name="url" size="40" value="<c:out value="${publication.url}" />" />
	<a  class="smallLink" href="<c:out value="${publication.url}" />" >Follow Link</a>
</td>
</tr>
<tr>
<td><label for="doi">DOI:</label></td>
<td><input type="text" name="doi" size="40" value="<c:out value="${publication.doi}" />" /></td>
</tr>
<tr>
<td><label for="keywords">Keywords:</label></td>
<td><input type="text" name="keywords"  value="<c:out value="${publication.keyWords}" />"/></td>
</tr>
</table>
<table class="abstract">
<tr>
<td>
<label for="abstract">Abstract:</label>
</td>
<td>
    <textarea name="abstract" rows="15" cols="40" onKeyUp="wordCount(this.value);">
<c:out value="${publication.abstractText}" />
</textarea>
</td>
</tr>
<tr>
    <td></td>
    <td><label id="wordcount" class="smallLink"></label></td>
</tr>
</table>
</div>
<a href="index.jsp"><input type="button" name="back" value="Back" /></a>
<input type="submit" name="confirm" value="Confirm"/>
</form>
</div>
</body>
</html>
