package com.aztec.map.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.aztec.map.domain.Region;

@Service
public class PlaceService {
	private static Logger log = LoggerFactory.getLogger(PlaceService.class);
	
	@Autowired
	private TwitterTemplate twitterTemplate;
	
	@Autowired
	private PlaceDao placeDao;
	
	@Autowired
	private TopTrendConsumer topTrendConsumer;
	
	public List<Region> getRegions() {
		log.debug("Getting regions.");

		SearchOperations ops = twitterTemplate.searchOperations();
		
		Trends trends = ops.getLocalTrends(23424975);
		List<Trend> trendList = trends.getTrends();
		Trend topTrend = trendList.get(0);
		SearchParameters searchParameters = new SearchParameters(topTrend.getQuery());
		searchParameters.count(100);
		
		log.debug("Top trend: name ["+topTrend.getName()+"] query ["+topTrend.getQuery()+"].");
		
    	List<Region> regions = placeDao.getRegions();
    	
    	for(Region region : regions) {
    		log.debug("Region: "+region.getName());

    		GeoCode geoCode = new GeoCode(region.getLatitude().doubleValue(), region.getLongitude().doubleValue(), 10, Unit.MILE);
    		searchParameters.geoCode(geoCode);
    		
    		SearchResults results = ops.search(searchParameters);
    		long count = 0l;
    		if(results!=null && results.getTweets()!=null) {
    			count = results.getTweets().size();
        		log.debug("Region: name ["+region.getName()+"] count ["+count+"].");
    		}

    		region.setCount(count);
    	}
    	
    	return regions;
	}

	public List<City> getCities() {
		log.debug("Getting cities.");

    	return topTrendConsumer.getTopTrendsByCity();
	}
}