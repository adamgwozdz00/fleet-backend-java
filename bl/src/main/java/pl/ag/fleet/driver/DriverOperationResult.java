package pl.ag.fleet.driver;

import lombok.Value;

@Value
public class DriverOperationResult {

  private boolean success;
  private String reason;

  public DriverOperationResult() {
    this.success = true;
    this.reason = "";
  }

  public DriverOperationResult(String reason) {
    this.success = false;
    this.reason = reason;
  }
}
