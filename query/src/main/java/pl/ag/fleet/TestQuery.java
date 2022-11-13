package pl.ag.fleet;

import static pl.ag.fleet.Tables.VEHICLE;

import javax.annotation.PostConstruct;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestQuery {

  @Autowired
  private DSLContext create;

  public static void main(String[] args) {
    SpringApplication.run(TestQuery.class, args);
  }

  @PostConstruct
  void testQuery(){
    var result = create.select(VEHICLE.VEHICLE_ID,
        VEHICLE.MAKE,
        VEHICLE.MODEL,
        VEHICLE.PRODUCTION_YEAR,
        VEHICLE.FUEL_TYPE).from(VEHICLE);
    result.stream().forEach(System.out::println);

  }
}