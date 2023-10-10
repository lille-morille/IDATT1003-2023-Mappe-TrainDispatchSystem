package edu.ntnu.stud.commands;

import edu.ntnu.stud.TrainDispatchApp;
import edu.ntnu.stud.models.TrainDeparture;
import java.util.List;

public abstract class Command {
  private final String name;
  private final String description;

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public Command(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public abstract void execute(TrainDispatchApp app);
}
