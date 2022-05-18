package Bush.OutFit.Repository;

import Bush.OutFit.Domain.Material.Material;
import Bush.OutFit.Domain.Outfits.*;
import Bush.OutFit.Domain.Tags.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class OutfitRepository {

    private final EntityManager em;

    public OutfitRepository(EntityManager em){
        this.em = em;
    }

    public Outfit save(Outfit outfit) {
        em.persist(outfit);
        return outfit;
    }
    public Optional<Outfit> getOutfit(int id){
        Outfit outfit = em.find(Outfit.class, id);
        return Optional.ofNullable(outfit);
    }

    public OutfitPiece save(OutfitPiece outfitPiece) {
        em.persist(outfitPiece);
        return outfitPiece;
    }
    public Optional<OutfitPiece> getOutfitPiece(int id){
        OutfitPiece outfitPiece = em.find(OutfitPiece.class, id);
        return Optional.ofNullable(outfitPiece);
    }

    public Section save(Section section) {
        em.persist(section);
        return section;
    }
    public Optional<Section> getSection(int id){
        Section section = em.find(Section.class, id);
        return Optional.ofNullable(section);
    }

    public Tag save(Tag tag) {
        em.persist(tag);
        return tag;
    }
    public Optional<Tag> getTag(int id){
        Tag tag = em.find(Tag.class, id);
        return Optional.ofNullable(tag);
    }

    public Material save(Material material) {
        em.persist(material);
        return material;
    }
    public Optional<Material> getMaterial(int id){
        Material material = em.find(Material.class, id);
        return Optional.ofNullable(material);
    }

    public Outfit_OutfitPiece save(Outfit_OutfitPiece outfit_outfitPiece) {
        em.persist(outfit_outfitPiece);
        return outfit_outfitPiece;
    }
    public List<Outfit_OutfitPiece> findAllOutfitPiece(int outfitId) {
        return em.createQuery("select m from Outfit_OutfitPiece m where m.outfitId = :id", Outfit_OutfitPiece.class)
                .setParameter("id", outfitId).getResultList();
    }

    public OutfitPiece_Tag save(OutfitPiece_Tag outfitPiece_Tag) {
        em.persist(outfitPiece_Tag);
        return outfitPiece_Tag;
    }
    public List<OutfitPiece_Tag> findAllTag(int outfitPieceId) {
        return em.createQuery("select m from OutfitPiece_Tag m where m.outfitPieceId = :id", OutfitPiece_Tag.class)
                .setParameter("id", outfitPieceId).getResultList();
    }

    public Section_Subsection save(Section_Subsection section_Subsection) {
        em.persist(section_Subsection);
        return section_Subsection;
    }
    public List<Section_Subsection> findAllSubsection(int sectionId) {
        return em.createQuery("select m from Section_Subsection m where m.sectionId = :id", Section_Subsection.class)
                .setParameter("id", sectionId).getResultList();
    }




}
