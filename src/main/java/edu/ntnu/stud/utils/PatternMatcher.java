package edu.ntnu.stud.utils;

import java.util.regex.Pattern;

/**
 * A utility class for matching user input patterns throughout the program.
 */
public final class PatternMatcher {
  static final String timeRegex = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$"; // matches hh:mm
  static final String lineRegex = "^(L|F|R|RE|FLY)\\d{1,2}$"; // matches L1, F12, RE21 etc.

  /**
   * Match a time string in the format hh:mm.
   *
   * @param input the string to match
   * @return true if the string matches the time format, false otherwise
   */
  public static boolean matchTime(String input) {
    return Pattern.matches(timeRegex, input);
  }

  /**
   * Match a line string in the format L1, F12, RE21 etc.
   *
   * @param input the string to match
   * @return true if the string matches the line format, false otherwise
   */
  public static boolean matchLine(String input) {
    return Pattern.matches(lineRegex, input);
  }
}
