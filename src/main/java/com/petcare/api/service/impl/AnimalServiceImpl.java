package com.petcare.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.petcare.api.model.Animal;
import com.petcare.api.model.Tutor;
import com.petcare.api.repository.AnimalRepository;
import com.petcare.api.repository.TutorRepository;
import com.petcare.api.service.AnimalService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final TutorRepository tutorRepository;

    public AnimalServiceImpl(AnimalRepository animalRepository, TutorRepository tutorRepository) {
        this.animalRepository = animalRepository;
        this.tutorRepository = tutorRepository;
    }

    @Override
    public Animal salvar(Animal animal) {
        Tutor tutor = tutorRepository.findById(animal.getTutor().getId())
                .orElseThrow(() -> new EntityNotFoundException("Tutor não encontrado"));

        animal.setTutor(tutor);
        return animalRepository.save(animal);
    }

    @Override
    public List<Animal> listarTodos() {
        return animalRepository.findAll();
    }

    @Override
    public Optional<Animal> buscarPorId(Long id) {
        return animalRepository.findById(id);
    }

    @Override
    public List<Animal> buscarPorTutor(Long tutorId) {
        return animalRepository.findByTutorId(tutorId);
    }

    @Override
    public Animal atualizar(Long id, Animal animalAtualizado) {
        Animal animalExistente = animalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Animal não encontrado"));

        Tutor tutor = tutorRepository.findById(animalAtualizado.getTutor().getId())
                .orElseThrow(() -> new EntityNotFoundException("Tutor não encontrado"));

        animalExistente.setNome(animalAtualizado.getNome());
        animalExistente.setEspecie(animalAtualizado.getEspecie());
        animalExistente.setRaca(animalAtualizado.getRaca());
        animalExistente.setDataNascimento(animalAtualizado.getDataNascimento());
        animalExistente.setTutor(tutor);

        return animalRepository.save(animalExistente);
    }

    @Override
    public void excluir(Long id) {
        if (!animalRepository.existsById(id)) {
            throw new EntityNotFoundException("Animal não encontrado");
        }
        animalRepository.deleteById(id);
    }
}
