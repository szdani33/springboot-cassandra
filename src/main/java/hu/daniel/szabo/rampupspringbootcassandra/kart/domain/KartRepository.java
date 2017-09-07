package hu.daniel.szabo.rampupspringbootcassandra.kart.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface KartRepository extends CrudRepository<Kart, UUID> {
}
