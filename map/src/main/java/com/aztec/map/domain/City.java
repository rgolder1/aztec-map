package com.aztec.map.domain;

import java.math.BigDecimal;
import java.util.List;

public class City {

	private Integer id;
	private String name;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private List<TweetCount> counts;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public List<TweetCount> getCounts() {
		return counts;
	}

	public void setCounts(List<TweetCount> counts) {
		this.counts = counts;
	}
}
