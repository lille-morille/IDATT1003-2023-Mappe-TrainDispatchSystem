package edu.ntnu.stud.input;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class PatternMatcherTest {

  @Test
  void testMatchTime() {
    assertDoesNotThrow(() -> PatternMatcher.assertTimeFormatIsValid("00:00"));
    assertDoesNotThrow(() -> PatternMatcher.assertTimeFormatIsValid("23:59"));
    assertDoesNotThrow(() -> PatternMatcher.assertTimeFormatIsValid("12:00"));
    assertDoesNotThrow(() -> PatternMatcher.assertTimeFormatIsValid("01:00"));

    assertThrows(InvalidInputException.class,
        () -> PatternMatcher.assertTimeFormatIsValid("24:00"));
    assertThrows(InvalidInputException.class,
        () -> PatternMatcher.assertTimeFormatIsValid("00:60"));
    assertThrows(InvalidInputException.class, () -> PatternMatcher.assertTimeFormatIsValid("00:0"));
    assertThrows(InvalidInputException.class,
        () -> PatternMatcher.assertTimeFormatIsValid("f0:00"));
    assertThrows(InvalidInputException.class,
        () -> PatternMatcher.assertTimeFormatIsValid("AB:CD"));
    assertThrows(InvalidInputException.class,
        () -> PatternMatcher.assertTimeFormatIsValid("12:4!"));
  }

  @Test
  void testMatchLine() {
    assertDoesNotThrow(() -> PatternMatcher.assertLineFormatIsValid("L1"));
    assertDoesNotThrow(() -> PatternMatcher.assertLineFormatIsValid("F12"));
    assertDoesNotThrow(() -> PatternMatcher.assertLineFormatIsValid("RE21"));
    assertDoesNotThrow(() -> PatternMatcher.assertLineFormatIsValid("FLY1"));

    assertThrows(InvalidInputException.class, () -> PatternMatcher.assertLineFormatIsValid("L"));
    assertThrows(InvalidInputException.class, () -> PatternMatcher.assertLineFormatIsValid("L123"));
    assertThrows(InvalidInputException.class, () -> PatternMatcher.assertLineFormatIsValid("L1A"));
    assertThrows(InvalidInputException.class, () -> PatternMatcher.assertLineFormatIsValid("L1!"));
    assertThrows(InvalidInputException.class, () -> PatternMatcher.assertLineFormatIsValid("L1 "));
    assertThrows(InvalidInputException.class, () -> PatternMatcher.assertLineFormatIsValid("L 1"));
  }

  @Test
  void matchDestination() {
    assertDoesNotThrow(() -> PatternMatcher.assertDestinationFormatIsValid("Bergen"));
    assertDoesNotThrow(() -> PatternMatcher.assertDestinationFormatIsValid("ÆØÅæøå"));
    assertDoesNotThrow(() -> PatternMatcher.assertDestinationFormatIsValid("trondheim"));
    assertDoesNotThrow(() -> PatternMatcher.assertDestinationFormatIsValid("fdsjflkdsjfkdsljfkds"));

    assertThrows(InvalidInputException.class,
        () -> PatternMatcher.assertDestinationFormatIsValid("Trondheim1"));
    assertThrows(InvalidInputException.class,
        () -> PatternMatcher.assertDestinationFormatIsValid("abc_def"));
    assertThrows(InvalidInputException.class,
        () -> PatternMatcher.assertDestinationFormatIsValid("abc123"));
    assertThrows(InvalidInputException.class,
        () -> PatternMatcher.assertDestinationFormatIsValid("!"));
    assertThrows(InvalidInputException.class, () -> PatternMatcher.assertDestinationFormatIsValid(
        "fdsjflkdsjfkdsljfkdsfdsfds"));
  }
}
