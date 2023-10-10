package edu.ntnu.stud.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PatternMatcherTest {

  @Test
  void matches() {
    assertTrue(PatternMatcher.matchTime("00:00"));
    assertTrue(PatternMatcher.matchTime("23:59"));
    assertTrue(PatternMatcher.matchTime("12:00"));
    assertTrue(PatternMatcher.matchTime("01:00"));
    assertFalse(PatternMatcher.matchTime("24:00"));
    assertFalse(PatternMatcher.matchTime("00:60"));
    assertFalse(PatternMatcher.matchTime("00:0"));
    assertFalse(PatternMatcher.matchTime("f0:00"));
    assertFalse(PatternMatcher.matchTime("AB:CD"));
    assertFalse(PatternMatcher.matchTime("12:4!"));
  }
}
