package net.macrohex.cineapi.mods.api.repository;

import net.macrohex.cineapi.mods.api.model.MovieVersionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieVersionRepository extends JpaRepository<MovieVersionEntity, Long> {
}
