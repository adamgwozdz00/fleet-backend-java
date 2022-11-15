package pl.ag.fleet.application.driver;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@ApiResponse
@Data
@AllArgsConstructor
public class DriverResponse {

  private boolean success;
  private String FailureReason;
}
