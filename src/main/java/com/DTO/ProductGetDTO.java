package com.DTO;

import com.dao.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductGetDTO {
    private Long pid;
    private String name;
    private Date register_date;
    private Long manufacturer;
    private Product.Rating rating;
}
