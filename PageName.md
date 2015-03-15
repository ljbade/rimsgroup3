How to use the CrossRef auuthor search tool

# Introduction #

For our purposes we want the CrossRef tool to return an XML response rather than redirect to the article's location (which is what it is designed to do)


# Details #

To search without a DOI the crossRef API supplies another service with the following format:

http://www.crossref.org/openurl?pid=email&aulast=&title=&volume=&issn=&spage=&date=

Where :
email = complementary CrossRef account email
aulast = primary author's last name
title = journal title where article appears
spage = start page
date = the year of publication

The problem here is that the service is designed to redirect to the location of the resource (e.g. Supply enough infomartion and you will be taken to the article). If the result set returns multiple hits an XML response is generated that includes the DOI of each matching result.

An example:

No result found for:
pid=s\_allannz@yahoo.com&aulast=Morozova&title=%20Biophysical%20Journal

pid=s\_allannz@yahoo.com&aulast=Morozova&title=%20Biophysical%20Journal
&volume=99

Partial response for:   pid=s\_allannz@yahoo.com&aulast=Morozova&title=%20Biophysical%20Journal&date=2010  (which gives us the DOI in the response so this is what we want)

Redirected to article:
http://www.crossref.org/openurl/?pid=s_allannz@yahoo.com&aulast=Morozova&title=%20Biophysical%20Journal&volume=99&date=2010




To search for DOI based on author's name, it seems the journal title and date (year) will need to be supplied as well which will generate a useable XML response (this worked for 7 articles I tried).
Too much data being sent in the query (start page,volume, issue, issn) almost always cause a redirect to the article's location which is not as useful.