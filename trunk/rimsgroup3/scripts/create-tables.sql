/* create-tables.sql                                  	*/
/* This script file creates the following tables:	*/
/* MISC_AUTHOR, MASSEY_AUTHOR, PUBLICATION	*/
/* Subject to change via customers needs */
/* some variables can be narrowed later */

DROP TABLE if exists MISC_AUTHOR;
DROP TABLE if exists MASSEY_AUTHOR;
DROP TABLE if exists PUBLICATION;


CREATE TABLE MASSEY_AUTHOR (
MASSEY_ID 			VARCHAR(20)	PRIMARY KEY,
MASSEY_FIRST_NAME	VARCHAR(30)	NOT NULL,
MASSEY_LAST_NAME	VARCHAR(30)	NOT NULL,
MASSEY_MIDDLE_NAME	VARCHAR(30)			,
TYPE				VARCHAR(15)         ,
DEPARTMENT			VARCHAR(255)		,
COLLEGE				VARCHAR(255)		
);


CREATE INDEX MASSSEY_LN_INDEX USING BTREE ON MASSEY_AUTHOR (MASSEY_LAST_NAME(4));


CREATE TABLE MISC_AUTHOR (
MISC_FIRST_NAME		VARCHAR(30)	NOT NULL,
MISC_LAST_NAME		VARCHAR(30)	NOT NULL,
MISC_MIDDLE_NAME	VARCHAR(30)			,
AFFILIATION			VARCHAR(255)		,
MISC_ID				INTEGER		NOT NULL AUTO_INCREMENT,
PRIMARY KEY (MISC_ID)
);


CREATE INDEX MISC_LN_INDEX USING BTREE ON MISC_AUTHOR (MISC_LAST_NAME(4));


CREATE TABLE PUBLICATION (
PUBLICATION_DOI		VARCHAR(255)	PRIMARY KEY
);
