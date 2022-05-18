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
public class OutfitPiece {

    @Id
    private int id;
    private int sectionId;
    private String type;
    private String location;


}
