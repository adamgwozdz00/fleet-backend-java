package pl.ag.fleet.vehicle;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class TimePeriodTest {

  private final LocalDateTime TODAY = LocalDateTime.of(2023, 1, 2, 16, 30);
  private final LocalDateTime TOMORROW = LocalDateTime.of(2023, 1, 3, 16, 30);

  @Test
  void testCreateTimePeriod() {
    TimePeriod.of(TODAY, TOMORROW);
  }

  @Test
  void testCreateTimePeriodIfPeriodIsIncorrect() {
    assertThrows(IllegalArgumentException.class, () -> TimePeriod.of(TOMORROW, TODAY));
  }

}