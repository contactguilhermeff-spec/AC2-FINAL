package com.petcare.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.petcare.api.model.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> findByTutorId(Long tutorId);

    @Query("SELECT a FROM Animal a WHERE a.tutor.id = :tutorId AND a.especie = :especie")
    List<Animal> findByTutorAndEspecie(@Param("tutorId") Long tutorId, @Param("especie") String especie);

    @Query("SELECT a FROM Animal a WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Animal> findByNomeContainingIgnoreCase(@Param("nome") String nome);
}
