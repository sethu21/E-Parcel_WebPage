package MY_E_Parcel.impl;

import MY_E_Parcel.model.Enum.City;
import MY_E_Parcel.model.Parcel;

import java.util.List;

public interface IParcelService {
    List<Parcel> selectAllParcelsByCustomerId(Long customerId);

    List<Parcel> selectAllParcelsDeliveredByDriverId(Long driverId);

    List<Parcel> selectAllParcelsPriceLessThan(double price);

    List<Parcel> selectAllParcelsDeliveredToCity(City city);

    void insertNewParcelByCustomerCodeAndDriverId(String customerCode, Long driverId, Parcel parcel);

    void changeParcelDriverByParcelIdAndDriverId(Long parcelId, Long driverId);

    double calculateIncomeOfParcelsByCustomerId(Long customerId);

    int calculateHowManyParcelsNeedToDeliverToday();
}
