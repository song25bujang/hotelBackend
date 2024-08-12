package org.spring.middleproject.hotelback.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasketDTO {
    private int id;
    private int userID;
    private int hotelId;
}
