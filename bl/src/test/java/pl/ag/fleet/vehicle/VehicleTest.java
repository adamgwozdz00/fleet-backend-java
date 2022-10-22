package pl.ag.fleet.vehicle;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import pl.ag.fleet.common.FuelType;

class VehicleTest {

  private Vehicle vehicle;


  @Test
  void testCreateVehicle(){
    // given

    // when
    createVehicle("Ford Focus");

    // then
    assertVehicleActualDriverUndefined();
    assertVehicleActualFuel(0);
    assertVehicleActualKilometers(0);
  }

  @Test
  void testUpdateState(){
    // given
    createVehicle("Ford Focus");

    // when
    updateVehicle(1,60,10_000);

    // then
    assertVehicleActualDriverId(1);
    assertVehicleActualFuel(60);
    assertVehicleActualKilometers(10_000);
  }

  @Test
  void testUpdateStateThrowsExceptionIfKilometersStateIsLowerThanBefore(){
    // given
    createVehicle("Ford Focus");
    updateVehicle(1,60,10_000);

    // when
    assertThrows(RuntimeException.class,() ->updateVehicle(1,60,9_000));

    // then
    assertVehicleActualDriverId(1);
    assertVehicleActualFuel(60);
    assertVehicleActualKilometers(10_000);
  }

  private void assertVehicleActualDriverId(long driverId) {
    assertEquals(new DriverId(driverId),this.vehicle.getState().getActualDriver());
  }

  private void updateVehicle(long driverId,double fuelInLiters, double kilometers) {
    this.vehicle.updateState(new VehicleState(new DriverId(driverId), new Liters(BigDecimal.valueOf(fuelInLiters)),new Kilometers(BigDecimal.valueOf(kilometers))));
  }

  private void assertVehicleActualKilometers(double kilometers) {
    assertEquals(kilometers,vehicle.getState().getActualKilometers().getBigDecimal().doubleValue());
  }

  private void assertVehicleActualFuel(double fuelInLiters) {
    assertEquals(fuelInLiters,vehicle.getState().getActualFuel().getLiters().doubleValue());
  }

  private void assertVehicleActualDriverUndefined() {
    assertNull(vehicle.getState().getActualDriver());
  }

  private void createVehicle(String vehicle) {
    this.vehicle = new Vehicle(new CompanyId(1L),new VehicleDetails(vehicle,vehicle,2020, FuelType.DIESEL,"4S3BL626467206698"));
  }

}