package com.petcare.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.petcare.api.model.Veterinario;
import com.petcare.api.repository.VeterinarioRepository;
import com.petcare.api.service.VeterinarioService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class VeterinarioServiceImpl implements VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;

    public VeterinarioServiceImpl(VeterinarioRepository veterinarioRepository) {
        this.veterinarioRepository = veterinarioRepository;
    }

    @Override
    public Veterinario salvar(Veterinario veterinario) {
        if (veterinarioRepository.existsByCrmv(veterinario.getCrmv())) {
            throw new IllegalArgumentException("CRMV já cadastrado");
        }
        return veterinarioRepository.save(veterinario);
    }

    @Override
    public List<Veterinario> listarTodos() {
        return veterinarioRepository.findAll();
    }

    @Override
    public Optional<Veterinario> buscarPorId(Long id) {
        return veterinarioRepository.findById(id);
    }

    @Override
    public List<Veterinario> buscarPorEspecie(String especie) {
        return veterinarioRepository.findByEspecieAtendida(especie);
    }

    @Override
    public Veterinario atualizar(Long id, Veterinario veterinarioAtualizado) {
        Veterinario veterinarioExistente = veterinarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Veterinário não encontrado"));

        if (!veterinarioExistente.getCrmv().equals(veterinarioAtualizado.getCrmv())
                && veterinarioRepository.existsByCrmv(veterinarioAtualizado.getCrmv())) {
            throw new IllegalArgumentException("CRMV já cadastrado");
        }

        veterinarioExistente.setNome(veterinarioAtualizado.getNome());
        veterinarioExistente.setCrmv(veterinarioAtualizado.getCrmv());
        veterinarioExistente.setEspeciesAtendidas(veterinarioAtualizado.getEspeciesAtendidas());

        return veterinarioRepository.save(veterinarioExistente);
    }

    @Override
    public void excluir(Long id) {
        if (!veterinarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Veterinário não encontrado");
        }
        veterinarioRepository.deleteById(id);
    }
}
