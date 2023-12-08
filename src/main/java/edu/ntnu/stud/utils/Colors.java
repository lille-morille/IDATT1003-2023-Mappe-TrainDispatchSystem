package edu.ntnu.stud.utils;

/**
 * Color constants for terminal output.
 */
public class Colors {
  public static final String RESET = "\u001B[0m";
  public static final String RED = "\u001B[31m";
  public static final String GREEN = "\u001B[32m";
  public static final String YELLOW = "\u001B[33m";
  public static final String BLUE = "\u001B[34m";
  public static final String CYAN = "\033[0;36m";
  public static final String PURPLE = "\u001B[35m";
  public static final String RED_BOLD = "\033[1;31m";
  public static final String GREEN_BOLD = "\033[1;32m";
  public static final String BLUE_BOLD = "\033[1;34m";
  public static final String CYAN_BOLD = "\033[1;36m";
  public static final String PURPLE_BOLD = "\033[1;35m";

  /**
   * Get the appropriate color for a train line.
   * Remember to use {@link #RESET} after the color to avoid bleeding into other lines.
   *
   * @param line   The line
   * @param isBold Whether the color should be bold
   * @return The color
   */
  public static String getForLine(String line, boolean isBold) {
    line = line.replaceAll("[0-9]", "");

    return switch (line) {
      case "R" -> isBold ? RED_BOLD : RED;
      case "L" -> isBold ? GREEN_BOLD : GREEN;
      case "F" -> isBold ? CYAN_BOLD : CYAN;
      case "RE" -> isBold ? BLUE_BOLD : BLUE;
      case "FLY" -> isBold ? PURPLE_BOLD : PURPLE;
      default -> "";
    };
  }
}
