package hexagonal.architecture.esdras.adapter.out.persistence.inmemory;

import hexagonal.architecture.esdras.application.port.output.matches.persistence.OutputPortMatch;
import hexagonal.architecture.esdras.domain.entity.MatchDomain;
import hexagonal.architecture.esdras.domain.vo.MatchIdentityDomain;
import io.quarkus.arc.lookup.LookupIfProperty;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@LookupIfProperty(name = "persistence", stringValue = "inmemory", lookupIfMissing = true)
@ApplicationScoped
public class MatchMemoryAdapter implements OutputPortMatch {
    private final Map<MatchIdentityDomain, MatchDomain> matches = new ConcurrentHashMap<>();

    public MatchMemoryAdapter() {
    }

    @Override
    public MatchDomain save(MatchDomain matchDomain) {
        matches.put(matchDomain.getId(), matchDomain);
        return matches.get(matchDomain.getId());
    }
}
