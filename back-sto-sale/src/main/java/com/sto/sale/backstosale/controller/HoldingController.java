package com.sto.sale.backstosale.controller;

import com.sto.sale.backstosale.domain.Holding;
import com.sto.sale.backstosale.service.HoldingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HoldingController {

	private HoldingService holdingService;

	@Autowired
	public HoldingController(HoldingService holdingService) {
		this.holdingService = holdingService;
	}

	@GetMapping("/holding/all")
	public List<Holding> getAllHoldings() {
		List<Holding> holdings = holdingService.findAllHoldings();
		return holdings;
	}

	//	@PostMapping("/holding/add")
//	public ArrayList<HashMap<String, Object>> addHoldingData(@RequestBody HashMap<String, Object> requestJsonHashMap) throws Exception {
////		Optional<Holding> holdingData = Optional.ofNullable(holdingService.findHoldingData(holding.getUser_id(), holding.getGoods_id()));
//		ArrayList<HashMap<String, Object>> rtnArray = new ArrayList<HashMap<String, Object>>();
//		HashMap<String, Object> rtnMap = new HashMap<String, Object>();
//
//		rtnMap.put("requestData1", requestJsonHashMap.get("holding_id"));
//		rtnMap.put("requestData2", requestJsonHashMap.get("user_id"));
//		rtnMap.put("requestData3", requestJsonHashMap.get("goods_id"));
//		rtnMap.put("requestData4", requestJsonHashMap.get("goods_cnt"));
//		rtnArray.add(rtnMap);
//		System.out.println(rtnArray.toString());
//		return rtnArray;
//	}
	@PostMapping("/holding/add")
	public Holding createHoldingData(@RequestBody Holding holding) {
		System.out.println("통신 성공");
		System.out.println(">>> " + holding.getGoods_id());
		holdingService.addHoldingData(holding);
		return holding;
	}
}
