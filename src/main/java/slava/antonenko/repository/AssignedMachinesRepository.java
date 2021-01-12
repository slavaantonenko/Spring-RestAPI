package slava.antonenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import slava.antonenko.Constants;
import slava.antonenko.model.AssignedMachine;

public interface AssignedMachinesRepository extends JpaRepository<AssignedMachine, Integer> {

    @Query(value = "SELECT COUNT(*) > 0 FROM " + Constants.ASSIGNED_MACHINES_TABLE +
            " WHERE " + Constants.TEAM_ID_COLUMN + " = ?1 AND " +
            Constants.MACHINE_ID_COLUMN + " = ?2", nativeQuery = true)
    int isAssigned(int teamID, int stringID);
}
