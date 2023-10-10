package edu.ntnu.stud.commands;

import edu.ntnu.stud.TrainDispatchApp;

/**
 * Exits the program.
 */
public final class ExitCommand extends Command {
  public ExitCommand() {
    super("exit", "Exits the program");
  }

  @Override
  public void execute(TrainDispatchApp app) {
    System.out.println("Exiting Train Dispatch App ...");
    System.exit(0);
  }
}
