package pl.ag.fleet.user;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CompanyUserService {

  private final CompanyUserRepository companyUserRepository;
  private final VehicleAvailabilityService vehicleAvailabilityService;

  public Result addVehicleToUser(VehicleUserDTO vehicleUserDTO) {
    val user = createOrLoadUser(new UserId(vehicleUserDTO.getUserId()),
        new CompanyId(vehicleUserDTO.getCompanyId()));
    val result = user.add(new VehicleId(vehicleUserDTO.getVehicleId()), vehicleAvailabilityService);

    companyUserRepository.save(user);

    return result;
  }

  public void removeVehicleFormUser(VehicleUserDTO vehicleUserDTO) {
    val user = createOrLoadUser(new UserId(vehicleUserDTO.getUserId()),
        new CompanyId(vehicleUserDTO.getCompanyId()));
    user.remove(new VehicleId(vehicleUserDTO.getVehicleId()));
    companyUserRepository.save(user);
  }

  private User createOrLoadUser(UserId userId, CompanyId companyId) {
    var user = companyUserRepository.load(userId);
    if (user == null) {
      user = new User(userId, companyId);
    }
    return user;
  }
}
