package Service;
import com.example.demo.*;
import Repositories.SuppressionRepository;
import com.example.demo.Suppression;
import com.example.demo.User;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

public class SuppressionService {
    private SuppressionRepository suppressionRepo;

    public String addSuppression() {
        return "addSuppression";
    }

    public String addSuppression(
            @RequestParam("id") long id,
            @RequestParam("userSupprime") User newUserSupprime,
            @RequestParam("raison") String newRaison,
            @RequestParam("date") Date newDate) {
        Suppression u1 = new Suppression(newUserSupprime, newRaison, newDate);
        suppressionRepo.save(u1);
        return ("Suppression created successfully.");
    }

    public List<Suppression> getSuppression(){
        List<Suppression> suppressionList = suppressionRepo.findAll();

        return (suppressionList);
    }

    public Suppression getSuppression(@RequestParam("id") long id){
        Suppression suppression = suppressionRepo.findById(id);

        return (suppression);
    }

    public String modifySuppression(@RequestBody Suppression suppressionBody){
        Suppression suppression = suppressionRepo.findById(suppressionBody.getId());
        if (suppression == null){return ("Suppression does not exist, could not be modified");}
        suppressionRepo.save(suppressionBody);
        return ("Suppression modified successfully");
    }

    public String deleteSuppression(@RequestParam("id") long id){
        Suppression suppression = suppressionRepo.findById(id);
        if (suppression == null){return ("Suppression does not exist, could not be deleted");}
        suppressionRepo.delete(suppression);
        return ("suppression deleted successfully");
    }
}
