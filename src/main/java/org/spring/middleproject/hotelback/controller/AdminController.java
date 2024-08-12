package org.spring.middleproject.hotelback.controller;

import org.spring.middleproject.hotelback.DTO.UserDTO;
import org.spring.middleproject.hotelback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/")
@CrossOrigin(origins = "http://localhost:3000")  // CORS 설정 추가
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("users") //ADMIN - role에 따른 User 목록
    public List<UserDTO> showAlluser(){
        List<UserDTO> alluserlist = userService.selectAll();
        return alluserlist;
    }

    @GetMapping("user/{roleString}") //ADMIN - role에 따른 User 목록
    public List<UserDTO> pickuserByRole(@PathVariable String roleString){
        List<UserDTO> userlist = userService.selectAllByRole(roleString);
        for(UserDTO u : userlist){
            System.out.println("[userlist(B Role)] - " + u.toString());
        }
        return userlist;
    }
    @DeleteMapping("user/{userId}")
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
