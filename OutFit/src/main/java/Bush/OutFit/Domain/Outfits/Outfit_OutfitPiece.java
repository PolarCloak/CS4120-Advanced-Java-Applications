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
public class Outfit_OutfitPiece{

    @Id
    private int outfitId;
    @Id
    private int outfitPieceId;

}
