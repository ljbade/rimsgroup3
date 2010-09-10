<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="styles/main.css" type="text/css"/>
<title>RIMS Assistant - Results</title>
</head>

<body>
<div class="results">
<h3>Results</h3>

<form name="confirmationForm" action="printExport.jsp" method="post" onsubmit="">
<div class="firstSet">
<table align="left" class="resultTable">
<tr>
	<td width="68"><label for="name">Name:</label></td>
	<td width="235">&nbsp;</td>
</tr>
<tr>
	<td><label for="staffId">Staff ID:</label></td>
	<td>&nbsp;</td>
</tr>
<tr>
	<td><label for="unit">Unit:</label></td>
	<td>&nbsp;</td>
</tr>
<tr>
	<td><label for="mailCode">Mail Code:</label></td>
	<td>&nbsp;</td>
</tr>
</table>

<table class="resultTable" cellpadding="5px">
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
<div class="secondSet">
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
</div>

<div class="thirdSet">
<table align="center">
<tr>
<td><label for="authors">Authors:</label></td>
<td>&nbsp;</td>
</tr>
<tr>
<td><label for="articleTitle">Article/Output Title:</label></td>
<td>&nbsp;</td>
</tr>
<tr>
<td><label for="journalTitle">Journal/Publication Title:</label></td>
<td>&nbsp;</td>
</tr>
<tr>
<td><label for="year">Publication Year:</label></td>
<td>&nbsp;</td>
</tr>
<tr>
<td><label for="publisher">Publisher:</label></td>
<td>&nbsp;</td>
</tr>
<tr>
<td><label for="issn">ISSN:</label></td>
<td>&nbsp;</td>
</tr>
<tr>
<td><label for="volume">Volume/Number:</label></td>
<td>&nbsp;</td>
</tr>
<tr>
<td><label for="pageNum">Page numbers:</label></td>
<td>&nbsp;</td>
</tr>
<tr>
<td><label for="url">URL Address:</label></td>
<td>&nbsp;</td>
</tr>
</table>
</div>
<a href="results.jsp"><input type="button" name="back" value="Back" /></a>
<a href="printExport.jsp"><input type="submit" name="confirm" value="Confirm" /></a>
</form>
</div>
</body>
</html>
