package org.spring.middleproject.hotelback.controller;

import org.spring.middleproject.hotelback.DTO.BasketDTO;
import org.spring.middleproject.hotelback.DTO.HotelDTO;
import org.spring.middleproject.hotelback.DTO.UserDTO;
import org.spring.middleproject.hotelback.service.HotelService;
import org.spring.middleproject.hotelback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
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
        String data = "user";
        System.out.println(data);
        return data;
    }
    @GetMapping("/user/{userId}") //id(PK)에 따른 One User
    public UserDTO pickuserById(@PathVariable int userId){
        //userDTO, hotelinMyBasket
        UserDTO userDTO = userService.selectOne(userId);
        System.out.println("[userPage entered] - " + userDTO.toString());
        List<HotelDTO> hotelinMyBasket = hotelService.selectAllHotelInBasketByUserID(userId);
        System.out.printf("%d번 고객의 장바구니 호텔 목록\n",userId);
        for(HotelDTO h : hotelinMyBasket){
            System.out.println(h.toString());
        }
        return userDTO;
    }
    @GetMapping("/user/basket/{userId}") //id(PK)에 따른 One User
    public List<HotelDTO> pickBasketByuserId(@PathVariable int userId){
        //hotelinMyBasket
        System.out.println("hotelinMyBasket");
        List<HotelDTO> hotelinMyBasket = hotelService.selectAllHotelInBasketByUserID(userId);
        System.out.printf("%d번 고객님의 장바구니 호텔 목록\n",userId);
        for(HotelDTO h : hotelinMyBasket){
            System.out.println(h.toString());
        }
        return hotelinMyBasket;
    }

    @GetMapping("/admin/users") //ADMIN - role에 따른 User 목록
    public List<UserDTO> showAlluser(){
        List<UserDTO> alluserlist = userService.selectAll();
//        for(UserDTO u : userlist){
//            System.out.println("[userlist(B Role)] - " + u.toString());
//        }
        return alluserlist;
    }

    @GetMapping("/admin/user/{roleString}") //ADMIN - role에 따른 User 목록
    public List<UserDTO> pickuserByRole(@PathVariable String roleString){
        List<UserDTO> userlist = userService.selectAllByRole(roleString);
        for(UserDTO u : userlist){
            System.out.println("[userlist(B Role)] - " + u.toString());
        }
        return userlist;
    }
    @DeleteMapping("/admin/user/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        int isDeleted = userService.deleteUserById(userId);
        System.out.println(isDeleted);
        if (isDeleted > 0) {
            // 삭제 성공 시  204 No Content를 반환
            return ResponseEntity.noContent().build();
        } else {
            // 삭제할 사용자가 존재하지 않으면 404 Not Found를 반환
            return ResponseEntity.notFound().build();
        }
    }


}
