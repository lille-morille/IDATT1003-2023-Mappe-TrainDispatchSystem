package edu.ntnu.stud.models;

import edu.ntnu.stud.utils.Colors;
import edu.ntnu.stud.utils.DurationRenderer;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatterBuilder;

/**
 * Contains data about one train departure.
 *
 * <p>Tracks information about a train's departure time, delay, line, train number, destination and
 * track.
 */
public class TrainDeparture {
  private final LocalTime departureTime;
  private final String line;
  private final int trainNumber;
  private final String destination;
  private int track;
  private Duration delay;

  /**
   * Creates a new train departure from a set of primitive fields.
   *
   * @param departureHours   The hour of the departure time (0-23)
   * @param departureMinutes The minute of the departure time (0-59)
   * @param line             The line number of the train
   * @param trainNumber      The train number for this specific train
   * @param destination      The destination station of this train
   * @param track            The track at which the train will stop on this station
   * @param delayMinutes     The total delay duration added to the departure time in minutes
   */
  public TrainDeparture(int departureHours, int departureMinutes, String line, int trainNumber,
                        String destination, int track, int delayMinutes) {
    validateParams(departureHours, departureMinutes, line, trainNumber, destination, track,
        delayMinutes);

    this.departureTime = LocalTime.of(departureHours, departureMinutes, 0, 0);
    this.line = line;
    this.trainNumber = trainNumber;
    this.destination = destination;
    this.track = track;
    this.delay = Duration.ofMinutes(delayMinutes);
  }

  /**
   * Creates a new train departure from primitive and compound type fields.
   *
   * @param departureTime The departure time
   * @param line          The line number
   * @param trainNumber   The train number
   * @param destination   The destination
   * @param track         The track
   * @param delay         The delay
   */
  public TrainDeparture(LocalTime departureTime, String line, int trainNumber,
                        String destination, int track, Duration delay) {
    validateParams(departureTime.getHour(), departureTime.getMinute(), line, trainNumber,
        destination, track, (int) delay.toMinutes());

    this.departureTime = departureTime;
    this.line = line;
    this.trainNumber = trainNumber;
    this.destination = destination;
    this.track = track;
    this.delay = delay;
  }

  /**
   * Validates that all parameters passed into the constructor are valid.
   */
  private static void validateParams(int departureHours, int departureMinutes, String line,
                                     int trainNumber, String destination, int track,
                                     int delayMinutes) {
    // Validation
    assert delayMinutes >= 0 && delayMinutes < 60 * 24 :
        "Delay cannot be negative or greater than one day";
    assert departureHours >= 0 && departureHours < 24 :
        "Departure hours must be between 0 and 23";
    assert departureMinutes >= 0 && departureMinutes < 60 :
        "Departure minutes must be between 0 and 59";
    assert !line.isEmpty() : "Line cannot be empty";
    assert trainNumber > 0 : "Train number must be positive";
    assert !destination.isEmpty() : "Destination cannot be empty";
    assert track == -1 || track > 0 : "Track must be -1 or positive";
  }

  /**
   * Returns the final departure time including (any) delay.
   */
  public LocalTime getAdjustedDepartureTime() {
    return departureTime.plusMinutes(delay.toMinutes());
  }

  /**
   * Returns the original departure time without delay information.
   */
  public LocalTime getOriginalDepartureTime() {
    return departureTime;
  }

  /**
   * The line number of the train.
   */
  public String getLine() {
    return line;
  }

  /**
   * The train number for this specific train.
   */
  public int getTrainNumber() {
    return trainNumber;
  }

  /**
   * The destination station of this train.
   */
  public String getDestination() {
    return destination;
  }

  /**
   * The track at which the train will stop on this station.
   */
  public int getTrack() {
    return track;
  }

  /**
   * Update the track at which the train will stop at the station.
   *
   * @param track The new track to set
   */
  public void setTrack(int track) {
    this.track = track;
  }

  /**
   * The total delay duration added to the departure time.
   */
  public Duration getDelay() {
    return delay;
  }

  /**
   * Returns whether the train is delayed or not.
   *
   * @return True if the train is delayed, false otherwise
   */
  public boolean isDelayed() {
    return !delay.isZero();
  }

  /**
   * Update the delay time.
   *
   * @param delay New delay time to set
   */
  public void setDelay(Duration delay) {
    this.delay = delay;
  }

  @Override
  public String toString() {
    String delayString = DurationRenderer.render(getDelay(), false);
    String str = String.format(
        "Train %s %s-%s departs at %s from track %d",
        getTrainNumber(),
        Colors.getForLine(getLine(), true) + getLine() + Colors.RESET,
        getDestination(),
        getAdjustedDepartureTime(),
        getTrack());
    if (!delayString.isEmpty()) {
      return str + " with a delay of " + delayString;
    } else {
      return str;
    }
  }
}
