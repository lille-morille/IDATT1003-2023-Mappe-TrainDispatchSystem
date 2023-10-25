package edu.ntnu.stud;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.stud.models.TrainDeparture;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;

class TrainDispatchAppTest {

  @Test
  void testAddDeparture() {
    var app = new TrainDispatchApp();
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

    app.addDeparture(new TrainDeparture(
        10,
        30,
        "L4",
        300,
        "Bergen",
        4,
        10
    ));

    assertEquals(2, app.getDepartures().size());
  }

  @Test
  void testGetDeparturesInCorrectOrder() {
    // Departures should be ordered by departure time, then track number
    var app = new TrainDispatchApp();

    // 11:40
    app.addDeparture(new TrainDeparture(
        11,
        30,
        "L4",
        200,
        "Bergen",
        4,
        10
    ));

    // 10:40
    app.addDeparture(new TrainDeparture(
        10,
        30,
        "L4",
        102,
        "Bergen",
        4,
        10
    ));

    // 11:00 track 4
    app.addDeparture(new TrainDeparture(
        10,
        50,
        "L4",
        111,
        "Bergen",
        4,
        10
    ));

    // 11:00 track 3
    app.addDeparture(new TrainDeparture(
        10,
        50,
        "L4",
        112,
        "Bergen",
        3,
        10
    ));

    // Order should be 10:40, 11:00-track-3, 11:00-track-4, 11:40

    LocalTime departure1 = app.getDepartures().get(0).getFinalDepartureTime();
    assertEquals(10, departure1.getHour());
    assertEquals(40, departure1.getMinute());

    var departure2 = app.getDepartures().get(1);
    assertEquals(11, departure2.getFinalDepartureTime().getHour());
    assertEquals(0, departure2.getFinalDepartureTime().getMinute());
    assertEquals(3, departure2.getTrack());

    var departure3 = app.getDepartures().get(2);
    assertEquals(11, departure3.getFinalDepartureTime().getHour());
    assertEquals(0, departure3.getFinalDepartureTime().getMinute());
    assertEquals(4, departure3.getTrack());

    LocalTime departure4 = app.getDepartures().get(3).getFinalDepartureTime();
    assertEquals(11, departure4.getHour());
    assertEquals(40, departure4.getMinute());
  }
}
