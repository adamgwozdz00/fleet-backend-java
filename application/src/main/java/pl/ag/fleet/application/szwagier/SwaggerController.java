package pl.ag.fleet.application.szwagier;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ag.fleet.manager.security.LoginDetails;
import pl.ag.fleet.manager.security.SecurityService;

@RestController
@RequiredArgsConstructor
public class SwaggerController {

  private final SecurityService service;

  @GetMapping
  public String devTestToken() {
    return ":)";
  }
}
