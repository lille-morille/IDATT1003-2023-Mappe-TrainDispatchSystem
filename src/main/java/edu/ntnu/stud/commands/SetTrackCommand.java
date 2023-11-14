package edu.ntnu.stud.commands;

import edu.ntnu.stud.input.UserInputHandler;
import edu.ntnu.stud.models.TrainDepartureManager;

/**
 * Command for setting a new track on a train.
 */
public class SetTrackCommand extends Command {
  public SetTrackCommand() {
    super("set track", "Sets the track for a train");
  }

  @Override
  public void execute(TrainDepartureManager manager) {
    System.out.println("Set track for a train");

    var input = new UserInputHandler(manager);

    var departure = input.getDeparture();
    var track = input.getTrack();

    departure.setTrack(track);
    System.out.println("Track set successfully!");
    System.out.println();
    new PrintDeparturesCommand().execute(manager);
  }
}
