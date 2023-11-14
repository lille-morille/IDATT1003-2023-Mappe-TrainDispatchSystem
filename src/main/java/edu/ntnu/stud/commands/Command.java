package edu.ntnu.stud.commands;

import edu.ntnu.stud.models.TrainDepartureManager;

/**
 * Base class for all commands.
 * Contains a name, description and method for executing the command.
 */
public abstract class Command {
  private final String name;
  private final String description;

  /**
   * Get the name of the command.
   *
   * @return The name of the command.
   */
  public String getName() {
    return name;
  }

  /**
   * Get the description of the command.
   *
   * @return The description of the command.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Creates a new command with the given name and description.
   *
   * @param name        The name of the command
   * @param description The description of the command
   */
  public Command(String name, String description) {
    this.name = name;
    this.description = description;
  }

  /**
   * Executes the command.
   *
   * @param manager The manager to execute the command on
   */
  public abstract void execute(TrainDepartureManager manager);
}
