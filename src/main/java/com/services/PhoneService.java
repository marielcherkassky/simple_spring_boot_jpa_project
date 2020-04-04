package com.services;

import com.dao.Identities.PhoneId;
import com.dao.Phone;
import com.repositories.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    @Transactional
    public Phone addPhone(Phone p)
    {
        return phoneRepository.save(p);
    }

    @Transactional
    public List<Phone> addPhones(List<Phone>ps){ return phoneRepository.saveAll(ps);}
    @Transactional
    public List<Phone> getAllPhones()
    {
        return phoneRepository.findAll();
    }

    @Transactional
    public Phone getPhoneByPid(Long id)
    {
        Optional<Phone> op = phoneRepository.findByPid(id);
        return op.isPresent() ? op.get() : null;
    }


    @Transactional
    public void deletePhoneById(PhoneId phoneId)
    {
        phoneRepository.deleteById(phoneId);
    }

/*When u run a delete without PK what actually happens: select with the deletes where clause to obtain PK and then delete by PK
* The following 2 delete methods will work in this way*/
@Transactional
public void deletePhoneByPid(Long id)
    {
         phoneRepository.deleteByPid(id);
    }

    @Transactional
    public void deletePhoneByNumber(String num)
    {
        phoneRepository.deleteByNumber(num);
    }


}
