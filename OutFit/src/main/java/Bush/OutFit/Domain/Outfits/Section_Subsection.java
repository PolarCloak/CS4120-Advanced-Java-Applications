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
public class Section_Subsection {

    @Id
    private int sectionId;
    @Id
    private int subsectionId;

}
