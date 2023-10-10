package edu.ntnu.stud.commands;

import edu.ntnu.stud.TrainDispatchApp;
import edu.ntnu.stud.utils.UserInputHandler;

/**
 * Command for finding train departures by destination.
 */
public class FindByDestinationCommand extends Command {
  public FindByDestinationCommand() {
    super("find by dest", "Find train departures by destination");
  }

  @Override
  public void execute(TrainDispatchApp app) {
    System.out.println("Find a train departure by train number");

    var input = new UserInputHandler(app);
    var destination = input.getDestination();

    var departures = app.getDeparturesByDestination(destination);

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
