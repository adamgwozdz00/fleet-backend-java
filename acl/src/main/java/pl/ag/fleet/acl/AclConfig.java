package pl.ag.fleet.acl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AclConfig {


  @Bean
  public ObjectMapper mapper() {
    return new ObjectMapper().registerModule(new JavaTimeModule());
  }
}
