package org.spring.middleproject.hotelback.controller;

import org.spring.middleproject.hotelback.DTO.HotelDTO;
import org.spring.middleproject.hotelback.DTO.UserDTO;
import org.spring.middleproject.hotelback.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/hotel/")
public class HotelController {
    private HotelService HOTEL_SERVICE;
    @Autowired
    public HotelController(HotelService hotelService) {//생성자 주입 방식
        HOTEL_SERVICE = hotelService;
    }
    @GetMapping("showOne/{id}")
    public HotelDTO showOne(@PathVariable int id){
        HotelDTO oneHotel = HOTEL_SERVICE.selectOne(id);
//        System.out.printf("선택한 Hotel:%d\n",id);
//        System.out.println(oneHotel.toString());
        return oneHotel;
    }
    @GetMapping("showList/{pageNo}")
    public HashMap<String, Object> showAll(@PathVariable int pageNo, Model model, @RequestParam(value="searchType",required = false) String searchType,
                                           @RequestParam(value="keyword",required = false) String keyword){

        HashMap<String, Object> resultMap = new HashMap<>();

        //(페이징
        int maxPage = HOTEL_SERVICE.selectMaxPage();

        int startPage = 1;
        int endPage = 1;

        if (maxPage < 5) {
            endPage = maxPage;
        } else if (pageNo <= 3) {
            endPage = 5;
        } else if (pageNo >= maxPage - 2) {
            startPage = maxPage - 4;
            endPage = maxPage;
        } else {
            startPage = pageNo - 2;
            endPage = pageNo + 2;
        }

        resultMap.put("currentPage", pageNo);
        resultMap.put("startPage", startPage);
        resultMap.put("endPage", endPage);
        resultMap.put("maxPage", maxPage);
        resultMap.put("hotelList", HOTEL_SERVICE.selectAll(pageNo));
        //페이징)

        List<HotelDTO> list;// = HOTEL_SERVICE.selectAll();
        if(searchType != null & keyword != null && !keyword.isEmpty()){
            list = HOTEL_SERVICE.searchHotels(searchType,keyword);
            model.addAttribute("searchType",searchType);
            model.addAttribute("keyword",keyword);
        }else{
            list = HOTEL_SERVICE.selectAll(pageNo);
        }
        model.addAttribute("list",list);

        return resultMap;
    }

}
