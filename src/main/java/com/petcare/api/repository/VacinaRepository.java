package com.petcare.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.petcare.api.model.Vacina;

@Repository
public interface VacinaRepository extends JpaRepository<Vacina, Long> {

    List<Vacina> findByAnimalId(Long animalId);

    @Query("SELECT v FROM Vacina v WHERE v.animal.id = :animalId ORDER BY v.dataAplicacao DESC")
    List<Vacina> findHistoricoVacinasByAnimal(@Param("animalId") Long animalId);

    @Query("SELECT v FROM Vacina v WHERE LOWER(v.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Vacina> findByNomeContainingIgnoreCase(@Param("nome") String nome);
}
