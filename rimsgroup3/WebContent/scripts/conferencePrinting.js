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
				"<div class='contribution'><b>Conference Contribution</b><br /><table class='topTable'>");
		
		if(document.getElementById('abstractRB').checked) {
			newWin.document.write("<tr><td><input type='radio' id='abstractRB' checked='true' >Abstract in Published Proceedings</input></td>");
		} else {
			newWin.document.write("<tr><td><input type='radio' id='abstractRB'>Abstract in Published Proceedings</input></td>");
		}
		if (document.getElementById('proceedingsRB').checked) {
			newWin.document.write("<td><input type='radio' id='proceedingsRB' checked='true'>Proceedings Editor</input></td>");
		} else {
			newWin.document.write("<td><input type='radio' id='proceedingsRB'>Proceedings Editor</input></td>");
		}
		if(document.getElementById('fullPaperRB').checked){
			newWin.document.write("<td><input type='radio' id='fullPaperRB' checked='true'>Full Paper in Published Proceedings</input></td></tr>");
		} else {
			newWin.document.write("<td><input type='radio' id='fullPaperRB'>Full Paper in Published Proceedings</input></td></tr>");
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
		newWin.document.write("<tr><td width='25%'><strong>Title of Abstract/Paper:</strong></td><td width='75%'>" + document.getElementById('abstractTitle').value + "</td></tr>");
		newWin.document.write("<tr><td><strong>Conference name/proceedings title::</strong></td><td>" +  document.getElementById('conferenceName').value + "</td></tr>");
		newWin.document.write("<tr><td><strong>Location:</strong></td><td>" + document.getElementById('location').value + "</td></tr>");
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
			var submitter = "submitter" + i;
			if(document.getElementById(submitter).checked) {
				newWin.document.write("<tr><td><b>" + document.getElementById(first).value + "  " + document.getElementById(middle).value); 
				newWin.document.write(" " + document.getElementById(last).value + "  (submitter)</b></td><td>" + document.getElementById(affil).value + "</td><td>" + document.getElementById(id).value + "</td></tr>");				
			} else {
				newWin.document.write("<tr><td>" + document.getElementById(first).value + "  " + document.getElementById(middle).value); 
				newWin.document.write(" " + document.getElementById(last).value + "</td><td>" + document.getElementById(affil).value + "</td><td>" + document.getElementById(id).value + "</td></tr>");
			}
		}
		newWin.document.write("<tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr></table>");
		
		// article details
		newWin.document.write("<table>");
		newWin.document.write("<tr><td><strong>Editors:</strong></td><td>" + document.getElementById('editors').value + "</td></tr>");
		newWin.document.write("<tr><td><strong>Start Date:</strong></td><td>" + document.getElementById('startDate').value + "</td></tr>");
		newWin.document.write("<tr><td><strong>End Date:</strong></td><td>" + document.getElementById('endDate').value + "</td></tr>");
		newWin.document.write("<tr><td><strong>ISSN/ISBN:</strong></td><td>" + document.getElementById('issn').value + "</td></tr>");
		newWin.document.write("<tr><td><strong>Start Page:</strong></td><td>" + document.getElementById('startPageNum').value + "</td></tr>");
		newWin.document.write("<tr><td><strong>End Page:</strong></td><td>" + document.getElementById('endPageNum').value + "</td></tr>");
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