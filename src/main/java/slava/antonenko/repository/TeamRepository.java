package slava.antonenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import slava.antonenko.Constants;
import slava.antonenko.model.Team;

import javax.transaction.Transactional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE " + Constants.TEAM_TABLE + " SET " +
            Constants.QUOTA_COLUMN + " = " + Constants.QUOTA_COLUMN + " - 1" + " WHERE id = ?1", nativeQuery = true)
    void updateQuota(int teamID);
}
