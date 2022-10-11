package com.sto.sale.backstosale.controller;

import com.sto.sale.backstosale.domain.Product;
import com.sto.sale.backstosale.dto.CancellationSaleDto;
import com.sto.sale.backstosale.dto.CompletionSaleDto;
import com.sto.sale.backstosale.dto.ProductDto;
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

	@GetMapping(value = "product/all")
	public List<Product> getAllProducts() {
		List<Product> products = productService.findAllProducts();

		return products;
	}

//    @RequestMapping(value = "product/on-sale", method = {RequestMethod.GET, RequestMethod.POST})
//    public List<Product> getOnSaleProducts() {
//        List<Product> products = productService.findOnSaleProducts();
//        return products;
//    }

//	@CrossOrigin
//	@GetMapping("/order/{id}")
//	public ResponseEntity<?> findById(@PathVariable Long id) {
//		return new ResponseEntity<>(productService.한건가져오기(id), HttpStatus.OK); // 200 응답
//		// httpstatus 코드를 같이 보내기 위해 ResponseEntity 사용
//	}

	/**
	 * 판매 상품 목록 조회 (Product join Sale)
	 */
	@GetMapping("/product/on-sale")
	public List<ProductDto> getJoinProduct(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "5") Integer size) {
		List<ProductDto> joinProduct = productService.findOnSaleProducts(page, size);
		return joinProduct;
	}

	@GetMapping("/product/on-sale/count")
	public Long getOnSaleProductsCnt() {
		Long totalOnSaleProductCnt = productService.findOnSaleProductsCnt();
		return totalOnSaleProductCnt;
	}

	@GetMapping("/product/sold-out")
	public List<ProductDto> getSoldOutProduct(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "5") Integer size) {
		List<ProductDto> soldOutProduct = productService.findSoldOutProducts(page, size);
		return soldOutProduct;
	}

	@GetMapping("/product/sold-out/count")
	public Long getSoldOutProductCnt() {
		Long totalSoldOutProductCnt = productService.findSoldOutProductsCnt();
		return totalSoldOutProductCnt;
	}

	@GetMapping("/product/order/{id}")
	public Optional<ProductDto> getOne(@PathVariable Long id) {
		Optional<ProductDto> optionalProduct = Optional.ofNullable(productService.findProductId(id));
		return optionalProduct;
	}

	@PostMapping("/product/stat/update")
	public CompletionSaleDto getChangeGoodsStat(@RequestBody CompletionSaleDto completionSaleDto) {
		productService.changeGoodsStat(completionSaleDto);
		return completionSaleDto;
	}

	@PostMapping("/product/stat/reset")
	public CancellationSaleDto getResetGoodsStat(@RequestBody CancellationSaleDto cancellationSaleDto) {
		productService.resetGoodsStat(cancellationSaleDto);
		return cancellationSaleDto;
	}

	// fixxxx
//	@GetMapping("/product/insert")
//	public List<Integer> numOfTokenInsert(@RequestParam Integer numberOfToken) {
//		System.out.println("numOfToken : " + numberOfToken);
////		System.out.println(numberOfToken.getClass().getName());
//		return Arrays.asList(numberOfToken);
//	}

	@GetMapping("test")
	public List<Integer> Test() {
		int num = 33;
		System.out.println(((Object) num).getClass().getName());
		return Arrays.asList(8080, 3000);
	}
}
