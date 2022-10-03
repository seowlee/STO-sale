package com.sto.sale.backstosale.config;

import com.sto.sale.backstosale.repository.HoldingRepository;
import com.sto.sale.backstosale.repository.ProductRepository;
import com.sto.sale.backstosale.repository.SaleRepository;
import com.sto.sale.backstosale.repository.UserRepository;
import com.sto.sale.backstosale.service.HoldingService;
import com.sto.sale.backstosale.service.ProductService;
import com.sto.sale.backstosale.service.SaleService;
import com.sto.sale.backstosale.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

	private final ProductRepository productRepository;
	private final SaleRepository saleRepository;
	private final UserRepository userRepository;
	private final HoldingRepository holdingRepository;

	@Autowired
	public SpringConfig(ProductRepository productRepository, SaleRepository saleRepository, UserRepository userRepository, HoldingRepository holdingRepository) {
		this.productRepository = productRepository;
		this.saleRepository = saleRepository;
		this.userRepository = userRepository;
		this.holdingRepository = holdingRepository;
	}

	@Bean
	public ProductService productService() {
		return new ProductService(productRepository);
	}

	@Bean
	public SaleService saleService() {
		return new SaleService(saleRepository);
	}

	@Bean
	public UserService userService() {
		return new UserService(userRepository);
	}

	@Bean
	public HoldingService holdingService() {
		return new HoldingService(holdingRepository);
	}
}
