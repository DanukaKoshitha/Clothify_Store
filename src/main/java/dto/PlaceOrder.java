package dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class PlaceOrder {
    private String orderID;
    private int userID;
    private double total;
    private String date;
}
