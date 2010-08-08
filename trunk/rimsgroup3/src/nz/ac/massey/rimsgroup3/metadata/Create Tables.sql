/* create_tables.sql                                  	*/
/* This script file creates the following tables:	*/
/* EDITORS, BOOK, CONFERENCE, JOURNAL, PUBLISHED, AUTHOR, PUBLICATION	*/
/* Subject to change via customers needs */
/* some variables can be narrowed later */

DROP TABLE if exists EDITORS;
DROP TABLE if exists BOOK;
DROP TABLE if exists CONFERENCE;
DROP TABLE if exists JOURNAL;
DROP TABLE if exists PUBLISHED;
DROP TABLE if exists AUTHOR;
DROP TABLE if exists PUBLICATION;


CREATE TABLE AUTHOR (
AUTHOR_ID 		INTEGER 	PRIMARY KEY,
FIRST_NAME		VARCHAR(20)	NOT NULL,
LAST_NAME		VARCHAR(20)	NOT NULL,
MIDDLE_NAME		VARCHAR(20)			,
TYPE			VARCHAR(15)         ,
DEPARTMENT		VARCHAR(255)		,
COLLEGE			VARCHAR(255)		,
EMAIL			VARCHAR(255)		
);


CREATE TABLE PUBLICATION (
PUBLICATION_ID			INTEGER 		PRIMARY KEY,
PUBLICATION_CATEGORY	VARCHAR(255) 	NOT NULL,
PUBLISHER				VARCHAR(255)	NOT NULL,
PUBLICATION_YEAR 		DATE			NOT NULL,
START_PAGE				INTEGER			NOT NULL,	
END_PAGE				INTEGER			NOT NULL,
ISSN_ISBN				VARCHAR(25)		NOT NULL,	
URL_LOCATION			VARCHAR(255)	NOT NULL,
QUALITY_ASSURED			TINYINT(1)		NOT NULL,
ABSTRACT				VARCHAR(255)	NOT NULL,
KEYWORDS				VARCHAR(255)	NOT NULL	
);

CREATE TABLE PUBLISHED (
PUBLICATION_ID		INTEGER		NOT NULL,
AUTHOR_ID			INTEGER		NOT NULL,
PRIMARY KEY (PUBLICATION_ID, AUTHOR_ID),
FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHOR(AUTHOR_ID),
FOREIGN KEY (PUBLICATION_ID) REFERENCES PUBLICATION(PUBLICATION_ID)
);


CREATE TABLE JOURNAL (
PUBLICATION_ID		INTEGER			PRIMARY KEY,
ARTICLE_TITLE		VARCHAR(255)	NOT NULL,
JOURNAL_TITLE 		VARCHAR(255)	NOT NULL,
VOLUME_NO			INTEGER			NOT NULL,
ISSUE_NO			INTEGER			NOT NULL,
FOREIGN KEY (PUBLICATION_ID) REFERENCES PUBLICATION(PUBLICATION_ID)
);

CREATE TABLE CONFERENCE (
PUBLICATION_ID		INTEGER			PRIMARY KEY,
ABSTRACT_TITLE		VARCHAR(255)	NOT NULL,
CONFERENCE_NAME		VARCHAR(255)	NOT NULL,
START_DATE			DATE			NOT NULL,
END_DATE			DATE			NOT NULL,
LOCATION			VARCHAR(255)	NOT NULL,
FOREIGN KEY (PUBLICATION_ID) REFERENCES PUBLICATION(PUBLICATION_ID)
);


CREATE TABLE BOOK (
PUBLICATION_ID		INTEGER			PRIMARY KEY,
PLACE_PUBLISHED		VARCHAR(255)	NOT NULL,
CHAPTER_TITLE		VARCHAR(255)	NOT NULL,
BOOK_TITLE			VARCHAR(255)	NOT NULL
);


CREATE TABLE EDITORS (
EDITOR_ID			INTEGER		PRIMARY KEY,
EDITOR_FIRST_NAME	VARCHAR(20)	NOT NULL,
EDITOR_MIDDLE_NAME	VARCHAR(20)	NOT NULL,
EDITOR_LAST_NAME	VARCHAR(20)	NOT NULL,
PUBLICATION_ID		INTEGER		NOT NULL,
FOREIGN KEY (PUBLICATION_ID) REFERENCES BOOK(PUBLICATION_ID)
);
