package com.dao.Identities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneId implements Serializable {

    private Long pid;
    private Long areaCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneId pId1 = (PhoneId) o;
        return pid.equals(pId1.pid);
    }

    @Override
    public int hashCode() {
        return pid.hashCode();
    }
}
