package edu.ntnu.stud;

import edu.ntnu.stud.commands.SetDelayCommand;
import edu.ntnu.stud.commands.AddDepartureCommand;
import edu.ntnu.stud.commands.Command;
import edu.ntnu.stud.commands.ExitCommand;
import edu.ntnu.stud.commands.RenderDeparturesCommand;
import edu.ntnu.stud.models.TrainDeparture;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * This is the main class for the train dispatch application.
 */
public class TrainDispatchApp {
  /**
   * The entry point of the program.
   */
  public static void main(String[] args) {
    TrainDispatchApp app = new TrainDispatchApp();
    app.init();
    app.start();
  }

  private static final Command[] COMMANDS = {
      new RenderDeparturesCommand(),
      new AddDepartureCommand(),
      new SetDelayCommand(),
      new ExitCommand(),
  };

  private void runCommand(String command) {
    Arrays.stream(COMMANDS)
        .filter(c -> c.getName().equals(command))
        .findFirst()
        .ifPresentOrElse(
            c -> c.execute(this),
            () -> System.out.println("Unknown command: " + command)
        );
  }


  private void init() {
    System.out.println("Starting train dispatch application...");
    // TODO remove sample data for production
    departures.addAll(getSampleDepartures());
  }

  private void start() {
    // Exit command handles exiting
    // noinspection InfiniteLoopStatement
    while (true) {
      System.out.println("============================");
      System.out.println("Enter a command:");
      for (Command command : COMMANDS) {
        System.out.println(command.getName() + " - " + command.getDescription());
      }
      System.out.println();
      System.out.print("> ");
      Scanner scanner = new Scanner(System.in);
      String command = scanner.nextLine();
      runCommand(command);
      System.out.println();
    }
  }

  /**
   * Safely adds a new train departure to the list of departures.
   *
   * @param departure The departure to add
   * @throws IllegalArgumentException If the train number is not unique, producing an illegal
   *                                  application state.
   */
  public void addDeparture(TrainDeparture departure) throws IllegalStateException {
    // Check that the train number is unique
    for (TrainDeparture d : departures) {
      if (d.getTrainNumber() == departure.getTrainNumber()) {
        throw new IllegalStateException("Train number must be unique");
      }
    }

    departures.add(departure);
  }

  /**
   * Gets the list of departures.
   *
   * @return The list of departures
   */
  public List<TrainDeparture> getDepartures() {
    return departures;
  }

  private final List<TrainDeparture> departures = new ArrayList<>();

  private List<TrainDeparture> getSampleDepartures() {
    return Arrays.stream(new TrainDeparture[] {
        new TrainDeparture(
            10,
            30,
            "L4",
            1,
            "Bergen",
            1,
            10
        ),
        new TrainDeparture(
            10,
            35,
            "R3",
            2,
            "Trondheim",
            2,
            5
        ),
        new TrainDeparture(
            10,
            40,
            "L4",
            3,
            "Stavanger",
            1,
            0
        ),
        new TrainDeparture(
            10,
            45,
            "R3",
            4,
            "Oslo",
            2,
            0
        ),
    }).toList();
  }
}
