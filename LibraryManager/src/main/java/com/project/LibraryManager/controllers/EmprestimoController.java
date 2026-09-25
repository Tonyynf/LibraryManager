package com.project.LibraryManager.controllers;

import com.project.LibraryManager.dto.EmprestimoRequestDTO;
import com.project.LibraryManager.dto.EmprestimoResponseDTO;
import com.project.LibraryManager.models.Emprestimo;
import com.project.LibraryManager.services.EmprestimoService;
import com.project.LibraryManager.services.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/emprestimos")
@RequiredArgsConstructor
public class EmprestimoController {
    private final EmprestimoService emprestimoService;

    @PostMapping
    public ResponseEntity<EmprestimoResponseDTO> emprestar(@RequestBody EmprestimoRequestDTO dto) {
        Emprestimo e = emprestimoService.emprestar(dto.exemplar().getId(), dto.usuario().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(e));
    }

    @PatchMapping("/{id}/devolucao")
    public ResponseEntity<EmprestimoResponseDTO> devolver(@PathVariable Long id) {
        Emprestimo e = emprestimoService.devolver(id);
        return ResponseEntity.ok(mapper.toDTO(e));
    }
}
