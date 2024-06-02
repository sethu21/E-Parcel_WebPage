package MY_E_Parcel.repo;

import MY_E_Parcel.model.CustomerAsCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAsCompanyRepository extends JpaRepository<CustomerAsCompany, Long> {

    CustomerAsCompany findByCompanyRegNo(String companyRegNo);
}
