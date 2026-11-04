package com.petcare.api.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.petcare.api.model.Consulta;
import com.petcare.api.model.Veterinario;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByAnimalId(Long animalId);

    List<Consulta> findByVeterinarioId(Long veterinarioId);

    @Query("SELECT c FROM Consulta c WHERE c.veterinario = :veterinario AND c.data = :data AND c.horario = :horario AND c.cancelada = false")
    List<Consulta> findByVeterinarioDataHorario(@Param("veterinario") Veterinario veterinario,
                                              @Param("data") LocalDate data,
                                              @Param("horario") LocalTime horario);

    @Query("SELECT c FROM Consulta c WHERE c.animal.id = :animalId AND c.cancelada = false ORDER BY c.data DESC, c.horario DESC")
    List<Consulta> findAgendamentosAtivosByAnimal(@Param("animalId") Long animalId);
}
