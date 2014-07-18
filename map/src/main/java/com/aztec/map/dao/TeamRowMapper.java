package com.aztec.map.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.aztec.map.domain.Team;

@Component
public class TeamRowMapper implements RowMapper<Team> {

	@Override
	public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
	
		Team team = new Team();
		team.setId(rs.getInt("id"));
		team.setName(rs.getString("name"));
		team.setGround(rs.getString("ground"));
		team.setLeague(rs.getString("league"));
		team.setLatitude(rs.getBigDecimal("latitude"));
		team.setLongitude(rs.getBigDecimal("longitude"));
		String labelAlign = rs.getString("label_align");
		team.setLabelAlign(labelAlign==null?"":labelAlign);
		
		return team;
	}
}
