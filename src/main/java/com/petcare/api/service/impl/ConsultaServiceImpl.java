package com.petcare.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.petcare.api.model.Animal;
import com.petcare.api.model.Consulta;
import com.petcare.api.model.Veterinario;
import com.petcare.api.repository.AnimalRepository;
import com.petcare.api.repository.ConsultaRepository;
import com.petcare.api.repository.VeterinarioRepository;
import com.petcare.api.service.ConsultaService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ConsultaServiceImpl implements ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final AnimalRepository animalRepository;
    private final VeterinarioRepository veterinarioRepository;

    public ConsultaServiceImpl(ConsultaRepository consultaRepository,
                              AnimalRepository animalRepository,
                              VeterinarioRepository veterinarioRepository) {
        this.consultaRepository = consultaRepository;
        this.animalRepository = animalRepository;
        this.veterinarioRepository = veterinarioRepository;
    }

    @Override
    public Consulta agendar(Consulta consulta) {
        Animal animal = animalRepository.findById(consulta.getAnimal().getId())
                .orElseThrow(() -> new EntityNotFoundException("Animal não encontrado"));

        Veterinario veterinario = veterinarioRepository.findById(consulta.getVeterinario().getId())
                .orElseThrow(() -> new EntityNotFoundException("Veterinário não encontrado"));

        boolean habilitado = veterinario.getEspeciesAtendidas().stream()
                .anyMatch(especie -> especie.equalsIgnoreCase(animal.getEspecie()));

        if (!habilitado) {
            throw new IllegalArgumentException("Veterinário não atende a espécie do animal");
        }

        List<Consulta> conflitos = consultaRepository.findByVeterinarioDataHorario(
                veterinario, consulta.getData(), consulta.getHorario());

        if (!conflitos.isEmpty()) {
            throw new IllegalArgumentException("Veterinário já possui consulta agendada neste horário");
        }

        consulta.setAnimal(animal);
        consulta.setVeterinario(veterinario);

        return consultaRepository.save(consulta);
    }

    @Override
    public List<Consulta> listarTodos() {
        return consultaRepository.findAll();
    }

    @Override
    public Optional<Consulta> buscarPorId(Long id) {
        return consultaRepository.findById(id);
    }

    @Override
    public List<Consulta> buscarPorAnimal(Long animalId) {
        return consultaRepository.findByAnimalId(animalId);
    }

    @Override
    public Consulta cancelar(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada"));

        consulta.setCancelada(true);
        return consultaRepository.save(consulta);
    }

    @Override
    public void excluir(Long id) {
        if (!consultaRepository.existsById(id)) {
            throw new EntityNotFoundException("Consulta não encontrada");
        }
        consultaRepository.deleteById(id);
    }
}
