package Bush.OutFit.Domain.Outfits;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Section {

    @Id
    private int id;
    private String name;
    private int materialId;
    private String description;

}
