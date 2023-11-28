package edu.ntnu.stud.models;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Manages the list of train departures.
 * Represents one logical station.
 */
public class TrainDepartureManager {
  public TrainDepartureManager() {
    departures = new ArrayList<>();
    clock = LocalTime.of(0, 0);
  }

  private final ArrayList<TrainDeparture> departures;

  /**
   * Safely adds a new train departure to the list of departures.
   *
   * @param departure The departure to add
   * @throws IllegalArgumentException If the train number is not unique, producing an illegal
   *                                  application state.
   */
  public void addDeparture(TrainDeparture departure) throws IllegalStateException {
    departures.stream().filter(d -> d.getTrainNumber() == departure.getTrainNumber()).findFirst()
        .ifPresent(d -> {
          throw new IllegalStateException("Train number must be unique");
        });

    departures.add(departure);
  }

  /**
   * Gets the list of departures in correct order.
   * The list is sorted by departure time, then by track number.
   *
   * @return The list of departures
   */
  public List<TrainDeparture> getDepartures() {
    departures.sort(Comparator.comparing(TrainDeparture::getAdjustedDepartureTime)
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

  private LocalTime clock;

  /**
   * Gets the clock.
   *
   * @return The clock.
   */
  public LocalTime getClock() {
    return clock;
  }

  /**
   * Updates the applications clock.
   * Also removes departures that have already departed.
   */
  public void setClock(LocalTime clock) {
    this.clock = clock;
  }

  /**
   * Removes all departures that have already departed.
   */
  public void removeDepartedDepartures() {
    // Remove departures before this time
    // We use !isAfter because we want to remove departures that are exactly on time
    // isBefore checks for strictly before, not equal
    departures.removeIf(d -> !d.getAdjustedDepartureTime().isAfter(clock));
  }

  /**
   * Returns a set of sample departures to use as a starting point in the application,
   * or for testing purposes.
   */
  public static List<TrainDeparture> getSampleDepartures() {
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
}
