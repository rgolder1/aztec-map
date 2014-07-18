package com.aztec.map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

import com.aztec.map.dao.InitializingDao;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableScheduling
public class Application implements InitializingBean {

	@Autowired
	private InitializingDao initializingDao;
	
	@Value("${twitter.api.key}")
	private String twitterApiKey;
	
	@Value("${twitter.api.secret}")
	private String twitterApiSecret;
	
	@Value("${twitter.access.token.key}")
	private String twitterAccessTokenKey;
	
	@Value("${twitter.access.token.secret}")
	private String twitterAccessTokenSecret;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public TwitterTemplate twitterTemplate() {
		return new TwitterTemplate(twitterApiKey, twitterApiSecret, 
				twitterAccessTokenKey, twitterAccessTokenSecret);
	}
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

	/**
	 * Populate the database once the Spring beans are initialized.
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		initializingDao.loadData();
	}
}
