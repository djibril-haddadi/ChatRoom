package Repositories;

import com.example.demo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface SuppressionRepository extends JpaRepository<Suppression,Long> {
    public Suppression findById(long id);
}
