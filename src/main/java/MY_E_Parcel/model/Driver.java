package MY_E_Parcel.model;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "DRIVER_TABLE")
@AllArgsConstructor
public class Driver extends Person {

    @NotNull
    private int experienceInYears;

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "License number must be alphanumeric")
    private String licenseNo;

    @OneToMany(mappedBy = "driver")
    private List<Parcel> parcels;

    public Driver() {
        super(); // Invoke the default constructor of the Person class
    }

    public Driver(Long id, String firstName, String lastName, String personCode, int experienceInYears, String licenseNo) {
        super(id, firstName, lastName, personCode); // Invoke the parameterized constructor of the Person class
        this.experienceInYears = experienceInYears;
        this.licenseNo = licenseNo;
    }


}
