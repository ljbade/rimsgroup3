<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="styles/main.css" type="text/css"/>
<script type="text/javascript" src="scripts/resultsScript.js"></script>
<title>RIMS Assisstant - Results</title>
</head>
<body>

<div class="results">
<h3>Results</h3>

<form name="resultsForm" action="confirmation.jsp" method="post" onSubmit="">
<div class="firstSet">
<div id="mainContainer">
	<div><label for="author0">Author:</label><input type="text" id="author0">
       	 <label for="staffID0">Staff ID:</label><input type="text" id="staffID0">
       	 <label for="unit0">Unit:</label><input type="text" id="unit0">
      	 <label for="mailCode0">Mail Code:</label><input type="text" id="mailCode0">
    </div>
</div>
<table class="resultTable" align="center">
<tr>
	<td><label for="placePublished">Place Published:</label></td>
	<td><input type="text" name="placePublished"/></td>
</tr>
<tr>
	<td><label for="chapterTitle">Chapter Title:</label></td>
	<td><input type="text" name="chapterTitle"/></td>
</tr>
<tr>
	<td><label for="bookTitle">Book Title:</label></td>
	<td><input type="text" name="bookTitle"/></td>
</tr>
<tr>
	<td><label for="editors">Editors:</label></td>
	<td><input type="text" name="editors"/></td>
</tr>
</table>
</div>

<div class="thirdSet">
<table align="left">
<tr>
<td><label for="authors">Authors:</label></td>
<td><input type="text" name="authors" size="40" value="<c:out value="${doiObj.authorsStr}" />" /></td>
</tr>
<tr>
<td><label for="articleTitle">Article/Output Title:</label></td>
<td><input type="text" name="articleTitle" size="40" value="<c:out value="${doiObj.title}" />" /></td>
</tr>
<tr>
<td><label for="journalTitle">Journal/Publication Title:</label></td>
<td><input type="text" name="journalTitle" size="40" value="<c:out value="${doiObj.journal}" />" /></td>
</tr>
<tr>
<td><label for="year">Publication Year:</label></td>
<td><input type="text" name="year" size="40" value="<c:out value="${doiObj.year}" />" /> </td>
</tr>
<tr>
<td><label for="publisher">Publisher:</label></td>
<td><input type="text" name="publisher" size="40" /></td>
</tr>
<tr>
<td><label for="issn">ISSN:</label></td>
<td><input type="text" name="issn" size="40" value="<c:out value="${doiObj.issn}" />" /></td>
</tr>
<tr>
<td><label for="volume">Volume/Number:</label></td>
<td><input type="text" name="volume" size="40" value="<c:out value="${doiObj.volume}"/>" /></td>
</tr>
<tr>
<td><label for="pageNum">Page numbers:</label></td>
<td><input type="text" name="pageNum" size="40" value="<c:out value="${doiObj.startPage}" />" /> </td>
</tr>
<tr>
<td><label for="url">URL Address:</label></td>
<td><input type="text" name="url" size="40" value="<c:out value="${doiObj.resource}" />" />
	<a  class="smallLink" href="<c:out value="${doiObj.resource}" />" >Follow Link</a>
</td>
</tr>
<tr>
<td><label for="doi">DOI:</label></td>
<td><input type="text" name="doi" size="40" value="<c:out value="${doiObj.doi}" />" /></td>
</tr>
<tr>
<td><label for="keywords">Keywords:</label></td>
<td><input type="text" name="keywords" /></td>
</tr>
</table>
<table>
<tr>
<td>
<label for="abstract">Abstract:</label>
</td>
<td>
<textarea name="abstract" rows="15" cols="40">
</textarea>
</td>
</tr>
</table>
</div>
<a href="index.jsp"><input type="button" name="back" value="Back" /></a>
<input type="submit" name="confirm" value="Confirm"/>
</form>
</div>
</body>
</html>