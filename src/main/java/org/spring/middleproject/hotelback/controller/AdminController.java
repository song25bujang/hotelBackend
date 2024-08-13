package org.spring.middleproject.hotelback.controller;

import org.spring.middleproject.hotelback.DTO.UserDTO;
import org.spring.middleproject.hotelback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
//        System.out.println(isDeleted); 삭제하면 1 반환
        if (isDeleted > 0) {// 삭제 성공 시  204 No Content를 반환
            return ResponseEntity.noContent().build();
        } else {// 삭제할 사용자가 존재하지 않으면 404 Not Found를 반환
            return ResponseEntity.notFound().build();
        }
    }
    @PatchMapping("user/{userId}/{role}")
    public ResponseEntity<Void> updateUserRole(@PathVariable int userId, @PathVariable int role) {
        //System.out.println("요청 들어옴 - "+"userId"+userId+", role(int)"+role);
        String roleString = null;
        try {
            if(role == 2){
                roleString = "role_seller";
//                System.out.println("role바뀜 - " + roleString);
            }else if(role == 3){
                roleString = "role_customer";
//                System.out.println("role바뀜 - " + roleString);
            }else { //(role != 2 && role != 3)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            int isUpdated = userService.updateUserRole(userId, roleString);
//            System.out.println(isUpdated);
            if (isUpdated != 1) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
