package pl.ag.fleet.application.reports;

import java.time.Year;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ag.fleet.manager.security.AuthenticatedUserContextHolder;
import pl.ag.fleet.reports.CostData;
import pl.ag.fleet.reports.CostFilters;
import pl.ag.fleet.reports.CostService;

@RestController
@RequestMapping("/cost")
@RequiredArgsConstructor
public class CostReportsController {

  private final AuthenticatedUserContextHolder contextHolder;
  private final CostService costService;

  @GetMapping
  public ResponseEntity<CostData> getCosts(CostParams prams) {
    val filters = new CostFilters(contextHolder.getAuthenticatedUserCompany(),
        prams.getYears().stream().map(Year::of).collect(
            Collectors.toList()),
        prams.isIncludeFuelCost(),
        prams.isIncludeOverviewCost(),
        prams.isIncludeInsuranceCost(),
        prams.isIncludeRepairCost());
    return ResponseEntity.ok(this.costService.getCosts(filters));
  }
}
