package edu.ntnu.stud.commands;

import edu.ntnu.stud.TrainDispatchApp;
import edu.ntnu.stud.utils.TrainDepartureRenderer;

/**
 * Renders the departures in a table.
 */
public final class RenderDeparturesCommand extends Command {
  public RenderDeparturesCommand() {
    super("render", "Renders the departures in a table");
  }

  @Override
  public void execute(TrainDispatchApp app) {
    System.out.println("Rendering departures table ...");
    TrainDepartureRenderer.renderDepartures(app.getDepartures());
  }
}
