package edu.ntnu.stud.commands;

import edu.ntnu.stud.TrainDispatchApp;
import edu.ntnu.stud.input.UserInputHandler;
import edu.ntnu.stud.models.TrainDeparture;

/**
 * Adds a train departure to the list of departures.
 */
public final class AddCommand extends Command {
  public AddCommand() {
    super("add", "Add a train departure");
  }

  @Override
  public void execute(TrainDispatchApp app) {
    System.out.println("Add train departure");

    var input = new UserInputHandler(app);

    var departureTime = input.getTime();
    var line = input.getLine();
    var trainNumber = input.getTrainNumber();
    var destination = input.getDestination();
    var track = input.getTrack();
    var delay = input.getDelay();

    TrainDeparture departure = new TrainDeparture(departureTime, line, trainNumber, destination,
        track, delay);

    app.addDeparture(departure);
    System.out.println("Train departure added successfully!");

    // Insert space before printing table
    System.out.println();

    // Display the departures after inserting
    new PrintDeparturesCommand().execute(app);
  }
}
