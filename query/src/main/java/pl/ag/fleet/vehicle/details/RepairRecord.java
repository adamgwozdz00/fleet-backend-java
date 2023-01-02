package pl.ag.fleet.vehicle.details;

import static nu.studer.sample.Tables.REPAIR;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import lombok.Value;
import org.jooq.Record;
import org.jooq.TableField;

@Value
public class RepairRecord {

  static final List<TableField<Record, ? extends Serializable>> FIELDS = Arrays.asList(
      REPAIR.START_TIME,
      REPAIR.FINISH_TIME,
      REPAIR.COST,
      REPAIR.SERVICE_NAME,
      REPAIR.TITLE
  );

  private LocalDateTime startTime;
  private LocalDateTime finishTime;

  private BigDecimal cost;

  private String serviceName;

  private String title;
}
