package com.aztec.map.domain;

import java.math.BigDecimal;

public class Region {

	private Integer id;
	private String name;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private Long woeId;
	private Long count;

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
	
	public Long getWoeId() {
		return woeId;
	}

	public void setWoeId(Long woeId) {
		this.woeId = woeId;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
}
