package pl.ag.fleet.vehicle;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.ag.fleet.common.FuelType;

class VehicleServiceTest {

  private MockVehicleRepository repository;
  private VehicleService service;

  @BeforeEach
  void setUp() {
    repository = new MockVehicleRepository();
    service = new VehicleService(repository);
  }

  @Test
  void testCreateVehicle() {
    //given

    // when
    createVehicle();

    //then
    assertNumberOfVehicles(1);
  }


  @Test
  void testUpdateVehicleState() {
    // given
    databaseWithVehicleId("1");

    // when
    assertDoesNotThrow(() -> updateVehicleState("1"));

    // then
    assertNumberOfVehicles(1);
  }

  @Test
  void testUpdateVehicleStateWhenVehicleNotExists() {
    // given

    // when
    assertThrows(IllegalStateException.class, () -> updateVehicleState("1"));

    // then
    assertNumberOfVehicles(0);
  }


  @Test
  void testAddOrUpdateInsurance() {
    // given
    databaseWithVehicleId("1");

    // when
    assertDoesNotThrow(() -> addOrUpdateInsurance("1"));

    // then
    assertNumberOfVehicles(1);
  }

  @Test
  void testAddOrUpdateInsuranceWhenVehicleNotExists() {
    // given

    // when
    assertThrows(IllegalStateException.class, () -> addOrUpdateInsurance("1"));

    // then
    assertNumberOfVehicles(0);
  }


  @Test
  void testAddOrUpdateOverview() {
    // given
    databaseWithVehicleId("1");

    // when
    assertDoesNotThrow(() -> addOrUpdateOverview("1"));

    // then
    assertNumberOfVehicles(1);
  }

  @Test
  void testAddOrUpdateOverviewWhenVehicleNotExists() {
    // given

    // when
    assertThrows(IllegalStateException.class, () -> addOrUpdateOverview("1"));

    // then
    assertNumberOfVehicles(0);
  }

  private void addOrUpdateOverview(String vehicleId) {
    service.addOrUpdateOverview(new VehicleId(vehicleId),
        new OverviewDTO("Overview 1", LocalDate.now().plusDays(3), BigDecimal.valueOf(200), ""));
  }

  private void addOrUpdateInsurance(String vehicleId) {
    this.service.addOrUpdateInsurance(new VehicleId(vehicleId), new InsuranceDTO("Insurance",
        LocalDate.now().plusDays(3), BigDecimal.valueOf(200)));
  }

  private void updateVehicleState(String vehicleId) {
    this.service.updateVehicleState(new VehicleId(vehicleId), new VehicleStateDTO(
        1L, BigDecimal.valueOf(200), BigDecimal.valueOf(200), LocalDateTime.now()));
  }

  private void assertNumberOfVehicles(int numberOfVehicles) {
    assertTrue(numberOfVehicles <= this.repository.vehicles.size());
  }

  private void createVehicle() {
    this.service.createVehicle(new VehicleDTO(1L,
        "Ford",
        "Focus",
        2020,
        FuelType.DIESEL,
        "VINNUMBER123"));
  }

  private void databaseWithVehicleId(String vehicleId) {
    this.repository.vehicles.put(new VehicleId(vehicleId), new Vehicle(new CompanyId(1L),
        new VehicleDetails("Ford", "Focus", 2020, FuelType.DIESEL, "VINNUMBER123")));
  }
}
