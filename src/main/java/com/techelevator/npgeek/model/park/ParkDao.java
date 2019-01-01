package com.techelevator.npgeek.model.park;

import java.util.List;

public interface ParkDao {

	public List<Park> getAllParkInfo();
	public Park getParkByParkCode(String parkCode);
	
}
