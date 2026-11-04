package com.petcare.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.petcare.api.model.Atendimento;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

    List<Atendimento> findByAnimalId(Long animalId);

    @Query("SELECT a FROM Atendimento a WHERE a.animal.id = :animalId ORDER BY a.dataAtendimento DESC")
    List<Atendimento> findHistoricoByAnimal(@Param("animalId") Long animalId);

    @Query("SELECT a FROM Atendimento a WHERE LOWER(a.diagnostico) LIKE LOWER(CONCAT('%', :diagnostico, '%'))")
    List<Atendimento> findByDiagnosticoContainingIgnoreCase(@Param("diagnostico") String diagnostico);
}
