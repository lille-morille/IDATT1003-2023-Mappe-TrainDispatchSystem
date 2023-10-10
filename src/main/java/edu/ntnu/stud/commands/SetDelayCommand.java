package edu.ntnu.stud.commands;

import edu.ntnu.stud.TrainDispatchApp;
import edu.ntnu.stud.models.TrainDeparture;
import java.time.Duration;
import java.util.Scanner;

/**
 * Sets the delay to a train departure.
 */
public final class SetDelayCommand extends Command {
  public SetDelayCommand() {
    super("set-delay", "Sets the delay to a train departure");
  }

  @Override
  public void execute(TrainDispatchApp app) {
    new RenderDeparturesCommand().execute(app);

    System.out.println("Add delay to a train departure");
    final var in = new Scanner(System.in);

    TrainDeparture departure;
    while (true) {
      int trainNumber;
      System.out.print("Which train? (train number): ");
      try {
        trainNumber = in.nextInt();
      } catch (Exception e) {
        System.out.println("Invalid input, please try again.");
        in.nextLine();
        continue;
      }

      // TODO refactor into TrainDispatchApp
      // find the train
      departure = app.getDepartures().stream()
          .filter(d -> d.getTrainNumber() == trainNumber)
          .findFirst()
          .orElse(null);

      if (departure == null) {
        System.out.println("No train found with this train number. Please try again.");
        continue;
      }

      break;
    }

    int delayMinutes;
    while (true) {
      System.out.println("Enter the delay in minutes: ");
      try {
        delayMinutes = in.nextInt();
      } catch (Exception e) {
        System.out.println("Invalid input, please try again.");
        continue;
      }

      if (delayMinutes < 0 || delayMinutes > 60 * 24) {
        System.out.println("Delay must be between 0 minutes and 1 day, please try again.");
        continue;
      }

      break;
    }

    departure.setDelay(Duration.ofMinutes(delayMinutes));
  }
}
