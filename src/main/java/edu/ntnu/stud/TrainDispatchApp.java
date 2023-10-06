package edu.ntnu.stud;

/**
 * This is the main class for the train dispatch application.
 */
public class TrainDispatchApp {
  /**
   * The entry point of the program.
   */
  public static void main(String[] args) {
    TrainDispatchApp app = new TrainDispatchApp();
    app.init();
    app.start();
  }

  public void init() {
    System.out.println("Starting train dispatch application...");
  }

  public void start() {
    TrainDepartureRenderer.renderDepartures(getDepartures());
  }

  private TrainDeparture[] getDepartures() {
    return new TrainDeparture[] {
        new TrainDeparture(
            10,
            30,
            "L4",
            200,
            "Bergen",
            1,
            10
        ),
        new TrainDeparture(
            10,
            35,
            "R3",
            200,
            "Trondheim",
            2,
            5
        ),
        new TrainDeparture(
            10,
            40,
            "L4",
            200,
            "Stavanger",
            1,
            0
        ),
        new TrainDeparture(
            10,
            45,
            "R3",
            200,
            "Oslo",
            2,
            0
        ),
    };
  }
}
