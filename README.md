newspaper
==============================
PILLAR KATA EXERCISE:

Create a program that meets the following description. You can use any language, frameworks, technology you wish with the overall goal of creating a clean, readable, and testable application.

Acme places Ads in Newspapers and needs a system to keep track of which Ads are in which Newspaper. Create a program that allows Acme to create Ads, create Newspapers and relate Ads to Newspapers.
==============================

The newspaper application is a REST-based service using Maven and Springframework.
The design is layered as such:

controller - handles incoming requests, defers to service layer
domain - a newspaper
exception - when bad things happen
service - service layer that does actual work.

The main calls are:

Create a newspaper
/newspaper/{name}   (POST)

Create an ad for a newspaper 
/newspaper/{name}/{ad}) (POST)

Get the ads for a newspaper
/newspaper/{name} (GET)


The unit test uses Springs MockMvc and Mockito to test
the controller.  We want to test the controller only, not
the service it contains.


TODO:

Needs more hard-core testing, verification command?
Write tests for service layer as well
Look at passing objects around rather than strings
Incorporate maven site into build

