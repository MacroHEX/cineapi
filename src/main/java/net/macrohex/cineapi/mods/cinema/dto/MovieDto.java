package net.macrohex.cineapi.mods.cinema.dto;

import java.sql.Date;
import java.util.ArrayList;

public record MovieDto(
        String title,
        String trailer_url,
        String graphic_url,
        String runtime,
        String rating,
        String film_HO_code,
        String corporate_film_id,
        String synopsis,
        String synopsis_alt,
        Date opening_date,
        String genre,
        String genre2,
        String genre3,
        ArrayList<CastDto> cast,
        ArrayList<MovieVersionDto> movie_versions
) {
}
