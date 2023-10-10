package edu.ntnu.stud.utils;

import java.time.Duration;

/**
 * Util class for rendering a duration.
 */
public final class DurationRenderer {
  /**
   * Renders a duration in the format xh xm like 1h 30m or 30m if the duration is less than 1 hour.
   * If there is no duration, an empty string is returned.
   *
   * @param duration the duration to render
   * @return the rendered duration
   */
  public static String render(Duration duration) {
    long hours = duration.toHours();
    long minutes = duration.toMinutes() % 60;

    if (hours == 0 && minutes == 0) {
      return "";
    }

    if (hours == 0) {
      return String.format("%dm", minutes);
    }
    // return in the format 1h 30m
    return String.format("%dh %dm", hours, minutes);
  }
}
