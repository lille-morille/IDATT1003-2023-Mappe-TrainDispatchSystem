package edu.ntnu.stud.input;

import java.util.regex.Pattern;

/**
 * A utility class for matching user input patterns throughout the program.
 */
final class PatternMatcher {
  static final String timeRegex = "^([01][0-9]|2[0-3]):[0-5][0-9]$"; // matches hh:mm

  static final String lineRegex = "^(L|F|R|RE|FLY)\\d{1,2}$"; // matches L1, F12, RE21 etc.
  static final String destinationRegex = "^[a-zA-Z ÆØÅæøå\\-]{1,20}$"; // matches a string of
  // 1-20
  // characters with letters, hyphens and spaces

  private static void assertFormat(String regex, String value) throws InvalidInputException {
    if (!Pattern.matches(regex, value)) {
      throw InvalidInputException.invalidFormat();
    }
  }

  /**
   * Match a time string in the format hh:mm.
   */
  public static void testTimeFormat(String input) throws InvalidInputException {
    assertFormat(timeRegex, input);
  }

  /**
   * Match a line string in the format L1, F12, RE21 etc.
   */
  public static void testLineFormat(String input) throws InvalidInputException {
    assertFormat(lineRegex, input);
  }

  /**
   * Match a destination string of max 20 length consisting of letters, hyphens and spaces.
   */
  public static void testDestinationFormat(String destination) throws InvalidInputException {
    assertFormat(destinationRegex, destination);
  }
}
