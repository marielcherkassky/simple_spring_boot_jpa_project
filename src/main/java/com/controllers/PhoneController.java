package com.controllers;

import com.dao.Identities.PhoneId;
import com.dao.Phone;
import com.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phone")
public class PhoneController {

    @Autowired
    PhoneService phoneService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Phone addPhone(@RequestBody Phone p)
    {
        return phoneService.addPhone(p);
    }

    //Multiple inserts - insert per each obj
    @RequestMapping(value = "add/bulk1", method = RequestMethod.POST)
    public List<Phone> addPhones(@RequestBody List<Phone> phones)

    {
        long startms = System.currentTimeMillis();
        List<Phone> ps =  phoneService.addPhones(phones);
        long delta = (System.currentTimeMillis() - startms);
        System.out.println("duration of addPhones:{} seconds".format(String.valueOf(delta / 1000)));
        return ps;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Phone> getAllPhones()
    {
        return phoneService.getAllPhones();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Phone getPhone(@PathVariable ("id") Long id)
    {
        return phoneService.getPhoneByPid(id);
    }

    @RequestMapping(value = "delete/id/{id}", method = RequestMethod.POST)
    public void deletePhoneByPid(@PathVariable ("id") Long id)
    {
         phoneService.deletePhoneByPid(id);
    }

    @RequestMapping(value = "delete/num/{num}", method = RequestMethod.POST)
    public void deletePhoneByNum(@PathVariable ("num") String num)
    {
        phoneService.deletePhoneByNumber(num);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public void deletePhoneById(@RequestBody PhoneId phoneId)
    {
         phoneService.deletePhoneById(phoneId);
    }






}
