package com.aztec.map.common;

public enum League {
	ALL(0, "All"), PREMIERSHIP(1, "Premiership"), CHAMPIONSHIP(2, "Championship");
	
	private int id;
	private String name;

	private League(int id, String name) {
		this.id = id;
		this.name = name;
	}
		
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public static League parseFromName(String name)
    {
		League matchingLeague = League.ALL;
    	for(League league : League.values()) {
    		if(league.getName().equalsIgnoreCase(name)) {
    			matchingLeague = league;
    			break;
    		}
    	}
    	return matchingLeague;
    }
}
