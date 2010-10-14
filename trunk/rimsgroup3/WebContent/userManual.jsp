<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="styles/main.css" type="text/css"/>
<title>Proficio - User Manual</title>
</head>
<body>

<div class="results">
<a name="contents" href="index.jsp"><img alt="Proficio" src="images/proficio.jpg" style="border:none;"/></a>
 <div class="secondSet" >	
 <h3> Contents  </h3> 
	<ol>
		<li><a href="#userguide">User Guide</a> </li>
		<li><a href="#faqs">FAQs</a></li>
		<li><a href="#glossary">Glossary Of Terms</a> </li>
	</ol>
<br/>
<a href="index.jsp">Back to DOI search page</a>
</div>

 <div class="secondSet" >	
	<h3> <a name="userguide"> 1. User Guide: </a></h3> 
 	<h4> Basic Search </h4>
 	<img alt="index page" src="images/index.jpg" />
 	<ul>
   	<li>Enter the DOI into the text box and click the Search button to search
   	 for the information regarding the DOI. <br/> <br/> </li>
   	 
   	 <li>Please wait while Proficio finds the article.</li>
   	</ul>
   	
   	<h4> Results Returned</h4>
   	<img alt="results page" src="images/results.jpg" />
   	<ul>
   	<li>Use the information gathered by the Proficio to fill in the details in the RIMS system. <br/> 
   	This can be done by highlighting the relevant fields text, then copying (Ctrl+C) and pasting 
   	(Ctrl+V) the text into corresponding text field in the RIMS system.<br/> 
   	<br/> </li>
   	<li>Any information not retrieved from the Proficio will have to be filled out manually.  <br/> 
 	Click the new tab that has appeared to access the remaining information. Alternatively click
   	the Follow Link link <br/> 
   	<br/>  </li>
   	<li>If you wish to print, set the check box to print.<br/> Fill out any blank text fields and 
   	click the appropriate radio buttons in Proficio, that are relevant to information that is required
   	for printing (any information left blank will not be printed). <br/> 
   	<br/> </li>
   	<li>Publication ID is the RIMS system ID number after committing the article to the RIMS system. <br/>
   	This information can only be gathered from the RIMS system. <br/> 
   	<br/> </li>
   	<li>Click the Confirm button.<br/> 
   	<br/> </li>
   	<li>Now you are done! Any information in the DOI text box and the Authors text boxes has been saved 
   	to the database, The DOI has been saved to prevent anyone from duplicating the work, and the 
   	if the same author has any more publications all the details will be returned next time.
   	</li>
   	</ul>
   	
   	<h4> Advanced Search</h4>
   	<img alt="index page with advanced search" src="images/indexAdvanced.jpg" />
   	<ul>
	<li> If for whatever reason you are not supplied with a DOI, click the  Advanced Search link  <br/> 
	 <br/>  </li> 
	 <li> Fill out the all the appropriate fields, click the bottom Search button  <br/>
	 <br/> </li>
	 <li> If the application somehow fails please use the DX DOI tool to locate the article.  <br/>
	 Enter in the DOI and click the Locate Article button. <br/> 
	 We apologise for the inconvenience  that was caused. </li>
	</ul>
	<div class="rightAligned" > <p> <a href="#contents"> Top Of Page</a> <br/> <br/> </p></div>
	<br/>
<a href="index.jsp">Back to DOI search page</a>
 </div>
 <div class="secondSet">
 	<h3> <a name="faqs"> 2. FAQs: </a></h3>
 	<div class="rightAligned" > <p> <a href="#contents"> Top Of Page</a> </p>  </div>
 	<div style="text-align: left; margin: 15px;">
 	<b>I got an error about an email or devloper key being missing.</b><br />
 	The APIs used by Proficio usually require registered emails or developer keys to work correctly. This is generally handled
 	by the devloper but can be registered by the Proficio users (your system administrator
 	 will need to do this).
 	 <br /><br /><br />
 	 <b>I got an error about a database not being available.</b><br />
 	 Proficio uses a MySql database to record details relating to staff members and DOIs that have been committed
 	 using the program. If a database is not available these details wil be missing and Proficio will not be able to offer
 	 all of its functionality. Please contact your system administrator.
 	</div>
 </div>
 <div class="secondSet" >
 	<h3> <a name="glossary"> 3. Glossary of terms: </a></h3>
 	<div class="rightAligned" > <p> <a href="#contents"> Top Of Page</a> </p> </div>
  	<img alt="description of browsers" src="images/describing.jpg" />	
  	<table class="description" align="center">
  	<tr> <td>1. Browser </td>				<td>&nbsp</td> <td>2. Tabs </td> </tr> 
  	<tr> <td>3. Application - Proficio </td><td>&nbsp</td> <td>4. Radio Button </td> </tr> 
  	<tr> <td>5. Text Box </td>				<td>&nbsp</td> <td>6. Button </td> </tr> 
  	<tr> <td>7. Link </td>					<td>&nbsp</td> <td>8. Check Box </td> </tr> 
  	</table>
  	<div class="rightAligned" > <p> <a href="#contents"> Top Of Page</a> <br/> <br/> </p> </div>
  	<br/>
<a href="index.jsp">Back to DOI search page</a>
 </div>
</div>
</body>
</html>