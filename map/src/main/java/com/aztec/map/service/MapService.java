package com.aztec.map.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aztec.map.dao.MapDao;
import com.aztec.map.domain.Team;

@Service
public class MapService {
	private static Logger log = LoggerFactory.getLogger(MapService.class);
	
	@Autowired
	private TwitterStreamConsumer streamConsumer;
	
	@Autowired
	private MapDao mapDao;
	
	public List<Team> getTeams() {
		log.debug("Getting teams.");
    	List<Team> teams = mapDao.getTeams();
    	
    	for(Team team : teams) {
    		Long count = streamConsumer.getTweetCount(team.getId());
    		team.setCount(count);
    	}
    	
    	return teams;
	}
}
