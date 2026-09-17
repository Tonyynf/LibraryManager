package com.project.LibraryManager.controllers;

import com.project.LibraryManager.dto.LivroRequestDTO;
import com.project.LibraryManager.dto.LivroResponseDTO;
import com.project.LibraryManager.models.Livro;
import com.project.LibraryManager.services.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livros")
@RequiredArgsConstructor
public class LivroController {
    private final LivroService livroService;

    @GetMapping
    public ResponseEntity<List<LivroResponseDTO>> getAllLivros(){
        return ResponseEntity.ok(livroService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> getLivroById(@PathVariable Long id){
        return ResponseEntity.ok(livroService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<LivroResponseDTO> createLivro(@RequestBody LivroRequestDTO livro){
        return ResponseEntity.status(HttpStatus.CREATED).body(livroService.criarLivro(livro));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> updateLivro(@PathVariable Long id, @RequestBody LivroRequestDTO livro) {
        return ResponseEntity.ok(livroService.atualizarLivro(id, livro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivro(@PathVariable Long id) {
        livroService.deletarLivro(id);
        return ResponseEntity.noContent().build();
    }
}
