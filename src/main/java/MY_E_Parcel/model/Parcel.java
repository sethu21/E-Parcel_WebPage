package MY_E_Parcel.model;

import MY_E_Parcel.model.Enum.ParcelSize;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "PARCEL_TABLE")
@NoArgsConstructor

public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private boolean isFragile;

    @NotNull
    private LocalDateTime orderCreated;

    @NotNull
    private LocalDateTime plannedDelivery;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ParcelSize parcelSize;

    @NotNull
    private double price;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private AbstractCustomer customer;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public Parcel(Long id, boolean isFragile, LocalDateTime orderCreated, LocalDateTime plannedDelivery, ParcelSize parcelSize, Driver driver, AbstractCustomer customer, Address address) {
        this.id = id;
        this.isFragile = isFragile;
        this.orderCreated = orderCreated;
        this.plannedDelivery = plannedDelivery;
        this.parcelSize = parcelSize;
        this.driver = driver;
        this.customer = customer;
        this.address = address;
    }

    public double calculatePrice() {
        // Let's assume that price is determined based on parcel size and fragility
        double basePrice = 10; // Base price for all parcels
        double sizeMultiplier = 1.0; // Size multiplier (adjust according to size)
        double fragilityMultiplier = isFragile ? 1.5 : 1.0; // Fragility multiplier

        // Adjust size multiplier based on parcel size
        switch (parcelSize) {
            case SMALL:
                sizeMultiplier = 0.8;
                break;
            case MEDIUM:
                sizeMultiplier = 1.0;
                break;
            case LARGE:
                sizeMultiplier = 1.2;
                break;
        }

        return basePrice * sizeMultiplier * fragilityMultiplier;
    }
}
