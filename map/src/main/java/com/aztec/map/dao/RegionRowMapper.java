package com.aztec.map.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.aztec.map.domain.Region;

@Component
public class RegionRowMapper implements RowMapper<Region> {

	@Override
	public Region mapRow(ResultSet rs, int rowNum) throws SQLException {
	
		Region region = new Region();
		region.setId(rs.getInt("id"));
		region.setName(rs.getString("name"));
		region.setLatitude(rs.getBigDecimal("latitude"));
		region.setLongitude(rs.getBigDecimal("longitude"));
		region.setWoeId(rs.getLong("woe_id"));
		
		return region;
	}
}
