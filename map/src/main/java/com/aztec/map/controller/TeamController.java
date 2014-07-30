package com.aztec.map.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aztec.map.common.League;
import com.aztec.map.domain.Team;
import com.aztec.map.service.TeamService;

@RestController
public class TeamController {
	
	@Autowired
	private TeamService mapService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/data/teams")
    public ResponseEntity<List<Team>> getTeams(@RequestParam("league") String league) {

		League leagueEnum = League.parseFromName(league);
		
    	List<Team> teams = mapService.getTeams(leagueEnum);
    	
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }
}
