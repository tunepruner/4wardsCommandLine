# 4wards Command Line Tool

This is a tool allowing a user to do the following:
- clock in and out
- report level of focus during last session (1-10)
- report whether the session achieved the goals it was meant to 
 achieve, a parameter referred to as "relevance" (1-10)
- print info on preview sessions to the console
- adjust any parameter of any previous session
by referencing its session ID and parameter name
 
The data is persisted in xml format at present. 

The project is written in Java 8 and depends on JAXB. 
As of the time of this writing (shaw bae2cf81f), the 
program runs perfectly in the IDE, but I can't figure out 
why JAXB doesn't work anymore once I package the app
into a JAR file and try to run it elsewhere. (See related 
issue). 