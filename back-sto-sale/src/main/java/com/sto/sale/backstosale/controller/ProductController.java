package com.sto.sale.backstosale.controller;

import com.sto.sale.backstosale.domain.Product;
import com.sto.sale.backstosale.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "product/all", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Product> getAllProducts() {
        List<Product> products = productService.findAllProducts();

        return products;
    }

    @RequestMapping(value = "product/on-sale", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Product> getOnSaleProducts() {
        List<Product> products = productService.findOnSaleProducts();
        return products;
    }

//	@CrossOrigin
//	@GetMapping("/order/{id}")
//	public ResponseEntity<?> findById(@PathVariable Long id) {
//		return new ResponseEntity<>(productService.한건가져오기(id), HttpStatus.OK); // 200 응답
//		// httpstatus 코드를 같이 보내기 위해 ResponseEntity 사용
//	}

    @GetMapping("/order/{id}")
    public Optional<Product> getOne(@PathVariable Long id) {
        Optional<Product> optionalProduct = Optional.ofNullable(productService.findOne(id));
        return optionalProduct;
    }

//    @GetMapping("/join")
//    public List<ProductDto> getJoin() {
//        List<ProductDto> joinProduct = productService.findJoin();
//        return joinProduct;
//    }

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
