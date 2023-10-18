package edu.ntnu.stud.input;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.stud.input.PatternMatcher;
import org.junit.jupiter.api.Test;

class PatternMatcherTest {

  @Test
  void testMatchTime() {
    assertDoesNotThrow(() -> PatternMatcher.testTimeFormat("00:00"));
    assertDoesNotThrow(() -> PatternMatcher.testTimeFormat("23:59"));
    assertDoesNotThrow(() -> PatternMatcher.testTimeFormat("12:00"));
    assertDoesNotThrow(() -> PatternMatcher.testTimeFormat("01:00"));

    assertThrows(InvalidInputException.class, () -> PatternMatcher.testTimeFormat("24:00"));
    assertThrows(InvalidInputException.class, () -> PatternMatcher.testTimeFormat("00:60"));
    assertThrows(InvalidInputException.class, () -> PatternMatcher.testTimeFormat("00:0"));
    assertThrows(InvalidInputException.class, () -> PatternMatcher.testTimeFormat("f0:00"));
    assertThrows(InvalidInputException.class, () -> PatternMatcher.testTimeFormat("AB:CD"));
    assertThrows(InvalidInputException.class, () -> PatternMatcher.testTimeFormat("12:4!"));
  }

  @Test
  void testMatchLine() {
    assertDoesNotThrow(() -> PatternMatcher.testLineFormat("L1"));
    assertDoesNotThrow(() -> PatternMatcher.testLineFormat("F12"));
    assertDoesNotThrow(() -> PatternMatcher.testLineFormat("RE21"));
    assertDoesNotThrow(() -> PatternMatcher.testLineFormat("FLY1"));

    assertThrows(InvalidInputException.class, () -> PatternMatcher.testLineFormat("L"));
    assertThrows(InvalidInputException.class, () -> PatternMatcher.testLineFormat("L123"));
    assertThrows(InvalidInputException.class, () -> PatternMatcher.testLineFormat("L1A"));
    assertThrows(InvalidInputException.class, () -> PatternMatcher.testLineFormat("L1!"));
    assertThrows(InvalidInputException.class, () -> PatternMatcher.testLineFormat("L1 "));
    assertThrows(InvalidInputException.class, () -> PatternMatcher.testLineFormat("L 1"));
  }

  @Test
  void matchDestination() {
    assertDoesNotThrow(() -> PatternMatcher.testDestinationFormat("Bergen"));
    assertDoesNotThrow(() -> PatternMatcher.testDestinationFormat("ÆØÅæøå"));
    assertDoesNotThrow(() -> PatternMatcher.testDestinationFormat("trondheim"));
    assertDoesNotThrow(() -> PatternMatcher.testDestinationFormat("fdsjflkdsjfkdsljfkds"));

    assertThrows(InvalidInputException.class,
        () -> PatternMatcher.testDestinationFormat("Trondheim1"));
    assertThrows(InvalidInputException.class,
        () -> PatternMatcher.testDestinationFormat("abc_def"));
    assertThrows(InvalidInputException.class, () -> PatternMatcher.testDestinationFormat("abc123"));
    assertThrows(InvalidInputException.class, () -> PatternMatcher.testDestinationFormat("!"));
    assertThrows(InvalidInputException.class, () -> PatternMatcher.testDestinationFormat(
        "fdsjflkdsjfkdsljfkdsfdsfds"));
  }
}
