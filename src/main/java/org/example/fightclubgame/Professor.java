package org.example.fightclubgame;

import edu.missouriwestern.csc346.monsters.Human;

public class Professor extends Human {


  public Professor() {
    super("Professor", 0.25, "X", "Y");
    this.setAggressiveness(0.25D);
    this.setDefenseMessage("Blocks with an eraser.");

  }

  @Override
  protected String getNoAttackMessage() {
    return "pauses to tell yet another war story.";
  }

  @Override
  protected void attack() {
    this.setAttackMessage("throws chalk");
    this.setAttackEffectiveness(0.01D * Math.random());
  }

  //Erasers aren't good at defending. So this gets a low defense score
  public void defend() {
    this.setDefenseEffectiveness(0.1D + Math.random());
  }

}
