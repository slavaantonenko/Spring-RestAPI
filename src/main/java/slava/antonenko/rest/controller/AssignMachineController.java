package slava.antonenko.rest.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import slava.antonenko.model.AssignedMachine;
import slava.antonenko.repository.AssignedMachinesRepository;

import java.util.Map;

@RestController
@Log4j2
public class AssignMachineController {

    @Autowired
    private AssignedMachinesRepository assignedMachinesRepository;

    @Autowired
    private TeamController teamController;

    @PostMapping("/assign")
    public boolean assignMachine(@RequestBody Map<String, Integer> body) {
        int teamID = body.get("team");
        int machineID = body.get("machine");

        if (teamController.getQuota(teamID) > 0 && assignedMachinesRepository.isAssigned(teamID, machineID) == 0) {
            AssignedMachine assignedMachine = new AssignedMachine(teamID, machineID);

            try {
                teamController.teamRepository.updateQuota(teamID);
            }
            catch (Exception e) {
                log.error("Failed to update quota count for team with id " + teamID);
                return false;
            }

            try {
                assignedMachinesRepository.save(assignedMachine);
                log.info("Assigned machine with id " + machineID + " to team with id " + teamID);
                return true;
            }
            catch (Exception e) {
                log.error("Failed to assign machine with id " + machineID + " to team with id " + teamID);
            }
        }

        log.error("Failed to assign machine to a team because of the following reasons\n" +
                "1. Team's quota reached full capacity\n" +
                "2. The machine is already assigned to this team");

        return false;
    }
}
