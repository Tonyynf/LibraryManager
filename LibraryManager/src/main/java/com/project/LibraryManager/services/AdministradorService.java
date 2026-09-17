package com.project.LibraryManager.services;

import com.project.LibraryManager.dto.AdministradorRequestDTO;
import com.project.LibraryManager.dto.AdministradorResponseDTO;
import com.project.LibraryManager.models.Administrador;
import com.project.LibraryManager.repositories.AdministradorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdministradorService {
    private final AdministradorRepository administradorRepository;

    public AdministradorService(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    public AdministradorResponseDTO buscarAdministradorPorId(Long id) {
        Administrador admin = administradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador não encontrado com o ID: " + id));
        return converterParaResponseDto(admin);
    }

    public List<AdministradorResponseDTO> buscarTodos() {
        return administradorRepository.findAll()
                .stream()
                .map(this::converterParaResponseDto)
                .toList();
    }

    public AdministradorResponseDTO criarAdministrador(AdministradorRequestDTO dados) {
        Administrador administrador = new Administrador(
                null,
                dados.nome(),
                dados.email(),
                dados.senha(),
                dados.keyPass()
        );
        return converterParaResponseDto(administradorRepository.save(administrador));
    }

    public AdministradorResponseDTO atualizarAdministrador(Long id, AdministradorRequestDTO dados) {
        Administrador administrador = administradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador não encontrado!"));

        administrador.setNome(dados.nome());
        administrador.setEmail(dados.email());
        administrador.setSenha(dados.senha());
        administrador.setKeyPass(dados.keyPass());

        return converterParaResponseDto(administradorRepository.save(administrador));
    }

    public void deletarAdministradorPorId(Long id) {
        if (!administradorRepository.existsById(id)) {
            throw new RuntimeException("Administrador não encontrado!");
        }
        administradorRepository.deleteById(id);
    }

    private AdministradorResponseDTO converterParaResponseDto(Administrador admin) {
        return new AdministradorResponseDTO(
                admin.getId(),
                admin.getNome(),
                admin.getEmail()
        );
    }
}
