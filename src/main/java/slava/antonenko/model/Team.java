package slava.antonenko.model;

import lombok.Getter;
import lombok.Setter;
import slava.antonenko.Constants;

import javax.persistence.*;

@Entity
@Table(name = Constants.TEAM_TABLE)
@Getter
@Setter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quota;

    public Team() {}

    public Team(int quota) {
        this.quota = quota;
    }
}
