package org.spring.middleproject.hotelback.controller;

import org.spring.middleproject.hotelback.DTO.BasketDTO;
import org.spring.middleproject.hotelback.DTO.HotelDTO;
import org.spring.middleproject.hotelback.DTO.UserDTO;
import org.spring.middleproject.hotelback.service.HotelService;
import org.spring.middleproject.hotelback.service.UserDetailsServiceImpl;
import org.spring.middleproject.hotelback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private HotelService hotelService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    @Autowired
    public UserController(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }
    @PostMapping("/user/auth")
    public ResponseEntity<?> authenticateUser(@RequestParam String username, @RequestParam String password) {
        // 로그로 입력된 비밀번호 출력
        System.out.println("Raw Password: " + password);

        // 사용자 인증을 수행
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDTO userDetails = (UserDTO) userDetailsService.loadUserByUsername(username);

        // 인증 성공 시 사용자 정보를 반환
        return ResponseEntity.ok().body(userDetails);
    }
    @RequestMapping("/user/authSuccess")
    public ResponseEntity<Map<String, Object>> authSuccess(Authentication authentication) {
        System.out.println("login Success");
        Map<String, Object> resultMap = new HashMap<>();
        UserDTO userDTO = (UserDTO) authentication.getPrincipal();

        resultMap.put("result","success");
        resultMap.put("id", userDTO.getId());
        resultMap.put("nickname", userDTO.getNickname());
        resultMap.put("role", userDTO.getRole());

        return ResponseEntity.ok(resultMap);
    }

    @RequestMapping("/user/authFail")
    public ResponseEntity<Map<String, Object>> authFail() {
        System.out.println("Auth has failed");
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result","fail");

        return ResponseEntity.ok(resultMap);
    }

    @RequestMapping("/user/logOutSuccess")
    public ResponseEntity<Void> logOutSuccess(Authentication authentication) {
        System.out.println(authentication);
        System.out.println("log out success");

        return ResponseEntity.ok().build();
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


}
