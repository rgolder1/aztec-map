Aztec Technologies - Twitter Maps:
----------------------------------

A demo using the d3 javascript library to render a map of the UK, and plot locations and tweet volumes based on data received from Twitter.

Integrates with Twitter via Spring Social.  Demonstrates both direct searches using TwitterTemplate (city.html) to search for the current top trending Twitter topic, and connects to the Streaming API (uk.html) to receive tweets on the English league football teams, authenticating with the Scribe library.

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
twitter.access.token=foobar
twitter.access.token.secret=barfoo

...substituting foo/bar with genuine credentials.  If necessary, create a new Twitter app here, to generate these credentials: https://dev.twitter.com/apps


Technologies:
-------------

- Spring Boot (for rapid development)
- d3, jQuery and Bootstrap Javascript libraries (UI)
- Gradle (build)
- Spring Social (Twitter)
 -- TwitterTemplate searches
 -- Twitter's Streaming API
- Spring RESTful Web Services
- Deployed on Cloud Foundry
- Scribe (OAuth authentication)