package edu.ntnu.stud.commands;

import edu.ntnu.stud.input.UserInputHandler;
import edu.ntnu.stud.models.TrainDepartureManager;

/**
 * Command for finding train departures by destination.
 */
public class FindByTrainNumberCommand extends Command {
  public FindByTrainNumberCommand() {
    super("find by num", "Find a train departure by train number");
  }

  @Override
  public void execute(TrainDepartureManager manager) {
    System.out.println("Find a train departure by train number");

    var input = new UserInputHandler(manager);
    var departure = input.getDeparture();

    System.out.println(departure.toString());
  }
}
