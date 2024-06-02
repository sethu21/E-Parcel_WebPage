package MY_E_Parcel.service;

import MY_E_Parcel.impl.IParcelService;
import MY_E_Parcel.model.CustomerAsCompany;
import MY_E_Parcel.model.CustomerAsPerson;
import MY_E_Parcel.model.Driver;
import MY_E_Parcel.model.Enum.City;
import MY_E_Parcel.model.Parcel;
import MY_E_Parcel.repo.CustomerAsCompanyRepository;
import MY_E_Parcel.repo.CustomerAsPersonRepository;
import MY_E_Parcel.repo.DriverRepository;
import MY_E_Parcel.repo.ParcelRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParcelService implements IParcelService {

    private final ParcelRepository parcelRepository;
    private final CustomerAsPersonRepository customerPersonRepository;
    private final CustomerAsCompanyRepository customerCompanyRepository;
    private final DriverRepository driverRepository;

    public ParcelService(ParcelRepository parcelRepository,
                         CustomerAsPersonRepository customerPersonRepository,
                         CustomerAsCompanyRepository customerCompanyRepository,
                         DriverRepository driverRepository) {
        this.parcelRepository = parcelRepository;
        this.customerPersonRepository = customerPersonRepository;
        this.customerCompanyRepository = customerCompanyRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public List<Parcel> selectAllParcelsByCustomerId(Long customerId) {
        return parcelRepository.findAllByCustomer_Id(customerId);
    }

    @Override
    public List<Parcel> selectAllParcelsDeliveredByDriverId(Long driverId) {
        return parcelRepository.findAllByDriver_Id(driverId);
    }

    @Override
    public List<Parcel> selectAllParcelsPriceLessThan(double price) {
        // Get all parcels
        List<Parcel> allParcels = parcelRepository.findAll();

        // Filter parcels with price less than the given threshold
        List<Parcel> parcelsWithPriceLessThanThreshold = allParcels.stream()
                .filter(parcel -> parcel.calculatePrice() < price)
                .collect(Collectors.toList());

        return parcelsWithPriceLessThanThreshold;
    }

    @Override
    public List<Parcel> selectAllParcelsDeliveredToCity(City city) {
        return parcelRepository.findAllByAddress_City(city);
    }

    public void insertNewParcelByCustomerCodeAndDriverId(String customerCode, Long driverId, Parcel parcel) {
        // Fetch customer and driver from repositories based on provided ids
        CustomerAsPerson personCustomer = customerPersonRepository.findByCustomerCode(customerCode);
        CustomerAsCompany companyCustomer = customerCompanyRepository.findByCompanyRegNo(customerCode);
        Driver driver = driverRepository.findById(driverId).orElse(null);

        if ((personCustomer != null || companyCustomer != null) && driver != null) {
            if (personCustomer != null) {
                parcel.setCustomer(personCustomer);
            } else {
                parcel.setCustomer(companyCustomer);
            }
            parcel.setDriver(driver);
            parcelRepository.save(parcel);
        }
    }

    @Override
    public void changeParcelDriverByParcelIdAndDriverId(Long parcelId, Long driverId) {
        // Fetch parcel and driver from repositories based on provided ids
        Parcel parcel = parcelRepository.findById(parcelId).orElse(null);
        Driver driver = driverRepository.findById(driverId).orElse(null);

        if (parcel != null && driver != null) {
            parcel.setDriver(driver);
            parcelRepository.save(parcel);
        }
    }

    @Override
    public double calculateIncomeOfParcelsByCustomerId(Long customerId) {
        List<Parcel> parcels = parcelRepository.findAllByCustomer_Id(customerId);
        double totalIncome = 0;
        for (Parcel parcel : parcels) {
            totalIncome += parcel.calculatePrice();
        }
        return totalIncome;
    }

    @Override
    public int calculateHowManyParcelsNeedToDeliverToday() {
        // Get today's date
        LocalDate today = LocalDate.now();

        // Get all parcels from the repository
        List<Parcel> parcels = parcelRepository.findAll();

        // Initialize counter for parcels to be delivered today
        int parcelsToDeliverToday = 0;

        // Iterate through each parcel
        for (Parcel parcel : parcels) {
            // Check if the planned delivery date is today or after today
            if (!parcel.getPlannedDelivery().toLocalDate().isAfter(today)) {
                // Increment the counter if the planned delivery date is today or before today
                parcelsToDeliverToday++;
            }
        }

        return parcelsToDeliverToday;
    }
}