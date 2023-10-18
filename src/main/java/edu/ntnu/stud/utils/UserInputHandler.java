package edu.ntnu.stud.utils;

import edu.ntnu.stud.TrainDispatchApp;
import edu.ntnu.stud.models.TrainDeparture;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * Handles user input for common values to avoid code duplication.
 */
public final class UserInputHandler {
  private final TrainDispatchApp app;
  private final Scanner in;

  public UserInputHandler(TrainDispatchApp app) {
    this.app = app;
    this.in = new Scanner(System.in);
  }

  /**
   * Asks the user for a train number and returns the corresponding train departure.
   *
   * @return The train departure.
   */
  public TrainDeparture getDeparture() {
    TrainDeparture departure;

    while (true) {
      int trainNumber;
      System.out.print("Which train? (train number): ");
      try {
        trainNumber = in.nextInt();
      } catch (Exception e) {
        System.out.println("Invalid input, please try again.");
        in.nextLine();
        continue;
      }

      var maybeDeparture = app.getDepartureByTrainNumber(trainNumber);

      if (maybeDeparture.isEmpty()) {
        System.out.println("No train found with this train number. Please try again.");
        continue;
      }

      departure = maybeDeparture.get();

      break;
    }

    return departure;
  }

  /**
   * Asks the user for a track number and returns it.
   *
   * @return The track number.
   */
  public int getTrack() {
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
    return track;
  }

  /**
   * Asks the user for a destination and returns it.
   *
   * @return The destination.
   */
  public String getDestination() {
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
    return destination;
  }

  /**
   * Asks the user for a line and returns it.
   *
   * @return The line.
   */
  public String getLine() {
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
                   Invalid line. The accepted format is R, L, RE or FLY
                   followed by one or two digits. Please try again.
                """);

        continue;
      }

      break;
    }

    return line;
  }

  /**
   * Asks the user for a time and returns it.
   *
   * @return The time.
   */
  public LocalTime getTime() {
    int hour;
    int minute;

    // DEPARTURE TIME
    String departureTime;
    while (true) {
      System.out.println("Enter time (hh:mm): ");
      departureTime = in.nextLine();

      if (!PatternMatcher.matchTime(departureTime)) {
        System.out.println("Invalid time format, please try again.");
        continue;
      }

      String[] parts = departureTime.split(":");
      hour = Integer.parseInt(parts[0]);
      minute = Integer.parseInt(parts[1]);
      break;
    }

    return LocalTime.of(hour, minute);
  }

  /**
   * Asks the user for a delay and returns it.
   *
   * @return The delay.
   */
  public Duration getDelay() {
    int delayMinutes;
    while (true) {
      System.out.println("Enter the delay in minutes: ");
      try {
        delayMinutes = in.nextInt();
      } catch (Exception e) {
        System.out.println("Invalid input, please try again.");
        continue;
      }

      if (delayMinutes < 0 || delayMinutes > 60 * 24) {
        System.out.println("Delay must be between 0 minutes and 1 day, please try again.");
        continue;
      }

      break;
    }

    return Duration.ofMinutes(delayMinutes);
  }

  /**
   * Asks the user for a train number and returns it.
   * Validates that the train number is unique.
   *
   * @return The train number.
   */
  public int getTrainNumber() {
    int trainNumber;

    while (true) {
      System.out.println(
          "Enter train number (positive integer), or leave it blank to generate automatically: "
      );
      try {
        var tempTrainNumber = in.nextLine();
        if (tempTrainNumber.isBlank()) {
          trainNumber = app.generateTrainNumber();
        } else {
          trainNumber = Integer.parseInt(tempTrainNumber);
        }
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

    // Overflows to next line, so we need to consume it
    in.nextLine();
    return trainNumber;
  }
}
