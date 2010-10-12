function checkPrinting(form) {
	// check if print is checked
	var printCheck = document.getElementById('printCheck');
	if(printCheck.checked) {
		var newWin = window.open();
		newWin.document.write('<html><head><link type="text/css" href="styles/print.css" rel="stylesheet" /></head><body>');
		newWin.document.write("<img src='images/logo.gif'><br /><br/>");
		
		// journal contribution area
		// find contribution radio button that was ticked
		newWin.document.write("" +
				"<div class='contribution'><b>Journal Contribution</b><br /><table class='topTable'>");
		
		if(document.getElementById('reviewRB').checked) {
			newWin.document.write("<tr><td><input type='radio' id='reviewRB' checked='true' >Review Article</input></td>");
		} else {
			newWin.document.write("<tr><td><input type='radio' id='reviewRB'>Review Article</input></td>");
		}
		if (document.getElementById('fullArticleRB').checked) {
			newWin.document.write("<td><input type='radio' id='fullArticleRB' checked='true'>Full Article in Personal Publication</input></td>");
		} else {
			newWin.document.write("<td><input type='radio' id='fullArticleRB'>Full Article in Personal Publication</input></td>");
		}
		if(document.getElementById('editorRB').checked){
			newWin.document.write("<td><input type='radio' id='editorRB' checked='true'>Journal Editor</input></td></tr>");
		} else {
			newWin.document.write("<td><input type='radio' id='editorRB'>Journal Editor</input></td></tr>");
		}
		if(document.getElementById('fullArticle2RB').checked) {
			newWin.document.write("<tr><td><input type='radio' id='fullArticle2RB' checked='true'>Full Article in Journal</input></td>");
		} else {
			newWin.document.write("<tr><td><input type='radio' id='fullArticle2RB'>Full Article in Journal</input></td>");
		}
		if (document.getElementById('niscRB').checked) {
			newWin.document.write("<td><input type='radio' id='niscRB' checked='true'>Editorial, Brief Communication, Letter or Note</input></td>");
		} else {
			newWin.document.write("<td><input type='radio' id='niscRB'>Editorial, Brief Communication, Letter or Note</input></td>");
		}
		if(document.getElementById('otherRB').checked){
			newWin.document.write("<td><input type='radio' id='otherRB' checked='true'>Other</input></td></tr>");
		} else {
			newWin.document.write("<td><input type='radio' id='otherRB'>Other</input></td></tr>");
		}
		// blank row
		newWin.document.write("<tr><td>&nbsp;</td></tr>");
		// research or professional community
		if(document.getElementById('researchRB').checked){
			newWin.document.write("<tr><td>Research or Professional:</td><td><input type='radio' name='research' id='researchRB' checked='true'>Research (PBRF)</input></td>");	
			newWin.document.write("<td><input type='radio' name='research' id='professionalRB' >Professional/Community</input></td></tr>");
		} else if(document.getElementById('professionalRB').checked) {
			newWin.document.write("<tr><td>Research or Professional:</td><td><input type='radio' name='research' id='researchRB' >Research (PBRF)</input></td>");
			newWin.document.write("<td><input type='radio' name='research' id='professionalRB' checked='true'>Professional/Community</input></td></tr>");
		} else {
			newWin.document.write("<tr><td>Research or Professional:</td><td><input type='radio' name='research' id='researchRB' >Research (PBRF)</input></td>");
			newWin.document.write("<td><input type='radio' name='research' id='professionalRB' >Professional/Community</input></td></tr>");
		}
		newWin.document.write("<tr><td>Quality Assured:</td>");
		if(document.getElementById('qaYesRB').checked) {
			newWin.document.write("<td><input type='radio' name='qa' id='qaYesRB' checked='true' >Yes</input></td>");
			newWin.document.write("<td><input type='radio' name='qa' id='qaNoRB' >No</input></td></tr>");
		} else if(document.getElementById('qaNoRB').checked){
			newWin.document.write("<td><input type='radio' name='qa' id='qaYesRB' >Yes</input></td>");
			newWin.document.write("<td><input type='radio' name='qa' id='qaNoRB' checked='true'>No</input></td></tr>");
		} else {
			newWin.document.write("<td><input type='radio' name='qa' id='qaYesRB' >Yes</input></td>");
			newWin.document.write("<td><input type='radio' name='qa' id='qaNoRB'>No</input></td></tr>");
		}
		if(document.getElementById('confidYesRB').checked){
			newWin.document.write("<tr><td>Confidential:</td><td><input type='radio' name='confidential' id='confidYesRB' checked='true'>Yes</input></td>");	
			newWin.document.write("<td><input type='radio' name='confidential' id='confidNoRB' >No</input></td></tr>");
		} else if(document.getElementById('confidNoRB').checked){
			newWin.document.write("<tr><td>Confidential:</td><td><input type='radio' name='confidential' id='confidYesRB' >Yes</input></td>");
			newWin.document.write("<td><input type='radio' name='confidential' id='confidNoRB' checked='true'>No</input></td></tr>");
		} else {
			newWin.document.write("<tr><td>Confidential:</td><td><input type='radio' name='confidential' id='confidYesRB' >Yes</input></td>");
			newWin.document.write("<td><input type='radio' name='confidential' id='confidNoRB' >No</input></td></tr>");
		}
		newWin.document.write("</table></div>");
		// blank row
		newWin.document.write("<tr><td>&nbsp;</td></tr>");
		// set article title and journal 
		newWin.document.write("<div><table width='80%'>"); 
		newWin.document.write("<tr><td width='25%'><strong>Article:</strong></td><td width='75%'>" + document.getElementById('articleTitle').value + "</td></tr>");
		newWin.document.write("<tr><td><strong>Publication:</strong></td><td>" +  document.getElementById('journalTitle').value + "</td></tr>");
		newWin.document.write("<tr><td><strong>Publication Date:</strong></td><td>" + document.getElementById('year').value + "</td></tr>");
		newWin.document.write("<tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr></table></div>");
		
		// authors section
		newWin.document.write("<div><table width='60%'><tr><td width='35%'><strong>Authors</strong></td><td width='35%'><strong>Affiliation</strong></td><td width='30%'><strong>Staff ID</strong></td></tr>");
		var authorsCount = document.getElementById('hidden').value;
		for(i = 1; i <= authorsCount; i++) {
			var first = "fName" + i;			
			var middle = "mName" + i;
			var last = "lName" + i;
			var affil = "affiliation" + i;
			var id = "id" + i;
			newWin.document.write("<tr><td>" + document.getElementById(first).value + "  " + document.getElementById(middle).value); 
			newWin.document.write(" " + document.getElementById(last).value + "</td><td>" + document.getElementById(affil).value + "</td><td>" + document.getElementById(id).value + "</td></tr>");
		}
		newWin.document.write("<tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr></table>");
		
		// article details
		newWin.document.write("<table>");
		newWin.document.write("<tr><td><strong>ISSN/ISBN:</strong></td><td>" + document.getElementById('issn').value + "</td></tr>");
		newWin.document.write("<tr><td><strong>Volume:</strong></td><td>" + document.getElementById('volume').value + "</td></tr>");
		newWin.document.write("<tr><td><strong>Pages:</strong></td><td>" + document.getElementById('pageNum').value + "</td></tr>");
		newWin.document.write("<tr><td><strong>URL:</strong></td><td>" + document.getElementById('url').value + "</td></tr>");
		newWin.document.write("<tr><td><strong>DOI:</strong></td><td>" + document.getElementById('doi').value + "</td></tr>");
		newWin.document.write("<tr><td>&nbsp;</td><td>");
		newWin.document.write("<tr><td><strong>Key Words:</strong></td><td rowspan='12'>" + document.getElementById('keywords').value + "</td></tr>"); 
		newWin.document.write("<tr><td>&nbsp;</td><td>");
		newWin.document.write("<tr><td><strong>Abstract Text:</strong></td><td rowspan='12'>" + document.getElementById('abstract').value + "</td></tr>"); 
		
		newWin.document.write("</table></div>");
		
		
		
		newWin.document.write("</body></html>");
		newWin.document.close();
		newWin.print();
	} 
}