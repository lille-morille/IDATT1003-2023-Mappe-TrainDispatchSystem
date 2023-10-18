package edu.ntnu.stud.commands;

import edu.ntnu.stud.TrainDispatchApp;
import edu.ntnu.stud.utils.UserInputHandler;

/**
 * Command for setting a new track on a train.
 */
public class SetTrackCommand extends Command {
  public SetTrackCommand() {
    super("set track", "Sets the track for a train");
  }

  @Override
  public void execute(TrainDispatchApp app) {
    System.out.println("Set track for a train");

    var input = new UserInputHandler(app);

    var departure = input.getDeparture();
    var track = input.getTrack();

    departure.setTrack(track);
    System.out.println("Track set successfully!");
    System.out.println();
    new PrintDeparturesCommand().execute(app);
  }
}
