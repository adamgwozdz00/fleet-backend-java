package pl.ag.fleet.vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
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

  @Test
  void testCompare() {
    var A = TimePeriod.of(LocalDateTime.of(2023, 1, 1, 0, 0), LocalDateTime.of(2023, 1, 4, 0, 0));
    var B = TimePeriod.of(LocalDateTime.of(2023, 1, 2, 0, 0), LocalDateTime.of(2023, 1, 3, 0, 0));

    var LIST_A = Arrays.asList(A, B);
    var LIST_B = Arrays.asList(B, A);

    assertMax(LIST_A, B);
    assertMax(LIST_B, B);
  }

  private void assertMax(List<TimePeriod> list, TimePeriod variant) {
    assertEquals(variant, list.stream().max(TimePeriod::compareTo).get());
  }

}