package com.controllers;

import com.DTO.IdsArray;
import com.DTO.ProductCreationDTO;
import com.DTO.ProductGetDTO;
import com.dao.Identities.ProductId;
import com.dao.Manufacturer;
import com.dao.Product;
import com.repositories.ManufacturerRepository;
import com.services.ManufacturerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.services.ProductService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/bulk", method = RequestMethod.POST)
    public boolean insertProducts(@RequestBody List<Product> p) {
         return productService.addProducts(p);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String insertProduct(@RequestBody ProductCreationDTO pcd) {
       Product p = mapProductCreationDTOtoProduct(pcd);
        if(p.getManufacturer() == null)
            return "Failed to find Manufactuer";
        return productService.addProduct(p)!=null ? "CREATED \n" + p.toString() : "NOT CREATED - SOMETHING WENT WRONG";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ProductGetDTO getProductByPK(@RequestBody ProductId productId) {
        Optional<Manufacturer> om = manufacturerService.getManufacturer(productId.getManufacturer());
        if(!om.isPresent())
            return null;

        Optional<Product> op = productService.getProductByEmbeddedId(productId);

        return op.isPresent() ?  mapProductToProductGetDTO(op.get()) : null;

    }

    //input example: {"ids":[1,2,3]}
    @RequestMapping(value = "/get/pid_in/", method = RequestMethod.GET)
    public List<ProductGetDTO> getProductByPK(@RequestBody IdsArray pids) {
        return productService.getProductsOnlyById(pids.getIds())
                .stream()
                .map(product->mapProductToProductGetDTO(product))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<ProductGetDTO> getAllProducts()
    {
        List<Product> products = productService.getAllProducts();
        return products
                .stream()
                .map(product->mapProductToProductGetDTO(product))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String home() {
        return "ProductController boot is working!";

    }

    /// Maping DTO to DAO funcs ///
    private Product mapProductCreationDTOtoProduct(ProductCreationDTO pcd)
    {
        Product p = modelMapper.map(pcd,Product.class); // maps fields with same name that arent nested(like manufacturer)
        Optional<Manufacturer> o = manufacturerService.getManufacturer(pcd.getManufacturer());
        o.ifPresent(m->p.setManufacturer(m));
        return p;
    }

    private ProductGetDTO mapProductToProductGetDTO(Product p)
    {
        ProductGetDTO pgd = modelMapper.map(p,ProductGetDTO.class); // the manufacturer mapping is configured in the AppConfig
        return pgd;
    }
}
