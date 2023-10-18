package edu.ntnu.stud.input;

import edu.ntnu.stud.TrainDispatchApp;
import edu.ntnu.stud.models.TrainDeparture;
import java.time.Duration;
import java.time.LocalTime;

/**
 * Handles user input for common values to avoid code duplication.
 */
public final class UserInputHandler {
  private final UserInputHandlerImplementation input;

  public UserInputHandler(TrainDispatchApp app) {
    input = new UserInputHandlerImplementation(app);
  }

  private <T> T handleInput(InputMethod<T> inputMethod) {
    while (true) {
      try {
        return inputMethod.execute();
      } catch (InvalidInputException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  /**
   * Asks the user for a train number and returns the corresponding train departure.
   *
   * @return The train departure.
   */
  public TrainDeparture getDeparture() {
    return handleInput(input::getDeparture);
  }

  /**
   * Asks the user for a track number and returns it.
   *
   * @return The track number.
   */
  public int getTrack() {
    return handleInput(input::getTrack);
  }

  /**
   * Asks the user for a destination and returns it.
   *
   * @return The destination.
   */
  public String getDestination() {
    return handleInput(input::getDestination);
  }

  /**
   * Asks the user for a line and returns it.
   *
   * @return The line.
   */
  public String getLine() {
    return handleInput(input::getLine);
  }

  /**
   * Asks the user for a time and returns it.
   *
   * @return The time.
   */
  public LocalTime getTime() {
    return handleInput(input::getTime);
  }

  /**
   * Asks the user for a delay and returns it.
   *
   * @return The delay.
   */
  public Duration getDelay() {
    return handleInput(input::getDelay);
  }

  /**
   * Asks the user for a train number and returns it.
   * Validates that the train number is unique.
   *
   * @return The train number.
   */
  public int getTrainNumber() {
    return handleInput(input::getTrainNumber);
  }
}
