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

public class NewBetsDialogBox extends DialogBox implements ClickHandler, MouseOverHandler, MouseOutHandler {
	SpadesScores spadesScores;
	VerticalPanel mainPanel = new VerticalPanel();
	FlexTable betsFlexTable = new FlexTable();
	Label player1Label = new Label();
	TextBox player1TB = new TextBox();
	Label player2Label = new Label();
	TextBox player2TB = new TextBox();
	Label player3Label = new Label();
	TextBox player3TB = new TextBox();
	Label player4Label = new Label();
	TextBox player4TB = new TextBox();
	Label differenceLabel = new Label();
	HorizontalPanel buttonsPanel = new HorizontalPanel();
	Button okButton = new Button("OK");
	Button cancelButton = new Button("Cancel");
	Team team1;
	Team team2;
	
	
	NewBetsDialogBox(SpadesScores spadesScores, Team team1, Team team2) {
		if(team1.getRoundScores().size() < team1.getPlayer1().getBetList().size()) {
			Window.alert("Please Enter Round Scores First");
		}
		else {
			this.spadesScores = spadesScores;
			player1TB.addMouseOverHandler(this);
			player2TB.addMouseOverHandler(this);
			player3TB.addMouseOverHandler(this);
			player4TB.addMouseOverHandler(this);
			player1TB.addMouseOutHandler(this);
			player2TB.addMouseOutHandler(this);
			player3TB.addMouseOutHandler(this);
			player4TB.addMouseOutHandler(this);
			this.add(mainPanel);
			this.team1 = team1;
			this.team2 = team2;
			okButton.addClickHandler(this);
			cancelButton.addClickHandler(this);
			this.setAnimationEnabled(true);
			this.setGlassEnabled(true);
			this.setText("New Bets");
			player1Label.setText(team1.getPlayer1().getName() + ":");
			player1TB.setText("0");
			player1TB.setWidth("50px");
			betsFlexTable.setWidget(2,0,player1Label);
			betsFlexTable.setWidget(3,0,player1TB);
			player2Label.setText(team1.getPlayer2().getName()+ ":");
			player2TB.setText("0");
			player2TB.setWidth("50px");
			betsFlexTable.setWidget(2,3,player2Label);
			betsFlexTable.setWidget(3,3,player2TB);
			player3Label.setText(team2.getPlayer1().getName()+ ":");
			player3TB.setText("0");
			player3TB.setWidth("50px");
			betsFlexTable.setWidget(0,2,player3Label);
			betsFlexTable.setWidget(1,2,player3TB);
			player4Label.setText(team2.getPlayer2().getName() + ":");
			player4TB.setText("0");
			player4TB.setWidth("50px");
			betsFlexTable.setWidget(4,2,player4Label);
			betsFlexTable.setWidget(5,2,player4TB);
			betsFlexTable.setCellSpacing(5);
			betsFlexTable.setStyleName("roundedCornersFlexTable");
			updateDifferenceLabel();
			buttonsPanel.add(okButton);
			buttonsPanel.add(cancelButton);
			buttonsPanel.setSpacing(5);
			mainPanel.add(betsFlexTable);
			mainPanel.add(differenceLabel);
			mainPanel.add(buttonsPanel);
			this.show();
		}
	}


	@Override
	public void onClick(ClickEvent event) {
		Object sender = event.getSource();
		if(sender == okButton) {
			if(!player1TB.getText().matches("[0-9\\s]{1,2}") || !player2TB.getText().matches("[0-9\\s]{1,2}") || !player3TB.getText().matches("[0-9\\s]{1,2}") || !player4TB.getText().matches("[0-9\\s]{1,2}")) {
				Window.alert("Bets must be numbers 0-13");
			}
			
			int player1Bet = Integer.parseInt(player1TB.getText());
			int player2Bet = Integer.parseInt(player2TB.getText());
			int player3Bet = Integer.parseInt(player3TB.getText());
			int player4Bet = Integer.parseInt(player4TB.getText());
			
			if((player1Bet + player2Bet + player3Bet + player4Bet) == 13) {
				Window.alert("Bets can't total 13");
			}
			else if((player1Bet + player2Bet) < 4) {
				Window.alert("Team 1 must bet at least 4");
			}
			else if((player3Bet + player4Bet) < 4) {
				Window.alert("Team 2 must bet at least 4");
			}
			else {
				team1.getPlayer1().getBetList().add(player1Bet);
				team1.getPlayer2().getBetList().add(player2Bet);
				team2.getPlayer1().getBetList().add(player3Bet);
				team2.getPlayer2().getBetList().add(player4Bet);
				this.hide();
				spadesScores.resetFlexTables();
			}
		}
		
		if(sender == cancelButton) {
			this.hide();
		}
	}


	@Override
	public void onMouseOver(MouseOverEvent event) {
		Object sender = event.getSource();
		if (sender instanceof TextBox) {
			((TextBox)sender).selectAll();
		}
	}


	@Override
	public void onMouseOut(MouseOutEvent event) {
		updateDifferenceLabel();
	}


	private void updateDifferenceLabel() {
		Integer difference = new Integer((Integer.parseInt(player1TB.getText()) + Integer.parseInt(player2TB.getText()) + Integer.parseInt(player3TB.getText()) + Integer.parseInt(player4TB.getText()) - 13));
		differenceLabel.setText("Difference from 13: " + difference.toString());
		if(difference >= 0) {
			differenceLabel.setStyleName("red");
		}
	}
}
