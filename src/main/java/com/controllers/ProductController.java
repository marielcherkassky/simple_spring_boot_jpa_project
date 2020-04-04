package com.controllers;

import com.DTO.ProductCreationDTO;
import com.dao.Manufacturer;
import com.dao.Product;
import com.services.ManufacturerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.services.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "bulk", method = RequestMethod.POST)
    public boolean insertProducts(@RequestBody List<Product> p) {
         return productService.addProducts(p);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public HttpStatus insertProduct(@RequestBody ProductCreationDTO pcd) {
        Product p = mapProductCreationDTOtoProduct(pcd);
        if(p.getManufacturer() == null)
            return HttpStatus.BAD_REQUEST;
        return productService.addProduct(p) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public List<Product> getProductByPK(@PathVariable Long id, @RequestBody Manufacturer m) {
        return productService.getProductByEmbeddedId(id, m);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String home() {
        return "ProductController boot is working!";

    }

    private Product mapProductCreationDTOtoProduct(ProductCreationDTO pcd)
    {
        Product p = modelMapper.map(pcd,Product.class); // maps fields with same name that arent nested(like manufacturer)
        Optional<Manufacturer> o = manufacturerService.getManufacturer(pcd.getManufacturer());
        o.ifPresent(m->p.setManufacturer(m));
        return p;
    }
}
