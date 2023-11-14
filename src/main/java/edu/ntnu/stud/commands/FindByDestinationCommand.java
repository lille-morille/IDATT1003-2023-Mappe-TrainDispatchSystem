package edu.ntnu.stud.commands;

import edu.ntnu.stud.input.UserInputHandler;
import edu.ntnu.stud.models.TrainDepartureManager;

/**
 * Command for finding train departures by destination.
 */
public class FindByDestinationCommand extends Command {
  public FindByDestinationCommand() {
    super("find by dest", "Find train departures by destination");
  }

  @Override
  public void execute(TrainDepartureManager manager) {
    System.out.println("Find a train departure by train number");

    var input = new UserInputHandler(manager);
    var destination = input.getDestination();

    var departures = manager.getDeparturesByDestination(destination);

    if (departures.isEmpty()) {
      System.out.println("No departures found");
    } else if (departures.size() == 1) {
      System.out.println("Found 1 departure:");
      System.out.println(departures.get(0).toString());
    } else {
      System.out.println("Found " + departures.size() + " departures:");
      for (var departure : departures) {
        System.out.println(departure.toString());
      }
    }
  }
}
