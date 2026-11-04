package com.petcare.api.service;

import java.util.List;
import java.util.Optional;

import com.petcare.api.model.Veterinario;

public interface VeterinarioService {

    Veterinario salvar(Veterinario veterinario);

    List<Veterinario> listarTodos();

    Optional<Veterinario> buscarPorId(Long id);

    List<Veterinario> buscarPorEspecie(String especie);

    Veterinario atualizar(Long id, Veterinario veterinarioAtualizado);

    void excluir(Long id);
}
