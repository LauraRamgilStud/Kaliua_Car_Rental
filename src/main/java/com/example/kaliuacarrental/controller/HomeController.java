package com.example.kaliuacarrental.controller;
import com.example.kaliuacarrental.model.Car;
import com.example.kaliuacarrental.model.Customer;
import com.example.kaliuacarrental.repository.CarRepository;
import com.example.kaliuacarrental.repository.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;


@Controller
public class HomeController {
    CarRepository carRepository;
    CustomerRepository customerRepository;

    public HomeController(CarRepository carRepository, CustomerRepository customerRepository){
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/view/cars")
    public String viewCars(Model model){
        model.addAttribute("cars", carRepository.getAll());
        return "view-cars";
    }

    @GetMapping("/view/customers")
    public String viewCustomers(Model model){
        model.addAttribute("customers", customerRepository.getAll());
        return "view-customers";
    }

    @GetMapping("/info/car/{id}")
    public String viewCarInfo(@PathVariable("id") String id, Model model){
        Car car = carRepository.findCarById(id);
        model.addAttribute("car", car);
        return "info-car";
    }

    @GetMapping("/info/customer/{id}")
    public String viewCustomerInfo(@PathVariable("id") String id, Model model){
        Customer customer = customerRepository.findCustomerById(id);
        model.addAttribute("customer", customer);
        return "info-customer";
    }

    @GetMapping("/update/car/{id}")
    public String editCarForm(@PathVariable("id") String updateID, Model model){
        //Find car with id=updateID in DB
        Car updateCar = carRepository.findCarById(updateID);
        //Add car to view-model
        model.addAttribute("car", updateCar);
        //return view
        return "update-car";
    }

    @GetMapping("/update/customer/{id}")
    public String editCustomerForm(@PathVariable("id") String updateID, Model model){
        //Find customer with id=updateID in DB
        Customer updateCustomer = customerRepository.findCustomerById(updateID);
        //Add customer to view-model
        model.addAttribute("customer", updateCustomer);
        //return view
        return "update-customer";
    }

    @PostMapping("/update/car")
    public String updateCar(@RequestParam("car-status") String newStatus,
                            @RequestParam("car-km-driven") Integer newKmDriven,
                            @RequestParam("car-license") String license){
        Car car = new Car();
        car.setCarLicense(license);
        car.setCarStatus(newStatus);
        car.setCarKmDriven(newKmDriven);
        carRepository.updateCar(car);
        return "redirect:/";
    }

    @PostMapping("/update/customer")
    public String updateCustomer(@RequestParam("customer-email") String newEmail,
                            @RequestParam("customer-phone-number") Integer newPhoneNumber,
                            @RequestParam("customer-address") String newAddress,
                                 @RequestParam("customer-zip")  Integer newZip,
                                 @RequestParam("customer-city") String newCity
                                 ){
        Customer customer = new Customer();
        customer.setCustomerEmail(newEmail);
        customer.setCustomerPhoneNumber(newPhoneNumber);
        customer.setCustomerAddress(newAddress);
        customer.setCustomerZip(newZip);
        customer.setCustomerCity(newCity);
        customerRepository.updateCustomer(customer);
        return "redirect:/";
    }

    @GetMapping("/create/car")
    public String showCreateCar(){
        //show create car site
        return "create-car";
    }

    @GetMapping("/create/customer")
    public String showCreateCustomer(){
        //show create car site
        return "create-customer";
    }

    //from form action
    @PostMapping(value = "/create/car")
    public String createCar(@RequestParam("car-license") String newLicense,
                            @RequestParam("car-status") String newStatus,
                            @RequestParam("car-brand") String newCarBrand,
                            @RequestParam("car-model") String newModel,
                            @RequestParam("car-fuel-type") String newFuelType,
                            @RequestParam("car-registration-date") Date newRegistrationDate,
                            @RequestParam("car-km-driven") Integer newKmDriven,
                            @RequestParam("car-group") Integer newGroup){

        Car car = new Car(newLicense, newStatus, newCarBrand, newModel, newFuelType, newRegistrationDate, newKmDriven, newGroup);

        carRepository.addCar(car);

        //back to car list
        return "redirect:/";
    }

    @PostMapping(value = "/create/customer")
    public String createCustomer(@RequestParam("customer-license") String newLicense,
                            @RequestParam("customer-license-date") Date newLicenseDate,
                            @RequestParam("customer-name") String newName,
                            @RequestParam("customer-email") String newEmail,
                            @RequestParam("customer-phone-number") Integer newPhoneNumber,
                            @RequestParam("customer-address") String newAddress,
                            @RequestParam("customer-zip") Integer newZip,
                            @RequestParam("customer-city") String newCity){

        Customer customer = new Customer(newLicense, newLicenseDate, newName, newEmail, newPhoneNumber, newAddress, newZip, newCity);

        customerRepository.addCustomer(customer);

        //back to car list
        return "redirect:/";
    }

    @GetMapping("/delete/car/{id}")
    public String deleteCar(@PathVariable("id") String id){
        //delete from rep
        carRepository.deleteById(id);

        return "redirect:/";
    }

    @GetMapping("/delete/customer/{id}")
    public String deleteCustomer(@PathVariable("id") String id){
        //delete from rep
        customerRepository.deleteById(id);

        return "redirect:/";
    }
}



