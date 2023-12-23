package hexagonal.architecture.esdras.adapter.in.rest.player.mapper;

import hexagonal.architecture.esdras.adapter.in.rest.player.dto.PlayerCreateDto;
import hexagonal.architecture.esdras.application.port.input.players.commands.CreatePlayerCommand;
import hexagonal.architecture.esdras.application.port.input.players.commands.PaginationCommand;

public class PlayerMapper {


    public static CreatePlayerCommand toCreatePlayerCommand(PlayerCreateDto dto) {
        return new CreatePlayerCommand(
                dto.getName(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getNickname()
        );
    }

    public static PaginationCommand toPaginationCommand(int page, int size) {
        return new PaginationCommand(
                page,
                size
        );
    }

}
