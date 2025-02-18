package DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class ProductOrder {
    private String productName;
    private int qty;
}
