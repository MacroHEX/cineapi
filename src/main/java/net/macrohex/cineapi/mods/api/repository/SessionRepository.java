package net.macrohex.cineapi.mods.api.repository;

import net.macrohex.cineapi.mods.api.model.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<SessionEntity, Long> {
}
