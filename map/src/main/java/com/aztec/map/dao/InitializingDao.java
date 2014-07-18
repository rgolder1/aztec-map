package com.aztec.map.dao;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class InitializingDao {
	private static Logger log = Logger.getLogger(InitializingDao.class.getName());

	private static final String INSERT_TEAM_SQL = "INSERT INTO team (id, name, ground, league, latitude, longitude, label_align) " +
			"values (?, ?, ?, ?, ?, ?, ?)";
	
	private static final String INSERT_KEYWORDS_SQL = "INSERT INTO keyword (id, team_id, word) " +
			"values (?, ?, ?)";

	private static final String INSERT_REGIONS_SQL = "INSERT INTO region (id, name, latitude, longitude, woe_id) " +
			"values (?, ?, ?, ?, ?)";

	private static final String INSERT_CITIES_SQL = "INSERT INTO city (id, name, latitude, longitude) " +
			"values (?, ?, ?, ?)";
	
	private static final String CSV_DELIMITER = ",";
	
	private static final String FILE_NAME_TEAMS = "teams.csv";
	private static final String FILE_NAME_KEYWORDS = "keywords.csv";
	private static final String FILE_NAME_REGIONS = "regions.csv";
	private static final String FILE_NAME_CITIES = "cities.csv";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void loadData() {
		loadData(FILE_NAME_TEAMS, INSERT_TEAM_SQL);
		loadData(FILE_NAME_KEYWORDS, INSERT_KEYWORDS_SQL);
		loadData(FILE_NAME_REGIONS, INSERT_REGIONS_SQL);
		loadData(FILE_NAME_CITIES, INSERT_CITIES_SQL);
	}

    private void loadData(String fileName, String sql) {
		log.info("Loading file ["+fileName+"].");

		try(BufferedReader br = new BufferedReader(new InputStreamReader(getInputStream(fileName), "UTF-8"))) {
		    String strLine;
		    int i=0;
		    while ((strLine = br.readLine()) != null && !strLine.trim().equals("")) {
		    	// Skip the first line which has the headers.
		    	if(i!=0) {
			    	Object[] args = getSqlArgs(strLine);
			    	jdbcTemplate.update(sql, args);
		    	}
		    	i++;
		    }
	    } catch(Exception e) {
	    	throw new RuntimeException(e);
	    }
		
		log.info("Loaded file ["+fileName+"].");
	}

	private Object[] getSqlArgs(String strLine) {
		StringTokenizer tokenizer = new StringTokenizer(strLine, ",");
		int count = StringUtils.countOccurrencesOf(strLine, CSV_DELIMITER);
		Object[] args = new Object[++count];
		for(int i=0; tokenizer.hasMoreTokens(); i++) {
			String value = tokenizer.nextToken();
			args[i] = value;
		}
		return args;
	}

	private InputStream getInputStream(String fileName) throws Exception {
		InputStream is =  this.getClass().getClassLoader().getResourceAsStream(fileName);
		return is;
	}
}
