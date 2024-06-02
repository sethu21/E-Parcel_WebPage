package MY_E_Parcel.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "ABSTRACT_CUSTOMER_TABLE")
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String customerCode;

    @NotNull
    private String phoneNo;

    @OneToMany(mappedBy = "customer")
    private List<Parcel> parcels;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public AbstractCustomer(Long id, String customerCode, String phoneNo, Address address) {
        this.id = id;
        this.customerCode = customerCode;
        this.phoneNo = phoneNo;
        this.address = address;
    }
}