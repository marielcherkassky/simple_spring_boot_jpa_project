package com.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Manufacturer {
    @SequenceGenerator(name = "manufacturer_id_seq", sequenceName = "manufacturer_id_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "manufacturer_id_seq")
    @Id
    private Long id;
    private String name;
    private String country;
    @OneToMany(mappedBy = "manufacturer",fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

}
