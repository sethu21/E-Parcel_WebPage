package MY_E_Parcel.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "CUSTOMER_AS_COMPANY_TABLE")
@AllArgsConstructor
public class CustomerAsCompany extends AbstractCustomer {
    @NotNull
    private String companyRegNo;

    @NotNull
    private String title;

    public CustomerAsCompany() {
        super(); // Invoke the default constructor of the AbstractCustomer class
    }

    public CustomerAsCompany(Long id, String customerCode, String phoneNo, Address address, String companyRegNo, String title) {
        super(id, customerCode, phoneNo, address); // Invoke the parameterized constructor of the AbstractCustomer class
        this.companyRegNo = companyRegNo;
        this.title = title;
    }


}
