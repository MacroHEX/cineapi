package net.macrohex.cineapi.mods.api.repository;

import net.macrohex.cineapi.mods.api.model.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
}
