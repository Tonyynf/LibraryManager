package com.example.LibraryManager.services;

import com.example.LibraryManager.models.Administrador;
import com.example.LibraryManager.repositories.AdministradorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdministradorService {
    private final AdministradorRepository administradorRepository;

    public AdministradorService(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    public Optional<Administrador> buscarAdministradorPorId(Long id){
        return administradorRepository.findById(id);
    }
    public List<Administrador> buscarTodos(){
        return administradorRepository.findAll();
    }

    public void deleteAdministradorPorId(Long id){
        administradorRepository.deleteById(id);
    }
}
