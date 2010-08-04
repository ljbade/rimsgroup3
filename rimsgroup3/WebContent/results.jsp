<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="main.css" type="text/css"/>
<title>RIMS Assistant - Results</title>
</head>

<body>
<div class="results">
<h3>Results</h3>

<form name="resultsForm" action="confirmation.jsp" method="post" onsubmit="">
<div class="firstSet">
<table align="left" class="resultTable">
<tr>
	<td><label for="name">Name:</label></td>
	<td><input type="text" name="name"/></td>
</tr>
<tr>
	<td><label for="staffId">Staff ID:</label></td>
	<td><input type="text" name="staffId"/></td>
</tr>
<tr>
	<td><label for="unit">Unit:</label></td>
	<td><input type="text" name="unit"/></td>
</tr>
<tr>
	<td><label for="mailCode">Mail Code:</label></td>
	<td><input type="text" name="mailCode"/></td>
</tr>
</table>

<table class="resultTable" cellpadding="5px">
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
</div>

<div class="thirdSet">
<table align="center">
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
</div>
<a href="index.jsp"><input type="button" name="back" value="Back" /></a>
<input type="submit" name="confirm" value="Confirm"/>
</form>
</div>
</body>
</html>
