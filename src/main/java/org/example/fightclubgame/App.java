package org.example.fightclubgame;

import edu.missouriwestern.csc346.monsters.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
  BorderPane borderPane = new BorderPane();
  static VBox rosterBox = new VBox();

  @Override
  public void start(Stage stage) {
    stage.setTitle("Battle for Tar Valon");
    String s = "Welcome to the Battle Royale!";

    addLabelToCenter(s);

    ScrollPane scrollPane = new ScrollPane(center);
    borderPane.setCenter(scrollPane);


    rosterBox.setMinWidth(250);
    borderPane.setRight(rosterBox);


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

    displayRoster(roster);

    gm.contest(roster);

    displayRoster(roster);
  }

  public static void blankLine() {
    addLabelToCenter(" ");
  }

  private static void displayRoster(ArrayList<Player> roster) {
    rosterBox.getChildren().clear();
    blankLine();

    TextField title = new TextField("THE ROSTER");
    title.setAlignment(Pos.CENTER);
    title.setBackground(new Background(new BackgroundFill(Color.MINTCREAM, null, null)));
    rosterBox.getChildren().add(title);

    for (Player player : roster) {
      TextField textField = new TextField(player.toString());
      textField.setAlignment(Pos.CENTER);
      rosterBox.getChildren().add(textField);

    }
    blankLine();
  }
}