package edu.ntnu.stud;

/**
 * Handles rendering Train Departures into a nice table format.
 */
public class TrainDepartureRenderer {
  /**
   * Renders a set of train departures into a nice table format in the terminal.
   * Uses System.out.println to write to the terminal.
   */
  public static void renderDepartures(TrainDeparture[] departures) {
    MaxLengths maxLengths = getMaxLengths(departures);

    System.out.println(
        getVerticalBorder(maxLengths.maxCoreLength() + maxLengths.maxTrackDelayLength()));

    for (TrainDeparture departure : departures) {
      renderDeparture(departure, maxLengths);
    }

    System.out.println(
        getVerticalBorder(maxLengths.maxCoreLength() + maxLengths.maxTrackDelayLength()));
  }

  /**
   * Renders a single departure using computed max lengths.
   *
   * @param departure  The departure to render
   * @param maxLengths The max lengths computed
   */
  private static void renderDeparture(TrainDeparture departure, MaxLengths maxLengths) {
    System.out.print("|| ");
    System.out.print(departure.toFormattedString(maxLengths.maxCoreLength(),
        maxLengths.maxTrackDelayLength()));
    System.out.println("||");
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
  private static String getVerticalBorder(int maxTotalLength) {
    return new String(new char[maxTotalLength + 7]).replace("\0", "=");
  }

  /**
   * Simple struct for storing the max core length and track delay length.
   */
  private record MaxLengths(int maxCoreLength, int maxTrackDelayLength) {
  }
}
