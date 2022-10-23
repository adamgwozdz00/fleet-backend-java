package pl.ag.fleet.vehicle;

import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
class CompanyId {

  @Column(name = "company_id")
  private Long id;
}
