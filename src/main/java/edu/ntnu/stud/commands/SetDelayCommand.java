package edu.ntnu.stud.commands;

import edu.ntnu.stud.TrainDispatchApp;
import edu.ntnu.stud.models.TrainDeparture;
import edu.ntnu.stud.utils.UserInputHandler;
import java.time.Duration;
import java.util.Scanner;

/**
 * Sets the delay to a train departure.
 */
public final class SetDelayCommand extends Command {
  public SetDelayCommand() {
    super("set delay", "Sets the delay to a train departure");
  }

  @Override
  public void execute(TrainDispatchApp app) {
    new PrintDeparturesCommand().execute(app);

    System.out.println("Add delay to a train departure");

    var input = new UserInputHandler(app);

    var departure = input.getDeparture();
    var delay = input.getDelay();

    departure.setDelay(delay);
  }
}
