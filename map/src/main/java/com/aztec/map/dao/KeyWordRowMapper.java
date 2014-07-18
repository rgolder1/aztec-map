package com.aztec.map.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.aztec.map.domain.KeyWord;

@Component
public class KeyWordRowMapper implements RowMapper<KeyWord> {

	@Override
	public KeyWord mapRow(ResultSet rs, int rowNum) throws SQLException {
	
		KeyWord keyWord = new KeyWord();
		keyWord.setId(rs.getInt("id"));
		keyWord.setTeamId(rs.getInt("team_id"));
		keyWord.setWord(rs.getString("word"));
		
		return keyWord;
	}

}
