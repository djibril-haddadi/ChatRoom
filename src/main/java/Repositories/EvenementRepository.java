package Repositories;

import com.example.demo.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvenementRepository extends JpaRepository<Evenement,Long> {
    Evenement findById(long id);
}
