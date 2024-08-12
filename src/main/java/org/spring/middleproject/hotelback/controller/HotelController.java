package org.spring.middleproject.hotelback.controller;

import org.spring.middleproject.hotelback.DTO.HotelDTO;
import org.spring.middleproject.hotelback.DTO.UserDTO;
import org.spring.middleproject.hotelback.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HotelController {
    @Autowired
    private HotelService hotelService;
    @GetMapping("/hotel/showAll")
    public List<HotelDTO> showAll(){
        List<HotelDTO> hotelList = hotelService.selectAll();
        System.out.println("Hotel 목록 전체");
        for(HotelDTO h : hotelList){
            System.out.println(h.toString());
        }
        return hotelList;
    }
    @GetMapping("/hotel/showOne/{hotelId}")
    public HotelDTO showOne(@PathVariable int hotelId){
        HotelDTO oneHotel = hotelService.selectOne(hotelId);
        System.out.printf("선택한 Hotel:%d\n",hotelId);
        System.out.println(oneHotel.toString());

        return oneHotel;
    }
}
