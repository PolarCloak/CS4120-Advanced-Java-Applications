package Bush.OutFit.Domain.Material;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Material {

    @Id
    private int id;
    private String color;
    private String style;

}
