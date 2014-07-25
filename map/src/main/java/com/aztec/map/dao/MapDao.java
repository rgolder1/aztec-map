package com.aztec.map.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aztec.map.domain.KeyWord;
import com.aztec.map.domain.Team;

@Repository
public class MapDao {
	
	private static final String GET_TEAMS_SQL = "select id, name, ground, league, latitude, longitude, search_term, label_align from team order by id";

	private static final String GET_KEYWORDS_SQL = "select id, team_id, word from keyword order by team_id";

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private TeamRowMapper teamRowMapper;

	@Autowired
	private KeyWordRowMapper keyWordRowMapper;
	
	public List<Team> getTeams() {
		List<Team> teams = jdbcTemplate.query(GET_TEAMS_SQL, teamRowMapper);
		
		return teams;
	}
	
	public Map<Integer, List<String>> getKeyWords() {
		
		Map<Integer, List<String>> keyWordMap = new HashMap<>();

		List<KeyWord> keyWords = jdbcTemplate.query(GET_KEYWORDS_SQL, keyWordRowMapper);
		
		for(KeyWord keyWord : keyWords) {
			if(!keyWordMap.containsKey(keyWord.getTeamId())) {
				keyWordMap.put(keyWord.getTeamId(), new ArrayList<String>());
			}
			List<String> currentKeyWords = keyWordMap.get(keyWord.getTeamId());
			currentKeyWords.add(keyWord.getWord());
		}
		
		return keyWordMap;
	}
}
