package pl.ag.fleet.driver;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class SeniorityTest {

  @Test
  void testSeniorityIsIncorrect1() {
    // when
    assertThrows(IllegalArgumentException.class, () -> seniorityInYears(-1));
  }

  @Test
  void testSeniorityIsIncorrect2() {
    // when
    assertThrows(IllegalArgumentException.class, () -> seniorityInYears(100));
  }

  private void seniorityInYears(int i) {
    new Seniority(i);
  }
}