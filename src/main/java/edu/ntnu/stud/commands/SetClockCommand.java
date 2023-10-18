package edu.ntnu.stud.commands;

import edu.ntnu.stud.TrainDispatchApp;
import edu.ntnu.stud.input.UserInputHandler;

/**
 * Set the clock to a given time.
 */
public class SetClockCommand extends Command {
  public SetClockCommand() {
    super("set clock", "Set the clock to a given time");
  }

  @Override
  public void execute(TrainDispatchApp app) {
    System.out.println("Set the clock to a given time");

    var input = new UserInputHandler(app);
    var time = input.getTime();

    app.setClock(time);

    System.out.println();
    new PrintDeparturesCommand().execute(app);
  }
}
