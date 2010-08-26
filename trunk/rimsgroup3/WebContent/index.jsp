<%@ page  language="java" import="java.util.*" session="true" pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="main.css" type="text/css"/>
<title>RIMS Assistant - Home</title>
<script type="text/javascript" src="resultsScript.js"></script>
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
<div id="pageDiv">

<h2>RIMS Assistant</h2>
<form name="searchForm" action="DoiRequest" method="post" onSubmit=""> 
<table width="280px">
<tr>
	<td>10.1016/j.tcs.2010.07.007  -  multiple authors</td>
</tr>
<tr>
	<td>10.3998/3336451.0009.101  -  1 author</td>
</tr>
<tr>
<td>
<input type="text" name="search" size="30"/>
</td>
</tr>
<tr>
<td>
<input type="submit" name="new" value="New Article"/><input type="submit" name="search" value="Search"/>
</td>
</tr>

</table>
<c:if test="${param.success == null }">
</c:if>
<c:if test="${param.success == 'no'}">
	DOI search failed.		
</c:if>
</form>

<p><a href="#" onClick="showhide('div1');">Advanced Search</a></p> 
<div id="div1" style="display: none;">
<form name="advancedForm" action="AdvancedRequest" method="post" onSubmit=""> 
<table>
<tr>
<td><label for="authors">Primary Author:</label></td>
<td><input type="text" name="authors"/></td>
</tr>
<tr>
<td><label for="fName">First Name:</label></td>
<td><input type="text" name="fName" /></td>
</tr>
<tr>
<td><label for="lName">Last Name:</label></td>
<td><input type="text" name="lName" /></td>
</tr>
<tr>
<td><label for="articleTitle">Article/Output Title:</label></td>
<td><input type="text" name="articleTitle" /></td>
</tr>
<tr>
<td><label for="journalTitle">Journal/Publication Title:</label></td>
<td><input type="text" name="journalTitle" /></td>
</tr>
<tr>
<td><label for="year">Publication Year:</label></td>
<td><input type="text" name="year" /></td>
</tr>
<tr>
<td><label for="publisher">Publisher:</label></td>
<td><input type="text" name="publisher" /></td>
</tr>
<tr>
<td><label for="issn">ISSN:</label></td>
<td><input type="text" name="issn" /></td>
</tr>
</table>

<input type="submit" name="advSearch" value="Search"/>
</form>
</div>
</div>
</body>
</html>
