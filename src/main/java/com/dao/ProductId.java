package com.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.Id;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductId implements Serializable {

    private Long id; // matches name of @Id attribute
    private Long manufacturer; // name should match to @Id attribute and type of Manufacturer PK

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductId pId1 = (ProductId) o;
        if (!id.equals(pId1.id)) return false;
        return manufacturer.equals(pId1.manufacturer);
    }

    @Override
    public int hashCode() {
        return id.hashCode()+manufacturer.hashCode();
    }
}
