package edu.ntnu.stud;

import java.time.Duration;
import java.time.LocalTime;

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
    assert track > 0 : "Track must be positive";

    this.departureTime = LocalTime.of(departureHours, departureMinutes, 0, 0);
    this.line = line;
    this.trainNumber = trainNumber;
    this.destination = destination;
    this.track = track;
    this.delay = Duration.ofMinutes(delayMinutes);
  }

  /**
   * Returns the final departure time including (any) delay.
   */
  public LocalTime getFinalDepartureTime() {
    return departureTime.plusSeconds(delay.toSeconds());
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
   * Update the delay time.
   *
   * @param delay New delay time to set
   */
  public void setDelay(Duration delay) {
    this.delay = delay;
  }
}
