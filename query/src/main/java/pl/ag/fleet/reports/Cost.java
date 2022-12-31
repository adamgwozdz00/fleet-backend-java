package pl.ag.fleet.reports;

import java.math.BigDecimal;

public interface Cost {

  BigDecimal getCost();

  Cost add(Cost cost);
}
