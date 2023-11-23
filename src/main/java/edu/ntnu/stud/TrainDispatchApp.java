package edu.ntnu.stud;

import edu.ntnu.stud.commands.AddCommand;
import edu.ntnu.stud.commands.Command;
import edu.ntnu.stud.commands.ExitCommand;
import edu.ntnu.stud.commands.FindByDestinationCommand;
import edu.ntnu.stud.commands.FindByTrainNumberCommand;
import edu.ntnu.stud.commands.PrintDeparturesCommand;
import edu.ntnu.stud.commands.SayHiCommand;
import edu.ntnu.stud.commands.SetClockCommand;
import edu.ntnu.stud.commands.SetDelayCommand;
import edu.ntnu.stud.commands.SetTrackCommand;
import edu.ntnu.stud.models.TrainDepartureManager;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This is the main class for the train dispatch application.
 */
public class TrainDispatchApp {
  public TrainDispatchApp() {
    this.manager = new TrainDepartureManager();
  }

  private final TrainDepartureManager manager;

  /**
   * The entry point of the program.
   */
  public static void main(String[] args) {
    var app = new TrainDispatchApp();
    app.init();
    app.start();
  }


  private void init() {
    System.out.println("Starting train dispatch application...");
    for (var departure : TrainDepartureManager.getSampleDepartures()) {
      manager.addDeparture(departure);
    }
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

  /**
   * Runs a commend input string.
   *
   * @param command The command to execute, either by name or index
   */
  private void runCommand(String command) {
    try {
      // Try to parse the command as a number
      int commandIndex = Integer.parseInt(command) - 1;

      COMMANDS[commandIndex].execute(manager);
    } catch (NumberFormatException ignored) {
      // If that did not work, try the command name instead
      Arrays.stream(COMMANDS)
          .filter(c -> c.getName().equals(command))
          .findFirst()
          .ifPresentOrElse(
              (c) -> c.execute(manager),
              () -> System.out.printf("Unknown command: %s, please try again.%n", command)
          );
    } catch (IndexOutOfBoundsException e) {
      System.out.printf("Please specify a command in the range 1-%d, or the command name.",
          COMMANDS.length);
    }
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
}
