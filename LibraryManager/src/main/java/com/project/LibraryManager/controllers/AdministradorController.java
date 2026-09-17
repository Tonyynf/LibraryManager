package com.project.LibraryManager.controllers;

import com.project.LibraryManager.dto.AdministradorRequestDTO;
import com.project.LibraryManager.dto.AdministradorResponseDTO;
import com.project.LibraryManager.models.Administrador;
import com.project.LibraryManager.services.AdministradorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administradores")
@RequiredArgsConstructor
public class AdministradorController {
    private final AdministradorService administradorService;

    @GetMapping
    public ResponseEntity<List<AdministradorResponseDTO>> getAllAdministradores() {
        return ResponseEntity.ok(administradorService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministradorResponseDTO> getAdministradorById(@PathVariable Long id) {
        return ResponseEntity.ok(administradorService.buscarAdministradorPorId(id));
    }

    @PostMapping
    public ResponseEntity<AdministradorResponseDTO> createAdministrador(@RequestBody AdministradorRequestDTO administrador) {
        return ResponseEntity.status(HttpStatus.CREATED).body(administradorService.criarAdministrador(administrador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdministradorResponseDTO> updateAdministrador(@PathVariable Long id, @RequestBody AdministradorRequestDTO administrador) {
        return ResponseEntity.ok(administradorService.atualizarAdministrador(id, administrador));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministradorById(@PathVariable Long id) {
        administradorService.deletarAdministradorPorId(id);
        return ResponseEntity.noContent().build();
    }

}
