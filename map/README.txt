AZTEC TWITTER MAP:
------------------

A demo using the d3 javascript library to render a map of the UK, and plot locations and tweet volumes based on data received from Twitter.

Integrates with Twitter via Spring Social.  Demonstrates both direct searches using TwitterTemplate (city.html) to search for the current top trending Twitter topic, and connects to the Streaming API (uk.html) to receive tweets on the current Premiership football teams, authenticating with the Scribe library.

This app is deployed on Pivotal's Cloud Foundry.

View here:  http://aztec.cfapps.io/

The maps show:

1) The relative volume of tweets on the current top trending Twitter topic around the country are plotted on the map.
2) The number of tweets for each Premiership football club.

This app was previously deployed on Google App Engine.  http://aztectwittermap.appspot.com/

However Google App Engine does not support the Twitter Streaming API (as it does not allow an external persistent HTTP connection unless a paid app).  Therefore this portion of the app was not enabled on Google App Engine.

Note that the Twitter app requires real credentials.  Update src/main/resources/application.properties with the following:

twitter.api.key=foo
twitter.api.secret=bar
twitter.access.token=foo
twitter.access.token.secret=bar

...substituting foo/bar with genuine credentials.  If necessary, create a new Twitter app here, to generate these credentials: https://dev.twitter.com/apps


Technologies used:
------------------

- Spring Boot (for rapid development)
- d3 Javascript library (UI)
- Gradle (build)
- Spring Social (Twitter)
 -- TwitterTemplate searches
 -- Twitter's Streaming API
- Spring RESTful Web Services
- Deployed on Cloud Foundry
- Scribe (OAuth authentication)

Notes:
------

d3 tutorial:
http://chimera.labs.oreilly.com/books/1230000000345/pr01.html#_safari_books_online

Make the UK map:

Let's Make a Map:
http://bost.ocks.org/mike/map/

Make it interactive:

Interactive Map with d3.js:
http://www.tnoda.com/blog/2013-12-07

GeoJSON:

Chapter 12. Geomapping
http://chimera.labs.oreilly.com/books/1230000000345/ch12.html#_projections

Get latitude/longitude for a place using this tool:
http://www.gpsvisualizer.com/geocoder/