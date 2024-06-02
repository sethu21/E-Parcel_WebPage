package MY_E_Parcel.service;

import MY_E_Parcel.impl.ICustomerService;
import MY_E_Parcel.model.Address;
import MY_E_Parcel.model.CustomerAsCompany;
import MY_E_Parcel.model.CustomerAsPerson;
import MY_E_Parcel.repo.AddressRepository;
import MY_E_Parcel.repo.CustomerAsCompanyRepository;
import MY_E_Parcel.repo.CustomerAsPersonRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {

    private final CustomerAsPersonRepository customerPersonRepository;
    private final CustomerAsCompanyRepository customerCompanyRepository;
    private final AddressRepository addressRepository;

    public CustomerService(CustomerAsPersonRepository customerPersonRepository,
                           CustomerAsCompanyRepository customerCompanyRepository,
                           AddressRepository addressRepository) {
        this.customerPersonRepository = customerPersonRepository;
        this.customerCompanyRepository = customerCompanyRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public void insertNewCustomerAsPerson(CustomerAsPerson person) {
        // Check if the person already exists based on personCode
        CustomerAsPerson existingPerson = customerPersonRepository.findByPersonCode(person.getPersonCode());
        if (existingPerson == null) {
            customerPersonRepository.save(person);
        } else {
            // Handle case when person already exists
        }
    }

    @Override
    public void insertNewCustomerAsCompany(CustomerAsCompany company) {
        // Check if the company already exists based on companyRegNo
        CustomerAsCompany existingCompany = customerCompanyRepository.findByCompanyRegNo(company.getCompanyRegNo());
        if (existingCompany == null) {
            customerCompanyRepository.save(company);
        } else {
            // Handle case when company already exists
        }
    }

    @Override
    public void addAddressToCustomerByCustomerId(Long customerId, Address address) {
        // Fetch customer from repositories based on provided id
        CustomerAsPerson person = customerPersonRepository.findById(customerId).orElse(null);
        CustomerAsCompany company = customerCompanyRepository.findById(customerId).orElse(null);

        if (person != null) {
            person.setAddress(address);
            customerPersonRepository.save(person);
        } else if (company != null) {
            company.setAddress(address);
            customerCompanyRepository.save(company);
        } else {
            // Handle case when customer is not found
        }
    }
}