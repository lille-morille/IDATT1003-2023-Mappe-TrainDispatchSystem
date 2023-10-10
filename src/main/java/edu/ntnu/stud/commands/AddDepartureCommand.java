package edu.ntnu.stud.commands;

import edu.ntnu.stud.TrainDispatchApp;
import edu.ntnu.stud.models.TrainDeparture;
import edu.ntnu.stud.utils.PatternMatcher;
import java.util.Scanner;

/**
 * Adds a train departure to the list of departures.
 */
public final class AddDepartureCommand extends Command {
  public AddDepartureCommand() {
    super("add", "Add a train departure");
  }

  @Override
  public void execute(TrainDispatchApp app) {
    Scanner in = new Scanner(System.in);
    System.out.println("Add train departure");

    // TRAIN NUMBER
    int trainNumber;

    while (true) {
      System.out.println("Enter train number (positive integer): ");
      try {
        trainNumber = in.nextInt();
      } catch (Exception e) {
        System.out.println("Invalid train number, please try again.");
        continue;
      }

      if (trainNumber < 1) {
        System.out.println("Train number must be positive, please try again.");
        continue;
      }

      boolean isTrainNumberUnique = true;
      for (TrainDeparture departure : app.getDepartures()) {
        if (departure.getTrainNumber() == trainNumber) {
          isTrainNumberUnique = false;
          break;
        }
      }

      if (!isTrainNumberUnique) {
        System.out.println("Train number must be unique, please try again.");
        continue;
      }
      break;


    }

    // Skip the next line
    in.nextLine();

    int departureHour;
    int departureMinute;

    // DEPARTURE TIME
    String departureTime;
    while (true) {
      System.out.println("Enter departure time (hh:mm): ");
      departureTime = in.nextLine();

      if (!PatternMatcher.matchTime(departureTime)) {
        System.out.println("Invalid time format, please try again.");
        continue;
      }

      departureHour = Integer.parseInt(departureTime.split(":")[0]);
      departureMinute = Integer.parseInt(departureTime.split(":")[1]);
      break;
    }

    System.out.println("Enter line: ");
    String line;
    while (true) {
      try {
        line = in.nextLine();
      } catch (Exception e) {
        System.out.println("You must specify a line, please try again.");
        continue;
      }

      if (!PatternMatcher.matchLine(line)) {
        System.out.println(
            """
                   Invalid line. The accepted format is R, L, RE or F
                   followed by one or two digits. Please try again.
                """);

        continue;
      }

      break;
    }

    String destination = "";
    while (true) {
      System.out.println("Enter destination: ");
      try {
        destination = in.nextLine();
      } catch (Exception e) {
        System.out.println("You must specify a destination, please try again.");
      }

      if (destination.isBlank()) {
        System.out.println("You must specify a destination, please try again.");
        continue;
      }

      break;
    }

    int track;
    while (true) {
      System.out.println("Enter track: (-1 for unknown): ");
      try {
        track = in.nextInt();
      } catch (Exception e) {
        System.out.println("Invalid track, please try again.");
        continue;
      }

      if (track < -1 || track == 0) {
        System.out.println("Track must be -1 or positive, please try again.");
        continue;
      }
      break;
    }

    int delayMinues;
    while (true) {
      System.out.println("Enter delay (minutes): ");
      try {
        delayMinues = in.nextInt();
      } catch (Exception e) {
        System.out.println("Invalid delay, please try again.");
        continue;
      }

      if (delayMinues < 0 || delayMinues > 24 * 60) {
        System.out.println("Delay must be between 0 minutes and 1 day, please try again.");
        continue;
      }

      break;
    }

    System.out.println("Train departure added successfully!");
    app.addDeparture(
        new TrainDeparture(departureHour, departureMinute, line, trainNumber, destination,
            track, delayMinues));

    // Display the departures after inserting
    new RenderDeparturesCommand().execute(app);
  }
}
