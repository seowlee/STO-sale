package com.sto.salepurchase.backstosalepurchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/product")
@RestController
public class ProductAPIController {
	@Autowired
	private ProductRepository productRepository;

	@RequestMapping(value = "/select", method = {RequestMethod.GET, RequestMethod.POST})
	public List<Product> selectAll() {
		return productRepository.findAll();
	}

	@RequestMapping(value = "/insert", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json; charset=utf-8")
	public Product insert(@RequestBody Map<String, String> map) {
		return productRepository.save(new Product(map.get("id"), map.get("name"), map.get("password")));
	}

}
