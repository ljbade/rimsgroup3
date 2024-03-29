<%@ page  language="java" pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="styles/main.css" type="text/css"/>
<script type="text/javascript" src="scripts/resultsScript.js"></script>
<script type="text/javascript" src="scripts/ajax.js"></script>
<script type="text/javascript" src="scripts/printing.js"></script>
<script type="text/javascript" src="scripts/autocomplete.js"></script>
<script type="text/javascript" src="scripts/copy.js"></script>
<script type="text/javascript" src="clipboard/ZeroClipboard.js"></script>
<title>Proficio - Results</title>
</head>
<body>
<script type="text/javascript">
	window.onload = function() {
		parse(); // replace special characters in the title string
		var resource = document.getElementById('url').value;
		//getAbstract('get', 'AbstractText', resource); // get abstract text from the article page
	};
</script>
<div id="page">
<div class="results">
<img alt="Proficio" src="images/proficio.jpg" />
<jsp:useBean id="publication" class="nz.ac.massey.rimsgroup3.metadata.bean.Journal" scope="session" ></jsp:useBean>

<form id="resultsForm" name="resultsForm" action="CommitRequest" method="post" onSubmit="">
<input type="hidden" id="hidden" value="" />
<div class="secondSet" >
<table class="resultTable" align="center">
<tr>
<th colspan="3">Journal Contribution:</th>
</tr>
<tr>
<td><input type="radio" name="contribution" id="reviewRB" value="review article"/>Review Article</td>
<td><input type="radio" name="contribution" id="fullArticleRB" value="full article"/>Full Article in Personal Publication</td>
<td><input type="radio" name="contribution" id="editorRB" value="journal editor"/>Journal Editor</td>
</tr>
<tr>
<td><input type="radio" name="contribution" id="fullArticle2RB" value="full article in journal"/>Full Article in Journal</td>
<td><input type="radio" name="contribution" id="niscRB" value="misc"/>Editorial, Brief Communication, Letter or Note</td>
<td><input type="radio" name="contribution" id="otherRB" value="other"/>Other</td>
</tr>
</table>
<table class="resultTable" cellpadding="5px" align="center">
<tr>
<th>Research or Professsional/Community</th><th>Quality Assured?</th><th>Confidential?</th>
</tr>
<tr>
<td><input type="radio" name="research" id="researchRB" value="research"/>Research (PBRF)<br />
    <input type="radio" name="research" id="professionalRB" value="professional"/>Professional/Community
</td>
<td>
	<input type="radio" name="qa" id="qaYesRB" value="yes"/>Yes<br />
    <input type="radio" name="qa" id="qaNoRB" value="no"/>No
</td>
<td>
	<input type="radio" name="confidential" id="confidYesRB" value="yes"/>Yes<br />
    <input type="radio" name="confidential" id="confidNoRB" value="no"/>No
</td>
</tr>
</table>
</div>

<div class="firstSet">
<h2>Authors</h2> Number of authors is: <span id="authorCount">${publication.numberOfAuthors}</span>
<div id="authorDiv">
<label id="submitterLabel" style="position:absolute; left: -2000px;">Submitter</label>
	<c:forEach items="${publication.authors}" var="author" varStatus="status">
	<c:if test="${status.count==1}">
		<script>
			setCount(${publication.numberOfAuthors});
			document.getElementById('hidden').value = ${publication.numberOfAuthors};
		</script>
	</c:if>
	    <div id="${status.count}">
		    <label for="fName${status.count}">First Name:</label>
		    <input type="text" name="fName${status.count}" id="fName${status.count}" size="15" value="<c:out value="${author.firstName}" />" />
		    
		    <label for="mName${status.count}">M. Name:</label>
		    <input type="text" name="mName${status.count}" id="mName${status.count}" size="15" value="<c:out value="${author.middleName}" />" />
		    
		    <label for="lName${status.count}">Last Name:</label>
		    <input type="text" name="lName${status.count}" id="lName${status.count}" size="15" value="<c:out value="${author.lastName}" />" />
		    
		    <label for="afiliation${status.count}">Affiliation:</label>
		    <input type="text" name="affiliation${status.count}" id="affiliation${status.count}" size="15" value="<c:out value="${author.affiliation}" />" />
		    
		    <label for="id${status.count}">ID Number:</label>
		    <input type="text" name="id${status.count}" id="id${status.count}" size="15" value="<c:out value="${author.ID}" />"/>
		    
		    <input type="radio" id="submitter${status.count}" name="submitter" value="submitter" />
<!-- 	    
		    <c:if test="${status.count > 1}">
		    	<input type="button" id="moveUp${status.count}" onclick="moveUp(this.id);" value="Move Up"/>
		    </c:if>
-->
	    </div>
	</c:forEach>
</div>
<input align="right" type="button" value="Add" onClick="addNew();"/><input align="right" type="button" value="Delete" onClick="deleteIt();"/>
</div>

<div class="thirdSet">
<h2>Publication Details <span class="smallHeading">&nbsp;&nbsp;&nbsp;&nbsp;(Data retrieved from <c:out value="${publication.source}" />)</span></h2>
<table align="left" width="60%" id="publicationTable">
<tr>
<td><label for="pubID">Publication ID:</label></td>
<td><input type="text" name="pubID" id="pubID" size="65" /></td>
</tr>
<tr>
<td><label for="articleTitle">Article/Output Title:</label></td>
<td><input type="text" name="articleTitle" id="articleTitle" size="65" value="<c:out value="${publication.articleTitle}" />" />
<img src="images/copy.jpg" onclick="copyToClipboard('articleTitle');"/></td>
</tr>
<tr>
<td></td><td><label id="htmlTitle" ></label>
<img src="images/copy.jpg" onclick="copyToClipboard('htmlTitle');"/></td>
</tr>
<tr>
<td><label for="journalTitle">Journal/Publication Title:</label></td>
<td><input type="text" name="journalTitle" id="journalTitle" size="65" value="<c:out value="${publication.journalTitle}" />" />
<img src="images/copy.jpg" onclick="copyToClipboard('journalTitle');"/></td>
</tr>
<tr>
<td><label for="year">Publication Year:</label></td>
<td><input type="text" name="year" id="year" size="65" value="<c:out value="${publication.year}" />" />
<img src="images/copy.jpg" onclick="copyToClipboard('year');" /></td>
</tr>
<tr>
<td><label for="publisher">Publisher:</label></td>
<td><input type="text" name="publisher" id="publisher" size="65" autocomplete="off" onkeyup="doCompletion();" value="<c:out value="${publication.publisher}" />" />
<img src="images/copy.jpg" onclick="copyToClipboard('publisher');" /></td>
</tr>

<tr><td id="auto-row"> </td></tr>

<tr>
<td><label for="issn">ISSN:</label></td>
<td><input type="text" name="issn" id="issn" size="65" value="<c:out value="${publication.issn}" />" />
<img src="images/copy.jpg" onclick="copyToClipboard('issn');"/></td>
</tr>
<tr>
<td><label for="volume">Volume:</label></td>
<td><input type="text" name="volume" id="volume" size="23" value="<c:out value="${publication.volume}"/>" />
<img src="images/copy.jpg" onclick="copyToClipboard('volume');"/>
<label for="number">Number:&nbsp;&nbsp;&nbsp;</label>
<input type="text" name="number" id="number" size="23" value="<c:out value="${publication.issue}"/>" />
<img src="images/copy.jpg" onclick="copyToClipboard('number');"/>
</td>
</tr>
<tr>
<td>
<label for="startPage">Start Page:</label></td>
<td><input type="text" name="startPage" id="startPageNum" size="23" value="<c:out value="${publication.startPage}" />" />
<img src="images/copy.jpg" onclick="copyToClipboard('startPageNum');"/>
<label for="endPage">End Page:</label>
<input type="text" name="endPage" id="endPageNum" size="23" value="<c:out value="${publication.endPage}" />" /> 
<img src="images/copy.jpg" onclick="copyToClipboard('endPageNum');"/>
</td>
</tr>
<tr>
<td><label for="url">URL Address:</label></td>
<td><input type="text" name="url" id="url" size="53" value="<c:out value="${publication.url}" />" />
	<a  class="smallLink" target="_new" href="<c:out value="${publication.url}" />" >Follow Link</a>
<img src="images/copy.jpg" onclick="copyToClipboard('url');"/></td>
</tr>
<tr>
<td><label for="doi">DOI:</label></td>
<td><input type="text" name="doi" id="doi" size="65" value="DOI:<c:out value="${publication.doi}" />" />
<img src="images/copy.jpg" onclick="copyToClipboard('doi');" /></td>
</tr>
<tr>
<td><label for="keywords">Keywords:</label></td>
<td><input type="text" name="keywords" id="keywords" size="65"  value="<c:out value="${publication.keyWords}" />" />
<img src="images/copy.jpg" /></td>
</tr>
</table>

<div>
<div id="abstract_progress"></div>
<table class="abstract">
<tr>
<td>
<label for="abstract">Abstract:</label>
</td>
<td>
    <textarea name="abstract" id="abstract" rows="15" cols="45" onKeyUp="wordCount(this.value);">
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
</div>
<div>
<a href="index.jsp"><input type="button" name="back" value="Back" /></a>
<input type="submit" name="confirm" value="Confirm" onclick="checkPrinting(document.getElementById('resultsForm'));" />
<input type="checkbox" value="print" name="printCheck" id="printCheck" /><label for="printCheck">Print</label>
</div>

</form>
<div class="menu-popup" id="menu-popup">
<table class="completeTable" id="completeTable" border="1" bordercolor="black" cellpadding="0" cellspacing="0"> </table>
</div>


</div>
<script type="text/javascript">
	alignSubmitter();
</script>
</div>
</body>
</html>
