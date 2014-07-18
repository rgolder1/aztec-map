package com.aztec.map.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aztec.map.domain.City;
import com.aztec.map.domain.Region;

@Repository
public class PlaceDao {
	
	private static final String GET_REGIONS_SQL = "select id, name, latitude, longitude, woe_id from region order by id";
	private static final String GET_CITIES_SQL = "select id, name, latitude, longitude from city order by id";

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private RegionRowMapper regionRowMapper;

	@Autowired
	private CityRowMapper cityRowMapper;
	
	public List<Region> getRegions() {
		List<Region> regions = jdbcTemplate.query(GET_REGIONS_SQL, regionRowMapper);
		
		return regions;
	}

	public List<City> getCities() {
		List<City> cities = jdbcTemplate.query(GET_CITIES_SQL, cityRowMapper);
		
		return cities;
	}
}
