package com.sto.sale.backstosale.service;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sto.sale.backstosale.domain.Holding;
import com.sto.sale.backstosale.domain.QHolding;
import com.sto.sale.backstosale.dto.GoodsHoldingDto;
import com.sto.sale.backstosale.dto.HoldingDto;
import com.sto.sale.backstosale.repository.HoldingRepository;
import com.sto.sale.backstosale.repository.ProductRepository;
import com.sto.sale.backstosale.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.sto.sale.backstosale.domain.QProduct.product;
import static com.sto.sale.backstosale.domain.QUser.user;

public class HoldingService {
	private HoldingRepository holdingRepository;
	private UserRepository userRepository;
	private ProductRepository productRepository;

	@Autowired
	JPAQueryFactory jpaQueryFactory;

	public HoldingService(HoldingRepository holdingRepository) {
		this.holdingRepository = holdingRepository;
	}

	@Autowired
	ModelMapper modelMapper;

//    @Autowired
//    public HoldingService(ModelMapper modelMapper) {
//        this.modelMapper = modelMapper;
//        this.modelMapper.addConverter(entityToDto);
//        this.modelMapper.addConverter(dtoToEntity);
//    }

//	@Autowired
//	HoldingConverter holdingConverter;

	/**
	 * 모든 보유 기록 조회
	 */
	public List<HoldingDto> findAllHoldings() {
		return holdingRepository.findByAllHoldings();
	}

	/**
	 * 상품 별 판매 개수, 보유자 리스트
	 */
	public List<GoodsHoldingDto> findListHolding() {
		QHolding qHolding = QHolding.holding;
		List<GoodsHoldingDto> list = jpaQueryFactory.select(Projections.fields(GoodsHoldingDto.class, product.goods_id.as("goodsId"), product.goods_nm.as("goodsNm"), qHolding.goods_cnt.sum().as("sumGoodsCnt"), Expressions.stringTemplate("group_concat({0})", qHolding.user.user_id).as("userIds")))
				.from(qHolding)
				.join(qHolding.product, product)
				.join(qHolding.user, user)
				.groupBy(qHolding.product.goods_id)
				.fetch();

		return list;

//		return holdingRepository.findByListHolding();
	}


	/**
	 * 보유 데이터 추가, 업데이트
	 */
	public HoldingDto addHoldingData(HoldingDto addedHoldingDto) {
		HoldingDto holdingDto = holdingRepository
				.findByHoldingData(addedHoldingDto.getUserId(), addedHoldingDto.getGoodsId());
		System.out.println("=======");
		System.out.println(addedHoldingDto.toString());

//		ModelMapper modelMapper = new ModelMapper();


//        Holding addedHolding = modelMapper.map(addedHoldingDto, Holding.class);
//        System.out.println(addedHolding.toString());

		if (holdingDto != null) {
			holdingDto.update_holding(addedHoldingDto.getGoods_cnt());
			System.out.println("holdingdto " + holdingDto.toString());
		} else {
			holdingDto = addedHoldingDto;
//            holdingDto.insert(addedHoldingDto.getUserId(), addedHoldingDto.getGoodsId(), addedHoldingDto.getGoods_cnt());
			System.out.println("tttttt");
			System.out.println("insertdto " + holdingDto.toString());

//            holding.builder()
//                    .user_id(addedHolding.getUser_id())
//                    .goods_id(addedHolding.getGoods_id())
//                    .goods_cnt(addedHolding.getGoods_cnt())
//                    .build();
		}
//		Holding holding = modelMapper.map(holdingDto, Holding.class);

		Holding holding = new Holding(holdingDto);
		System.out.println("---- mapper -----");
		System.out.println(holding.toString());

		holdingRepository.save(holding);
//        holdingRepository.save(holding);
//		HoldingDto holdingDto = modelMapper.map(holding, HoldingDto.class);

		return addedHoldingDto;
	}

//    Converter<Holding, HoldingDto> entityToDto = new Converter<Holding, HoldingDto>() {
//        @Override
//        public HoldingDto convert(MappingContext<Holding, HoldingDto> context) {
//            context.getDestination().setHolding_id(context.getSource().getHolding_id());
//            context.getDestination().setUserId(context.getSource().getUser().getUser_id());
//            context.getDestination().setGoodsId(context.getSource().getProduct().getGoods_id());
//            context.getDestination().setGoods_cnt(context.getSource().getGoods_cnt());
//            return context.getDestination();
//        }
//    };
//
//    Converter<HoldingDto, Holding> dtoToEntity = new Converter<HoldingDto, Holding>() {
//        @Override
//        public Holding convert(MappingContext<HoldingDto, Holding> context) {
//            context.getDestination().setHolding_id(context.getSource().getHolding_id());
////            context.getDestination().setUser(context.getSource().getUser());
//            return context.getDestination();
//        }
//    };
//    TypeMap<HoldingDto, Holding> propertyMapper =
//            this.modelMapper.createTypeMap(HoldingDto.class, Holding.class);
//        propertyMapper.addMappings(mapper -> {
//        mapper.using(idToArea).map(HoldingDto::getUser_id, Holding::setUser);
//    });

}
