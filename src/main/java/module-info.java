module org.example.fightclubgame {
  requires javafx.controls;
  requires javafx.fxml;


  opens org.example.fightclubgame to javafx.fxml;
  exports org.example.fightclubgame;
}