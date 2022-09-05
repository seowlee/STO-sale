package com.sto.salepurchase.backstosalepurchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class ProductAPIController {
	@Autowired
	private ProductRepository productRepository;

	@RequestMapping(value = "product/select", method = {RequestMethod.GET, RequestMethod.POST})
	public List<Product> selectAll() {
		return productRepository.findAll();
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
