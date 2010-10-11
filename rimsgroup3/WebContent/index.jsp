<%@ page  language="java" import="java.util.*" session="true" pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 	response.setHeader("Cache-Control", "no-cache, must-revalidate, no-store, max-age=0, proxy-revalidate"); 
	response.setHeader("Pragma","no-cache");
	response.setDateHeader("Expires",-1);%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="styles/main.css" type="text/css"/>
<title>Proficio - Home</title>
<script type="text/javascript" src="scripts/resultsScript.js"></script>
<script type="text/javascript" src="scripts/ajax.js"></script>
<script language="javascript"> 
<!--
var state = 'none';

function showhide(layer_ref) {

if (state == 'block') { 
state = 'none';
} 
else { 
state = 'block'; 
} 
if (document.all) { //IS IE 4 or 5 (or 6 beta) 
eval( "document.all." + layer_ref + ".style.display = state"); 
} 
if (document.layers) { //IS NETSCAPE 4 or below 
document.layers[layer_ref].display = state; 
} 
if (document.getElementById &&!document.all) { 
hza = document.getElementById(layer_ref); 
hza.style.display = state; 
} 
} 
//--> 
</script> 
</head>

<body>
<div id="container">
<div class="smallPage">
<img alt="Proficio" src="images/proficio.jpg" />
  <div class="innerIndex">
    <form name="searchForm" action="DoiRequest" method="post" onSubmit=""> 
	    <h2>DOI Search:</h2>
	    <table>
	    <tr>
		    <td><input type="text" name="search" id="search" size="45" onkeydown="if (event.keyCode == 13) sendRequest('get', 'DoiRequest', document.getElementById('search').value)"/></td>
		    <td><input type="button" id="searchBtn" name="new" value="Search" onclick="sendRequest('get', 'DoiRequest', document.getElementById('search').value)"/></td>
	    </tr>  
	    </table>
	    <div id="ajax_response" >
	        
	    </div>
	    <c:if test="${param.success == null }">
	    </c:if> 
	    <c:if test="${param.success == 'foundInDatabase'}">
	        Publication has previously been submitted.		
	    </c:if>
	
	    <c:if test="${param.success == 'no'}">
	        DOI search failed.		
	    </c:if>
	    <c:if test="${param.success == 'successfulCommit'}">
	        Publication has been successfully submitted.		
	    </c:if>
    </form>
   
    <p><a href="#" onClick="showhide('div1');">Advanced Search</a></p> 
    <!--  added closing div here --> 
  </div>
    <br />
    <!--  advanced search div -->
    <div id="div1" style="display: none;" class="innerIndex">
    <form name="advancedForm" action="AdvancedRequest" method="post" onSubmit=""> 
    <h2>Advanced Search:</h2>
    <span style="color:  navy; font-size: 8pt; text-align: right;">All fields are required.</span>
    <table>
    <tr>
    	<td><label for="lName">Last Name:</label></td>
    	<td><input type="text" name="lName" id="lName" onkeydown="if (event.keyCode == 13) document.getElementById('advSearch').click()"/></td>
    </tr>
    <tr>
   	 	<td><label for="journalTitle">Journal/Publication Title:</label></td>
    	<td><input type="text" name="journalTitle" id="journalTitle" onkeydown="if (event.keyCode == 13) document.getElementById('advSearch').click()"/></td>
    </tr>
    <tr>
        <td><label for="articleTitle">Article Title:</label></td>
        <td><input type="text" name="articleTitle" id="articleTitle" onkeydown="if (event.keyCode == 13) document.getElementById('advSearch').click()"/></td>
    </tr>
    <tr>
    	<td><label for="year">Publication Year:</label></td>
    	<td><input type="text" name="year" id="year" onkeydown="if (event.keyCode == 13) sendAdvancedRequest('get', 'AdvancedRequest', document.getElementById('lName').value, document.getElementById('journalTitle').value, document.getElementById('articleTitle').value, document.getElementById('year').value);"/></td>
    </tr>
    </table>
    <input type="button" id="advSearch" name="advSearch" value="Search" onclick="sendAdvancedRequest('get', 'AdvancedRequest', document.getElementById('lName').value, document.getElementById('journalTitle').value, document.getElementById('articleTitle').value, document.getElementById('year').value);" />
    <br />
    <div id="ajax_response2" >
        
    </div>
    </form>
    <br />
    <form name="dxdoiForm" action="DXDoiRequest" method="post" onSubmit="" target="_blank">
    Or use the DOI DX tool to go to the article:<br />
    http://dx.doi.org/<input type='text' id="dxDoi" name="dxDoi" onkeydown="if (event.keyCode == 13) document.getElementById('dxDoiSearch').click()"/>
    <br />
    <input type='submit' id="dxDoiSearch" name="dxDoiSearch" value='Locate Article' />
    </form>
    </div>
  </div>
</div>
</body>
</html>
