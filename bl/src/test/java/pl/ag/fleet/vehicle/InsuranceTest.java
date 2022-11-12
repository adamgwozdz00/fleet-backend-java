package pl.ag.fleet.vehicle;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InsuranceTest {

  private Insurance actualInsurance;
  private Insurance newInsurance;

  @BeforeEach
  void setUp() {
    newInsurance = null;
  }

  @Test
  void testValidateNewInsurance() {
    // given
    insuranceWithExpirationDate("2022-10-15");

    // when
    validateNewInsuranceWithExpirationDate("2022-12-10");

    // then
    assertNewInsuranceIsValid();
  }

  @Test
  void testValidateNewInsuranceWhenItsExpirationDateIsBeforeOrEqualActualInsurance() {
    // given
    insuranceWithExpirationDate("2022-10-15");

    // when
    assertThrows(RuntimeException.class,
        () -> validateNewInsuranceWithExpirationDate("2022-10-15"));

  }


  private void validateNewInsuranceWithExpirationDate(String expirationDate) {
    newInsurance = actualInsurance.validateAndReturn(
        new Insurance("", LocalDate.parse(expirationDate), BigDecimal.valueOf(100),
            new VehicleId("1")));
  }

  private void insuranceWithExpirationDate(String expirationDate) {
    actualInsurance = new Insurance("", LocalDate.parse(expirationDate), BigDecimal.valueOf(100),
        new VehicleId("1"));
  }

  private void assertNewInsuranceIsValid() {
    assertNotNull(newInsurance);
  }
}