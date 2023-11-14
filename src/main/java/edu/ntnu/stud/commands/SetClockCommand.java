package edu.ntnu.stud.commands;

import edu.ntnu.stud.input.UserInputHandler;
import edu.ntnu.stud.models.TrainDepartureManager;

/**
 * Set the clock to a given time.
 */
public class SetClockCommand extends Command {
  public SetClockCommand() {
    super("set clock", "Set the clock to a given time");
  }

  @Override
  public void execute(TrainDepartureManager manager) {
    System.out.println("Set the clock to a given time");

    var input = new UserInputHandler(manager);
    var time = input.getTime();

    manager.setClock(time);

    System.out.println();
    new PrintDeparturesCommand().execute(manager);
  }
}
