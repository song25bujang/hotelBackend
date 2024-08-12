package org.spring.middleproject.hotelback.service;

import org.apache.catalina.User;
import org.apache.ibatis.session.SqlSession;
import org.spring.middleproject.hotelback.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final String NAMESPACE = "org.spring.middleproject.hotelback.mappers.UserMapper";

    @Autowired
    public SqlSession SESSION;

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

}
