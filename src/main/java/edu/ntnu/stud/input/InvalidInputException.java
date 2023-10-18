package edu.ntnu.stud.input;

import com.sun.source.tree.NewArrayTree;

class InvalidInputException extends Exception {
  public static final String DEFAULT_MESSAGE = "Invalid input, please try again.";
  public static final String INVALID_FORMAT_MESSAGE = "Invalid format, please try again.";

  public InvalidInputException() {
    super(DEFAULT_MESSAGE);
  }

  public InvalidInputException(String message) {
    super(message);
  }

  public static InvalidInputException invalidFormat() {
    return new InvalidInputException(INVALID_FORMAT_MESSAGE);
  }
}
