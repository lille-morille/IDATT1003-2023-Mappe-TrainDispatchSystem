package edu.ntnu.stud.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import org.junit.jupiter.api.Test;

class DurationRendererTest {

  @Test
  void render() {
    assertEquals("30m", DurationRenderer.render(Duration.ofMinutes(30)));
    assertEquals("1h 30m", DurationRenderer.render(Duration.ofMinutes(90)));
    assertEquals("2h 30m", DurationRenderer.render(Duration.ofMinutes(150)));
    assertEquals("", DurationRenderer.render(Duration.ofMinutes(0)));
  }
}
