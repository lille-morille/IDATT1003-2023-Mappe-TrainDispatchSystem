package edu.ntnu.stud;

import edu.ntnu.stud.commands.AddCommand;
import edu.ntnu.stud.commands.Command;
import edu.ntnu.stud.commands.ExitCommand;
import edu.ntnu.stud.commands.FindByDestinationCommand;
import edu.ntnu.stud.commands.FindByTrainNumberCommand;
import edu.ntnu.stud.commands.PrintDeparturesCommand;
import edu.ntnu.stud.commands.SetClockCommand;
import edu.ntnu.stud.commands.SetDelayCommand;
import edu.ntnu.stud.commands.SetTrackCommand;
import edu.ntnu.stud.models.TrainDeparture;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * This is the main class for the train dispatch application.
 */
public class TrainDispatchApp {
  public TrainDispatchApp() {
    departures = new ArrayList<>();
    clock = LocalTime.of(0, 0);
  }

  /**
   * The entry point of the program.
   */
  public static void main(String[] args) {
    var app = new TrainDispatchApp();
    app.init();
    app.start();
  }

  /**
   * The list of commands available in the application.
   */
  private static final Command[] COMMANDS = {
      new PrintDeparturesCommand(),
      new AddCommand(),
      new SetDelayCommand(),
      new SetTrackCommand(),
      new FindByTrainNumberCommand(),
      new FindByDestinationCommand(),
      new SetClockCommand(),
      new ExitCommand(),
  };

  private void runCommand(String command) {
    try {
      // Try to parse the command as a number
      int commandIndex = Integer.parseInt(command) - 1;

      COMMANDS[commandIndex].execute(this);
    } catch (NumberFormatException ignored) {
      // If that did not work, try the command name instead
      Arrays.stream(COMMANDS)
          .filter(c -> c.getName().equals(command))
          .findFirst()
          .ifPresentOrElse(
              (c) -> c.execute(this),
              () -> System.out.printf("Unknown command: %s, please try again.%n", command)
          );
    } catch (IndexOutOfBoundsException e) {
      System.out.printf("Please specify a command in the range 1-%d, or the command name.",
          COMMANDS.length);
    }
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
      System.out.println("Enter a command (name or number):");
      for (int i = 0; i < COMMANDS.length; i++) {
        Command command = COMMANDS[i];
        System.out.printf("[%d] %s - %s%n", i + 1, command.getName(), command.getDescription());
      }
      System.out.println();
      System.out.print("> ");
      Scanner scanner = new Scanner(System.in);
      String command = scanner.nextLine();
      runCommand(command);
      System.out.println();
    }
  }

  private ArrayList<TrainDeparture> departures;

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
   * Gets the list of departures in correct order.
   * The list is sorted by departure time, then by track number.
   *
   * @return The list of departures
   */
  public List<TrainDeparture> getDepartures() {
    departures.sort(Comparator.comparing(TrainDeparture::getFinalDepartureTime)
        .thenComparingInt(TrainDeparture::getTrack));
    return departures;
  }

  /**
   * Gets a departure by train number.
   *
   * @param trainNumber The train number to query by.
   * @return The departure with the given train number, or null if not found.
   */
  public Optional<TrainDeparture> getDepartureByTrainNumber(int trainNumber) {
    return getDepartures().stream()
        .filter(d -> d.getTrainNumber() == trainNumber)
        .findFirst();
  }

  /**
   * Gets a list of departures by destination.
   *
   * @param destination The destination to query by.
   * @return The list of departures. ! May be empty !
   */
  public List<TrainDeparture> getDeparturesByDestination(String destination) {
    return getDepartures().stream()
        .filter(d -> d.getDestination().equals(destination)).toList();
  }

  private LocalTime clock;

  public LocalTime getClock() {
    return clock;
  }

  /**
   * Updates the applications clock.
   * Also removes departures that have already departed.
   */
  public void setClock(LocalTime clock) {
    this.clock = clock;

    // Remove departures before this time
    // We use !isAfter because we want to remove departures that are exactly on time
    // isBefore checks for strictly before, not equal
    departures.removeIf(d -> !d.getFinalDepartureTime().isAfter(clock));
  }

  private List<TrainDeparture> getSampleDepartures() {
    return Arrays.stream(new TrainDeparture[] {
        new TrainDeparture(
            10,
            30,
            "F1",
            1,
            "Bergen",
            1,
            10
        ),
        new TrainDeparture(
            10,
            35,
            "RE2",
            2,
            "Trondheim",
            2,
            5
        ),
        new TrainDeparture(
            10,
            40,
            "R2",
            3,
            "Stavanger",
            1,
            0
        ),
        new TrainDeparture(
            10,
            45,
            "FLY1",
            4,
            "Oslo",
            2,
            0
        ),
    }).toList();
  }

  /**
   * Generates a train number that is unique ; not already in use.
   */
  public int generateTrainNumber() {
    int trainNumber = 1;
    while (true) {
      if (getDepartureByTrainNumber(trainNumber).isEmpty()) {
        return trainNumber;
      }
      trainNumber++;
    }
  }
}
