package com.aztec.map.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.aztec.map.domain.City;

@Component
public class CityRowMapper implements RowMapper<City> {

	@Override
	public City mapRow(ResultSet rs, int rowNum) throws SQLException {
	
		City city = new City();
		city.setId(rs.getInt("id"));
		city.setName(rs.getString("name"));
		city.setLatitude(rs.getBigDecimal("latitude"));
		city.setLongitude(rs.getBigDecimal("longitude"));
		
		return city;
	}

}
