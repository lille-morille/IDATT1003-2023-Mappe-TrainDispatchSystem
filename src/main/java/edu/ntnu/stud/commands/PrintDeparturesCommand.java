package edu.ntnu.stud.commands;

import edu.ntnu.stud.models.TrainDepartureManager;
import edu.ntnu.stud.utils.TrainDepartureRenderer;

/**
 * Renders the departures in a table.
 */
public final class PrintDeparturesCommand extends Command {
  public PrintDeparturesCommand() {
    super("print", "Renders the departures in a table");
  }

  @Override
  public void execute(TrainDepartureManager manager) {
    manager.removeDepartedDepartures();
    System.out.println("Clock: " + manager.getClock().toString());
    TrainDepartureRenderer.renderDepartures(manager.getDepartures());
  }
}
