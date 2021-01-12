package slava.antonenko.model;

import lombok.Getter;
import lombok.Setter;
import slava.antonenko.Constants;

import javax.persistence.*;

@Entity
@Table(name = Constants.ASSIGNED_MACHINES_TABLE)
@Getter
@Setter
public class AssignedMachine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = Constants.TEAM_ID_COLUMN)
    private int teamID;
    @Column(name = Constants.MACHINE_ID_COLUMN)
    private int machineID;

    public AssignedMachine() {}

    public AssignedMachine(int teamID, int machineID) {
        this.teamID = teamID;
        this.machineID = machineID;
    }
}
