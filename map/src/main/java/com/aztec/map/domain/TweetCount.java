package com.aztec.map.domain;

public class TweetCount {

	private String tweet;
	private Long count;
	
	public TweetCount(String tweet, long count) {
		this.tweet = tweet;
		this.count = count;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
}
