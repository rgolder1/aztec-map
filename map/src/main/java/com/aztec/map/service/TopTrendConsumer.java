package com.aztec.map.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.social.RateLimitExceededException;
import org.springframework.social.twitter.api.GeoCode;
import org.springframework.social.twitter.api.GeoCode.Unit;
import org.springframework.social.twitter.api.SearchOperations;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Trend;
import org.springframework.social.twitter.api.Trends;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

import com.aztec.map.dao.PlaceDao;
import com.aztec.map.domain.City;
import com.aztec.map.domain.TweetCount;

@Service
public class TopTrendConsumer implements InitializingBean {
	private static Logger log = LoggerFactory.getLogger(TopTrendConsumer.class);

	@Autowired
	private TwitterTemplate twitterTemplate;
	
	@Autowired
	private PlaceDao placeDao;
	
	private List<City> cities;

    private static List<City> topTrends = new ArrayList<>();
    
    // Yahoo's WhereOnEarth ID for the UK.
    private static final Integer UK_WOE_ID = 23424975;

    @Scheduled(initialDelay=0, fixedRateString="${team.tweet.top.trend.capture.period.milliseconds}")
    public void updateTopTrendsByCity() {
    	List<City> latestTrends = lookupTopTrendsByCity();
    	if(latestTrends!=null) {
    		topTrends = latestTrends;

    		City firstCity = topTrends.get(0);
        	List<TweetCount> tweetCount = firstCity.getCounts();
        	log.info("updateTopTrendsByCity: city ["+firstCity.getName()+"] count ["+tweetCount+"].");
    	}
    }
    
    public List<City> getTopTrendsByCity() {
    	return topTrends;
    }
    
	protected List<City> lookupTopTrendsByCity() {
		log.info("Looking up top trends by city.");

		try {
			SearchOperations ops = twitterTemplate.searchOperations();
			
			Trends trends = ops.getLocalTrends(UK_WOE_ID);
			List<Trend> trendList = trends.getTrends();
			Trend topTrend = trendList.get(0);
			SearchParameters searchParameters = new SearchParameters(topTrend.getQuery());
			searchParameters.count(100);
			
			log.info("Top trend: name ["+topTrend.getName()+"] query ["+topTrend.getQuery()+"].");

    		int i=1;
	    	for(City city : cities) {
	    		GeoCode geoCode = new GeoCode(city.getLatitude().doubleValue(), city.getLongitude().doubleValue(), 10, Unit.MILE);
	    		searchParameters.geoCode(geoCode);
	    		SearchResults results = ops.search(searchParameters);

	    		long count = 0l;
	    		if(results!=null && results.getTweets()!=null) {
	    			count = results.getTweets().size();
	    			if(i<4) {
	    				log.info("City "+i+": name ["+city.getName()+"] count ["+count+"].");
	    			}
	        		i++;
	    		}
	    		List<TweetCount> counts = new ArrayList<>(1);
	    		counts.add(new TweetCount(topTrend.getName(), count));
	    		city.setCounts(counts);
	    	}    	
		} catch(RateLimitExceededException e) {
			log.warn("Rate limit exceeded.");
			return null;
		}
		log.info("Looked up top trends by city.");
    	return cities;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("afterPropertiesSet()");
		cities = placeDao.getCities();
		topTrends = cities;
	}
}
