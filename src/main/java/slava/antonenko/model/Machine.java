package slava.antonenko.model;

import lombok.Getter;
import lombok.Setter;
import slava.antonenko.Constants;

import javax.persistence.*;

@Entity
@Table(name = Constants.MACHINE_TABLE)
@Getter
@Setter
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public Machine() {}

    public Machine(String name) {
        this.name = name;
    }
}
