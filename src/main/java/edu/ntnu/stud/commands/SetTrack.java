package edu.ntnu.stud.commands;

import edu.ntnu.stud.TrainDispatchApp;

/**
 * Command for setting a new track on a train.
 */
public class SetTrack extends Command {
  public SetTrack() {
    super("set-track", "Sets the track for a train");
  }

  @Override
  public void execute(TrainDispatchApp app) {

  }
}
