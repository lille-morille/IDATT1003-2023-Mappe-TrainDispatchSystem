package edu.ntnu.stud.commands;

import edu.ntnu.stud.models.TrainDepartureManager;

public class SayHiCommand extends Command {
  public SayHiCommand() {
    super("say hi", "Says hi");
  }

  @Override
  public void execute(TrainDepartureManager manager) {
    System.out.println("Hi!");
  }
}
