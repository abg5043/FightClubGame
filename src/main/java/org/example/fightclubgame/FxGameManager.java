package org.example.fightclubgame;

import edu.missouriwestern.csc346.monsters.GameManager;

public class FxGameManager extends GameManager {
  @Override
  public String displayMessage(String message) {
    App.addLabelToCenter(message);
    return message;
  }

}
