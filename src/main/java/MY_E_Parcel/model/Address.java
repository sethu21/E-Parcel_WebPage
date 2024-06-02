package MY_E_Parcel.model;

import MY_E_Parcel.model.Enum.City;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ADDRESS")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp = "\\d+", message = "House number must be a numeric value")
    @Column(name = "house_no")
    private String houseNo;

    @NotNull
    @Enumerated(EnumType.STRING)
    private City city;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Street or house title must be alphanumeric")
    @Column(name = "street_or_house_title")
    private String streetOrHouseTitle;


    @OneToMany(mappedBy = "address")
    private List<AbstractCustomer> customers;

    @OneToMany(mappedBy = "address")
    private List<Parcel> parcels;

    public Address(Long id, String houseNo, City city, String streetOrHouseTitle) {
        this.id = id;
        this.houseNo = houseNo;
        this.city = city;
        this.streetOrHouseTitle = streetOrHouseTitle;
    }


}
