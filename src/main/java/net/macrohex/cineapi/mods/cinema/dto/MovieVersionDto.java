package net.macrohex.cineapi.mods.cinema.dto;

import java.util.ArrayList;

public record MovieVersionDto(
        String film_HOPK,
        String title,
        String film_HO_code,
        String id,
        ArrayList<SessionDto> sessions
) {
}
