package com.petcare.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.petcare.api.model.Tutor;
import com.petcare.api.repository.TutorRepository;
import com.petcare.api.service.TutorService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TutorServiceImpl implements TutorService {

    private final TutorRepository tutorRepository;

    public TutorServiceImpl(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    @Override
    public Tutor salvar(Tutor tutor) {
        if (tutorRepository.existsByCpf(tutor.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }
        return tutorRepository.save(tutor);
    }

    @Override
    public List<Tutor> listarTodos() {
        return tutorRepository.findAll();
    }

    @Override
    public Optional<Tutor> buscarPorId(Long id) {
        return tutorRepository.findById(id);
    }

    @Override
    public Tutor atualizar(Long id, Tutor tutorAtualizado) {
        Tutor tutorExistente = tutorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tutor não encontrado"));

        if (!tutorExistente.getCpf().equals(tutorAtualizado.getCpf())
                && tutorRepository.existsByCpf(tutorAtualizado.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }

        tutorExistente.setNome(tutorAtualizado.getNome());
        tutorExistente.setCpf(tutorAtualizado.getCpf());
        tutorExistente.setTelefone(tutorAtualizado.getTelefone());
        tutorExistente.setEndereco(tutorAtualizado.getEndereco());

        return tutorRepository.save(tutorExistente);
    }

    @Override
    public void excluir(Long id) {
        if (!tutorRepository.existsById(id)) {
            throw new EntityNotFoundException("Tutor não encontrado");
        }
        tutorRepository.deleteById(id);
    }
}
