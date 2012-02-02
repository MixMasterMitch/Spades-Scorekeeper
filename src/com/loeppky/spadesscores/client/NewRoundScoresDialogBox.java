package com.loeppky.spadesscores.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class NewRoundScoresDialogBox extends DialogBox implements ClickHandler, MouseOverHandler, MouseOutHandler {
	SpadesScores spadesScores;
	VerticalPanel mainPanel = new VerticalPanel();
	HorizontalPanel hPanel = new HorizontalPanel();
	FlexTable scoreReportFlexTable = new FlexTable();
	Label team1Label = new Label("Team 1 Score:");
	Label team1ScoreLabel = new Label();
	Label team2Label = new Label("Team 2 Score:");
	Label team2ScoreLabel = new Label();
	Label totalTricks = new Label();
	FlexTable tricksFlexTable = new FlexTable();
	Label player1Label = new Label();
	TextBox player1TB = new TextBox();
	Label player2Label = new Label();
	TextBox player2TB = new TextBox();
	Label player3Label = new Label();
	TextBox player3TB = new TextBox();
	Label player4Label = new Label();
	TextBox player4TB = new TextBox();
	HorizontalPanel buttonsPanel = new HorizontalPanel();
	Button okButton = new Button("OK");
	Button cancelButton = new Button("Cancel");
	Team team1;
	Team team2;
	int player1LastBet;
	int player2LastBet;
	int player3LastBet;
	int player4LastBet;
	int player1TricksTaken;
	int player2TricksTaken;
	int player3TricksTaken;
	int player4TricksTaken;
	Integer player1Rating;
	Integer player2Rating;
	Integer player3Rating;
	Integer player4Rating;
	
	NewRoundScoresDialogBox(SpadesScores spadesScores, Team team1, Team team2) {
		if(team1.getPlayer1().getBetList().size() <= team1.getRoundTrickesTaken().size()){
			Window.alert("Please bet first!");
		}
		else {
			SpadesScoresUtil.addMouseOverHandler(this, player1TB, player2TB, player3TB, player4TB);
			SpadesScoresUtil.addMouseOutHandler(this, player1TB, player2TB, player3TB, player4TB);
			this.spadesScores = spadesScores;
			player1LastBet = team1.getPlayer1().getBetList().get(team1.getPlayer1().getBetList().size()-1).intValue();
			player2LastBet = team1.getPlayer2().getBetList().get(team1.getPlayer2().getBetList().size()-1).intValue();
			player3LastBet = team2.getPlayer1().getBetList().get(team1.getPlayer1().getBetList().size()-1).intValue();
			player4LastBet = team2.getPlayer2().getBetList().get(team1.getPlayer2().getBetList().size()-1).intValue();
			this.add(mainPanel);
			this.team1 = team1;
			this.team2 = team2;
			okButton.addClickHandler(this);
			cancelButton.addClickHandler(this);
			this.setAnimationEnabled(true);
			this.setGlassEnabled(true);
			this.setText("New Bets");
			player1Label.setText(team1.getPlayer1().getName() + ":");
			player1TB.setWidth("50px");
			tricksFlexTable.setWidget(2,0,player1Label);
			tricksFlexTable.setWidget(3,0,player1TB);
			player1TB.setText(team1.getPlayer1().getBetList().get(team1.getPlayer1().getBetList().size() - 1).toString());
			player2Label.setText(team1.getPlayer2().getName()+ ":");
			player2TB.setWidth("50px");
			tricksFlexTable.setWidget(2,2,player2Label);
			tricksFlexTable.setWidget(3,2,player2TB);
			player2TB.setText(team1.getPlayer2().getBetList().get(team1.getPlayer2().getBetList().size() - 1).toString());
			player3Label.setText(team2.getPlayer1().getName()+ ":");
			player3TB.setWidth("50px");
			tricksFlexTable.setWidget(0,1,player3Label);
			tricksFlexTable.setWidget(1,1,player3TB);
			player3TB.setText(team2.getPlayer1().getBetList().get(team2.getPlayer1().getBetList().size() - 1).toString());
			player4Label.setText(team2.getPlayer2().getName() + ":");
			player4TB.setWidth("50px");
			tricksFlexTable.setWidget(4,1,player4Label);
			tricksFlexTable.setWidget(5,1,player4TB);
			player4TB.setText(team2.getPlayer2().getBetList().get(team2.getPlayer2().getBetList().size() - 1).toString());
			tricksFlexTable.setCellSpacing(5);
			tricksFlexTable.setStyleName("roundedCornersBorder");
			buttonsPanel.add(okButton);
			buttonsPanel.add(cancelButton);
			buttonsPanel.setSpacing(5);
			scoreReportFlexTable.setWidget(0,0,team1Label);
			scoreReportFlexTable.setWidget(0,1,team1ScoreLabel);
			team1ScoreLabel.setStyleName("green");
			scoreReportFlexTable.setWidget(1,0,team2Label);
			scoreReportFlexTable.setWidget(1,1,team2ScoreLabel);
			team2ScoreLabel.setStyleName("green");
			scoreReportFlexTable.setWidget(2,0,totalTricks);
			scoreReportFlexTable.setStyleName("roundedCornersBorder");
			totalTricks.setStyleName("green");
			hPanel.add(tricksFlexTable);
			hPanel.add(scoreReportFlexTable);
			hPanel.setSpacing(5);
			mainPanel.add(hPanel);
			mainPanel.add(buttonsPanel);
			updateTricksTaken();
			this.show();
		}
	}

	private void updateTricksTaken() {
		player1TricksTaken = Integer.parseInt(player1TB.getText());
		player2TricksTaken = Integer.parseInt(player2TB.getText());
		team1ScoreLabel.setText(getTeamRoundScore(player1LastBet, player2LastBet, player1TricksTaken, player2TricksTaken).toString());
		if(Integer.parseInt(team1ScoreLabel.getText()) < 0) {
			team1ScoreLabel.setStyleName("red");
		}
		else team1ScoreLabel.setStyleName("green");
		player3TricksTaken = Integer.parseInt(player3TB.getText());
		player4TricksTaken = Integer.parseInt(player4TB.getText());
		team2ScoreLabel.setText(getTeamRoundScore(player3LastBet, player4LastBet, player3TricksTaken, player4TricksTaken).toString());
		if(Integer.parseInt(team2ScoreLabel.getText()) < 0) {
			team2ScoreLabel.setStyleName("red");
		}
		else team2ScoreLabel.setStyleName("green");
		totalTricks.setText("Total Tricks: " + new Integer(player1TricksTaken + player2TricksTaken + player3TricksTaken + player4TricksTaken).toString());
		if((player1TricksTaken + player2TricksTaken + player3TricksTaken + player4TricksTaken) != 13) {
			totalTricks.setStyleName("red");
		}
		else totalTricks.setStyleName("green");
	}

	public Integer getTeamRoundScore(Integer player1Bet, Integer player2Bet, Integer player1Tricks, Integer player2Tricks) {
		int combinedBet = (player1Bet + player2Bet);
		int combinedTricks = (player1Tricks + player2Tricks);
		if(player1Bet == 0) {
			if(player1Tricks == 0 && player2Bet <= player2Tricks) {
				return new Integer(60 + 10*player2Bet + (player2Tricks - player2Bet));
			}
			else return new Integer(-60 - 10*player2Bet);
		}
		else if(player2Bet == 0) {
			if(player2Tricks == 0 && player1Bet <= player1Tricks) {
				return new Integer(60 + 10*player1Bet + (player1Tricks - player1Bet));
			}
			else return new Integer(-60 - 10*player1Bet);
		}
		else if((2*combinedBet) <= combinedTricks) {
			return new Integer(-(10*combinedBet));
		}
		else if(combinedBet <= combinedTricks) {
			return new Integer(10*combinedBet + (combinedTricks - combinedBet));
		}
		else return new Integer(-10*combinedBet);
	}
	
	@Override
	public void onClick(ClickEvent event) {
		Object sender = event.getSource();
		if(sender.equals(okButton)) {
			if(!player1TB.getText().matches("[0-9\\s]{1,2}") || !player2TB.getText().matches("[0-9\\s]{1,2}") || !player3TB.getText().matches("[0-9\\s]{1,2}") || !player4TB.getText().matches("[0-9\\s]{1,2}")) {
				Window.alert("Tricks must be numbers 1-13");
			} else if((player1TricksTaken + player2TricksTaken + player3TricksTaken + player4TricksTaken) != 13) {
				Window.alert("There must be 13 Total Tricks");
			} else {
				this.hide();
				team1.getPlayer1().getTricksTakenList().add(player1TricksTaken);
				team1.getPlayer2().getTricksTakenList().add(player2TricksTaken);
				team2.getPlayer1().getTricksTakenList().add(player3TricksTaken);
				team2.getPlayer2().getTricksTakenList().add(player4TricksTaken);
				team1.getRoundTrickesTaken().add(player1TricksTaken + player2TricksTaken);
				team2.getRoundTrickesTaken().add(player3TricksTaken + player4TricksTaken);
				team1.getRoundScores().add(Integer.parseInt(team1ScoreLabel.getText()));
				team1.updateTotalScores();
				team2.getRoundScores().add(Integer.parseInt(team2ScoreLabel.getText()));
				team2.updateTotalScores();
				spadesScores.resetFlexTables();
				Integer team1Score = team1.getTotalScores().get(team1.getTotalScores().size() - 1);
				Integer team2Score = team2.getTotalScores().get(team2.getTotalScores().size() - 1);
				SpadesScoresUtil.updateAnaylsisTable(1,1, team1.getPlayer1().getBetList().size(),spadesScores.analysisFlexTable,team1.getPlayer1(),team1.getPlayer2(),team2.getPlayer1(),team2.getPlayer2());
				if(team1Score >= spadesScores.objective && team1Score > team2Score) {
					new WinnerMessageDialogBox(team1);
				}
				if(team2Score >= spadesScores.objective && team2Score > team1Score) {
					new WinnerMessageDialogBox(team2);
				}
			}
		} else if(sender.equals(cancelButton)) {
			this.hide();
		}
	}
	
	

	@Override
	public void onMouseOver(MouseOverEvent event) {
		Object sender = event.getSource();
		if(sender instanceof TextBox) {
			((TextBox) sender).selectAll();
		}
		
	}

	@Override
	public void onMouseOut(MouseOutEvent event) {
		updateTricksTaken();
	}
	
}
