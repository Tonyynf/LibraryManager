package com.example.LibraryManager.controllers;

import com.example.LibraryManager.models.Administrador;
import com.example.LibraryManager.services.AdministradorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administradores")
@RequiredArgsConstructor
public class AdministradorController {
    private final AdministradorService administradorService;

    @GetMapping
    public ResponseEntity<List<Administrador>> getAllAdministradores() {
        return ResponseEntity.ok(administradorService.buscarTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Administrador> getAdministradorById(@PathVariable Long id) {
        return ResponseEntity.ok(administradorService.buscarAdministradorPorId(id));
    }
    @PostMapping
    public ResponseEntity<Administrador> createAdministrador(@RequestBody Administrador administrador) {
        return ResponseEntity.ok(administradorService.criarAdministrador(administrador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrador> updateAdministrador(@PathVariable Long id,@RequestBody Administrador administrador) {
        return ResponseEntity.ok(administradorService.atualizarAdministrador(id, administrador));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Administrador> deleteAdministradorById(@PathVariable Long id) {
        administradorService.deleteAdministradorPorId(id);
        return ResponseEntity.ok().build();
    }
}
