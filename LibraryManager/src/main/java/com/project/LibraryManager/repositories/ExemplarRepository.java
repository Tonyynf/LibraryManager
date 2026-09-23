package com.project.LibraryManager.repositories;

import com.project.LibraryManager.models.Exemplar;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExemplarRepository extends JpaRepository<Exemplar, Long>{

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT e FROM Exemplar e WHERE e.livro.id = :livroId AND e.status = 'DISPONIVEL' ")
    List<Exemplar> findDisponiveisComLock(Long livroId);
}
