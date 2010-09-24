function checkPrinting(form) {
	// check if print is checked
	var printCheck = document.getElementById('printCheck');
	if(printCheck.checked) {
		var newWin = window.open();
		newWin.document.write("<html><head><link type='text/css' href='styles/print.css' rel='stylesheet' /></head><body><table width='80%'>"); // string to hold built up print page
		// set article title and journal 
		newWin.document.write("<tr><td width='16%'><strong>Article:</strong></td><td width='84%'>" + document.getElementById('articleTitle').value + "</td></tr>");
		newWin.document.write("<tr><td><strong>Publication:</strong></td><td>" +  document.getElementById('journalTitle').value + "</td></tr>");
		newWin.document.write("<tr><td><strong>Publication Date:</strong></td><td>" + document.getElementById('year').value + "</td></tr>");
		newWin.document.write("<tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr></table>");
		
		// authors
		newWin.document.write("<table width='60%'><tr><td width='35%'><strong>Authors</strong></td><td width='35%'><strong>Affiliation</strong></td><td width='30%'><strong>Staff ID</strong></td></tr>");
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
		newWin.document.write("<tr><td><strong>Abstract Text:</strong></td><td rowspan='12'>" + document.getElementById('abstract').value + "</td></tr>"); 
		
		newWin.document.write("</table>");
		
		// journal contribution area - floats on the right
		newWin.document.write("<div class='rightFloat'>Journal Contribution<br /><table>");
		newWin.document.write("<tr><td></td><td></td></tr>");
		newWin.document.write("</table>");
		
		newWin.document.write("</body></html>");
		
		newWin.print();
	} 
}