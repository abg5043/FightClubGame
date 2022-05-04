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
 * Driver for JavaFX version of fight game
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


  @Override
  public void start(Stage stage) {
    stage.setTitle("Battle for Tar Valon");
    String s = "Welcome to the Battle Royale!";

    addLabelToCenter(s);

    ScrollPane scrollPane = new ScrollPane(center);
    borderPane.setCenter(scrollPane);

    setFightAnnouncement(new TextField("No fights to announce. Please stand by."));
    fightAnnouncement.setAlignment(Pos.CENTER);
    fightAnnouncement.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, null, null)));
    fightAnnouncement.setStyle("-fx-text-fill: grey; -fx-font-size: 16px;");
    borderPane.setBottom(fightAnnouncement);

    setRoundAnnouncement(new TextField("No rounds to announce. Please stand by."));
    roundAnnouncement.setAlignment(Pos.CENTER);
    roundAnnouncement.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, null, null)));
    roundAnnouncement.setStyle("-fx-text-fill: grey; -fx-font-size: 16px;");
    borderPane.setTop(roundAnnouncement);


    overallRosterBox.setMinWidth(250);
    borderPane.setRight(overallRosterBox);

    currentRosterBox.setMinWidth(250);
    borderPane.setLeft(currentRosterBox);

    var scene = new Scene(borderPane, 1024, 768);
    stage.setScene(scene);
    stage.show();

    mainApp();
  }

  public static void main(String[] args) {
    launch();
  }

  public static void addLabelToCenter(String[] messages) {
    for (String message : messages) {
      addLabelToCenter(message);
    }
  }

  public static void addLabelToCenter(ArrayList<String> messages) {
    for (String message : messages ) {
      addLabelToCenter(message);
    }
  }

  public static void addLabelToCenter(String message) {
    center.getChildren().add(new Label("    " + message));
  }

  public static void mainApp() {
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

    FxGameManager gm = new FxGameManager();

    displayOverallRoster(roster);

    gm.contest(roster);
  }

  public static void blankLine() {
    addLabelToCenter(" ");
  }

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