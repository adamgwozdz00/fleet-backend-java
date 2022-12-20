/*
 * This file is generated by jOOQ.
 */
package pl.ag.fleet.tables;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import pl.ag.fleet.Fleet;
import pl.ag.fleet.Keys;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class VehicleState extends TableImpl<Record> {

  /**
   * The reference instance of <code>fleet.vehicle_state</code>
   */
  public static final VehicleState VEHICLE_STATE = new VehicleState();
  private static final long serialVersionUID = 1L;
  /**
   * The column <code>fleet.vehicle_state.id</code>.
   */
  public final TableField<Record, Long> ID = createField(DSL.name("id"),
      SQLDataType.BIGINT.nullable(false).identity(true), this, "");
  /**
   * The column <code>fleet.vehicle_state.actual_driver_id</code>.
   */
  public final TableField<Record, Long> ACTUAL_DRIVER_ID = createField(DSL.name("actual_driver_id"),
      SQLDataType.BIGINT, this, "");
  /**
   * The column <code>fleet.vehicle_state.liters</code>.
   */
  public final TableField<Record, BigDecimal> LITERS = createField(DSL.name("liters"),
      SQLDataType.NUMERIC(19, 2), this, "");
  /**
   * The column <code>fleet.vehicle_state.kilometers</code>.
   */
  public final TableField<Record, BigDecimal> KILOMETERS = createField(DSL.name("kilometers"),
      SQLDataType.NUMERIC(19, 2), this, "");
  /**
   * The column <code>fleet.vehicle_state.time</code>.
   */
  public final TableField<Record, LocalDateTime> TIME = createField(DSL.name("time"),
      SQLDataType.LOCALDATETIME(6), this, "");
  /**
   * The column <code>fleet.vehicle_state.vehicle_id</code>.
   */
  public final TableField<Record, String> VEHICLE_ID = createField(DSL.name("vehicle_id"),
      SQLDataType.VARCHAR(255), this, "");

  private VehicleState(Name alias, Table<Record> aliased) {
    this(alias, aliased, null);
  }

  private VehicleState(Name alias, Table<Record> aliased, Field<?>[] parameters) {
    super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
  }

  /**
   * Create an aliased <code>fleet.vehicle_state</code> table reference
   */
  public VehicleState(String alias) {
    this(DSL.name(alias), VEHICLE_STATE);
  }

  /**
   * Create an aliased <code>fleet.vehicle_state</code> table reference
   */
  public VehicleState(Name alias) {
    this(alias, VEHICLE_STATE);
  }

  /**
   * Create a <code>fleet.vehicle_state</code> table reference
   */
  public VehicleState() {
    this(DSL.name("vehicle_state"), null);
  }

  public <O extends Record> VehicleState(Table<O> child, ForeignKey<O, Record> key) {
    super(child, key, VEHICLE_STATE);
  }

  /**
   * The class holding records for this type
   */
  @Override
  public Class<Record> getRecordType() {
    return Record.class;
  }

  @Override
  public Schema getSchema() {
    return aliased() ? null : Fleet.FLEET;
  }

  @Override
  public Identity<Record, Long> getIdentity() {
    return (Identity<Record, Long>) super.getIdentity();
  }

  @Override
  public UniqueKey<Record> getPrimaryKey() {
    return Keys.VEHICLE_STATE_PKEY;
  }

  @Override
  public VehicleState as(String alias) {
    return new VehicleState(DSL.name(alias), this);
  }

  @Override
  public VehicleState as(Name alias) {
    return new VehicleState(alias, this);
  }

  /**
   * Rename this table
   */
  @Override
  public VehicleState rename(String name) {
    return new VehicleState(DSL.name(name), null);
  }

  /**
   * Rename this table
   */
  @Override
  public VehicleState rename(Name name) {
    return new VehicleState(name, null);
  }
}
