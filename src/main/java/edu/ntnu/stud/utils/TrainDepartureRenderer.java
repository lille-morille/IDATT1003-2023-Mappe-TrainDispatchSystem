package edu.ntnu.stud.utils;

import edu.ntnu.stud.models.TrainDeparture;
import java.util.List;

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
  public static void renderDepartures(List<TrainDeparture> departures) {
    if (departures.isEmpty()) {
      System.out.println("No departures to render.");
      return;
    }

    var maxLengths = MaxLengths.fromDepartures(departures);

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
    System.out.print(departureToFormattedString(departure, maxLengths.maxCoreLength(),
        maxLengths.maxTrackDelayLength()));
    System.out.println(" |");
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
    str +=
        new String(new char[Math.max(maxLengths.maxCoreLength() - base.length() + 1, 1)]).replace(
            "\0",
            "=");
    str += "track|train";
    str += new String(
        new char[Math.max(maxLengths.maxTrackDelayLength() - "train|track".length() + 3, 3)])
        .replace("\0", "=");
    return str;
  }

  /**
   * Simple struct for storing the max core length and track delay length.
   */
  private record MaxLengths(int maxCoreLength, int maxTrackDelayLength) {
    /**
     * Computes the max lengths for each part in the table.
     * This allows us to align the track number and end borders evenly.
     */
    public static MaxLengths fromDepartures(List<TrainDeparture> departures) {
      // Find the longest core info and track delay info
      // So we can align everything nicely in columns
      int maxCoreLength = 0;
      int maxTrackDelayLength = 0;

      for (TrainDeparture departure : departures) {
        var coreLength = getCoreInfoString(departure).length();
        var trackDelayLength = getTrackDelayInfoString(departure).length();

        if (coreLength > maxCoreLength) {
          maxCoreLength = coreLength;
        }
        if (trackDelayLength > maxTrackDelayLength) {
          maxTrackDelayLength = trackDelayLength;
        }
      }
      return new MaxLengths(maxCoreLength, maxTrackDelayLength);
    }
  }

  /**
   * Returns the second pieces of information to display in the departure table.
   */
  public static String getTrackDelayInfoString(TrainDeparture departure) {
    return String.format("|%s| n.%s %s",
        departure.getTrack(),
        departure.getTrainNumber(),
        DurationRenderer.render(departure.getDelay(), true));
  }


  /**
   * Returns the first pieces of information to display in the departure table.
   */
  public static String getCoreInfoString(TrainDeparture departure) {
    return String.format("%s %s-%s",
        departure.getAdjustedDepartureTime().toString(),
        departure.getLine(),
        departure.getDestination());
  }

  /**
   * Returns the first pieces of information to display in the departure table, with color.
   */
  public static String getCoreInfoWithColorString(TrainDeparture departure) {
    var lineColor = Colors.getForLine(departure.getLine(), false);
    var lineColorBold = Colors.getForLine(departure.getLine(), true);
    return String.format("%s %s-%s",
        (departure.isDelayed() ? Colors.YELLOW : "")
            + departure.getAdjustedDepartureTime().toString(),
        lineColorBold + departure.getLine(),
        Colors.RESET + lineColor + departure.getDestination()) + Colors.RESET;
  }


  /**
   * Returns the second pieces of information to display in the departure table, with color.
   */
  public static String getTrackDelayInfoWithColorString(TrainDeparture departure) {
    return String.format("%s n.%s %s",
        departure.getTrack() == -1 ? "| |" : "|" + departure.getTrack() + "|",
        departure.getTrainNumber(),
        (departure.isDelayed() ? Colors.YELLOW : "") + DurationRenderer.render(departure.getDelay(),
            true))
        + Colors.RESET;
  }

  /**
   * Returns a formatted string with the departure information.
   * This is used to align the departure information in columns.
   *
   * @param maxCoreLength       The maximum length of the core info across all train departures.
   * @param maxTrackDelayLength The maximum length of the track delay info across all train
   * @return A formatted string with the departure information, that fits with the rest.
   */
  public static String departureToFormattedString(TrainDeparture departure, int maxCoreLength,
                                                  int maxTrackDelayLength) {
    return String.format("%s%s%s%s",
        getCoreInfoWithColorString(departure),
        " ".repeat(maxCoreLength - getCoreInfoString(departure).length() + 1),
        getTrackDelayInfoWithColorString(departure),
        " ".repeat(maxTrackDelayLength - getTrackDelayInfoString(departure).length() + 1));
  }
}
