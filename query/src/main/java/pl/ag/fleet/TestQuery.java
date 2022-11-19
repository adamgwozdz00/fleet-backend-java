package pl.ag.fleet;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.ag.fleet.vehicle.details.VehicleDetailsDataProvider;

@SpringBootApplication
public class TestQuery {

  @Autowired
  private VehicleDetailsDataProvider detailsDataProvider;

  public static void main(String[] args) {
    SpringApplication.run(TestQuery.class, args);
  }

  @PostConstruct
  void testQuery() {
    detailsDataProvider.getDriverHistory("f9a2e373-e04c-45b8-a1ae-bea157dd7c0f")
        .forEach(System.out::println);
    detailsDataProvider.getInsuranceHistory("f97e07a3-0a8c-4d84-9b58-f3727866cafb")
        .forEach(System.out::println);
    detailsDataProvider.getOverviewHistory("f97e07a3-0a8c-4d84-9b58-f3727866cafb")
        .forEach(System.out::println);
    System.out.println(
        detailsDataProvider.getActualInsurance("f97e07a3-0a8c-4d84-9b58-f3727866cafb"));
    System.out.println(
        detailsDataProvider.getActualOverview("f97e07a3-0a8c-4d84-9b58-f3727866cafb"));
  }
}