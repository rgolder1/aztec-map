package com.aztec.map.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class TwitterStreamConsumerTest {

	private TwitterStreamConsumer consumer;
	private Map<Integer, List<String>> keyWordsMap;
	
	@Before
	public void setup() {
		consumer = new TwitterStreamConsumer();
		keyWordsMap = new HashMap<>();
		keyWordsMap.put(Integer.valueOf(1), Arrays.asList("Team1a", "Team1b", "Team1c"));
		keyWordsMap.put(Integer.valueOf(2), Arrays.asList("Team2a"));
		keyWordsMap.put(Integer.valueOf(3), Arrays.asList("Team3a", "Team3b"));
	}
	
	@Test
	public void testGetAllKeyWords() {
		
		
		assertEquals("Team1a,Team1b,Team1c,Team2a,Team3a,Team3b", consumer.getAllKeyWords(keyWordsMap));
	}
	
	@Test
	public void testProcessTweet() {
		consumer.processTweet(keyWordsMap, "Team1b has a match against Team3a");
		consumer.processTweet(keyWordsMap, "Team1c should win tomorrow");
		consumer.processTweet(keyWordsMap, "Team3b is likely to beat Team2a");
		
		// Move the captured tweet counts in to the count map for querying.
		consumer.resetTweetCounts();
		
		assertEquals(Long.valueOf(2), consumer.getTweetCount(1));
		assertEquals(Long.valueOf(1), consumer.getTweetCount(2));
		assertEquals(Long.valueOf(2), consumer.getTweetCount(3));
	}
}
