package pl.ag.fleet.vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import pl.ag.fleet.common.FuelType;

class VehicleTest {

  private Vehicle vehicle;


  @Test
  void testCreateVehicle() {
    // given

    // when
    createVehicle("Ford Focus");

    // then
    assertVehicleActualDriverUndefined();
    assertVehicleInsuranceUndefined();
    assertVehicleOverviewUndefined();
    assertVehicleActualFuel(0);
    assertVehicleActualKilometers(0);
  }

  private void assertVehicleOverviewUndefined() {
    assertEquals(Overview.initial(),vehicle.getOverview());
  }

  private void assertVehicleInsuranceUndefined() {
    assertEquals(Insurance.initial(),vehicle.getInsurance());
  }

  @Test
  void testUpdateState() {
    // given
    createVehicle("Ford Focus");

    // when
    updateVehicle(1, 60, 10_000);

    // then
    assertVehicleActualDriverId(1);
    assertVehicleActualFuel(60);
    assertVehicleActualKilometers(10_000);
  }

  @Test
  void testUpdateStateThrowsExceptionIfKilometersStateIsLowerThanBefore() {
    // given
    createVehicle("Ford Focus");
    updateVehicle(1, 60, 10_000);

    // when
    assertThrows(RuntimeException.class, () -> updateVehicle(1, 60, 9_000));

    // then
    assertVehicleActualDriverId(1);
    assertVehicleActualFuel(60);
    assertVehicleActualKilometers(10_000);
  }

  @Test
  void testUpdateInsurance() {
    // given
    createVehicle("Ford Focus");

    // when
    updateVehicle("Allianz", "2022-12-05", 555);

    // then
    assertInsuranceName("Allianz");
    assertInsuranceExpiresAt("2022-12-05");
    assertInsuranceCost(555);
  }

  @Test
  void testUpdateInsuranceWhenExpirationDateIncorrect() {
    // given
    createVehicle("Ford Focus");
    updateVehicle("Allianz", "2022-12-05", 555);

    // when
    assertThrows(RuntimeException.class, () -> updateVehicle("Allianz", "2022-09-05", 555));

    // then
  }

  private void assertInsuranceCost(double cost) {
    assertEquals(BigDecimal.valueOf(cost), vehicle.getInsurance().getCost());
  }

  private void assertInsuranceExpiresAt(String expirationDate) {
    assertEquals(LocalDate.parse(expirationDate), vehicle.getInsurance().getExpirationDate());
  }

  private void assertInsuranceName(String insuranceName) {
    assertEquals(insuranceName, this.vehicle.getInsurance().getName());
  }

  private void assertVehicleActualDriverId(long driverId) {
    assertEquals(new DriverId(driverId), this.vehicle.getState().getActualDriver());
  }

  private void updateVehicle(String insuranceName, String expirationDate, double cost) {
    this.vehicle.updateInsurance(
        new Insurance(insuranceName, LocalDate.parse(expirationDate), BigDecimal.valueOf(cost)));
  }

  private void updateVehicle(long driverId, double fuelInLiters, double kilometers) {
    this.vehicle.updateState(
        new VehicleState(new DriverId(driverId), new Liters(BigDecimal.valueOf(fuelInLiters)),
            new Kilometers(BigDecimal.valueOf(kilometers))));
  }

  private void assertVehicleActualKilometers(double kilometers) {
    assertEquals(kilometers,
        vehicle.getState().getActualKilometers().getKilometers().doubleValue());
  }

  private void assertVehicleActualFuel(double fuelInLiters) {
    assertEquals(fuelInLiters, vehicle.getState().getActualFuel().getLiters().doubleValue());
  }

  private void assertVehicleActualDriverUndefined() {
    assertNull(vehicle.getState().getActualDriver());
  }

  private void createVehicle(String vehicle) {
    this.vehicle = new Vehicle(new CompanyId(1L),
        new VehicleDetails(vehicle, vehicle, 2020, FuelType.DIESEL, "4S3BL626467206698"));
  }

}