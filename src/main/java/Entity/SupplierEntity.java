package Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class SupplierEntity {
    private String id;
    private String name;
    private String email;
    private String company;
    private String item;
}
