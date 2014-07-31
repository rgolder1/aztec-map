package com.aztec.map.integration;

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
public class CityIntegrationTest {

	@Autowired
	private CityController controller;
	
	private RestTemplate restTemplate = new RestTemplate();	
	
	@Test
	public void testGetCities() {
		String jsonResponse = restTemplate.getForObject("http://localhost:8080/data/cities", String.class);

		String expectedJson = "[{\"id\":1,\"name\":\"London\",\"latitude\":51.51,\"longitude\":-0.13,\"counts\":null},{\"id\":2,\"name\":\"Manchester\",\"latitude\":53.47,\"longitude\":-2.23,\"counts\":null},{\"id\":3,\"name\":\"Newcastle\",\"latitude\":54.99,\"longitude\":-1.65,\"counts\":null},{\"id\":4,\"name\":\"Edinburgh\",\"latitude\":55.94,\"longitude\":-3.22,\"counts\":null},{\"id\":5,\"name\":\"Glasgow\",\"latitude\":55.85,\"longitude\":-4.26,\"counts\":null},{\"id\":6,\"name\":\"Bristol\",\"latitude\":51.46,\"longitude\":-2.58,\"counts\":null},{\"id\":7,\"name\":\"Truro\",\"latitude\":50.26,\"longitude\":-5.05,\"counts\":null},{\"id\":8,\"name\":\"Exeter\",\"latitude\":50.72,\"longitude\":-3.52,\"counts\":null},{\"id\":9,\"name\":\"Leicester\",\"latitude\":52.64,\"longitude\":-1.13,\"counts\":null},{\"id\":10,\"name\":\"Ipswich\",\"latitude\":52.05,\"longitude\":1.16,\"counts\":null},{\"id\":11,\"name\":\"Coventry\",\"latitude\":52.41,\"longitude\":-1.51,\"counts\":null},{\"id\":12,\"name\":\"Luton\",\"latitude\":51.9,\"longitude\":-0.43,\"counts\":null},{\"id\":13,\"name\":\"Cardiff\",\"latitude\":51.5,\"longitude\":-3.18,\"counts\":null},{\"id\":14,\"name\":\"Pembroke\",\"latitude\":51.68,\"longitude\":-4.91,\"counts\":null},{\"id\":15,\"name\":\"York\",\"latitude\":53.96,\"longitude\":-1.09,\"counts\":null},{\"id\":16,\"name\":\"Reading\",\"latitude\":51.45,\"longitude\":-0.99,\"counts\":null},{\"id\":17,\"name\":\"Bath\",\"latitude\":51.38,\"longitude\":-2.36,\"counts\":null},{\"id\":18,\"name\":\"Southampton\",\"latitude\":50.92,\"longitude\":-1.4,\"counts\":null},{\"id\":19,\"name\":\"Sheffield\",\"latitude\":53.38,\"longitude\":-1.45,\"counts\":null},{\"id\":20,\"name\":\"Birmingham\",\"latitude\":52.48,\"longitude\":-1.91,\"counts\":null},{\"id\":21,\"name\":\"Belfast\",\"latitude\":54.59,\"longitude\":-5.93,\"counts\":null},{\"id\":22,\"name\":\"Dublin\",\"latitude\":53.33,\"longitude\":-6.29,\"counts\":null},{\"id\":23,\"name\":\"Liverpool\",\"latitude\":53.41,\"longitude\":-2.92,\"counts\":null},{\"id\":24,\"name\":\"Nottingham\",\"latitude\":52.96,\"longitude\":-1.16,\"counts\":null},{\"id\":25,\"name\":\"Derby\",\"latitude\":52.91,\"longitude\":-1.47,\"counts\":null},{\"id\":26,\"name\":\"Swansea\",\"latitude\":51.63,\"longitude\":-3.96,\"counts\":null},{\"id\":27,\"name\":\"Hull\",\"latitude\":53.76,\"longitude\":-0.34,\"counts\":null},{\"id\":28,\"name\":\"Leeds\",\"latitude\":53.81,\"longitude\":-1.56,\"counts\":null},{\"id\":29,\"name\":\"Blackpool\",\"latitude\":53.82,\"longitude\":-3.03,\"counts\":null},{\"id\":30,\"name\":\"Aberdeen\",\"latitude\":57.15,\"longitude\":-2.11,\"counts\":null},{\"id\":31,\"name\":\"Portsmouth\",\"latitude\":50.82,\"longitude\":-1.07,\"counts\":null},{\"id\":32,\"name\":\"Bournemouth\",\"latitude\":50.74,\"longitude\":-1.86,\"counts\":null},{\"id\":33,\"name\":\"Dover\",\"latitude\":51.14,\"longitude\":1.3,\"counts\":null},{\"id\":34,\"name\":\"Plymouth\",\"latitude\":50.39,\"longitude\":-4.13,\"counts\":null},{\"id\":35,\"name\":\"Stoke\",\"latitude\":53.02,\"longitude\":-2.17,\"counts\":null},{\"id\":36,\"name\":\"Inverness\",\"latitude\":57.47,\"longitude\":-4.23,\"counts\":null},{\"id\":37,\"name\":\"Limerick\",\"latitude\":52.66,\"longitude\":-8.62,\"counts\":null},{\"id\":38,\"name\":\"Cork\",\"latitude\":51.89,\"longitude\":-8.46,\"counts\":null},{\"id\":39,\"name\":\"Galway\",\"latitude\":53.28,\"longitude\":-9.05,\"counts\":null},{\"id\":40,\"name\":\"Derry\",\"latitude\":55.01,\"longitude\":-7.31,\"counts\":null},{\"id\":41,\"name\":\"Dundee\",\"latitude\":56.48,\"longitude\":-2.98,\"counts\":null},{\"id\":42,\"name\":\"Bangor\",\"latitude\":53.22,\"longitude\":-4.14,\"counts\":null},{\"id\":43,\"name\":\"Aberystwyth\",\"latitude\":52.41,\"longitude\":-4.07,\"counts\":null},{\"id\":44,\"name\":\"Newport\",\"latitude\":51.58,\"longitude\":-3,\"counts\":null},{\"id\":45,\"name\":\"Norwich\",\"latitude\":52.64,\"longitude\":1.28,\"counts\":null},{\"id\":46,\"name\":\"Brighton\",\"latitude\":50.84,\"longitude\":-0.13,\"counts\":null},{\"id\":47,\"name\":\"Cambridge\",\"latitude\":52.2,\"longitude\":0.13,\"counts\":null},{\"id\":48,\"name\":\"Oxford\",\"latitude\":51.75,\"longitude\":-1.23,\"counts\":null}]";
		
        assertEquals(expectedJson, jsonResponse);
	}
}
