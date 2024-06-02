package MY_E_Parcel.controller;

import MY_E_Parcel.model.AbstractCustomer;
import MY_E_Parcel.model.Address;
import MY_E_Parcel.model.CustomerAsCompany;
import MY_E_Parcel.model.CustomerAsPerson;
import MY_E_Parcel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/create/person")
    public String createPersonForm(Model model) {
        CustomerAsPerson customer = new CustomerAsPerson();
        model.addAttribute("customer", customer);
        return "customer-person-create-page";
    }

    @PostMapping("/create/person")
    public String createPerson(@ModelAttribute CustomerAsPerson customer) {
        customerService.insertNewCustomerAsPerson(customer);
        return "redirect:/customer/show/all";
    }

    @GetMapping("/create/company")
    public String createCompanyForm(Model model) {
        CustomerAsCompany customer = new CustomerAsCompany();
        model.addAttribute("customer", customer);
        return "customer-company-create-page";
    }

    @PostMapping("/create/company")
    public String createCompany(@ModelAttribute CustomerAsCompany customer) {
        customerService.insertNewCustomerAsCompany(customer);
        return "redirect:/customer/show/all";
    }

    @GetMapping("/add/address/{customerid}")
    public String addAddressForm(@PathVariable Long customerid, Model model) {
        Address address = new Address();
        model.addAttribute("address", address);
        model.addAttribute("customerId", customerid);
        return "customer-add-address-page";
    }

    @PostMapping("/add/address/{customerid}")
    public String addAddress(@PathVariable Long customerid, @ModelAttribute Address address) {
        customerService.addAddressToCustomerByCustomerId(customerid, address);
        return "redirect:/customer/show/all";
    }
}