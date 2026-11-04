package com.petcare.api.service;

import java.util.List;
import java.util.Optional;

import com.petcare.api.model.Tutor;

public interface TutorService {

    Tutor salvar(Tutor tutor);

    List<Tutor> listarTodos();

    Optional<Tutor> buscarPorId(Long id);

    Tutor atualizar(Long id, Tutor tutorAtualizado);

    void excluir(Long id);
}
