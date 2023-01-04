package pl.ag.fleet.acl.reports;

import pl.ag.fleet.common.CompanyId;
import pl.ag.fleet.reports.CostFilters;
import pl.ag.fleet.reports.CostQueryParams;


class CostParamsFactory {

  CostQueryParams create(CostFilters costFilters) {
    return new CostQueryParams(new CompanyId(costFilters.getCompanyId()), costFilters.getYears());
  }
}
