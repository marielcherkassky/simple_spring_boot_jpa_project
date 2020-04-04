package com.repositories;

import com.dao.Identities.PhoneId;
import com.dao.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhoneRepository extends JpaRepository<Phone, PhoneId> {
    Optional<Phone> findByPid(Long pid);
    void deleteByPid(Long pid);
    void deleteById(PhoneId phoneId);
    void deleteByNumber(String num);
}
