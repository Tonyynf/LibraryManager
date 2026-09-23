package com.project.LibraryManager.repositories;


import com.project.LibraryManager.models.Emprestimo;
import com.project.LibraryManager.models.StatusEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    boolean existsByUsuarioIdAndStatus(Long usuarioId, StatusEmprestimo statusEmprestimo);
}

