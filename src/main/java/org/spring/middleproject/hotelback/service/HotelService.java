package org.spring.middleproject.hotelback.service;

import org.apache.ibatis.session.SqlSession;
import org.spring.middleproject.hotelback.DTO.HotelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    private final String NAMESPACE = "org.spring.middleproject.hotelback.mappers.HotelMapper";
    @Autowired
    public SqlSession SESSION;

    public HotelDTO selectOne(int hotelid){
        return SESSION.selectOne(NAMESPACE+".selectOne", hotelid);
    }
    public List<HotelDTO> selectAll(){
        return SESSION.selectList(NAMESPACE+".selectAll");
    }
    public List<HotelDTO> selectAllHotelInBasketByUserID(int userId){
        return SESSION.selectList(NAMESPACE+".hotelInBasket",userId);
    }

    public void updateInfo(){

    }
    public void deleteOne(){

    }
}
