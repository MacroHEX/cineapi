package net.macrohex.cineapi.mods.api.repository;

import net.macrohex.cineapi.mods.api.model.CastEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CastRepository extends JpaRepository<CastEntity, Long> {
}
