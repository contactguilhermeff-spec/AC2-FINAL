package com.petcare.api.service;

import java.util.List;
import java.util.Optional;

import com.petcare.api.model.Consulta;

public interface ConsultaService {

    Consulta agendar(Consulta consulta);

    List<Consulta> listarTodos();

    Optional<Consulta> buscarPorId(Long id);

    List<Consulta> buscarPorAnimal(Long animalId);

    Consulta cancelar(Long id);

    void excluir(Long id);
}
