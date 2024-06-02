package MY_E_Parcel;

import MY_E_Parcel.model.*;
import MY_E_Parcel.model.Enum.City;
import MY_E_Parcel.model.Enum.ParcelSize;
import MY_E_Parcel.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
public class MyEParcelApplication implements CommandLineRunner {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private DriverRepository driverRepository;

	@Autowired
	private CustomerAsCompanyRepository customerAsCompanyRepository;

	@Autowired
	private CustomerAsPersonRepository customerAsPersonRepository;

	@Autowired
	private ParcelRepository parcelRepository;

	public static void main(String[] args) {
		SpringApplication.run(MyEParcelApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Creating some addresses
		Address address1 = new Address(null, "200", City.VENTSPILS, "Inzenieru");
		Address address2 = new Address(null, "101", City.RIGA, "Brivibas");

		addressRepository.saveAll(Arrays.asList(address1, address2));

		// Creating some drivers
		Driver driver1 = new Driver((Long) null, "Janis", "Braucej", "121212-12345", (int) 4.0, "AT123456");
		Driver driver2 = new Driver((Long) null, "Baiba", "Atra","123456-12345" , (int) 8.0, "AT765432");

		driverRepository.saveAll(Arrays.asList(driver1, driver2));

		// Creating some customers
		CustomerAsPerson customerPerson1 = new CustomerAsPerson(null, "0_person_123456-12345", "+371206279051", address1, "123456-12345");
		CustomerAsCompany customerCompany1 = new CustomerAsCompany(null, "0_company_LV20784837214", "+371206279052", address2, "LV20784837214", "SIA Gribu pacifiers");

		customerAsPersonRepository.save(customerPerson1);
		customerAsCompanyRepository.save(customerCompany1);

		// Creating some parcels
		Parcel parcel1 = new Parcel(null, false, LocalDateTime.parse("2004-04-29T23:02:44"), LocalDateTime.parse("2004-05-01T23:02:44"), ParcelSize.MEDIUM, driver1, customerPerson1, address1);
		Parcel parcel2 = new Parcel(null, true, LocalDateTime.parse("2004-04-29T23:02:44"), LocalDateTime.parse("2004-05-02T23:02:44"), ParcelSize.LARGE, driver2, customerCompany1, address2);
		parcelRepository.saveAll(Arrays.asList(parcel1, parcel2));
	}
}
