package com.petcare.api.service;

import java.util.List;
import java.util.Optional;

import com.petcare.api.model.Vacina;

public interface VacinaService {

    Vacina registrar(Vacina vacina);

    List<Vacina> listarTodos();

    Optional<Vacina> buscarPorId(Long id);

    List<Vacina> buscarPorAnimal(Long animalId);

    Vacina atualizar(Long id, Vacina vacinaAtualizada);

    void excluir(Long id);
}
