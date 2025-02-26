package dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class User {
    private String name;
    private String email;
    private String password;
    private String role;
}
