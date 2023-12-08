package edu.ntnu.stud.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sun.source.tree.CaseTree;
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
  void testRenderEmptyDepartures() {
    List<TrainDeparture> departures = new ArrayList<>();

    TrainDepartureRenderer.renderDepartures(departures);

    final String expectedOut = "No departures to render.\n";

    // Assert that the output is as expected
    assertEquals(expectedOut, outContent.toString());
  }
}
