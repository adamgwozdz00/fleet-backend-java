package pl.ag.fleet.event;

import lombok.val;
import org.springframework.stereotype.Component;

@Component
public class EventDTOFactory {

  EventDTO create(Event event) {
    val data = event.getEventData();
    switch (data.getEventType()) {
      case OVERVIEW:
        var overviewData = ((OverviewData) data);
        return new OverviewEventDTO(overviewData.getEventType(),
            event.getTime().getTime(),
            overviewData.getVehicleId().getId(),
            overviewData.getOverview().getExpirationDate(),
            overviewData.getOverview().getName(),
            overviewData.getOverview().getCost().getCost().doubleValue(),
            overviewData.getOverview().getDescription());
      case INSURANCE:
        var insuranceData = ((InsuranceData) data);
        return new InsuranceEventDTO(insuranceData.getEventType(),
            event.getTime().getTime(),
            insuranceData.getVehicleId().getId(),
            insuranceData.getInsurance().getName(),
            insuranceData.getInsurance().getExpirationDate(),
            insuranceData.getInsurance().getCost().getCost().doubleValue());
      case REFUEL:
        var fuelData = (RefuelData) data;
        return new RefuelEventDTO(fuelData.getEventType(),
            event.getTime().getTime(),
            fuelData.getVehicleId().getId(),
            fuelData.getLiters().getLiters().doubleValue(),
            fuelData.getCost().getCost().doubleValue());
      case ACTUAL_VEHICLE_DATA:
        var actualData = (ActualData) data;
        return new ActualDataEventDTO(actualData.getEventType(),
            event.getTime().getTime(),
            actualData.getVehicleId().getId(),
            actualData.getDriverId().getId(),
            actualData.getFuelStateInLiters().getLiters().doubleValue(),
            actualData.getKilometersState().getKilometers().doubleValue()
        );
      case CREATE_VEHICLE:
        var createData = (CreateVehicleData) data;
        return new CreateVehicleEventDTO(createData.getEventType(),
            event.getTime().getTime(),
            createData.getVinNumber().getVinNumber(),
            createData.getCompanyId().getId(),
            createData.getMake().getMake(),
            createData.getModel().getModel(),
            createData.getProductionYear().getYear(),
            createData.getFuelType());
      default:
        throw new IllegalArgumentException("Unknown event type.");
    }
  }
}
