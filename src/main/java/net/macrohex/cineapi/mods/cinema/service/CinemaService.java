package net.macrohex.cineapi.mods.cinema.service;

import net.macrohex.cineapi.mods.api.model.CastEntity;
import net.macrohex.cineapi.mods.api.model.MovieEntity;
import net.macrohex.cineapi.mods.api.model.MovieVersionEntity;
import net.macrohex.cineapi.mods.api.model.SessionEntity;
import net.macrohex.cineapi.mods.api.repository.CastRepository;
import net.macrohex.cineapi.mods.api.repository.MovieRepository;
import net.macrohex.cineapi.mods.api.repository.MovieVersionRepository;
import net.macrohex.cineapi.mods.api.repository.SessionRepository;
import net.macrohex.cineapi.mods.cinema.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Service class responsible for retrieving movie data from an external API,
 * persisting it to the local database, and managing associated movie metadata such as
 * cast, versions, and session schedules.
 */
@Service
public class CinemaService {

    private final WebClient webClient;
    private final MovieRepository movieRepository;
    private final CastRepository castRepository;
    private final MovieVersionRepository movieVersionRepository;
    private final SessionRepository sessionRepository;

    /**
     * Constructs a new {@code CinemaService} with the given dependencies.
     *
     * @param webClient              WebClient instance to perform external API calls.
     * @param movieRepository        Repository for storing and retrieving movie entities.
     * @param castRepository         Repository for storing and retrieving cast entities.
     * @param movieVersionRepository Repository for storing movie version entities.
     * @param sessionRepository      Repository for storing session entities.
     */
    public CinemaService(WebClient webClient,
                         MovieRepository movieRepository,
                         CastRepository castRepository,
                         MovieVersionRepository movieVersionRepository,
                         SessionRepository sessionRepository) {
        this.webClient = webClient;
        this.movieRepository = movieRepository;
        this.castRepository = castRepository;
        this.movieVersionRepository = movieVersionRepository;
        this.sessionRepository = sessionRepository;
    }

    /**
     * Fetches a list of movies from an external service and stores them in the local database.
     *
     * @return a {@link Mono} that emits a list of {@link ResponseDto} containing the movie data.
     */
    public Mono<List<ResponseDto>> listMovies() {
        return webClient.get()
                .retrieve()
                .bodyToFlux(ResponseDto.class)
                .doOnNext(this::saveMovies)
                .collectList();
    }

    /**
     * Processes and saves movie data including cast, versions, and sessions into the database.
     *
     * @param dto the {@link ResponseDto} object containing movie details.
     */
    private void saveMovies(ResponseDto dto) {
        for (MovieDto movieDto : dto.movies()) {
            MovieEntity movie = new MovieEntity();
            movie.setTitle(movieDto.title());
            movie.setTrailerUrl(movieDto.trailer_url());
            movie.setGraphicUrl(movieDto.graphic_url());
            movie.setRuntime(movieDto.runtime());
            movie.setRating(movieDto.rating());
            movie.setFilmHoCode(movieDto.film_HO_code());
            movie.setCorporateFilmId(movieDto.corporate_film_id());
            movie.setSynopsis(movieDto.synopsis());
            movie.setSynopsisAlt(movieDto.synopsis_alt());
            movie.setOpeningDate(movieDto.opening_date());
            movie.setGenre(movieDto.genre());
            movie.setGenre2(movieDto.genre2());
            movie.setGenre3(movieDto.genre3());

            // Save the movie entity to the database
            movieRepository.save(movie);

            // Save each cast member associated with the movie
            for (CastDto castDto : movieDto.cast()) {
                CastEntity castEntity = new CastEntity();
                castEntity.setFirstName(castDto.firstName());
                castEntity.setLastName(castDto.lastName());
                castEntity.setPersonType(castDto.personType());
                castEntity.setMovie(movie);

                castRepository.save(castEntity);
            }

            // Save all movie versions and their associated sessions
            for (MovieVersionDto movieVersionDto : movieDto.movie_versions()) {
                MovieVersionEntity movieVersionEntity = new MovieVersionEntity();
                movieVersionEntity.setFilmHopk(movieVersionDto.film_HOPK());
                movieVersionEntity.setTitle(movieVersionDto.title());
                movieVersionEntity.setFilmHoCode(movieVersionDto.film_HO_code());
                movieVersionEntity.setMovie(movie);

                movieVersionRepository.save(movieVersionEntity);

                // Save each session associated with this version of the movie
                for (SessionDto sessionDto : movieVersionDto.sessions()) {
                    SessionEntity sessionEntity = new SessionEntity();
                    sessionEntity.setShowtime(sessionDto.showtime());
                    sessionEntity.setDay(sessionDto.day());
                    sessionEntity.setHour(sessionDto.hour());
                    sessionEntity.setMovieVersion(movieVersionEntity);

                    sessionRepository.save(sessionEntity);
                }
            }
        }
    }

}
