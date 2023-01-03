package pl.ag.fleet.reports;

import java.time.Year;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CostFilters {

  private Long companyId;
  private List<Year> years;
}
