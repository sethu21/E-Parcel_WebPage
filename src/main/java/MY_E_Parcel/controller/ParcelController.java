package MY_E_Parcel.controller;

import MY_E_Parcel.model.Enum.City;
import MY_E_Parcel.model.Parcel;
import MY_E_Parcel.service.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/parcel")
public class ParcelController {

    @Autowired
    private ParcelService parcelService;

    @GetMapping("/show/customer/{id}")
    public String getAllParcelsForCustomer(@PathVariable Long id, Model model) {
        List<Parcel> parcels = parcelService.selectAllParcelsByCustomerId(id);
        model.addAttribute("parcels", parcels);
        return "parcel-customer-page";
    }

    @GetMapping("/show/driver/{id}")
    public String getAllParcelsForDriver(@PathVariable Long id, Model model) {
        List<Parcel> parcels = parcelService.selectAllParcelsDeliveredByDriverId(id);
        model.addAttribute("parcels", parcels);
        return "parcel-driver-page";
    }

    @GetMapping("/show/price/{threshold}")
    public String getAllParcelsWithPriceLessThan(@PathVariable double threshold, Model model) {
        List<Parcel> parcels = parcelService.selectAllParcelsPriceLessThan(threshold);
        model.addAttribute("parcels", parcels);
        return "parcel-price-page";
    }

    @GetMapping("/show/city/{cityparam}")
    public String getAllParcelsToCity(@PathVariable City cityparam, Model model) {
        List<Parcel> parcels = parcelService.selectAllParcelsDeliveredToCity(cityparam);
        model.addAttribute("parcels", parcels);
        return "parcel-city-page";
    }

    @GetMapping("/add/{customercode}/{driverid}")
    public String addParcelForm(@PathVariable String customercode, @PathVariable Long driverid, Model model) {
        Parcel parcel = new Parcel();
        model.addAttribute("parcel", parcel);
        model.addAttribute("customerCode", customercode);
        model.addAttribute("driverId", driverid);
        return "parcel-add-page";
    }

    @PostMapping("/add/{customercode}/{driverid}")
    public String addParcel(@PathVariable String customercode, @PathVariable Long driverid, @ModelAttribute Parcel parcel) {
        parcelService.insertNewParcelByCustomerCodeAndDriverId(customercode, driverid, parcel);
        return "redirect:/parcel/show/all";
    }
}
