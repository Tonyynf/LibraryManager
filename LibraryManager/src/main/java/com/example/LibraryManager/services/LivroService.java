package com.example.LibraryManager.services;

import com.example.LibraryManager.models.Livro;
import com.example.LibraryManager.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {
    @Autowired
    private LivroRepository LivroRepository;

    public List<Livro> listarTodos(){
        return LivroRepository.findAll();
    }

    public Livro buscarPorId(Long id){
        return LivroRepository.findById(id).get();
    }

    public Livro buscarPorTitulo(String titulo){
        return LivroRepository.findByTitulo(titulo).get();
    }



}
