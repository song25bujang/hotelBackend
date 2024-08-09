package org.spring.middleproject.hotelback.service;

import org.apache.ibatis.session.SqlSession;
import org.spring.middleproject.hotelback.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final String NAMESPACE = "org.spring.middleproject.hotelback.mappers.UserMapper";

    @Autowired
    public SqlSession SESSION;

    public UserDTO selectOne(int userid){
        return SESSION.selectOne(NAMESPACE+".selectOne",userid);
    }
}
