package pl.ag.fleet.reports;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CostService {


  private final CostDataService costDataService;

  public CostData getCosts(CostFilters costFilters) {
    return this.costDataService.getData(costFilters);
  }
}
