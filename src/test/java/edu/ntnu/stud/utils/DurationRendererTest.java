package edu.ntnu.stud.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import org.junit.jupiter.api.Test;

class DurationRendererTest {

  @Test
  void testRenderDuration() {
    assertEquals("30m", DurationRenderer.render(Duration.ofMinutes(30), false));
    assertEquals("1h 30m", DurationRenderer.render(Duration.ofMinutes(90), false));
    assertEquals("2h 30m", DurationRenderer.render(Duration.ofMinutes(150), false));
    assertEquals("", DurationRenderer.render(Duration.ofMinutes(0), false));

    assertEquals("30m delay", DurationRenderer.render(Duration.ofMinutes(30), true));
    assertEquals("1h 30m delay", DurationRenderer.render(Duration.ofMinutes(90), true));
    assertEquals("2h 30m delay", DurationRenderer.render(Duration.ofMinutes(150), true));
    assertEquals("", DurationRenderer.render(Duration.ofMinutes(0), true));
  }
}
