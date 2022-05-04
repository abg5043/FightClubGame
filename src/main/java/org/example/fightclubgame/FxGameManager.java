package org.example.fightclubgame;

import edu.missouriwestern.csc346.monsters.GameManager;
import edu.missouriwestern.csc346.monsters.Player;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

/**
 * A class that manages the game. This extends GameManager from the command-line version of this app
 * so that it can function within a GUI
 */
public class FxGameManager extends GameManager {
  private Boolean firstTimeRoundAnnouncement = true; //keeps track of if displayMessage had found a round announcement yet

  /**
   * A method that handles displaying messages within the GUI app
   *
   * @param message - a string message to be displayed
   */
  @Override
  public String displayMessage(String message) {
    //determine if this is a round announcement or not
    if(message.contains("Maximum remaining rounds")) {
      //first time announcmeents should not have blank lines added
      if(!firstTimeRoundAnnouncement) {
        App.blankLine();
        App.blankLine();
        App.blankLine();
        App.blankLine();
      } else {
        firstTimeRoundAnnouncement = false;
      }

      //if it's a round announcement, break out the info
      String[] roundInfo = message.split("═════════");
      //first fine the round title
      String roundTitle = roundInfo[1];
      //then the round number
      String roundNumber = roundTitle.substring(7,8);
      //finally, make a string array of current roster
      String[] currentRoster = roundInfo[2].substring(4).split("  ");

      //add this information to the display
      displayCurrentRoster(currentRoster, roundNumber);
      displayRoundAnnouncement(roundTitle);

    } else if(message.contains("WINS!")) {
      //if this is a win announcement, create an alert after adding to the center
      App.addLabelToCenter(message);

      Alert a = new Alert(Alert.AlertType.INFORMATION);
      a.setTitle("Game Set Match");
      a.setHeaderText("A WINNER IS CROWNED");
      a.setContentText(message);
      a.show();
    } else {
      //just add info to the center if it's anything else, but if there was a line separator, make sure to add that too
      App.addLabelToCenter(message);
      if(message.contains(System.getProperty("line.separator"))) {
        App.blankLine();
      }
    }
    return message;
  }

  /**
   * A method for displaying the current roster in the currentRosterBox
   *
   * @param roster - a string array of player names and info
   * @param roundNumber - a string representation of the current round
   */
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

  /**
   * a method for settint the fight announcement text
   *
   * @param p1 - the first player in the fight
   * @param p2 - the second player in the fight
   */
  @Override
  public void fightAnnouncement(Player p1, Player p2) {
    String message = String.format("\n----- %s (%1.0f%%) and %s (%1.0f%%) enter the game -----", p1, p1.getBody().getHealth() * 100.0D, p2, p2.getBody().getHealth() * 100.0D);
    App.fightAnnouncement.setText(message);
  }

  /**
   * a method for displaying the current round announcement
   *
   * @param message - a String representation of that round.
   */
  public String displayRoundAnnouncement(String message) {
    App.roundAnnouncement.setText(message);
    return message;
  }
}
