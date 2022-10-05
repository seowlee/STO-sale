package com.sto.sale.backstosale.controller;

import com.sto.sale.backstosale.dto.HoldingDto;
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
	public List<HoldingDto> getAllHoldings() {
		List<HoldingDto> holdings = holdingService.findAllHoldings();
		return holdings;
	}

//    @GetMapping("/holding/list")
//    public List<ListHoldingDto> getListHolding() {
//        List<ListHoldingDto> listHolding = holdingService.findListHolding();
//        return listHolding;
//    }


	//    @PostMapping("/holding/add")
//    public ArrayList<HashMap<String, Object>> addHoldingData(@RequestBody HashMap<String, Object> requestJsonHashMap) throws Exception {
////		Optional<Holding> holdingData = Optional.ofNullable(holdingService.findHoldingData(holding.getUser_id(), holding.getGoods_id()));
//        ArrayList<HashMap<String, Object>> rtnArray = new ArrayList<HashMap<String, Object>>();
//        HashMap<String, Object> rtnMap = new HashMap<String, Object>();
//
//        rtnMap.put("requestData1", requestJsonHashMap.get("holding_id"));
//        rtnMap.put("requestData2", requestJsonHashMap.get("user_id"));
//        rtnMap.put("requestData3", requestJsonHashMap.get("goods_id"));
//        rtnMap.put("requestData4", requestJsonHashMap.get("goods_cnt"));
//        rtnArray.add(rtnMap);
//        System.out.println(rtnArray.toString());
//        return rtnArray;
//    }
	@PostMapping("/holding/add")
	public HoldingDto createHoldingData(@RequestBody HoldingDto holdingDto) {
		System.out.println("통신 성공");
		System.out.println("user_id : " + holdingDto.getUserId());
		System.out.println("goods_id : " + holdingDto.getGoodsId());
		System.out.println("goods_cnt : " + holdingDto.getGoods_cnt());
		holdingService.addHoldingData(holdingDto);
		return holdingDto;
	}

//    @PutMapping("holding/add")
//    public Holding updateHolding(@RequestBody Holding holding) {
//        holdingService.addHoldingData(holding);
//        return holding;
//    }
}
