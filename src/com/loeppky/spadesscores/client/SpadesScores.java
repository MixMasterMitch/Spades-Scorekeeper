package com.loeppky.spadesscores.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SpadesScores implements EntryPoint, MouseOverHandler, ClickHandler {
	Integer objective = new Integer(500);
	Team team1;
	Team team2;
	FlexTable mainPanel = new FlexTable();
	
	FlexTable newPlayersFlexTable = new FlexTable();
	TextBox team1NameTextBox = new TextBox();
	TextBox team2NameTextBox = new TextBox();
	TextBox player1TextBox = new TextBox();
	Label andLabel1 = new Label("and");
	TextBox player2TextBox = new TextBox();
	Label vsLabel = new Label("vs.");
	TextBox player3TextBox = new TextBox();
	Label andLabel2 = new Label("and");
	TextBox player4TextBox = new TextBox();
	Button addNewPlayersButton = new Button("Add Players");
	Label objectiveScoreLabel = new Label("Objective:");
	TextBox objectiveScore = new TextBox();
	Button objectiveScoreButton = new Button("Update");
	Button newBetsButton = new Button("New Bets");
	Button newRoundScoresButton = new Button("New Round Scores");
	Button editScoresButtons = new Button("Edit Scores");
	FlexTable gameControlsFlexTable = new FlexTable();
	DialogBox dialogBox;
	
	//Scores Panel Components
	VerticalPanel scoresVPanelTeam1 = new VerticalPanel();
	Label labelTeam1 = new Label("Team 1");
	FlexTable scoresTableTeam1 = new FlexTable();
	VerticalPanel scoresVPanelTeam2 = new VerticalPanel();
	Label labelTeam2 = new Label("Team 2");
	FlexTable scoresTableTeam2 = new FlexTable();
	
	//Analysis Panel Components
	FlexTable analysisFlexTable = new FlexTable();
	
	@Override
	public void onModuleLoad() {
		
		player1TextBox.addMouseOverHandler(this);
		player2TextBox.addMouseOverHandler(this);
		player3TextBox.addMouseOverHandler(this);
		player4TextBox.addMouseOverHandler(this);
		objectiveScore.addMouseOverHandler(this);
		team1NameTextBox.addMouseOverHandler(this);
		team2NameTextBox.addMouseOverHandler(this);
		addNewPlayersButton.addClickHandler(this);
		newBetsButton.addClickHandler(this);
		newRoundScoresButton.addClickHandler(this);
		editScoresButtons.addClickHandler(this);
		objectiveScoreButton.addClickHandler(this);
		objectiveScore.setText(objective.toString());
		gameControlsFlexTable.setWidget(0, 0, objectiveScoreLabel);
		gameControlsFlexTable.setWidget(0, 1, objectiveScore);
		gameControlsFlexTable.setWidget(0, 2, objectiveScoreButton);
		gameControlsFlexTable.setWidget(1, 0, newBetsButton);
		gameControlsFlexTable.setWidget(1, 1, newRoundScoresButton);
		gameControlsFlexTable.setWidget(1, 2, editScoresButtons);
		gameControlsFlexTable.setCellSpacing(5);
		gameControlsFlexTable.setStyleName("roundedCornersBorder");
		team1NameTextBox.setText("Team 1 Name ...");
		team2NameTextBox.setText("Team 2 Name ...");
		player1TextBox.setText("Player 1");
		player2TextBox.setText("Player 2");
		player3TextBox.setText("Player 3");
		player4TextBox.setText("Player 4");
		newPlayersFlexTable.setWidget(0, 0, team1NameTextBox);
		newPlayersFlexTable.setWidget(0, 4, team2NameTextBox);
		newPlayersFlexTable.setWidget(1, 0, player1TextBox);
		newPlayersFlexTable.setWidget(1, 1, andLabel1);
		newPlayersFlexTable.setWidget(1, 2, player2TextBox);
		newPlayersFlexTable.setWidget(1, 3, vsLabel);
		newPlayersFlexTable.setWidget(1, 4, player3TextBox);
		newPlayersFlexTable.setWidget(1, 5, andLabel2);
		newPlayersFlexTable.setWidget(1, 6, player4TextBox);
		newPlayersFlexTable.setWidget(1, 7, addNewPlayersButton);
		newPlayersFlexTable.setCellSpacing(5);
		newPlayersFlexTable.setStyleName("roundedCornersBorder");
		
		mainPanel.setWidget(0,0,newPlayersFlexTable);
		mainPanel.setStyleName("roundedCornersBorderWithGradient");
		mainPanel.setCellSpacing(5);
		RootPanel.get().add(mainPanel);
		
	}

	@Override
	public void onMouseOver(MouseOverEvent event) {
		Object sender = event.getSource();
		if(sender == player1TextBox) {
			player1TextBox.selectAll();
		}
		if(sender == player2TextBox) {
			player2TextBox.selectAll();
		}
		if(sender == player3TextBox) {
			player3TextBox.selectAll();
		}
		if(sender == player4TextBox) {
			player4TextBox.selectAll();
		}
		if(sender == objectiveScore) {
			objectiveScore.selectAll();
		}
		if(sender == team1NameTextBox) {
			team1NameTextBox.selectAll();
		}
		if(sender == team2NameTextBox) {
			team2NameTextBox.selectAll();
		}
		
	}

	@Override
	public void onClick(ClickEvent event) {
		Object sender = event.getSource();
		if(sender == addNewPlayersButton) {
			addNewPlayers();
		}
		if(sender == newBetsButton) {
			dialogBox = new NewBetsDialogBox(this, team1, team2);
		}
		if(sender == newRoundScoresButton) {
			dialogBox = new NewRoundScoresDialogBox(this, team1, team2);
		}
		if(sender == objectiveScoreButton) {
			if(!objectiveScore.getText().matches("[0-9//s]{1,4}")) {
				Window.alert("The Objective must be a number");
			}
			else {
				objective = Integer.parseInt(objectiveScore.getText());
				if(team1.getTotalScores().get(team1.getTotalScores().size()-1) >= objective && team1.getTotalScores().get(team1.getTotalScores().size()-1) >= team2.getTotalScores().get(team2.getTotalScores().size()-1)) {
					new WinnerMessageDialogBox(team1);
				}
				if(team2.getTotalScores().get(team2.getTotalScores().size()-1) >= objective && team2.getTotalScores().get(team2.getTotalScores().size()-1) >= team1.getTotalScores().get(team1.getTotalScores().size()-1)) {
					new WinnerMessageDialogBox(team2);
				}
			}
		}
		if(sender == editScoresButtons) {
			Window.alert("Feature not finnished yet!");
		}
	}
	
	private void addNewPlayers() {
		team1 = new Team(team1NameTextBox.getText(), new Player(player1TextBox.getText()), new Player(player2TextBox.getText()));
		team2 = new Team(team2NameTextBox.getText(), new Player(player3TextBox.getText()), new Player(player4TextBox.getText()));
		setupFlexTableHeaders();
		setupScoresFlexTables();
		setupAnalysisFlexTable();
		player1TextBox.setReadOnly(true);
		player2TextBox.setReadOnly(true);
		player3TextBox.setReadOnly(true);
		player4TextBox.setReadOnly(true);
		mainPanel.clear();
		mainPanel.setWidget(0,0,gameControlsFlexTable);
		mainPanel.setWidget(0,1,analysisFlexTable);
		mainPanel.setWidget(1,0,scoresVPanelTeam1);
		mainPanel.setWidget(1,1,scoresVPanelTeam2);
	}

	private void setupScoresFlexTables() {
		labelTeam1.setText(team1.getName());
		labelTeam1.setStyleName("nameLabel");
		scoresVPanelTeam1.add(labelTeam1);
		scoresVPanelTeam1.add(scoresTableTeam1);
		scoresVPanelTeam1.setSpacing(5);
		scoresVPanelTeam1.setStyleName("roundedCornersBorder");
		labelTeam2.setText(team2.getName());
		labelTeam2.setStyleName("nameLabel");
		scoresVPanelTeam2.add(labelTeam2);
		scoresVPanelTeam2.add(scoresTableTeam2);
		scoresVPanelTeam2.setSpacing(5);
		scoresVPanelTeam2.setStyleName("roundedCornersBorder");
	}
	
	private void setupAnalysisFlexTable() {
		analysisFlexTable.setStyleName("FlexTable");
		analysisFlexTable.setText(0, 0, "Player");
		analysisFlexTable.setText(0, 1, "Total Bets");
		analysisFlexTable.setText(0, 2, "Total Tricks");
		analysisFlexTable.setText(0, 3, "Rating");
		analysisFlexTable.getRowFormatter().setStyleName(0, "analysisFlexTableHeader");
		analysisFlexTable.setText(1, 0, team1.getPlayer1().getName());
		analysisFlexTable.setText(2, 0, team1.getPlayer2().getName());
		analysisFlexTable.setText(3, 0, team2.getPlayer1().getName());
		analysisFlexTable.setText(4, 0, team2.getPlayer2().getName());
		analysisFlexTable.setText(1, 1, "0");
		analysisFlexTable.setText(2, 1, "0");
		analysisFlexTable.setText(3, 1, "0");
		analysisFlexTable.setText(4, 1, "0");
		analysisFlexTable.setText(1, 2, "0");
		analysisFlexTable.setText(2, 2, "0");
		analysisFlexTable.setText(3, 2, "0");
		analysisFlexTable.setText(4, 2, "0");
		analysisFlexTable.setText(1, 3, "100");
		analysisFlexTable.setText(2, 3, "100");
		analysisFlexTable.setText(3, 3, "100");
		analysisFlexTable.setText(4, 3, "100");
	}
	
	public void resetFlexTables() {
		scoresTableTeam1.removeAllRows();
		setupFlexTableHeaders();
		int row = 1;
		for(Integer integer : team1.getPlayer1().getBetList()) {
			scoresTableTeam1.setText(row,0, integer.toString());
			row++;
		}
		row = 1;
		for(Integer integer : team1.getPlayer2().getBetList()) {
			scoresTableTeam1.setText(row,1, integer.toString());
			row++;
		}
		row = 1;
		for(Integer integer : team1.getRoundTrickesTaken()) {
			scoresTableTeam1.setText(row,2, integer.toString());
			row++;
		}
		row = 1;
		for(Integer integer : team1.getRoundScores()) {
			scoresTableTeam1.setText(row,3, integer.toString());
			row++;
		}
		row = 1;
		for(Integer integer : team1.getTotalScores()) {
			scoresTableTeam1.setText(row,4, integer.toString());
			if(integer < 0) {
				scoresTableTeam1.getCellFormatter().addStyleName(row,4, "red");
			}
			else scoresTableTeam1.getCellFormatter().addStyleName(row,4, "green");
			row++;
		}
		row = 1;
		for(Integer integer : team2.getPlayer1().getBetList()) {
			scoresTableTeam2.setText(row,0, integer.toString());
			row++;
		}
		row = 1;
		for(Integer integer : team2.getPlayer2().getBetList()) {
			scoresTableTeam2.setText(row,1, integer.toString());
			row++;
		}
		row = 1;
		for(Integer integer : team2.getRoundTrickesTaken()) {
			scoresTableTeam2.setText(row,2, integer.toString());
			row++;
		}
		row = 1;
		for(Integer integer : team2.getRoundScores()) {
			scoresTableTeam2.setText(row,3, integer.toString());
			row++;
		}
		row = 1;
		for(Integer integer : team2.getTotalScores()) {
			scoresTableTeam2.setText(row,4, integer.toString());
			if(integer < 0) {
				scoresTableTeam2.getCellFormatter().addStyleName(row,4, "red");
			}
			else scoresTableTeam2.getCellFormatter().addStyleName(row,4, "green");
			row++;	
		}
	}
	
	private void setupFlexTableHeaders() {
		scoresTableTeam1.setText(0,0, team1.getPlayer1().getName() + " Bets");
		scoresTableTeam1.setText(0,1, team1.getPlayer2().getName() + " Bets");
		scoresTableTeam1.setText(0,2, "Tricks Taken");
		scoresTableTeam1.setText(0,3, "Round Score");
		scoresTableTeam1.setText(0,4, "Total Score");
		scoresTableTeam2.setText(0,0, team2.getPlayer1().getName() + " Bets");
		scoresTableTeam2.setText(0,1, team2.getPlayer2().getName() + " Bets");
		scoresTableTeam2.setText(0,2, "Tricks Taken");
		scoresTableTeam2.setText(0,3, "Round Score");
		scoresTableTeam2.setText(0,4, "Total Score");
		scoresTableTeam1.getRowFormatter().addStyleName(0, "team1FlexTableHeader");
		scoresTableTeam1.addStyleName("FlexTable");
		scoresTableTeam2.getRowFormatter().addStyleName(0, "team2FlexTableHeader");
		scoresTableTeam2.addStyleName("FlexTable");
	}
}
