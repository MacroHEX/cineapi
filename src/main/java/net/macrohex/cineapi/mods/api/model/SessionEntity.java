package net.macrohex.cineapi.mods.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "session")
@Data
@NoArgsConstructor
public class SessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Timestamp showtime;
    private java.sql.Date day;
    private java.sql.Time hour;
    private Integer seatsAvailable;

    @ManyToOne
    @JoinColumn(name = "movie_version_id", nullable = false)
    private MovieVersionEntity movieVersion;
}