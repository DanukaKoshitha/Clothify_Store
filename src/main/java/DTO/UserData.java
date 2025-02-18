package DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class UserData {
    private int id;
    private String name;
    private String email;
    private String password;
    private String role;
}
