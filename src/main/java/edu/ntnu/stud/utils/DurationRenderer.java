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
   * @param duration            the duration to render
   * @param includeDelayPostfix whether to include the postfix " delay" after the duration
   * @return the rendered duration
   */
  public static String render(Duration duration, boolean includeDelayPostfix) {
    long hours = duration.toHours();
    long minutes = duration.toMinutes() % 60;

    String postfix = (includeDelayPostfix ? " delay" : "");

    if (hours == 0 && minutes == 0) {
      return "";
    } else if (hours == 0) {
      return String.format("%dm", minutes) + postfix;
    } else {
      return String.format("%dh %dm", hours, minutes) + postfix;
    }
  }
}
