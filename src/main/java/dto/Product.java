package dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class Product {
    private String id;
    private String category;
    private String name;
    private String Supplier;
    private int qty;
    private double price;
    private String image;
}
