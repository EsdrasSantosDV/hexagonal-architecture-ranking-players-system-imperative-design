package hexagonal.architecture.esdras.application.port.output.matches.persistence;

import hexagonal.architecture.esdras.domain.entity.MatchDomain;

public interface OutputPortMatch {
    MatchDomain save(MatchDomain playerDomain);

}
