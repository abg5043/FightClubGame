module org.example.fightclubgame {
  requires javafx.controls;
  requires javafx.fxml;
  requires Monsters;

  opens org.example.fightclubgame to javafx.fxml;
  exports org.example.fightclubgame;
}