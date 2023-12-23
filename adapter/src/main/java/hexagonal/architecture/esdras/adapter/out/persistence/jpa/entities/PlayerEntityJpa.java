package hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "player_entity_jpa")
@Getter
@Setter
public class PlayerEntityJpa {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private Integer score;

    @ManyToMany(mappedBy = "players")
    private Set<MatchEntityJpa> matches = new HashSet<>();
}


