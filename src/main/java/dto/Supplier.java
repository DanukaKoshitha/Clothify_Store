package dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class Supplier {
    private String id;
    private String name;
    private String email;
    private String company;
    private String item;
}
