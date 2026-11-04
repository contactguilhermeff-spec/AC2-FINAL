package com.petcare.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.petcare.api.model.Animal;
import com.petcare.api.model.Atendimento;
import com.petcare.api.repository.AnimalRepository;
import com.petcare.api.repository.AtendimentoRepository;
import com.petcare.api.service.AtendimentoService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AtendimentoServiceImpl implements AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;
    private final AnimalRepository animalRepository;

    public AtendimentoServiceImpl(AtendimentoRepository atendimentoRepository, AnimalRepository animalRepository) {
        this.atendimentoRepository = atendimentoRepository;
        this.animalRepository = animalRepository;
    }

    @Override
    public Atendimento registrar(Atendimento atendimento) {
        Animal animal = animalRepository.findById(atendimento.getAnimal().getId())
                .orElseThrow(() -> new EntityNotFoundException("Animal não encontrado"));

        atendimento.setAnimal(animal);
        return atendimentoRepository.save(atendimento);
    }

    @Override
    public List<Atendimento> listarTodos() {
        return atendimentoRepository.findAll();
    }

    @Override
    public Optional<Atendimento> buscarPorId(Long id) {
        return atendimentoRepository.findById(id);
    }

    @Override
    public List<Atendimento> buscarPorAnimal(Long animalId) {
        return atendimentoRepository.findHistoricoByAnimal(animalId);
    }

    @Override
    public Atendimento atualizar(Long id, Atendimento atendimentoAtualizado) {
        Atendimento atendimentoExistente = atendimentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Atendimento não encontrado"));

        Animal animal = animalRepository.findById(atendimentoAtualizado.getAnimal().getId())
                .orElseThrow(() -> new EntityNotFoundException("Animal não encontrado"));

        atendimentoExistente.setDiagnostico(atendimentoAtualizado.getDiagnostico());
        atendimentoExistente.setObservacoes(atendimentoAtualizado.getObservacoes());
        atendimentoExistente.setOrientacoes(atendimentoAtualizado.getOrientacoes());
        atendimentoExistente.setDataAtendimento(atendimentoAtualizado.getDataAtendimento());
        atendimentoExistente.setAnimal(animal);

        return atendimentoRepository.save(atendimentoExistente);
    }

    @Override
    public void excluir(Long id) {
        if (!atendimentoRepository.existsById(id)) {
            throw new EntityNotFoundException("Atendimento não encontrado");
        }
        atendimentoRepository.deleteById(id);
    }
}
