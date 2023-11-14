package edu.ntnu.stud;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.stud.models.TrainDeparture;
import edu.ntnu.stud.models.TrainDepartureManager;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;

class TrainDispatchAppTest {

  @Test
  void testAddDeparture() {
    var manager = new TrainDepartureManager();
    manager.addDeparture(new TrainDeparture(
        10,
        30,
        "L4",
        200,
        "Bergen",
        4,
        10
    ));

    assertEquals(1, manager.getDepartures().size());

    manager.addDeparture(new TrainDeparture(
        10,
        30,
        "L4",
        300,
        "Bergen",
        4,
        10
    ));

    assertEquals(2, manager.getDepartures().size());
  }

  @Test
  void testGetDeparturesInCorrectOrder() {
    // Departures should be ordered by departure time, then track number
    var manager = new TrainDepartureManager();

    // 11:40
    manager.addDeparture(new TrainDeparture(
        11,
        30,
        "L4",
        200,
        "Bergen",
        4,
        10
    ));

    // 10:40
    manager.addDeparture(new TrainDeparture(
        10,
        30,
        "L4",
        102,
        "Bergen",
        4,
        10
    ));

    // 11:00 track 4
    manager.addDeparture(new TrainDeparture(
        10,
        50,
        "L4",
        111,
        "Bergen",
        4,
        10
    ));

    // 11:00 track 3
    manager.addDeparture(new TrainDeparture(
        10,
        50,
        "L4",
        112,
        "Bergen",
        3,
        10
    ));

    // Order should be 10:40, 11:00-track-3, 11:00-track-4, 11:40

    LocalTime departure1 = manager.getDepartures().get(0).getAdjustedDepartureTime();
    assertEquals(10, departure1.getHour());
    assertEquals(40, departure1.getMinute());

    var departure2 = manager.getDepartures().get(1);
    assertEquals(11, departure2.getAdjustedDepartureTime().getHour());
    assertEquals(0, departure2.getAdjustedDepartureTime().getMinute());
    assertEquals(3, departure2.getTrack());

    var departure3 = manager.getDepartures().get(2);
    assertEquals(11, departure3.getAdjustedDepartureTime().getHour());
    assertEquals(0, departure3.getAdjustedDepartureTime().getMinute());
    assertEquals(4, departure3.getTrack());

    LocalTime departure4 = manager.getDepartures().get(3).getAdjustedDepartureTime();
    assertEquals(11, departure4.getHour());
    assertEquals(40, departure4.getMinute());
  }
}
