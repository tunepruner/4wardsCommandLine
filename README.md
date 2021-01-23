# 4wards Command Line Tool

This is a tool allowing a user to do the following:
- clock in and out
- report level of focus during last session (1-10)
- report whether the session achieved the goals it was meant to 
 achieve, a parameter referred to as "relevance" (1-10)
- print info on preview sessions to the console
- adjust any parameter of any previous session
by referencing its session ID and parameter name
 
The data is persisted in json format on this branch. 

The project is written in Java 8 and depends on the GSon library. 
As of the time of this writing (shaw f4d905cf8a2), the 
data is persisted in json format. On the previous branch, 
it was done in xml using JAXB, with which I ran into 
problems while packaging the program into a JAR file. 