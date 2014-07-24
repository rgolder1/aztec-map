package com.aztec.map.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.aztec.map.dao.MapDao;

@Component
public class TwitterStreamConsumer implements Runnable {
	private static Logger log = LoggerFactory.getLogger(TwitterStreamConsumer.class);
	
    private static final String STREAM_URI = "https://stream.twitter.com/1.1/statuses/filter.json";
    private static final String EXCEEDED_CONNECTION_LIMIT_WARNING = "exceeded connection limit for user";
    private static Map<Integer, Long> newTweetCount = new HashMap<>();
    private static Map<Integer, Long> lastTweetCount = new HashMap<>();
    
    private enum State {RUNNING, STOPPING, STOPPED};
    private State state = State.STOPPED;
    
	@Value("${twitter.api.key}")
	private String twitterApiKey;
	
	@Value("${twitter.api.secret}")
	private String twitterApiSecret;
	
	@Value("${twitter.access.token.key}")
	private String twitterAccessTokenKey;
	
	@Value("${twitter.access.token.secret}")
	private String twitterAccessTokenSecret;
    
    @Autowired
    private MapDao mapDao;

    public void terminate() {
    	if(state==State.RUNNING) {
    		state = State.STOPPING;
    	} else {
    		state = State.STOPPED;
    	}
    }
    
    public boolean isRunning() {
    	return state==State.RUNNING;
    }
    
    public boolean isReadyToStart() {
    	return state==State.STOPPED;
    }
    
    public TwitterStreamConsumer() {
    	resetTweetCounts();
    }
    
    @Scheduled(initialDelay=0, fixedRateString="${team.tweet.count.capture.period.milliseconds}")
    public synchronized void resetTweetCounts() {
        log.info("Updating tweet counts.");
    	lastTweetCount.clear();
    	lastTweetCount.putAll(newTweetCount);
    	newTweetCount.clear();
    }

    public synchronized Long getTweetCount(Integer id){
    	Long count = lastTweetCount.get(id);
    	if(count==null) {
    		count = 0l;
    	}
        return count;
    }

    @Override
    public void run() {
    	log.info("Running stream capture.");
    	state = State.RUNNING;
    	
    	Map<Integer, List<String>> keyWordsMap = mapDao.getKeyWords();

        // Enter your consumer key and secret below.
        OAuthService service = new ServiceBuilder()
                .provider(TwitterApi.class)
                .apiKey(twitterApiKey)
                .apiSecret(twitterApiSecret)
                .build();
        
        // Set the access token.
        Token accessToken = new Token(twitterAccessTokenKey, twitterAccessTokenSecret);

        // Generate the request.
        OAuthRequest request = new OAuthRequest(Verb.POST, STREAM_URI);
        request.addHeader("version", "HTTP/1.1");
        request.addHeader("host", "stream.twitter.com");
        request.setConnectionKeepAlive(true);
        request.addHeader("user-agent", "Twitter Stream Reader");
        request.addBodyParameter("track", getAllKeyWords(keyWordsMap));
        service.signRequest(accessToken, request);
        Response response = request.send();

        // Create a reader to read Twitter's stream
        try(InputStream inputStream = response.getStream(); BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line = null;
            int i=0;
            while (state==State.RUNNING && (line = reader.readLine()) != null) {
                
            	line = line.toLowerCase();
            	if(i==0) {
            		log.info("First line of Twitter stream: "+line);
            	}
            	i++;
            	if(line.equals(EXCEEDED_CONNECTION_LIMIT_WARNING)) {
            		state = State.STOPPED;
            		log.error("The connection limit has been exceeded.");
            		return;
            	}
            	
            	processTweet(keyWordsMap, line);
            }
        }
        catch (IOException e){
        	throw new RuntimeException(e);
    	}
        resetTweetCounts();
        state = State.STOPPED;
        log.info("Stopped stream capture.");
    }

    /**
     * Update the tweet count for the required team.
     */
	protected void processTweet(Map<Integer, List<String>> keyWordsMap, String line) {
		Set<Integer> teamIds = keyWordsMap.keySet();
		
		for(Integer teamId : teamIds) {
			boolean match = false;

			List<String> keyWords = keyWordsMap.get(teamId);
			
			for(String keyWord : keyWords) {
				if(line.contains(keyWord)) {
					match = true;
					break;
				}
			}
			
			if(match) {
		    	Long tweetCount = newTweetCount.get(teamId);
		    	if(tweetCount==null) {
		    		tweetCount=0l;
		    	}
		    	newTweetCount.put(teamId, ++tweetCount);
			}
			match = false;
		}
	}

	/**
	 * Get all the keywords.  These are used to determine which tweets our stream picks up.
	 */
	protected String getAllKeyWords(Map<Integer, List<String>> keyWordsMap) {		
		StringBuilder allKeyWords = new StringBuilder();
		Collection<List<String>> values = keyWordsMap.values();
		
		int i=0;
		for(List<String> list : values) {
			for(String value : list) {
				if(i!=0) {
					allKeyWords.append(",");
				}
				allKeyWords.append(value);
				i++;
			}
		}
		
		return allKeyWords.toString();
	}
}
