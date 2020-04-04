package com.controllers;


import com.dao.Manufacturer;
import com.dao.Product;
import com.services.ManufacturerService;
import com.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/manufacturer/")
public class ManufacturerController {

    @Autowired
    private ManufacturerService manufacturerService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Manufacturer> getAllManufacturer() {
        return manufacturerService.getAllManufacturers();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Optional<Manufacturer> getManufacturer(@PathVariable Long id) {
        return manufacturerService.getManufacturer(id);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    // @RequestBody - map the json input to the manufacturer object
    public Manufacturer addManufacturer(@RequestBody Manufacturer m) {
        return manufacturerService.addManufacturer(m);
    }

    @RequestMapping(value = "bulk", method = RequestMethod.POST)
    // @RequestBody - map the json input to the manufacturer object
    public List<Manufacturer> addManufacturers(@RequestBody List<Manufacturer> ms) {
        return manufacturerService.addManufacturers(ms);
    }


    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String home() {
        return "ManufacturerController boot is working!";

    }
}
