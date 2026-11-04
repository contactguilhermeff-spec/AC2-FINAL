package com.petcare.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.petcare.api.model.Animal;
import com.petcare.api.model.Vacina;
import com.petcare.api.repository.AnimalRepository;
import com.petcare.api.repository.VacinaRepository;
import com.petcare.api.service.VacinaService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class VacinaServiceImpl implements VacinaService {

    private final VacinaRepository vacinaRepository;
    private final AnimalRepository animalRepository;

    public VacinaServiceImpl(VacinaRepository vacinaRepository, AnimalRepository animalRepository) {
        this.vacinaRepository = vacinaRepository;
        this.animalRepository = animalRepository;
    }

    @Override
    public Vacina registrar(Vacina vacina) {
        Animal animal = animalRepository.findById(vacina.getAnimal().getId())
                .orElseThrow(() -> new EntityNotFoundException("Animal não encontrado"));

        vacina.setAnimal(animal);
        return vacinaRepository.save(vacina);
    }

    @Override
    public List<Vacina> listarTodos() {
        return vacinaRepository.findAll();
    }

    @Override
    public Optional<Vacina> buscarPorId(Long id) {
        return vacinaRepository.findById(id);
    }

    @Override
    public List<Vacina> buscarPorAnimal(Long animalId) {
        return vacinaRepository.findHistoricoVacinasByAnimal(animalId);
    }

    @Override
    public Vacina atualizar(Long id, Vacina vacinaAtualizada) {
        Vacina vacinaExistente = vacinaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vacina não encontrada"));

        Animal animal = animalRepository.findById(vacinaAtualizada.getAnimal().getId())
                .orElseThrow(() -> new EntityNotFoundException("Animal não encontrado"));

        vacinaExistente.setNome(vacinaAtualizada.getNome());
        vacinaExistente.setDataAplicacao(vacinaAtualizada.getDataAplicacao());
        vacinaExistente.setLote(vacinaAtualizada.getLote());
        vacinaExistente.setAnimal(animal);

        return vacinaRepository.save(vacinaExistente);
    }

    @Override
    public void excluir(Long id) {
        if (!vacinaRepository.existsById(id)) {
            throw new EntityNotFoundException("Vacina não encontrada");
        }
        vacinaRepository.deleteById(id);
    }
}
