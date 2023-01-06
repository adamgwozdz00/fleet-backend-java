package pl.ag.fleet.vehicle;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import pl.ag.fleet.common.Liters;

class VehicleStateTest {

  private final DriverId DRIVER_1 = new DriverId(1L);
  private final DriverId DRIVER_2 = new DriverId(2L);
  private final LocalDateTime TODAY = LocalDateTime.of(2022, 12, 12, 10, 10);
  private final LocalDateTime YESTERDAY = LocalDateTime.of(2022, 12, 11, 10, 10);

  @Test
  void testHasDriveAt() {
    // given
    var existingState = state(DRIVER_1, YESTERDAY);

    // when
    var result = existingState.hasAnyDriverAt(state(DRIVER_1, TODAY));

    // then
    assertFalse(result);
  }

  @Test
  void testHasDriveAt2() {
    // given
    var existingState = state(DRIVER_1, TODAY);

    // when
    var result = existingState.hasAnyDriverAt(state(DRIVER_2, TODAY));

    // then
    assertTrue(result);
  }


  private VehicleState state(DriverId driver, LocalDateTime dateTime) {
    return new VehicleState(driver, new Liters(BigDecimal.valueOf(100)),
        new Kilometers(BigDecimal.valueOf(100)), dateTime, VehicleStatus.ACTIVE,
        new Position());
  }

}