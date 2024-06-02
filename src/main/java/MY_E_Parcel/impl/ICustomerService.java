package MY_E_Parcel.impl;

import MY_E_Parcel.model.Address;
import MY_E_Parcel.model.CustomerAsCompany;
import MY_E_Parcel.model.CustomerAsPerson;

public interface ICustomerService {
    void insertNewCustomerAsPerson(CustomerAsPerson person);

    void insertNewCustomerAsCompany(CustomerAsCompany company);

    void addAddressToCustomerByCustomerId(Long customerId, Address address);
}
