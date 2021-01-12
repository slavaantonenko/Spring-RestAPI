package slava.antonenko.rest.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import slava.antonenko.model.Machine;
import slava.antonenko.repository.MachineRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Log4j2
public class MachineController {

    @Autowired
    private MachineRepository machineRepository;

    @GetMapping("/machines")
    public List<Machine> getMachines() {
        return machineRepository.findAll();
    }

    @GetMapping("/machine/{id}")
    public Machine getMachine(@PathVariable int id) {
        return machineRepository.findById(id).orElse(null);
    }

    @PostMapping("/machine")
    public boolean create(@RequestBody Map<String, String> body) {
        Machine machine = new Machine(body.get("name"));

        try {
            machineRepository.save(machine);
            log.info("Created a machine " + machine.getName());
        }
        catch (Exception e) {
            log.error("Failed to create a machine " + machine.getName());
            return false;
        }

        return true;
    }

    @PutMapping("/machine/{id}")
    public boolean update(@PathVariable int id, @RequestBody Map<String, String> body) {
        Optional<Machine> machineOptional = machineRepository.findById(id);

        if (machineOptional.isPresent()) {
            Machine machine = machineOptional.get();
            machine.setName(body.get("name"));

            try {
                machineRepository.save(machine);
                log.info("Updated a machine " + machine.getName());
                return true;
            }
            catch (Exception e) {
                log.error("Failed to update machine " + machine.getName());
            }
        }

        return false;
    }

    @DeleteMapping("/machine/{id}")
    public boolean delete(@PathVariable int id) {

        try {
            machineRepository.deleteById(id);
            log.info("Deleted a machine with id " + id);
        }
        catch (Exception e) {
            log.error("Failed to delete machine with id " + id);
            return false;
        }

        return true;
    }
}
