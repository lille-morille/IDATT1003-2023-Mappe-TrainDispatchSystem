package edu.ntnu.stud.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.stud.models.TrainDeparture;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Provides full test coverage of TrainDepartureRenderer's public api.
 */
class TrainDepartureRendererTest {
  // Emulate system.out.println for testing
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


  // Setup custom system.out stream
  @BeforeEach
  void setUp() {
    System.setOut(new PrintStream(outContent));
  }

  // Reset the system.out stream
  @AfterEach
  void tearDown() {
    System.setOut(System.out);
  }

  @Test
  void renderDepartures() {
    List<TrainDeparture> departures = new ArrayList<>();
    departures.add(new TrainDeparture(
        10,
        30,
        "L4",
        200,
        "Bergen",
        1,
        10
    ));

    departures.add(new TrainDeparture(
        10,
        35,
        "R3",
        200,
        "Trondheim",
        2,
        5
    ));

    departures.add(new TrainDeparture(
        10,
        40,
        "L4",
        200,
        "Stavanger",
        1,
        0
    ));

    departures.add(new TrainDeparture(
        10,
        45,
        "R3",
        200,
        "Oslo",
        2,
        0
    ));

    TrainDepartureRenderer.renderDepartures(departures);

    final String expectedOut = """
        ===time==line|dest====track|train|delay
        |  10:40 L4-Bergen    |1| n.200 10m  |
        |  10:40 R3-Trondheim |2| n.200 5m   |
        |  10:40 L4-Stavanger |1| n.200      |
        |  10:45 R3-Oslo      |2| n.200      |
        ======================================
        """;

    // Assert that the output is as expected
    assertEquals(expectedOut, outContent.toString());
  }

  @Test
  void renderEmptyDepartures() {
    List<TrainDeparture> departures = new ArrayList<>();

    TrainDepartureRenderer.renderDepartures(departures);

    final String expectedOut = "No departures to render.\n";

    // Assert that the output is as expected
    assertEquals(expectedOut, outContent.toString());
  }
}
