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
  private PrintStream originalOut;


  // Setup custom system.out stream
  @BeforeEach
  void setUp() {
    originalOut = System.out;
    System.setOut(new PrintStream(outContent));
  }

  // Reset the system.out stream
  @AfterEach
  void tearDown() {
    System.setOut(originalOut);
  }

  @Test
  void testRenderDepartures() {
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
        ===time==line|dest====track|train===========
                                                    |  [33m10:40 [1;32mL4-[0m[32mBergen[0m    200 n.|1| [33m10m delay[0m  |
                                                    |  [33m10:40 [1;31mR3-[0m[31mTrondheim[0m 200 n.|2| [33m5m delay[0m   |
                                                    |  10:40 [1;32mL4-[0m[32mStavanger[0m 200 n.|1| [0m           |
                                                    |  10:45 [1;31mR3-[0m[31mOslo[0m      200 n.|2| [0m           |
                                                    ============================================
                                                    
        """;

    // Assert that the output is as expected
    assertEquals(expectedOut, outContent.toString());
  }

  @Test
  void testRenderEmptyDepartures() {
    List<TrainDeparture> departures = new ArrayList<>();

    TrainDepartureRenderer.renderDepartures(departures);

    final String expectedOut = "No departures to render.\n";

    // Assert that the output is as expected
    assertEquals(expectedOut, outContent.toString());
  }
}
