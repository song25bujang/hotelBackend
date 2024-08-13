package org.spring.middleproject.hotelback.service;

import org.apache.catalina.User;
import org.apache.ibatis.session.SqlSession;
import org.spring.middleproject.hotelback.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private final String NAMESPACE = "org.spring.middleproject.hotelback.mappers.UserMapper";
    public SqlSession SESSION;
    @Autowired
    public UserService(SqlSession session){
        SESSION = session;
    }
    public UserDTO selectOne(int userid){
        return SESSION.selectOne(NAMESPACE+".selectOne",userid);
    }
    public List<UserDTO> selectAll(){
        return SESSION.selectList(NAMESPACE+".selectAll");
    }
    public List<UserDTO> selectAllByRole(String role){
        return SESSION.selectList(NAMESPACE+".selectByRole",role);
    }
    public void updateInfo(){

    }
    public int deleteUserById(int userId){
        return SESSION.delete(NAMESPACE+".deleteByUserID",userId);

    }
    public int updateUserRole(int userId, String role){
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("role", role);
        //System.out.println("요청 들어옴 - "+"userId"+userId+", role(String)"+role);
        return SESSION.update(NAMESPACE + ".updateUserRole", params);
    }
    public UserDTO selectByUsername(String username) {
        return SESSION.selectOne(NAMESPACE + ".selectByUsername", username);
    }

}
