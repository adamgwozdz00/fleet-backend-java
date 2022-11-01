package pl.ag.fleet.vehicle;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OverviewTest {

  private Overview actualOverview;
  private Overview newOverview;

  @BeforeEach
  void setUp() {
    newOverview = null;
  }

  @Test
  void testValidateNewOverview() {
    // given
    overviewWithExpirationDate("2022-10-15");

    // when
    validateNewOverviewWithExpirationDate("2022-12-10");

    // then
    assertNewOverviewIsValid();
  }

  @Test
  void testValidateNewOverviewWhenOverviewUndefined() {
    // given
    undefinedOverview();

    // when
    validateNewOverviewWithExpirationDate("2022-12-10");

    // then
    assertNewOverviewIsValid();
  }

  private void undefinedOverview() {
    actualOverview = Overview.initial(new VehicleId("1"));
  }

  @Test
  void testValidateNewOverviewWhenItsExpirationDateIsBeforeActualOverview() {
    // given
    overviewWithExpirationDate("2022-10-15");

    // when
    assertThrows(RuntimeException.class, () -> validateNewOverviewWithExpirationDate("2022-09-10"));

  }

  private void validateNewOverviewWithExpirationDate(String expirationDate) {
    newOverview = actualOverview.validateAndReturn(
        new Overview("", LocalDate.parse(expirationDate), BigDecimal.valueOf(100), "",
            new VehicleId("1")));
  }

  private void overviewWithExpirationDate(String expirationDate) {
    actualOverview = new Overview("", LocalDate.parse(expirationDate), BigDecimal.valueOf(100), "",
        new VehicleId("1"));
  }

  private void assertNewOverviewIsValid() {
    assertNotNull(newOverview);
  }

}