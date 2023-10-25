package edu.ntnu.stud.input;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.stud.TrainDispatchApp;
import edu.ntnu.stud.models.TrainDeparture;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserInputHandlerImplementationTest {
  private TrainDispatchApp app;
  private final PrintStream originalOut = System.out;
  private final InputStream originalIn = System.in;

  @BeforeEach
  void setUp() {
    app = new TrainDispatchApp();
    app.addDeparture(new TrainDeparture(
        10,
        30,
        "L4",
        200,
        "Bergen",
        1,
        10
    ));
  }

  @AfterEach
  void tearDown() {
    System.setOut(originalOut);
    System.setIn(originalIn);
  }

  UserInputHandlerImplementation getInput() {
    return new UserInputHandlerImplementation(app);
  }

  void simulateInput(String input) {
    System.setIn(new ByteArrayInputStream((input + "\r\n").getBytes()));
  }

  @Test
  void testDepartureNegativeNumber() {
    simulateInput("-1");
    var e = assertThrows(InvalidInputException.class, () -> getInput().getDeparture());
    assertEquals("Train number must be positive, please try again.", e.getMessage());
  }

  @Test
  void testDepartureNotFound() {
    simulateInput("10");
    var e = assertThrows(InvalidInputException.class, () -> getInput().getDeparture());
    assertEquals("No train found with this train number. Please try again.", e.getMessage());
  }

  @Test
  void testDepartureInvalidInput() {
    simulateInput("invalid input");
    var e = assertThrows(InvalidInputException.class, () -> getInput().getDeparture());
    assertEquals(InvalidInputException.DEFAULT_MESSAGE, e.getMessage());
  }

  @Test
  void testDepartureValid() {
    simulateInput("200");
    assertDoesNotThrow(() -> getInput().getDeparture());
  }

  @Test
  void testTrackNegativeNumber() {
    simulateInput("-2");
    var e = assertThrows(InvalidInputException.class, () -> getInput().getTrack());
    assertEquals("Track must be -1 or positive, please try again.", e.getMessage());
  }

  @Test
  void testTrackInvalidInput() {
    simulateInput("invalid input");
    var e = assertThrows(InvalidInputException.class, () -> getInput().getTrack());
    assertEquals(InvalidInputException.DEFAULT_MESSAGE, e.getMessage());
  }

  @Test
  void testTrackValid() {
    simulateInput("1");
    assertDoesNotThrow(() -> getInput().getTrack());
  }

  @Test
  void testTrackValidUnknownTrack() {
    simulateInput("-1");
    assertDoesNotThrow(() -> getInput().getTrack());
  }

  @Test
  void testDestinationTooLong() {
    simulateInput("jdkaslfjdksajfdksalff");
    var e = assertThrows(InvalidInputException.class, () -> getInput().getDestination());
    assertEquals(InvalidInputException.INVALID_FORMAT_MESSAGE, e.getMessage());
  }

  @Test
  void testDestinationWithNumbers() {
    simulateInput("123");
    var e = assertThrows(InvalidInputException.class, () -> getInput().getDestination());
    assertEquals(InvalidInputException.INVALID_FORMAT_MESSAGE,
        e.getMessage());
  }

  @Test
  void testDestinationWithSpecialChars() {
    simulateInput("!?");
    var e = assertThrows(InvalidInputException.class, () -> getInput().getDestination());
    assertEquals(InvalidInputException.INVALID_FORMAT_MESSAGE, e.getMessage());
  }

  @Test
  void testDestinationValid() {
    simulateInput("Åse-Bergen");
    assertDoesNotThrow(() -> getInput().getDestination());
  }

  @Test
  void testLineInvalid() {
    simulateInput("invalid input");
    var e = assertThrows(InvalidInputException.class, () -> getInput().getLine());
    assertEquals(InvalidInputException.INVALID_FORMAT_MESSAGE, e.getMessage());
  }

  @Test
  void testLineValid() {
    simulateInput("L4");
    assertDoesNotThrow(() -> getInput().getLine());
  }

  @Test
  void testInvalidTime() {
    simulateInput("invalid input");
    var e = assertThrows(InvalidInputException.class, () -> getInput().getTime());
    assertEquals(InvalidInputException.INVALID_FORMAT_MESSAGE, e.getMessage());
  }

  @Test
  void testValidTime() {
    simulateInput("10:30");
    assertDoesNotThrow(() -> getInput().getTime());
  }

  @Test
  void testInvalidTimeNumbers() {
    simulateInput("25:00");
    var e = assertThrows(InvalidInputException.class, () -> getInput().getTime());
    assertEquals(InvalidInputException.INVALID_FORMAT_MESSAGE, e.getMessage());
  }

  @Test
  void testInvalidDelay() {
    simulateInput("invalid input");
    var e = assertThrows(InvalidInputException.class, () -> getInput().getDelay());
    assertEquals(InvalidInputException.DEFAULT_MESSAGE, e.getMessage());
  }

  @Test
  void testInvalidDelayDuration() {
    simulateInput("10:60");
    var e = assertThrows(InvalidInputException.class, () -> getInput().getDelay());
    assertEquals(InvalidInputException.DEFAULT_MESSAGE, e.getMessage());
  }

  @Test
  void testTrainNumberNegativeInteger() {
    simulateInput("-1");
    var e = assertThrows(InvalidInputException.class, () -> getInput().getTrainNumber());
    assertEquals("Train number must be positive, please try again.", e.getMessage());
  }

  @Test
  void testTrainNumberAlreadyInUse() {
    simulateInput("200");
    var e = assertThrows(InvalidInputException.class, () -> getInput().getTrainNumber());
    assertEquals("Train number 200 is already taken, please try another train number.",
        e.getMessage());
  }
}
