package com.project.LibraryManager.repositories;

import com.project.LibraryManager.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{
    List<Livro> findByTitulo(String titulo);
}
