package com.sto.salepurchase.backstosalepurchase.controller;

import com.sto.salepurchase.backstosalepurchase.domain.Product;
import com.sto.salepurchase.backstosalepurchase.repository.ProductRepository;
import com.sto.salepurchase.backstosalepurchase.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "product/select", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Product> getProduct() {
        List<Product> products = productService.findProducts();
        return products;
    }

    // fixxxx
    @GetMapping("/product/insert")
    public List<Integer> numOfTokenInsert(@RequestParam Integer numberOfToken) {
        System.out.println("numOfToken : " + numberOfToken);
//		System.out.println(numberOfToken.getClass().getName());
        return Arrays.asList(numberOfToken);
    }

    @GetMapping("test")
    public List<Integer> Test() {
        int num = 33;
        System.out.println(((Object) num).getClass().getName());
        return Arrays.asList(8080, 3000);
    }
}
