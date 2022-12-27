package pl.ag.fleet.driver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.ag.fleet.driver.Title.JUNIOR;
import static pl.ag.fleet.driver.Title.SENIOR;

import org.junit.jupiter.api.Test;
import pl.ag.fleet.common.CompanyId;

class DriverTest {

  private Driver driver;

  @Test
  void testDriverPromotion() {
    // given
    driver(3);

    // when
    promoteDriver();

    // then
    assertDriverTitle(SENIOR);
    assertDriverSeniorityInYears(3);
  }

  @Test
  void testDriverPromotionWhenDriverIsAlreadySenior() {
    // given
    driver(3);
    promoteDriver();

    // when
    assertThrows(IllegalStateException.class, this::promoteDriver);

    // then
    assertDriverTitle(SENIOR);
    assertDriverSeniorityInYears(3);
  }

  @Test
  void testUpdateDriverSeniority() {
    // given
    driver(3);

    // when
    updateSeniority(4);

    // then
    assertDriverTitle(JUNIOR);
    assertDriverSeniorityInYears(4);
  }

  @Test
  void testUpdateDriverSeniority2() {
    // given
    driver(3);

    // when
    updateSeniority(2);

    // then
    assertDriverTitle(JUNIOR);
    assertDriverSeniorityInYears(2);
  }

  private void driver(int seniorityInYears) {
    this.driver = new Driver(new CompanyId(1L), new DriverDetails("Adam", "Kowalski"),
        new Seniority(seniorityInYears));
  }

  private void updateSeniority(int seniorityInYears) {
    driver.updateSeniority(new Seniority(seniorityInYears));
  }

  private void assertDriverSeniorityInYears(int seniorityInYears) {
    assertEquals(new Seniority(seniorityInYears), driver.getSeniority());
  }

  private void assertDriverTitle(Title title) {
    assertEquals(title, driver.getTitle());
  }

  private void promoteDriver() {
    this.driver.promote();
  }
}