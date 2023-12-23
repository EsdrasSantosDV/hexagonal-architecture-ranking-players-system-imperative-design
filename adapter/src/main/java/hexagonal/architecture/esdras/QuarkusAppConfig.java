package hexagonal.architecture.esdras;

import hexagonal.architecture.esdras.application.port.input.matches.InputPortCreateMatchUseCase;
import hexagonal.architecture.esdras.application.port.input.players.InputPortCreatePlayerUseCase;
import hexagonal.architecture.esdras.application.port.input.players.InputPortPaginationPlayerUseCase;
import hexagonal.architecture.esdras.application.port.output.matches.persistence.OutputPortMatch;
import hexagonal.architecture.esdras.application.port.output.players.persistence.OutputPortPlayer;
import hexagonal.architecture.esdras.application.service.matches.CreateMatchUseCaseService;
import hexagonal.architecture.esdras.application.service.players.CreatePlayerUseCaseService;
import hexagonal.architecture.esdras.application.service.players.PaginationPlayerUseCaseService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

class QuarkusAppConfig {


    @Inject
    Instance<OutputPortPlayer> outputPortPlayerRepository;

    @Inject
    Instance<OutputPortMatch> outputPortMatchRepository;


    @Produces
    @ApplicationScoped
    InputPortCreatePlayerUseCase inputPortCreatePlayerUseCase() {
        return new CreatePlayerUseCaseService(outputPortPlayerRepository.get());
    }

    @Produces
    @ApplicationScoped
    InputPortPaginationPlayerUseCase inputPortPaginationPlayerUseCase() {
        return new PaginationPlayerUseCaseService(outputPortPlayerRepository.get());
    }

    @Produces
    @ApplicationScoped
    InputPortCreateMatchUseCase inputPortCreateMatchUseCase() {
        return new CreateMatchUseCaseService(outputPortPlayerRepository.get(), outputPortMatchRepository.get());
    }


}
