package com.petcare.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.petcare.api.model.Veterinario;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {

    Optional<Veterinario> findByCrmv(String crmv);

    boolean existsByCrmv(String crmv);

    @Query("SELECT v FROM Veterinario v JOIN v.especiesAtendidas e WHERE e = :especie")
    List<Veterinario> findByEspecieAtendida(@Param("especie") String especie);

    @Query("SELECT v FROM Veterinario v WHERE LOWER(v.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Veterinario> findByNomeContainingIgnoreCase(@Param("nome") String nome);
}
