package MY_E_Parcel.repo;


import MY_E_Parcel.model.CustomerAsPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAsPersonRepository extends JpaRepository<CustomerAsPerson, Long> {


    CustomerAsPerson findByCustomerCode(String customerCode);

    CustomerAsPerson findByPersonCode(String personCode);
}
