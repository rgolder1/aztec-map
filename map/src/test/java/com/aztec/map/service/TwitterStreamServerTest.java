package com.aztec.map.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterStreamServerTest {

	@Mock
	private TwitterStreamConsumer consumer;
	
	private TwitterStreamServer server; 

	@Before
	public void setup() {
		server = new TwitterStreamServer(consumer);
	}
	
	@Test
	public void testValidateStreamCapture_AlreadyRunning() {
		
		when(consumer.isRunning()).thenReturn(Boolean.TRUE);
		
		server.validateStreamCapture();
		
		// Verify that run() is not called, as it is already running.
		verify(consumer, times(0)).run();
	}

	@Test
	public void testValidateStreamCapture_ReadyToStart() {
		
		when(consumer.isRunning()).thenReturn(Boolean.FALSE);
		when(consumer.isReadyToStart()).thenReturn(Boolean.TRUE);
		
		server.validateStreamCapture();
		
		verify(consumer, times(1)).run();
	}
}
