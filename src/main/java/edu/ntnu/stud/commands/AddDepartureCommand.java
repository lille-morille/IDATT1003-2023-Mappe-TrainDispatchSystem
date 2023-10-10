package edu.ntnu.stud.commands;

import edu.ntnu.stud.TrainDispatchApp;
import edu.ntnu.stud.models.TrainDeparture;
import edu.ntnu.stud.utils.UserInputHandler;

/**
 * Adds a train departure to the list of departures.
 */
public final class AddDepartureCommand extends Command {
  public AddDepartureCommand() {
    super("add", "Add a train departure");
  }

  @Override
  public void execute(TrainDispatchApp app) {
    System.out.println("Add train departure");

    var input = new UserInputHandler(app);
    var trainNumber = input.getTrainNumber();
    var departureTime = input.getTime();
    var line = input.getLine();
    var destination = input.getDestination();
    var track = input.getTrack();
    var delay = input.getDelay();

    System.out.println("Train departure added successfully!");
    app.addDeparture(
        new TrainDeparture(departureTime, line, trainNumber, destination,
            track, delay));

    // Display the departures after inserting
    new PrintDeparturesCommand().execute(app);
  }
}
