package net.macrohex.cineapi.mods.cinema.dto;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public record SessionDto(
        String id,
        Timestamp showtime,
        Date day,
        Time hour,
        int seats_available
) {
}
