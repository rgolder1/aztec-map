package com.aztec.service.integration;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.aztec.map.Application;
import com.aztec.map.controller.CityController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
@ActiveProfiles("test")
@DirtiesContext
public class MapIntegrationTest {

	@Autowired
	private CityController controller;
	
	private RestTemplate restTemplate = new RestTemplate();	
	
	@Test
	public void testGetCities() {
		String jsonResponse = restTemplate.getForObject("http://localhost:8080/data/teams", String.class);

		// Counts will be zero as we are not integrating with Twitter.
		String expectedJson = "[{\"id\":1,\"name\":\"Arsenal F.C.\",\"ground\":\"Emirates Stadium\",\"league\":\"Premiership\",\"latitude\":51.5583801,\"longitude\":-0.10559,\"count\":0,\"labelAlign\":\"left\"},{\"id\":2,\"name\":\"Aston Villa F.C.\",\"ground\":\"Villa Park\",\"league\":\"Premiership\",\"latitude\":52.5080185,\"longitude\":-1.88413,\"count\":0,\"labelAlign\":\"right\"},{\"id\":3,\"name\":\"Burnley F.C.\",\"ground\":\"Turf Moor\",\"league\":\"Premiership\",\"latitude\":53.7883797,\"longitude\":-2.23036,\"count\":0,\"labelAlign\":\"\"},{\"id\":4,\"name\":\"Hull City A.F.C.\",\"ground\":\"KC Stadium\",\"league\":\"Premiership\",\"latitude\":53.7469292,\"longitude\":-0.36873,\"count\":0,\"labelAlign\":\"\"},{\"id\":5,\"name\":\"Leicester City F.C.\",\"ground\":\"King Power Stadium\",\"league\":\"Premiership\",\"latitude\":52.6212196,\"longitude\":-1.1433901,\"count\":0,\"labelAlign\":\"\"},{\"id\":6,\"name\":\"Liverpool F.C.\",\"ground\":\"Anfield\",\"league\":\"Premiership\",\"latitude\":53.4308014,\"longitude\":-2.9611001,\"count\":0,\"labelAlign\":\"\"},{\"id\":7,\"name\":\"Manchester United F.C.\",\"ground\":\"Old Trafford\",\"league\":\"Premiership\",\"latitude\":53.476498,\"longitude\":-2.282984,\"count\":0,\"labelAlign\":\"\"},{\"id\":8,\"name\":\"Newcastle United F.C.\",\"ground\":\"St.James Park\",\"league\":\"Premiership\",\"latitude\":54.9742508,\"longitude\":-1.6218899,\"count\":0,\"labelAlign\":\"\"},{\"id\":9,\"name\":\"Southampton F.C.\",\"ground\":\"St Mary's Stadium\",\"league\":\"Premiership\",\"latitude\":50.905159,\"longitude\":-1.39013,\"count\":0,\"labelAlign\":\"\"},{\"id\":10,\"name\":\"Stoke City F.C.\",\"ground\":\"Britannia Stadium\",\"league\":\"Premiership\",\"latitude\":52.9865913,\"longitude\":-2.1752901,\"count\":0,\"labelAlign\":\"\"},{\"id\":11,\"name\":\"Swansea City A.F.C.\",\"ground\":\"Liberty Stadium\",\"league\":\"Premiership\",\"latitude\":51.6417618,\"longitude\":-3.9344101,\"count\":0,\"labelAlign\":\"\"},{\"id\":12,\"name\":\"Manchester City F.C.\",\"ground\":\"City of Manchester Stadium\",\"league\":\"Premiership\",\"latitude\":53.4828415,\"longitude\":-2.2024801,\"count\":0,\"labelAlign\":\"right\"},{\"id\":13,\"name\":\"Chelsea F.C.\",\"ground\":\"Stamford Bridge\",\"league\":\"Premiership\",\"latitude\":51.4804916,\"longitude\":-0.18891,\"count\":0,\"labelAlign\":\"\"},{\"id\":14,\"name\":\"QPR F.C.\",\"ground\":\"Loftus Road\",\"league\":\"Premiership\",\"latitude\":51.508275,\"longitude\":-0.23046,\"count\":0,\"labelAlign\":\"left\"},{\"id\":15,\"name\":\"West Ham United F.C.\",\"ground\":\"Boleyn Ground\",\"league\":\"Premiership\",\"latitude\":51.5318489,\"longitude\":0.03797,\"count\":0,\"labelAlign\":\"\"},{\"id\":16,\"name\":\"Tottenham Hotspur F.C.\",\"ground\":\"White Hart Lane\",\"league\":\"Premiership\",\"latitude\":51.6031609,\"longitude\":-0.06783,\"count\":0,\"labelAlign\":\"\"},{\"id\":17,\"name\":\"Sunderland A.F.C\",\"ground\":\"Stadium of Light\",\"league\":\"Premiership\",\"latitude\":54.9146309,\"longitude\":-1.38565,\"count\":0,\"labelAlign\":\"right\"},{\"id\":18,\"name\":\"West Bromwich Albion F.C.\",\"ground\":\"The Hawthorns\",\"league\":\"Premiership\",\"latitude\":52.5100517,\"longitude\":-1.9644901,\"count\":0,\"labelAlign\":\"\"},{\"id\":19,\"name\":\"Everton F.C.\",\"ground\":\"Goodison Park\",\"league\":\"Premiership\",\"latitude\":53.4389,\"longitude\":-2.9663999,\"count\":0,\"labelAlign\":\"right\"},{\"id\":20,\"name\":\"Crystal Palace F.C.\",\"ground\":\"Selhurst Park\",\"league\":\"Premiership\",\"latitude\":51.3982582,\"longitude\":-0.08693,\"count\":0,\"labelAlign\":\"left\"}]";
		
        assertEquals(expectedJson, jsonResponse);
	}
}
