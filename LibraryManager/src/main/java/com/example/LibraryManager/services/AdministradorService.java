package com.example.LibraryManager.services;

import com.example.LibraryManager.models.Administrador;
import com.example.LibraryManager.repositories.AdministradorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdministradorService {
    private final AdministradorRepository administradorRepository;

    public AdministradorService(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    public Administrador buscarAdministradorPorId(Long id){
        return administradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador não encontrado com o ID: " + id));
    }
    public List<Administrador> buscarTodos(){
        return administradorRepository.findAll();
    }

    public Administrador criarAdministrador(Administrador administrador){
        return administradorRepository.save(administrador);
    }

    public Administrador atualizarAdministrador(Long id, Administrador administradorAtualizado){
        return administradorRepository.findById(id).map(administradorExistente -> {
            administradorExistente.setNome(administradorAtualizado.getNome());
            administradorExistente.setEmail(administradorAtualizado.getEmail());
            //Talvez essaa alteração de senha precise passar pelo HashCode
            administradorExistente.setSenha(administradorAtualizado.getSenha());

            return administradorRepository.save(administradorExistente);
            //↓ ↓ ↓ Depois criar um package ou arquivo de exceptions ↓ ↓ ↓
        }).orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));
    }

    public void deleteAdministradorPorId(Long id){
        administradorRepository.deleteById(id);
    }
}
