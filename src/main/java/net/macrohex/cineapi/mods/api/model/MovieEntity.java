package net.macrohex.cineapi.mods.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "movie")
@Data
@NoArgsConstructor
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String trailerUrl;
    private String graphicUrl;
    private String runtime;
    private String rating;
    private String filmHoCode;
    private String corporateFilmId;
    @Column(columnDefinition = "TEXT")
    private String synopsis;
    private String synopsisAlt;
    private Date openingDate;
    private String genre;
    private String genre2;
    private String genre3;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CastEntity> castList;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovieVersionEntity> movieVersions;
}
