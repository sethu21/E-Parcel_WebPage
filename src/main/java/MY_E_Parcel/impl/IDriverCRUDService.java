package MY_E_Parcel.impl;

import MY_E_Parcel.model.Driver;

import java.util.List;

public interface IDriverCRUDService {
    List<Driver> selectAllDrivers();

    Driver selectDriverById(Long id);

    void deleteDriverById(Long id);

    void insertNewDriver(Driver driver);

    void updateDriverById(Long id, Driver updatedDriver);
}
