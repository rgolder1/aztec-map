package com.aztec.map.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TwitterStreamServer {
	private static Logger log = LoggerFactory.getLogger(TwitterStreamServer.class);

	@Autowired
	private TwitterStreamConsumer streamConsumer;
	
	public TwitterStreamServer() {
	}
	
	/**
	 * Ensure the stream is running.  If not, start it.
	 */
	@Scheduled(initialDelay=0, fixedRateString="${team.tweet.count.check.stream.alive.period.milliseconds}")
	public void validateStreamCapture() {
        log.info("Validating stream capture is running.");
		if(!streamConsumer.isRunning()) {
 			while(!streamConsumer.isReadyToStart()){
				try {
					log.warn("Stream capture is not running, but not ready to start.");
					Thread.sleep(50l);
				} catch (InterruptedException e) {}
			}
 			log.info("Starting stream capture.");
			new Thread(streamConsumer).start();
		}
        log.info("Validated stream capture is running.");
	}
	
	public TwitterStreamConsumer getConsumer() {
		return streamConsumer;
	}
}
