package com.loeppky.spadesscores.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;

public class WinnerMessageDialogBox {
	
	private Team team;
	private final DialogBox dialogBox;
	private final Label label;
	
	WinnerMessageDialogBox(Team team) {
		label = new Label(team.getName() + " WINS!");
		label.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});
		label.setStyleName("winnerLabel");
		dialogBox = new DialogBox();
		dialogBox.setAnimationEnabled(true);
		dialogBox.setGlassEnabled(true);
		dialogBox.add(label);
		dialogBox.show();
		dialogBox.center();
	}
}
