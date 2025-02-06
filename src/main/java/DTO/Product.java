package DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Product {
    private String id;
    private String category;
    private String name;
    private String Supplier;
    private int qty;
    private double price;
    private String image;
}
