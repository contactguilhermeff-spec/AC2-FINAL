package com.petcare.api.service;

import java.util.List;
import java.util.Optional;

import com.petcare.api.model.Atendimento;

public interface AtendimentoService {

    Atendimento registrar(Atendimento atendimento);

    List<Atendimento> listarTodos();

    Optional<Atendimento> buscarPorId(Long id);

    List<Atendimento> buscarPorAnimal(Long animalId);

    Atendimento atualizar(Long id, Atendimento atendimentoAtualizado);

    void excluir(Long id);
}
