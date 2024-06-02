package MY_E_Parcel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "CUSTOMER_AS_PERSON_TABLE")

public class CustomerAsPerson extends AbstractCustomer {
    @NotNull
    private String personCode;

    public CustomerAsPerson() {
        super(); // Invoke the default constructor of the AbstractCustomer class
    }

    public CustomerAsPerson(Long id, String customerCode, String phoneNo, Address address, String personCode) {
        super(id, customerCode, phoneNo, address); // Invoke the parameterized constructor of the AbstractCustomer class
        this.personCode = personCode;
    }

}
