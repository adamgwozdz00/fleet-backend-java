package pl.ag.fleet;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.ag.fleet.vehicle.VehicleProvider;
import pl.ag.fleet.vehicle.details.VehicleDetailsDataProvider;

@SpringBootApplication
public class TestQuery {

  @Autowired
  private VehicleDetailsDataProvider detailsDataProvider;
  @Autowired
  private VehicleProvider vehicleProvider;

  public static void main(String[] args) {
    SpringApplication.run(TestQuery.class, args);
  }

  @PostConstruct
  void testQuery() {

  }
}