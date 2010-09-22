<%@ page  language="java" pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="styles/main.css" type="text/css"/>
<script type="text/javascript" src="scripts/resultsScript.js">
</script>
<title>RIMS Assistant - Results</title>
</head>
<body>
<div class="results">
<h3>Results</h3>
<jsp:useBean id="publication" class="nz.ac.massey.rimsgroup3.metadata.bean.Journal" scope="session" ></jsp:useBean>

<form name="resultsForm" method="post">
<div id="mainContainer" class="firstSet">
<div>
<label for="topAuthor">Author:</label><input type="text" id="topAuthor" />
<label for="staffID">Staff ID:</label><input type="text" id="staffID" />
<label for="unit">Unit:</label><input type="text" id="unit" />
<label for="mailCode">Mail Code:</label><input type="text" id="mailCode" /> 
</div>
</div>
<div class="secondSet" >
<table class="resultTable" align="center">
<tr>
<th colspan="3">Journal Contribution:</th>
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
<th>Research or Professsional/Community</th><th>Quality Assured?</th><th>Confidential?</th>
</tr>
<tr>
<td><input type="radio" name="research" value="research"/>Research (PBRF)<br/>
    <input type="radio" name="research" value="professional"/>Professional/Community
</td>
<td>
	<input type="radio" name="qa" value="yes"/>Yes<br/>
    <input type="radio" name="qa" value="no"/>No
</td>
<td>
	<input type="radio" name="confidential" value="yes"/>Yes<br/>
    <input type="radio" name="confidential" value="no"/>No
</td>
</tr>
</table>
</div>

<div class="firstSet">
<div id="authorDiv" align="left">
	<c:forEach items="${publication.authors}" var="author" varStatus="status">
	<c:if test="${status.count==1}">
		<script>
			setCount("${publication.numberOfAuthors}");
		</script>
	</c:if>
	    <div id="${status.count}">
		    <label for="fName">First Name:</label>
		    <input type="text" name="fName${status.count}" size="15" value="<c:out value="${author.firstName}" />" />
		    <label for="mName">M. Name:</label>
		    <input type="text" name="mName${status.count}" size="15" value="<c:out value="${author.middleName}" />" />
		    <label for="lName">Last Name:</label>
		    <input type="text" name="lName${status.count}" size="15" value="<c:out value="${author.lastName}" />" />
		    <label for="afilliation${status.count}">Affiliation:</label>
		    <input type="text" name="affiliation" size="15" />
		    <label for="id${status.count}">ID Number:</label>
		    <input type="text" name="id${status.count}" size="15" />
	    
		    <c:if test="${status.count > 1}">
		    	<input type="button" onClick="moveUp();" value="Move Up"/>
		    </c:if>
	    </div>
	</c:forEach>
	
</div>
<input align="right" type="button" value="Add" onClick="addNew();"/><input align="right" type="button" value="Delete" onClick="deleteIt();"/>
</div>

<div class="thirdSet">
<table align="left">
<tr>
<td><label for="articleTitle">Article/Output Title:</label></td>
<td><input type="text" name="articleTitle" size="65" value="<c:out value="${publication.articleTitle}" />" /></td>
</tr>
<tr>
<td><label for="journalTitle">Journal/Publication Title:</label></td>
<td><input type="text" name="journalTitle" size="65" value="<c:out value="${publication.journalTitle}" />" /></td>
</tr>
<tr>
<td><label for="year">Publication Year:</label></td>
<td><input type="text" name="year" size="65" value="<c:out value="${publication.year}" />" /> </td>
</tr>
<tr>
<td><label for="publisher">Publisher:</label></td>
<td><input type="text" name="publisher" size="65" /></td>
</tr>
<tr>
<td><label for="issn">ISSN:</label></td>
<td><input type="text" name="issn" size="65" value="<c:out value="${publication.issn}" />" /></td>
</tr>
<tr>
<td><label for="volume">Volume/Number:</label></td>
<td><input type="text" name="volume" size="65" value="<c:out value="${publication.volume}"/>" /></td>
</tr>
<tr>
<td><label for="pageNum">Page numbers:</label></td>
<td><input type="text" name="pageNum" size="65" value="<c:out value="${publication.startPage}-${publication.endPage}" />" /> </td>
</tr>
<tr>
<td><label for="url">URL Address:</label></td>
<td><input type="text" name="url" size="45" value="<c:out value="${publication.url}" />" />
	<a  class="smallLink" target="_new" href="<c:out value="${publication.url}" />" >Follow Link</a>
</td>
</tr>
<tr>
<td><label for="doi">DOI:</label></td>
<td><input type="text" name="doi" size="65" value="<c:out value="${publication.doi}" />" /></td>
</tr>
<tr>
<td><label for="keywords">Keywords:</label></td>
<td><input type="text" name="keywords" size="65"  value="<c:out value="${publication.keyWords}" />"/></td>
</tr>
</table>
<table class="abstract">
<tr>
<td>
<label for="abstract">Abstract:</label>
</td>
<td>
    <textarea name="abstract" rows="15" cols="45" onKeyUp="wordCount(this.value);">
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
<div>
<a href="index.jsp"><input type="button" name="back" value="Back" /></a>
<input type="submit" name="confirm" value="Confirm"/>
<input type="checkbox" value="print" name="printCheck"/><label for="printCheck">Print</label>
</div>
</form>
</div>
</body>
</html>
