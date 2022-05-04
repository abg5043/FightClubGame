package org.example.fightclubgame;

import edu.missouriwestern.csc346.monsters.GameManager;
import edu.missouriwestern.csc346.monsters.Player;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class FxGameManager extends GameManager {
  private Boolean firstTime = true;

  @Override
  public String displayMessage(String message) {

    System.out.println(message);


    if(message.contains("Maximum remaining rounds")) {
      if(!firstTime) {
        App.blankLine();
        App.blankLine();
        App.blankLine();
        App.blankLine();
      } else {
        firstTime = false;
      }
      String[] roundInfo = message.split("═════════");
      String roundTitle = roundInfo[1];
      String roundNumber = roundTitle.substring(7,8);
      String[] currentRoster = roundInfo[2].substring(4).split("  ");

      displayCurrentRoster(currentRoster, roundNumber);
      displayRoundAnnouncement(message.split("═════════")[1]);
    }else if(message.contains("WINS!")) {
      App.addLabelToCenter(message);

      Alert a = new Alert(Alert.AlertType.INFORMATION);
      a.setTitle("Game Set Match");
      a.setHeaderText("A WINNER IS CROWNED");
      a.setContentText(message);
      a.show();
    } else {
      App.addLabelToCenter(message);
      if(message.contains(System.getProperty("line.separator"))) {
        App.blankLine();
      }
    }
    return message;
  }

  public static void displayCurrentRoster(String[] roster, String roundNumber) {
    App.currentRosterBox.getChildren().clear();
    App.blankLine();

    TextField title = new TextField("ROSTER FOR ROUND " + roundNumber);
    title.setAlignment(Pos.CENTER);
    title.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
    title.setBackground(new Background(new BackgroundFill(Color.DIMGRAY, null, null)));
    App.currentRosterBox.getChildren().add(title);

    for (String player : roster) {
      TextField textField = new TextField(player.toString());
      textField.setAlignment(Pos.CENTER);
      App.currentRosterBox.getChildren().add(textField);

    }
    App.blankLine();
  }

  @Override
  public void fightAnnouncement(Player p1, Player p2) {
    String message = String.format("\n----- %s (%1.0f%%) and %s (%1.0f%%) enter the game -----", p1, p1.getBody().getHealth() * 100.0D, p2, p2.getBody().getHealth() * 100.0D);
    App.fightAnnouncement.setText(message);
  }

  public String displayRoundAnnouncement(String message) {
    App.roundAnnouncement.setText(message);
    return message;
  }
}
