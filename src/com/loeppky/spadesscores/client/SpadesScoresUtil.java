package com.loeppky.spadesscores.client;

import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusWidget;

public class SpadesScoresUtil {


	
	public static void addMouseOverHandler(MouseOverHandler handler, FocusWidget... widgets) {
		for (FocusWidget widget : widgets) {
			widget.addMouseOverHandler(handler);
		}
	}
	
	public static void addMouseOutHandler(MouseOutHandler handler, FocusWidget... widgets) {
		for (FocusWidget widget : widgets) {
			widget.addMouseOutHandler(handler);
		}
	}
	
	public static void updateAnaylsisTable(int startRow, int startColumn, int round, FlexTable flexTable, Player... players) {
		int playerNumber = 0;
		for(Player player : players) {
			playerNumber++;
			Integer sumOfBets = 0;
			for(Integer bet : player.getBetList()) {
				sumOfBets = sumOfBets + bet;
			}
			flexTable.setText(startRow + playerNumber - 1, startColumn, sumOfBets.toString());
			
			Integer sumOfTricksTaken = 0;
			for(Integer tricksTaken : player.getTricksTakenList()) {
				sumOfTricksTaken = sumOfTricksTaken + tricksTaken;
			}
			flexTable.setText(startRow + playerNumber - 1, startColumn + 1, sumOfTricksTaken.toString());
			
			int playerLastBet = player.getBetList().get(round - 1);
			int playerTricksTaken = player.getTricksTakenList().get(round - 1);
			if(playerLastBet > playerTricksTaken) {
				player.setRating((player.getRating()*(round-1) + 100 - 30*(playerLastBet - playerTricksTaken))/round);
			} else if(playerLastBet == 0) {
				player.setRating((player.getRating()*(round-1) + 120 - 60*(playerTricksTaken))/round);
			} else if(playerTricksTaken > playerLastBet) {
				player.setRating((player.getRating()*(round-1) + 100 - 10*(playerTricksTaken - playerLastBet))/round);
			} else {
				player.setRating((player.getRating()*(round-1) + 100)/round);
			}
			flexTable.setText(startRow + playerNumber - 1, startColumn + 2, player.getRating().toString());
		}
	}
}
