package edu.ntnu.stud.commands;

import edu.ntnu.stud.input.UserInputHandler;
import edu.ntnu.stud.models.TrainDepartureManager;

/**
 * Sets the delay to a train departure.
 */
public final class SetDelayCommand extends Command {
  public SetDelayCommand() {
    super("set delay", "Sets the delay of a train departure");
  }

  @Override
  public void execute(TrainDepartureManager manager) {
    new PrintDeparturesCommand().execute(manager);

    System.out.println("Add delay to a train departure");

    var input = new UserInputHandler(manager);

    var departure = input.getDeparture();
    var delay = input.getDelay();

    departure.setDelay(delay);

    System.out.println("Delay added successfully!");
    System.out.println();
    new PrintDeparturesCommand().execute(manager);
  }
}
