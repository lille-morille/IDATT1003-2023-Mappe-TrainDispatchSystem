package edu.ntnu.stud.commands;

import edu.ntnu.stud.TrainDispatchApp;
import edu.ntnu.stud.utils.TrainDepartureRenderer;

/**
 * Renders the departures in a table.
 */
public final class PrintDeparturesCommand extends Command {
  public PrintDeparturesCommand() {
    super("print", "Renders the departures in a table");
  }

  @Override
  public void execute(TrainDispatchApp app) {
    TrainDepartureRenderer.renderDepartures(app.getDepartures());
  }
}
