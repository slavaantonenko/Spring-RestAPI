package slava.antonenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import slava.antonenko.model.Machine;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Integer> {}
