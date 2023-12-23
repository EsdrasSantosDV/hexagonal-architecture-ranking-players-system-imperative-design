package hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "match_entity_jpa")
@Getter
@Setter
public class MatchEntityJpa {

    @Column(nullable = false)
    Instant date;
    @Column(nullable = false)
    String local;
    @Column(nullable = false)
    int matchWeight;
    @Id
    private String id;

    @ManyToMany
    @JoinTable(
            name = "match_player",
            joinColumns = @JoinColumn(name = "match_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private Set<PlayerEntityJpa> players = new HashSet<>();
}
