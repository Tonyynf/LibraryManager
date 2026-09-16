package com.example.LibraryManager.controllers;

import com.example.LibraryManager.models.Livro;
import com.example.LibraryManager.services.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livros")
@RequiredArgsConstructor
public class LivroController {
    private final LivroService livroService;

    @GetMapping
    public ResponseEntity<List<Livro>> getAllLivros(){
        return ResponseEntity.ok(livroService.listarTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Livro> getLivroById(@PathVariable Long id){
        return ResponseEntity.ok(livroService.buscarPorId(id));
    }
    @GetMapping("/{titulo}")
    public ResponseEntity<List<Livro>> getAllLivrosPorTitulo(String titulo){
        return ResponseEntity.ok(livroService.buscarPorTitulo(titulo));
    }
}
