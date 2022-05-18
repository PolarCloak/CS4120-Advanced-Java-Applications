package Bush.OutFit.Controller;

import Bush.OutFit.Domain.Outfits.Outfit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home(){
        return("OutFit");
    }


    @GetMapping("/Outfit")
    public Outfit getOutfit(){
        return null;
    }

    @PostMapping("/Outfit")
    public Outfit postOutfit(){
        return null;
    }



}
