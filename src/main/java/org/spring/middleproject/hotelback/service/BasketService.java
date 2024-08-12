package org.spring.middleproject.hotelback.service;

import org.apache.ibatis.session.SqlSession;
import org.spring.middleproject.hotelback.DTO.HotelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BasketService {
    public SqlSession SESSION;
    private final String NAMESPACE = "org.spring.middleproject.hotelback.mappers.BasketMapper";
    @Autowired
    public BasketService(SqlSession session) { //생성자 주입
        SESSION = session;
    }
    public boolean deleteItemFromBasket(int userId, int productId) {
        int result = SESSION.delete(NAMESPACE + ".deleteOne", Map.of("userId", userId, "productId", productId));
        return result > 0;
    }
}
