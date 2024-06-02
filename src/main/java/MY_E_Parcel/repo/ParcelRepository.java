package MY_E_Parcel.repo;

import MY_E_Parcel.model.Enum.City;
import MY_E_Parcel.model.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParcelRepository extends JpaRepository<Parcel, Long> {
    List<Parcel> findAllByCustomer_Id(Long customerId);

    List<Parcel> findAllByDriver_Id(Long driverId);

    List<Parcel> findAllByPriceLessThan(double price);

    List<Parcel> findAllByAddress_City(City city);
}
