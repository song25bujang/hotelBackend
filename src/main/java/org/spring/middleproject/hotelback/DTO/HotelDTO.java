package org.spring.middleproject.hotelback.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelDTO {
    private int id;
    private String name;
    private int sellerId;
    private double price;
}
