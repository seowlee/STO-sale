//package com.sto.sale.backstosale.converter;
//
//import com.sto.sale.backstosale.domain.Holding;
//import com.sto.sale.backstosale.dto.HoldingDto;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Component;
//
//@Component
//public class HoldingConverter {
//    public HoldingDto convertEntityToDto(Holding holding) {
//        ModelMapper modelMapper = new ModelMapper();
//        HoldingDto holdingDto = modelMapper.map(holding, HoldingDto.class);
//        return holdingDto;
//    }
//
//    public Holding convertDtoToEntity(HoldingDto holdingDto) {
//        ModelMapper modelMapper = new ModelMapper();
//        Holding holding = modelMapper.map(holdingDto, Holding.class);
//        return holding;
//    }
//}
