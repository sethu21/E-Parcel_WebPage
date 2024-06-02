package MY_E_Parcel.service;

import MY_E_Parcel.impl.IDriverCRUDService;
import MY_E_Parcel.model.Driver;
import MY_E_Parcel.repo.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverCRUDService implements IDriverCRUDService {

    private final DriverRepository driverRepository;

    public DriverCRUDService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public List<Driver> selectAllDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public Driver selectDriverById(Long id) {
        return driverRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteDriverById(Long id) {
        driverRepository.deleteById(id);
    }

    @Override
    public void insertNewDriver(Driver driver) {
        driverRepository.save(driver);
    }

    @Override
    public void updateDriverById(Long id, Driver updatedDriver) {
        Driver driver = driverRepository.findById(id).orElse(null);
        if (driver != null) {
            driver.setFirstName(updatedDriver.getFirstName());
            driver.setLastName(updatedDriver.getLastName());
            driver.setExperienceInYears(updatedDriver.getExperienceInYears());
            driver.setLicenseNo(updatedDriver.getLicenseNo());
            driverRepository.save(driver);
        }
    }
}