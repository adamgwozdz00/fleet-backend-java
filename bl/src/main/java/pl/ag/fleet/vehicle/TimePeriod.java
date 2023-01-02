package pl.ag.fleet.vehicle;

import java.time.LocalDateTime;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class TimePeriod implements Comparable<TimePeriod> {

  private LocalDateTime from;
  private LocalDateTime to;

  static TimePeriod of(LocalDateTime from, LocalDateTime to) {
    if (from.isAfter(to)) {
      throw new IllegalArgumentException("Improper time period.");
    }
    return new TimePeriod(from, to);
  }


  @Override
  public int compareTo(TimePeriod o) {
    return this.from.compareTo(o.from);
  }
}
