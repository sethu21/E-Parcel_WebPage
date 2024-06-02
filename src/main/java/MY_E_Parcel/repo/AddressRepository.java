package MY_E_Parcel.repo;

import MY_E_Parcel.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
