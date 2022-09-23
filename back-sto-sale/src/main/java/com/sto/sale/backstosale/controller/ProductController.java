package com.sto.sale.backstosale.controller;

import com.sto.sale.backstosale.domain.Product;
import com.sto.sale.backstosale.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    //    @GetMapping("order/{id}")
//    public
    @CrossOrigin
    @GetMapping("/order/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.한건가져오기(id), HttpStatus.OK); // 200 응답
        // httpstatus 코드를 같이 보내기 위해 ResponseEntity 사용
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
