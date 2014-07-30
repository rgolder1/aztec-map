package com.aztec.map.common;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LeagueTest {

	@Test
	public void testParseFromName() {
		League league = League.parseFromName("premiership");
		assertEquals("Premiership", league.getName());

		league = League.parseFromName("CHAMPionsHIP");
		assertEquals("Championship", league.getName());
	}
}
