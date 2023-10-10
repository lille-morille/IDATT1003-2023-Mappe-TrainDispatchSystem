package edu.ntnu.stud.commands;

import edu.ntnu.stud.TrainDispatchApp;
import edu.ntnu.stud.utils.UserInputHandler;

public class FindByTrainNumber extends Command {
  public FindByTrainNumber() {
    super("find by num", "Find a train departure by train number");
  }

  @Override
  public void execute(TrainDispatchApp app) {
    System.out.println("Find a train departure by train number");

    var input = new UserInputHandler(app);
    var departure = input.getDeparture();

    System.out.println(departure.toString());
  }
}
