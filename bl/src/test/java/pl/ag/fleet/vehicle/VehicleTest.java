package pl.ag.fleet.vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.ag.fleet.common.CompanyId;
import pl.ag.fleet.common.FuelType;
import pl.ag.fleet.common.Liters;

class VehicleTest {

  private Vehicle vehicle;

  private final DriverId DRIVER_1 = new DriverId(1L);
  private final DriverId DRIVER_2 = new DriverId(2L);

  private MockDriverAvailabilityService driverAvailabilityService;

  private final LocalDateTime TOMORROW = LocalDateTime.of(2022, 12, 13, 10, 10);
  private final LocalDateTime TODAY = LocalDateTime.of(2022, 12, 12, 10, 10);
  private final LocalDateTime YESTERDAY = LocalDateTime.of(2022, 12, 11, 10, 10);

  private final Kilometers KM100 = new Kilometers(BigDecimal.valueOf(100));
  private final Kilometers KM200 = new Kilometers(BigDecimal.valueOf(200));

  private final Kilometers KM300 = new Kilometers(BigDecimal.valueOf(300));

  @BeforeEach
  void setUp() {
    vehicle = new Vehicle(new CompanyId(1),
        new VehicleDetails("", "", 2022, FuelType.DIESEL, ""));
    driverAvailabilityService = new MockDriverAvailabilityService();
  }

  @Test
  void testUpdateState() {
    // given

    // when
    var result = vehicle.updateState(state(DRIVER_1, TODAY, KM100), driverAvailabilityService);

    // then
    assertSuccess(result);
  }

  @Test
  void testUpdateStateWhenVehicleWasUsedYesterday() {
    // given
    vehicle.updateState(state(DRIVER_1, YESTERDAY, KM100), driverAvailabilityService);

    // when
    var result = vehicle.updateState(state(DRIVER_1, TODAY, KM200), driverAvailabilityService);

    // then
    assertSuccess(result);
  }

  @Test
  void testUpdateStateWhenWasUsedTodayToday() {
    // given
    vehicle.updateState(state(DRIVER_2, TODAY, KM100), driverAvailabilityService);

    // when
    var result = vehicle.updateState(state(DRIVER_1, TODAY, KM200), driverAvailabilityService);

    // then
    assertFail("Vehicle is busy.", result);
  }

  @Test
  void testUpdateStateWhenDriverIsAlreadyOnDrive() {
    // given
    driverOnRoad(DRIVER_1, TODAY);

    // when
    var result = vehicle.updateState(state(DRIVER_1, TODAY, KM100), driverAvailabilityService);

    // then
    assertFail("Driver is busy.", result);
  }


  @Test
  void testUpdateStateWhenNumberOfKilometersIncorrect() {
    // given
    vehicle.updateState(state(DRIVER_1, YESTERDAY, KM100), driverAvailabilityService);
    vehicle.updateState(state(DRIVER_1, TODAY, KM300), driverAvailabilityService);
    // when
    var result = vehicle.updateState(state(DRIVER_1, TOMORROW, KM200), driverAvailabilityService);

    // then
    assertFail("Invalid number of kilometers.", result);
  }

  private void driverOnRoad(DriverId driver1, LocalDateTime today) {
    driverAvailabilityService.setDriverAt(driver1, today);
  }


  private void assertSuccess(Result result) {
    assertTrue(result.isSuccess());
  }

  private void assertFail(String expectReason, Result result) {
    assertFalse(result.isSuccess());
    assertEquals(expectReason, result.getReason());
  }

  private VehicleState state(DriverId driver, LocalDateTime dateTime, Kilometers kilometers) {
    return new VehicleState(driver, new Liters(BigDecimal.valueOf(100)),
        kilometers, dateTime, VehicleStatus.ACTIVE,
        new Position());
  }

  private class MockDriverAvailabilityService implements DriverAvailabilityService {

    private final Set<String> driverBusyAtTime;

    public MockDriverAvailabilityService() {
      driverBusyAtTime = new HashSet<>();
    }

    @Override
    public boolean isAvailableAt(DriverId driverId, LocalDateTime time) {
      var driverAndDateTime = driverId.getId() + "" + time.toLocalDate() + "";
      var contains = driverBusyAtTime.contains(driverAndDateTime);
      driverBusyAtTime.add(driverAndDateTime);
      return !contains;
    }

    public void setDriverAt(DriverId driverId, LocalDateTime time) {
      this.driverBusyAtTime.add(driverId.getId() + "" + time.toLocalDate() + "");
    }
  }
}