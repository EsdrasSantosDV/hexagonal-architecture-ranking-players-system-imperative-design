package hexagonal.architecture.esdras.adapter.in.rest.match.mapper;


import hexagonal.architecture.esdras.adapter.in.rest.match.dto.MatchCreateDto;
import hexagonal.architecture.esdras.adapter.in.rest.match.dto.MatchPlayerDto;
import hexagonal.architecture.esdras.application.port.input.matches.commands.CreateMatchCommand;
import hexagonal.architecture.esdras.application.port.input.matches.commands.PlayerMatchCommand;

import java.util.stream.Collectors;

public class MatchMapper {

    public static CreateMatchCommand toCreateMatchCommand(MatchCreateDto dto) {
        return CreateMatchCommand.builder()
                .matchWeight(dto.getMatchWeight())
                .date(dto.getDate())
                .local(dto.getLocal())
                .players(dto.getPlayers().stream()
                        .map(MatchMapper::toPlayerMatchCommand)
                        .collect(Collectors.toList()))
                .build();
    }

    private static PlayerMatchCommand toPlayerMatchCommand(MatchPlayerDto dto) {
        return PlayerMatchCommand.builder()
                .email(dto.getEmail())
                .id(dto.getId())
                .build();
    }
}