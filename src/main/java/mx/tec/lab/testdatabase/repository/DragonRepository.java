package mx.tec.lab.testdatabase.repository;

import mx.tec.lab.testdatabase.entity.Dragon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DragonRepository extends JpaRepository<Dragon, Long> {
}
