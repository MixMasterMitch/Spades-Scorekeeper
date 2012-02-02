package com.loeppky.spadesscores.client;

import java.util.ArrayList;

public class Player {
	private String name;
	private ArrayList<Integer> betList = new ArrayList<Integer>();
	private final ArrayList<Integer> tricksTakenList = new ArrayList<Integer>();
	private Integer rating = 100;
	
	Player(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Integer> getBetList() {
		return betList;
	}
	public void setBetList(ArrayList<Integer> betList) {
		this.betList = betList;
	}

	public ArrayList<Integer> getTricksTakenList() {
		return tricksTakenList;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Integer getRating() {
		return rating;
	}
}
