package edu.ntnu.stud.commands;

import edu.ntnu.stud.TrainDispatchApp;
import edu.ntnu.stud.utils.UserInputHandler;

public class SearchCommand extends Command {
  public SearchCommand() {
    super("search", "Search for a train departure by train number");
  }

  @Override
  public void execute(TrainDispatchApp app) {
    System.out.println("Search for train departure");

    var input = new UserInputHandler(app);
    var departure = input.getDeparture();

    System.out.println(departure.toString());
  }
}
