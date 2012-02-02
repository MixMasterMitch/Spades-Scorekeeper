package com.loeppky.spadesscores.client;

import java.util.ArrayList;

public class Team {
	private String name;
	private Player player1;
	private Player player2;
	private ArrayList<Integer> roundTrickesTaken = new ArrayList<Integer>();
	private ArrayList<Integer> roundScores = new ArrayList<Integer>();
	private final ArrayList<Integer> totalScores = new ArrayList<Integer>();
	
	Team(String name, Player player1, Player player2) {
		this.setName(name);
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public Player getPlayer1() {
		return player1;
	}
	public Player getPlayer2() {
		return player2;
	}
	public ArrayList<Integer> getRoundScores() {
		return roundScores;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public void  setRoundScores(ArrayList<Integer> roundScores) {
		this.roundScores = roundScores;
	}

	public void updateTotalScores() {
		Integer newTotal = 0;
		for(Integer roundScore : roundScores) {
			newTotal = (newTotal + roundScore);
		}
		totalScores.add(newTotal);
	}

	public ArrayList<Integer> getTotalScores() {
		return totalScores;
	}

	public void setRoundTrickesTaken(ArrayList<Integer> roundTrickesTaken) {
		this.roundTrickesTaken = roundTrickesTaken;
	}

	public ArrayList<Integer> getRoundTrickesTaken() {
		return roundTrickesTaken;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
