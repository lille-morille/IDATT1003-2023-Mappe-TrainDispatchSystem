package edu.ntnu.stud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.sun.jdi.Value;
import java.time.Duration;
import org.junit.jupiter.api.Test;

/**
 * Provides full 100% test coverage for the {@link TrainDeparture} class.
 */
public final class TestTrainDeparture {
  // region Constructor

  /**
   * Normal constructor with good values should work.
   */
  @Test
  public void testConstructorWorks() {
    new TrainDeparture(
        10,
        30,
        "L4",
        200,
        "Bergen",
        4,
        10
    );
  }

  /**
   * Delay cannot be negative or greater than one day (24 * 60 minutes).
   */
  @Test
  public void testConstructorNegativeDelay() {
    Throwable negativeException = assertThrows(AssertionError.class, () -> new TrainDeparture(
        10,
        30,
        "L4",
        200,
        "Bergen",
        4,
        -10
    ));
    assertEquals(negativeException.getMessage(),
        "Delay cannot be negative or greater than one day");

    Throwable tooGreatException = assertThrows(AssertionError.class, () -> new TrainDeparture(
        10,
        30,
        "L4",
        200,
        "Bergen",
        4,
        60 * 24
    ));
    assertEquals(tooGreatException.getMessage(),
        "Delay cannot be negative or greater than one day");
  }

  /**
   * Departure hours must be between 0 and 23.
   * The test fails for values outside this range
   */
  @Test
  public void testConstructorDepartureHoursOutOfBounds() {
    Throwable negativeException = assertThrows(AssertionError.class, () -> new TrainDeparture(
        -1,
        30,
        "L4",
        200,
        "Bergen",
        4,
        10
    ));
    assertEquals(negativeException.getMessage(), "Departure hours must be between 0 and 23");

    Throwable tooLargeException = assertThrows(AssertionError.class, () -> new TrainDeparture(
        24,
        30,
        "L4",
        200,
        "Bergen",
        4,
        10
    ));
    assertEquals(tooLargeException.getMessage(), "Departure hours must be between 0 and 23");
  }

  /**
   * Departure minutes must be between 0 and 59.
   */
  @Test
  public void testConstructorDepartureMinutesOutOfBounds() {
    Throwable negativeException = assertThrows(AssertionError.class, () -> new TrainDeparture(
        10,
        -1,
        "L4",
        200,
        "Bergen",
        4,
        10
    ));
    assertEquals(negativeException.getMessage(), "Departure minutes must be between 0 and 59");

    Throwable tooLargeException = assertThrows(AssertionError.class, () -> new TrainDeparture(
        10,
        60,
        "L4",
        200,
        "Bergen",
        4,
        10
    ));
    assertEquals(tooLargeException.getMessage(), "Departure minutes must be between 0 and 59");
  }

  @Test
  public void testTrainNumberIsPositive() {
    Throwable negativeException = assertThrows(AssertionError.class, () -> new TrainDeparture(
        10,
        30,
        "L4",
        -200,
        "Bergen",
        4,
        10
    ));
    assertEquals(negativeException.getMessage(), "Train number must be positive");
  }

  @Test
  public void testDestinationIsNonEmpty() {
    Throwable emptyException = assertThrows(AssertionError.class, () -> new TrainDeparture(
        10,
        30,
        "L4",
        200,
        "",
        4,
        10
    ));
    assertEquals(emptyException.getMessage(), "Destination cannot be empty");
  }

  @Test
  public void testLineIsNotEmpty() {
    Throwable emptyException = assertThrows(AssertionError.class, () -> new TrainDeparture(
        10,
        30,
        "",
        200,
        "Bergen",
        4,
        10
    ));
    assertEquals(emptyException.getMessage(), "Line cannot be empty");
  }

  @Test
  public void testTrackIsPositive() {
    Throwable negativeException = assertThrows(AssertionError.class, () -> new TrainDeparture(
        10,
        30,
        "L4",
        200,
        "Bergen",
        -4,
        10
    ));
    assertEquals(negativeException.getMessage(), "Track must be positive");
  }
  // endregion

  // region Getters

  @Test
  public void testGetFinalDepartureTime() {
    TrainDeparture departure = new TrainDeparture(
        10,
        30,
        "L4",
        200,
        "Bergen",
        4,
        10
    );
    assertEquals(departure.getFinalDepartureTime().toString(), "10:40");

    departure = new TrainDeparture(
        10,
        30,
        "L4",
        200,
        "Bergen",
        4,
        0
    );
    assertEquals(departure.getFinalDepartureTime().toString(), "10:30");
  }

  @Test
  public void testOriginalDepartureTime() {
    TrainDeparture departure = new TrainDeparture(
        10,
        30,
        "L4",
        200,
        "Bergen",
        4,
        10
    );
    assertEquals(departure.getOriginalDepartureTime().toString(), "10:30");

    departure = new TrainDeparture(
        10,
        30,
        "L4",
        200,
        "Bergen",
        4,
        0
    );
    assertEquals(departure.getOriginalDepartureTime().toString(), "10:30");
  }

  @Test
  public void testGetLine() {
    TrainDeparture departure = new TrainDeparture(
        10,
        30,
        "L4",
        200,
        "Bergen",
        4,
        10
    );
    assertEquals(departure.getLine(), "L4");
  }

  @Test
  public void testGetTrainNumber() {
    TrainDeparture departure = new TrainDeparture(
        10,
        30,
        "L4",
        200,
        "Bergen",
        4,
        10
    );
    assertEquals(departure.getTrainNumber(), 200);
  }

  @Test
  public void testGetDestination() {
    TrainDeparture departure = new TrainDeparture(
        10,
        30,
        "L4",
        200,
        "Bergen",
        4,
        10
    );
    assertEquals(departure.getDestination(), "Bergen");
  }

  @Test
  public void testGetTrack() {
    TrainDeparture departure = new TrainDeparture(
        10,
        30,
        "L4",
        200,
        "Bergen",
        4,
        10
    );
    assertEquals(departure.getTrack(), 4);
  }

  @Test
  public void testGetDelay() {
    TrainDeparture departure = new TrainDeparture(
        10,
        30,
        "L4",
        200,
        "Bergen",
        1,
        10
    );
    assertEquals(departure.getDelay().toMinutes(), 10);
  }

  @Test
  public void testSetDelay() {
    TrainDeparture departure = new TrainDeparture(
        10,
        30,
        "L4",
        200,
        "Bergen",
        1,
        10
    );
    assertEquals(departure.getDelay().toMinutes(), 10);
    assertEquals(departure.getFinalDepartureTime().toString(), "10:40");

    departure.setDelay(Duration.ofMinutes(20));
    assertEquals(departure.getDelay().toMinutes(), 20);
    assertEquals(departure.getFinalDepartureTime().toString(), "10:50");
  }

  @Test
  public void testSetTrack() {
    TrainDeparture departure = new TrainDeparture(
        10,
        30,
        "L4",
        200,
        "Bergen",
        1,
        10
    );
    assertEquals(departure.getTrack(), 1);

    departure.setTrack(2);
    assertEquals(departure.getTrack(), 2);
  }

  // endregion
}
