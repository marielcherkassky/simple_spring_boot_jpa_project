package com.dao.Identities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductId implements Serializable {

    private Long pid; // matches name of @Id attribute
    private Long manufacturer; // name should match to @Id attribute and type of Manufacturer PK

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductId pId1 = (ProductId) o;
        if (!pid.equals(pId1.pid)) return false;
        return manufacturer.equals(pId1.manufacturer);
    }

    @Override
    public int hashCode() {
        return pid.hashCode()+manufacturer.hashCode();
    }
}
