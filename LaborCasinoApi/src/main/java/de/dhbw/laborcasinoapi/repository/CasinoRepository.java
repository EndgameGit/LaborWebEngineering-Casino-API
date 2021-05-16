package de.dhbw.laborcasinoapi.repository;

import de.dhbw.laborcasinoapi.model.Casino;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CasinoRepository extends JpaRepository<Casino, Long> {

    List<Casino> findBySpielNameContaining(String spielName);

    List<Casino> findByInBetrieb(boolean inBetrieb);
}