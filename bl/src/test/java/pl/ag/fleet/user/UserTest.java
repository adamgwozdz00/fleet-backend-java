package pl.ag.fleet.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

  private MockVehicleAvailabilityService mockVehicleAvailabilityService;
  private User user;
  private Result result;

  @BeforeEach
  void setUp() {
    this.user = new User(new UserId(1), new CompanyId(1L));
    mockVehicleAvailabilityService = new MockVehicleAvailabilityService();
  }

  @Test
  void testAddVehicle() {
    // given

    // when
    addVehicleWithId("1");

    // then
    assertSuccess();
    assertUserVehicles(1);

  }

  @Test
  void testAddVehicleTwice() {
    // given
    addVehicleWithId("1");

    // when
    addVehicleWithId("1");

    // then
    assertFail();
    assertUserVehicles(1);
  }

  @Test
  void testAddVehicleWhenOtherUserAlreadyHasVehicle() {
    // given
    otherUserWithVehicleId("1");

    // when
    addVehicleWithId("1");

    // then
    assertFail();
    assertUserVehicles(0);
  }

  @Test
  void testRemoveVehicle() {
    // given
    addVehicleWithId("1");
    addVehicleWithId("2");

    // when
    removeVehicleWithId("2");

    // then
    assertUserVehicles(1);
  }

  private void removeVehicleWithId(String vehicleId) {
    this.user.remove(new VehicleId(vehicleId));
  }

  private void assertSuccess() {
    assertTrue(result.isSuccess());
  }

  private void addVehicleWithId(String vehicleId) {
    this.result = this.user.add(new VehicleId(vehicleId), mockVehicleAvailabilityService);
  }

  private void assertFail() {
    assertFalse(this.result.isSuccess());
  }

  private void assertUserVehicles(int countOfVehicles) {
    assertEquals(countOfVehicles, this.user.getCountOfVehicles());
  }

  private void otherUserWithVehicleId(String vehicleId) {
    this.mockVehicleAvailabilityService.vehicleIdSet.add(new VehicleId(vehicleId));
  }

  private class MockVehicleAvailabilityService implements VehicleAvailabilityService {

    Set<VehicleId> vehicleIdSet;

    public MockVehicleAvailabilityService() {
      this.vehicleIdSet = new HashSet<>();
    }

    @Override
    public boolean isAvailable(VehicleId vehicleId) {
      var contains = vehicleIdSet.contains(vehicleId);
      vehicleIdSet.add(vehicleId);
      return !contains;
    }
  }
}