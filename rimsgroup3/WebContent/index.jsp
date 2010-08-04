<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="main.css" type="text/css"/>
<title>RIMS Assistant - Home</title>
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
<form name="searchForm" action="results.jsp" method="post" onsubmit=""> 
<table width="280px">
<tr>
<td>
<input type="text" name="searchBox" width="280px"/>
</td>
</tr>
<tr>
<td>
<input type="submit" name"new" value="New Article"/><input type="submit" name="search" value="Search"/>
</td>
</tr>
</table>
</form>

<p><a href="#" onclick="showhide('div1');">Advanced Search</a></p> 
<div id="div1" style="display: none;">
<form>
<table>
<tr>
<td><label for="authors">Authors:</label></td>
<td><input type="text" name="authors" /></td>
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
<tr>
<td><label for="volume">Volume/Number:</label></td>
<td><input type="text" name="volume" /></td>
</tr>
<tr>
<td><label for="pageNum">Page numbers:</label></td>
<td><input type="text" name="pageNum" /></td>
</tr>
<tr>
<td><label for="url">URL Address:</label></td>
<td><input type="text" name="url" /></td>
</tr>
</table>

<input type="button" name="advSearch" value="Search"/>
</form>
</div>

</body>
</html>
