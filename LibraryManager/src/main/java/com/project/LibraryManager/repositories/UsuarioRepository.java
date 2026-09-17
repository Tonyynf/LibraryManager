package com.project.LibraryManager.repositories;

import com.project.LibraryManager.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    boolean existsByEmail(String email);
}
