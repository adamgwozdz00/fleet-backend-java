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
    val user = this.companyUserRepository.load(new UserId(vehicleUserDTO.getUserId()));

    val result = user.add(new VehicleId(vehicleUserDTO.getVehicleId()), vehicleAvailabilityService);

    companyUserRepository.save(user);

    return result;
  }

  public void removeVehicleFormUser(VehicleUserDTO vehicleUserDTO) {
    val user = this.companyUserRepository.load(new UserId(vehicleUserDTO.getUserId()));
    user.remove(new VehicleId(vehicleUserDTO.getVehicleId()));
    companyUserRepository.save(user);
  }

  public Result createUser(UserId userId, CompanyId companyId) {
    var user = companyUserRepository.load(userId);
    if (user == null) {
      this.companyUserRepository.save(new User(userId, companyId));
      return Result.createSuccess();
    }
    return Result.createFail();
  }
}
