package edu.ntnu.stud;

/**
 * Handles rendering Train Departures into a nice table format.
 */
public class TrainDepartureRenderer {

  /**
   * Calibration constant for the amount of spaces before the vertical border.
   * Adjusted to make the table look nice.
   */
  public static final int BORDER_SPACE_CALIBRATION = 7;

  /**
   * Renders a set of train departures into a nice table format in the terminal.
   * Uses System.out.println to write to the terminal.
   */
  public static void renderDepartures(TrainDeparture[] departures) {
    MaxLengths maxLengths = getMaxLengths(departures);

    System.out.println(getVerticalHeaderBorder(maxLengths));

    for (TrainDeparture departure : departures) {
      renderDeparture(departure, maxLengths);
    }

    System.out.println(
        getVerticalEndBorder(maxLengths.maxCoreLength() + maxLengths.maxTrackDelayLength()));
  }

  /**
   * Renders a single departure using computed max lengths.
   *
   * @param departure  The departure to render
   * @param maxLengths The max lengths computed
   */
  private static void renderDeparture(TrainDeparture departure, MaxLengths maxLengths) {
    System.out.print("|  ");
    System.out.print(departure.toFormattedString(maxLengths.maxCoreLength(),
        maxLengths.maxTrackDelayLength()));
    System.out.println(" |");
  }

  /**
   * Computes the max lengths for each part in the table.
   * This allows us to align the track number and end borders evenly.
   */
  private static MaxLengths getMaxLengths(TrainDeparture[] departures) {
    // Find the longest core info and track delay info
    // So we can align everything nicely in columns
    int maxCoreLength = 0;
    int maxTrackDelayLength = 0;

    for (TrainDeparture departure : departures) {
      var coreLength = departure.getCoreInfo().length();
      var trackDelayLength = departure.getTrackDelayInfo().length();

      if (coreLength > maxCoreLength) {
        maxCoreLength = coreLength;
      }
      if (trackDelayLength > maxTrackDelayLength) {
        maxTrackDelayLength = trackDelayLength;
      }
    }
    return new MaxLengths(maxCoreLength, maxTrackDelayLength);
  }

  /**
   * Creates a vertical border with leading spaces.
   *
   * @param maxTotalLength The maximum total length of the table, to append the correct amount of
   *                       spaces before the border
   * @return A vertical border string with leading spaces to format the table correctly
   */
  private static String getVerticalEndBorder(int maxTotalLength) {
    return new String(new char[maxTotalLength + BORDER_SPACE_CALIBRATION]).replace("\0", "=");
  }

  /**
   * Creates the top header border with titles for each column.
   *
   * @param maxLengths The max lengths computed, used for calibrating headers with columns.
   * @return The top header bar to display in the table.
   */
  private static String getVerticalHeaderBorder(MaxLengths maxLengths) {
    // should look like this: ===time===line===destination===|track|train|delay===
    final var base = "time==line|dest";
    var str = "===" + base;
    str += new String(new char[maxLengths.maxCoreLength() - base.length() + 1]).replace("\0", "=");
    str += "track|train|delay";
    str += new String(new char[maxLengths.maxTrackDelayLength() - "track|train|delay".length() + 3])
        .replace("\0", "=");
    return str;
  }

  /**
   * Simple struct for storing the max core length and track delay length.
   */
  private record MaxLengths(int maxCoreLength, int maxTrackDelayLength) {
  }
}
