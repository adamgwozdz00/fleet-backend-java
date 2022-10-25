package pl.ag.fleet.event;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;
import pl.ag.fleet.common.FuelType;

class EventFactoryTest {

  private EventFactory factory = new EventFactory();

  @Test
  void test() {
    // given
    var event = new CreateVehicleEvent(
        new VinNumber("1231"),
        new CompanyId(1L),
        new Make("Ford"),
        new Model("Focus"),
        new ProductionYear(2022),
        FuelType.DIESEL);

    // when
    var result = factory.createEvent(event).getData();
    // then
    assertArrayEquals(new String[]{"vinNumber",
        "companyId",
        "make",
        "model",
        "productionYear",
        "fuelType",
        "time",
        "eventType"}, result.keySet().toArray());
  }
}