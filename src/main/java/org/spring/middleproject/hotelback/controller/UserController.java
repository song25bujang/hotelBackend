package org.spring.middleproject.hotelback.controller;

import org.spring.middleproject.hotelback.DTO.BasketDTO;
import org.spring.middleproject.hotelback.DTO.HotelDTO;
import org.spring.middleproject.hotelback.DTO.UserDTO;
import org.spring.middleproject.hotelback.service.HotelService;
import org.spring.middleproject.hotelback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private HotelService hotelService;
    @GetMapping("/user")
    public String test(){
        String data = "spring react connection Test";
        System.out.println(data);
        return data;
    }
    @GetMapping("/user/{userId}") //id(PK)에 따른 One User
    public UserDTO pickuserById(@PathVariable int userId){
        //userDTO, hotelinMyBasket
        UserDTO userDTO = userService.selectOne(userId);
        System.out.println("[userPage & 장바구니 entered] - " + userDTO.toString());
        List<HotelDTO> hotelinMyBasket = hotelService.selectAllHotelInBasketByUserID(userId);
        System.out.printf("%d번 고객님의 장바구니 호텔 목록\n",userId);
        for(HotelDTO h : hotelinMyBasket){
            System.out.println(h.toString());
        }
        return userDTO;
    }
    @GetMapping("/admin/user/{roleString}") //ADMIN - role에 따른 User 목록
    public List<UserDTO> pickuserByRole(@PathVariable String roleString){
//        String userrole = null;
//        if(roleid == 1){
//            userrole = "ADMIN";
//        }else if(roleid == 2){
//            userrole = "SELLER";
//        }else if(roleid == 3){
//            userrole = "USER";
//        }
        List<UserDTO> userlist = userService.selectAllByRole(roleString);
        for(UserDTO u : userlist){
            System.out.println("[userlist(B Role)] - " + u.toString());
        }
        return userlist;
    }


}
