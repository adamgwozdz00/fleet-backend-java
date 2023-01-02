package pl.ag.fleet.vehicle.details;

import static nu.studer.sample.Tables.VEHICLE_STATE;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import lombok.Value;
import org.jooq.Record;
import org.jooq.TableField;

@Value
public class VehicleStateRecord {

  static final List<TableField<Record, ? extends Serializable>> FIELDS = Arrays.asList(
      VEHICLE_STATE.TIME,
      VEHICLE_STATE.ACTUAL_DRIVER_ID,
      VEHICLE_STATE.STATUS,
      VEHICLE_STATE.KILOMETERS,
      VEHICLE_STATE.LITERS,
      VEHICLE_STATE.LONGITUDE,
      VEHICLE_STATE.LATITUDE);

  private LocalDateTime time;
  private long actualDriverId;
  private String status;
  private BigDecimal kilometers;
  private BigDecimal liters;
  private BigDecimal longitude;
  private BigDecimal latitude;
}
