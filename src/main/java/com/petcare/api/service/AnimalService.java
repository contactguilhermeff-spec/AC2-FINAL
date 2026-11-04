package com.petcare.api.service;

import java.util.List;
import java.util.Optional;

import com.petcare.api.model.Animal;

public interface AnimalService {

    Animal salvar(Animal animal);

    List<Animal> listarTodos();

    Optional<Animal> buscarPorId(Long id);

    List<Animal> buscarPorTutor(Long tutorId);

    Animal atualizar(Long id, Animal animalAtualizado);

    void excluir(Long id);
}
