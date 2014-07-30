package com.aztec.map.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aztec.map.common.League;
import com.aztec.map.dao.MapDao;
import com.aztec.map.domain.Team;

@Service
public class TeamService {
	private static Logger log = LoggerFactory.getLogger(TeamService.class);
	
	@Autowired
	private TwitterStreamConsumer streamConsumer;
	
	@Autowired
	private MapDao mapDao;
	
	public List<Team> getTeams(League league) {
		log.info("Getting teams for league "+league.getName());
		
		List<Team> teams = null; 
				
		if(league==League.ALL) {
			teams = mapDao.getAllTeams();
		} else {
			teams = mapDao.getTeams(league);
		}
    	
    	for(Team team : teams) {
    		Long count = streamConsumer.getTweetCount(team.getId());
    		team.setCount(count);
    	}
    	
    	return teams;
	}
}
