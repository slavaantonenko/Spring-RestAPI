package slava.antonenko.rest.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import slava.antonenko.model.Team;
import slava.antonenko.repository.TeamRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Log4j2
public class TeamController {

    @Autowired
    TeamRepository teamRepository;

    @GetMapping("/teams")
    public List<Team> getTeams() {
        return teamRepository.findAll();
    }

    @GetMapping("/team/{id}")
    public Team getTeam(@PathVariable int id) {
        return teamRepository.findById(id).orElse(null);
    }

    @PostMapping("/team")
    public boolean create(@RequestBody Map<String, Integer> body) {
        Team team = new Team(body.get("quota"));

        try {
            teamRepository.save(team);
            log.info("Created a team");
        }
        catch (Exception e) {
            log.error("Failed to create a team");
            return false;
        }

        return true;
    }

    @PutMapping("/team/{id}")
    public boolean update(@PathVariable int id, @RequestBody Map<String, Integer> body) {
        Optional<Team> teamOptional = teamRepository.findById(id);

        if (teamOptional.isPresent()) {
            Team team = teamOptional.get();
            team.setQuota(body.get("quota"));

            try {
                teamRepository.save(team);
                log.info("Updated a team");
                return true;
            }
            catch (Exception e) {
                log.error("Failed to update a team");
            }
        }

        return false;
    }

    @DeleteMapping("/team/{id}")
    public boolean delete(@PathVariable int id) {

        try {
            teamRepository.deleteById(id);
            log.info("Deleted a team");
        }
        catch (Exception e) {
            log.error("Failed to delete a team");
            return false;
        }

        return true;
    }

    public int getQuota(int id) {
        Team team = getTeam(id);

        return team != null ? team.getQuota() : -1;
    }
}
