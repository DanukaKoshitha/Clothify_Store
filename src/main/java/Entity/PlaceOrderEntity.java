package Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class PlaceOrderEntity {
    private String orderID;
    private int userID;
    private double total;
    private String date;
}
