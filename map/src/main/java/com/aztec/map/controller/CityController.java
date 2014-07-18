package com.aztec.map.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aztec.map.domain.City;
import com.aztec.map.service.PlaceService;

@RestController
public class CityController {
	
	@Autowired
	private PlaceService placeService;

	@RequestMapping(method = RequestMethod.GET, value = "/data/cities")
    public ResponseEntity<List<City>> getCities() {
    	
    	List<City> cities = placeService.getCities();
    	
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }
}
