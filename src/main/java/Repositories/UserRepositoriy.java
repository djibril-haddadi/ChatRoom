package Repositories;

import com.example.demo.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoriy extends JpaRepository<User,Integer> {
}
