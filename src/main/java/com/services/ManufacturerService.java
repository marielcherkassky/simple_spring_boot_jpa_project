package com.services;

import com.dao.Manufacturer;
import com.repositories.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerService {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Transactional
    public List<Manufacturer> getAllManufacturers()
    {
        return manufacturerRepository.findAll();
    }

    @Transactional
    public Optional<Manufacturer> getManufacturer(Long id)
    {
        return manufacturerRepository.findById(id);
    }

    @Transactional
    public Manufacturer addManufacturer(Manufacturer m)
    {
        return manufacturerRepository.save(m);
    }

    @Transactional
    public List<Manufacturer> addManufacturers(List<Manufacturer> m)
    {
        //Generates an insert per object in list. Not a real bulk ..
         return manufacturerRepository.saveAll(m);
    }

}
