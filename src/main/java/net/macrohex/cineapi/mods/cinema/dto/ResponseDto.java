package net.macrohex.cineapi.mods.cinema.dto;

import java.util.ArrayList;

public record ResponseDto(
        String date,
        ArrayList<MovieDto> movies
) {
}
