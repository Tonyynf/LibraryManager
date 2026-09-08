package com.example.LibraryManager.services;

import com.example.LibraryManager.models.Livro;
import com.example.LibraryManager.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    public List<Livro> listarTodos(){
        return livroRepository.findAll();
    }

    public Livro buscarPorId(Long id){
        return livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }

    public List<Livro> buscarPorTitulo(String titulo){
        return livroRepository.findByTitulo(titulo);
    }
}
