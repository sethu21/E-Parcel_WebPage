package MY_E_Parcel.controller;

import MY_E_Parcel.model.Driver;
import MY_E_Parcel.service.DriverCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private DriverCRUDService driverService;

    @GetMapping("/show/all")
    public String getAllDrivers(Model model) {
        List<Driver> driver = driverService.selectAllDrivers();
        model.addAttribute("driver", driver);
        return "driver-all-page";
    }

    @GetMapping("/show/all/{id}")
    public String getDriverById(@PathVariable Long id, Model model) {
        Driver driver = driverService.selectDriverById(id);
        model.addAttribute("driver", driver);
        return "driver-one-page";
    }

    @GetMapping("/remove/{id}")
    public String removeDriverById(@PathVariable Long id) {
        driverService.deleteDriverById(id);
        return "redirect:/driver/show/all";
    }

    @GetMapping("/add")
    public String addDriverForm(Model model) {
        model.addAttribute("driver", new Driver());
        return "driver-add-page";
    }

    @PostMapping("/add")
    public String addDriver(@ModelAttribute Driver driver) {
        driverService.insertNewDriver(driver);
        return "redirect:/driver/show/all";
    }

    @GetMapping("/update/{id}")
    public String updateDriverForm(@PathVariable Long id, Model model) {
        Driver driver = driverService.selectDriverById(id); // Fetch existing driver
        model.addAttribute("id", id);
        model.addAttribute("driver", driver);
        return "driver-update-page";
    }

    @PostMapping("/update/{id}")
    public String updateDriver(@PathVariable Long id, @ModelAttribute Driver updatedDriver) {
        driverService.updateDriverById(id, updatedDriver);
        return "redirect:/driver/show/all";
    }
}