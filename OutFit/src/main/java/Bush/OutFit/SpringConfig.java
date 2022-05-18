package Bush.OutFit;

import Bush.OutFit.Repository.OutfitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager en){
        this.em = em;
    }

    @Bean
    public OutfitRepository outfitRepository(){
        return new OutfitRepository(em);
    }

}
