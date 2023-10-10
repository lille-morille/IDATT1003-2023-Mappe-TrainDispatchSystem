package edu.ntnu.stud;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.stud.models.TrainDeparture;
import org.junit.jupiter.api.Test;

class TrainDispatchAppTest {

  @Test
  void addDeparture() {
    TrainDispatchApp app = new TrainDispatchApp();
    app.addDeparture(new TrainDeparture(
        10,
        30,
        "L4",
        200,
        "Bergen",
        4,
        10
    ));
   
    assertEquals(1, app.getDepartures().size());
  }
}
