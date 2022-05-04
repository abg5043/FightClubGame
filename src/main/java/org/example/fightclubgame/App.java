package org.example.fightclubgame;

import edu.missouriwestern.csc346.monsters.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Driver for JavaFX version of a fighting game
 *
 * @author Aaron Grant
 * @since May 2022
 */
public class App extends Application {
  static VBox center = new VBox();
  static BorderPane borderPane = new BorderPane();
  static VBox overallRosterBox = new VBox();
  public static TextField fightAnnouncement;
  public static TextField roundAnnouncement;
  public static VBox currentRosterBox = new VBox();


  //start method is what initiates JavaFX; this encapsulates the setting up of JavaFX components
  @Override
  public void start(Stage stage) {
    stage.setTitle("Battle for Tar Valon");
    String s = "Welcome to the Battle Royale!";

    //set up the center of the borderpane
    addLabelToCenter(s);
    ScrollPane scrollPane = new ScrollPane(center);
    borderPane.setCenter(scrollPane);

    //set up the bottom of border pane as fight announcement
    setFightAnnouncement(new TextField("No fights to announce. Please stand by."));
    fightAnnouncement.setAlignment(Pos.CENTER);
    fightAnnouncement.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, null, null)));
    fightAnnouncement.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
    borderPane.setBottom(fightAnnouncement);

    //set up the top of the border pane as a round announcement
    setRoundAnnouncement(new TextField("No rounds to announce. Please stand by."));
    roundAnnouncement.setAlignment(Pos.CENTER);
    roundAnnouncement.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, null, null)));
    roundAnnouncement.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
    borderPane.setTop(roundAnnouncement);

    //set up the right of the border pane as a log of all current monsters in the round
    currentRosterBox.setMinWidth(250);
    borderPane.setRight(currentRosterBox);

    //set up the left of the border pane as a log of all monsters who are competing in the tournament
    overallRosterBox.setMinWidth(250);
    borderPane.setLeft(overallRosterBox);

    //set the dimensions of the screen and start the game
    var scene = new Scene(borderPane, 1024, 768);
    stage.setScene(scene);
    stage.show();
    mainApp();
  }

  //main method launches start method
  public static void main(String[] args) {
    launch();
  }

  //method for adding labels to the center pain by passing in a string array
  public static void addLabelToCenter(String[] messages) {
    for (String message : messages) {
      addLabelToCenter(message);
    }
  }

  //method for adding labels to the center pain by passing in an ArrayList
  public static void addLabelToCenter(ArrayList<String> messages) {
    for (String message : messages ) {
      addLabelToCenter(message);
    }
  }

  //method for adding labels to the center pain by passing in a single string
  public static void addLabelToCenter(String message) {
    center.getChildren().add(new Label("    " + message));
  }

  //main driver for the app. This encapsulates all game logic
  public static void mainApp() {

    //create a roster and add players to it
    ArrayList<Player> roster = new ArrayList<>();
    Player p = new Player ("Ptomain Tony", new Cook());
    Player q = new Player("Broccoli Spears", new Cook());
    Player r = new Player("Honey Boo Boo", new Badger());
    Player s = new Player("Charlene", new Badger());
    roster.add(p);
    roster.add(q);
    roster.add(r);
    roster.add(s);
    roster.add(new Player("Ricky", new GiantRat()));
    roster.add(new Player("Randy", new GiantRat()));
    roster.add(new Player("Jimmy", new Rose()));
    roster.add(new Player("Potato", new Rose()));
    roster.add(new Player("Sunny", new Sunflower()));
    roster.add(new Player("Cher", new Sunflower()));
    roster.add(new Player("Dr. May", new Professor()));


    //adds the overall roster to the overallRosterBox
    displayOverallRoster(roster);

    //initiate a new GameManager
    FxGameManager gm = new FxGameManager();

    //starts the contest with the game manager
    gm.contest(roster);
  }

  //adds a blank line to the center pane
  public static void blankLine() {
    addLabelToCenter(" ");
  }

  /**
   * this adds and stylizes a roster to the overallRosterBox
   *
   * @param roster - an arraylist of players
   */
  private static void displayOverallRoster(ArrayList<Player> roster) {
    overallRosterBox.getChildren().clear();
    blankLine();

    TextField title = new TextField("THE ORIGINAL ROSTER");
    title.setAlignment(Pos.CENTER);
    title.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
    title.setBackground(new Background(new BackgroundFill(Color.DIMGRAY, null, null)));
    overallRosterBox.getChildren().add(title);

    for (Player player : roster) {
      TextField textField = new TextField(player.toString());
      textField.setAlignment(Pos.CENTER);
      overallRosterBox.getChildren().add(textField);

    }
    blankLine();
  }

  public static void setFightAnnouncement(TextField fightAnnouncement) {
    App.fightAnnouncement = fightAnnouncement;
  }

  public static void setRoundAnnouncement(TextField roundAnnouncement) {
    App.roundAnnouncement = roundAnnouncement;
  }
}