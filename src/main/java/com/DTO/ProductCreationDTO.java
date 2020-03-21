package com.DTO;

import com.dao.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.repositories.ManufacturerRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ProductCreationDTO {

    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/mm/yyyy")
    private Date register_date;
    private Long manufacturer;
    private Product.Rating rating;

}
