package com.sto.sale.backstosale.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sto.sale.backstosale.repository.*;
import com.sto.sale.backstosale.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

	private final ProductRepository productRepository;
	private final SaleRepository saleRepository;
	private final UserRepository userRepository;
	private final HoldingRepository holdingRepository;
	private final TransactionRepository transactionRepository;
	private final EntityManager em;

	@Autowired
	public SpringConfig(ProductRepository productRepository, SaleRepository saleRepository, UserRepository userRepository, HoldingRepository holdingRepository, TransactionRepository transactionRepository, EntityManager em) {
		this.productRepository = productRepository;
		this.saleRepository = saleRepository;
		this.userRepository = userRepository;
		this.holdingRepository = holdingRepository;
		this.transactionRepository = transactionRepository;
		this.em = em;
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
		return new UserService(userRepository, holdingRepository);
	}

	@Bean
	public HoldingService holdingService() {
		return new HoldingService(holdingRepository);
	}

	@Bean
	public TransactionService transactionService() {
		return new TransactionService(transactionRepository);
	}

	@Bean
	public JPAQueryFactory jpaQueryFactory(EntityManager em) {
		return new JPAQueryFactory(em);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}

//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);


}
