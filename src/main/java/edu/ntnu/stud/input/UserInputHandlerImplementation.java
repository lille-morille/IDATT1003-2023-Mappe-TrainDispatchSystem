package edu.ntnu.stud.input;

import edu.ntnu.stud.models.TrainDeparture;
import edu.ntnu.stud.models.TrainDepartureManager;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * Implementation for methods used by UserInputHandler.
 * Should not be used directly, but through UserInputHandler.
 */
class UserInputHandlerImplementation {
  private final TrainDepartureManager manager;
  private final Scanner in;

  public UserInputHandlerImplementation(TrainDepartureManager manager) {
    this.manager = manager;
    this.in = new Scanner(System.in);
  }

  public TrainDeparture getDeparture() throws InvalidInputException {
    int trainNumber;
    System.out.print("Which train? (train number): ");
    try {
      trainNumber = in.nextInt();
    } catch (Exception e) {
      throw new InvalidInputException();
    }

    if (trainNumber < 1) {
      throw new InvalidInputException("Train number must be positive, please try again.");
    }

    var maybeDeparture = manager.getDepartureByTrainNumber(trainNumber);

    if (maybeDeparture.isEmpty()) {
      throw new InvalidInputException("No train found with this train number. Please try again.");
    }

    return maybeDeparture.get();
  }

  public int getTrack() throws InvalidInputException {
    int track;
    System.out.print("Enter track: (-1 for unknown): ");
    try {
      track = in.nextInt();
    } catch (Exception e) {
      throw new InvalidInputException();
    }

    if (track < -1 || track == 0) {
      throw new InvalidInputException("Track must be -1 or positive, please try again.");
    }
    return track;
  }

  public String getDestination() throws InvalidInputException {
    String destination;
    System.out.print("Enter destination: ");
    try {
      destination = in.nextLine();
    } catch (Exception e) {
      throw new InvalidInputException();
    }

    PatternMatcher.testDestinationFormat(destination);

    return destination;
  }

  public String getLine() throws InvalidInputException {
    String line;
    System.out.print("Enter line (L, R, F or FLY followed by 1-2 digits) : ");
    try {
      line = in.nextLine();
    } catch (Exception e) {
      throw new InvalidInputException("You must specify a line, please try again.");
    }

    PatternMatcher.testLineFormat(line);

    return line;
  }

  public LocalTime getTime() throws InvalidInputException {
    String departureTime;
    System.out.print("Enter time (hh:mm): ");
    departureTime = in.nextLine();

    PatternMatcher.testTimeFormat(departureTime);

    String[] parts = departureTime.split(":");
    int hour = Integer.parseInt(parts[0]);
    int minute = Integer.parseInt(parts[1]);

    return LocalTime.of(hour, minute);
  }

  public Duration getDelay() throws InvalidInputException {
    int delayMinutes;
    System.out.print("Enter the delay in minutes: ");
    try {
      delayMinutes = in.nextInt();
    } catch (Exception e) {
      throw new InvalidInputException();
    }

    if (delayMinutes < 0 || delayMinutes > 60 * 24) {
      throw new InvalidInputException("Delay must be between 0 minutes and 1 day, please try "
          + "again.");
    }

    return Duration.ofMinutes(delayMinutes);
  }

  public int getTrainNumber() throws InvalidInputException {
    int trainNumber;

    System.out.print(
        "Enter train number (positive integer), or leave it blank to generate automatically: "
    );
    try {
      var tempTrainNumber = in.nextLine();
      if (tempTrainNumber.isBlank()) {
        return manager.generateTrainNumber();
      } else {
        trainNumber = Integer.parseInt(tempTrainNumber);
      }
    } catch (Exception e) {
      throw new InvalidInputException();
    }

    if (trainNumber < 1) {
      throw new InvalidInputException("Train number must be positive, please try again.");
    }

    boolean isTrainNumberUnique = true;
    for (TrainDeparture departure : manager.getDepartures()) {
      if (departure.getTrainNumber() == trainNumber) {
        isTrainNumberUnique = false;
        break;
      }
    }

    if (!isTrainNumberUnique) {
      throw new InvalidInputException(
          String.format("Train number %s is already taken, please try "
                  + "another train number.",
              trainNumber));
    }

    // Overflows to next line, so we need to consume it
    in.nextLine();
    return trainNumber;
  }
}
