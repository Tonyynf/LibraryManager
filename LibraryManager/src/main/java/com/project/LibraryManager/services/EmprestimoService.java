package com.project.LibraryManager.services;


import com.project.LibraryManager.exceptions.BusinessRuleException;
import com.project.LibraryManager.exceptions.ResourceNotFoundException;
import com.project.LibraryManager.models.Emprestimo;
import com.project.LibraryManager.models.Exemplar;
import com.project.LibraryManager.models.StatusEmprestimo;
import com.project.LibraryManager.models.StatusExemplar;
import com.project.LibraryManager.repositories.EmprestimoRepository;
import com.project.LibraryManager.repositories.ExemplarRepository;
import com.project.LibraryManager.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {
    private static final int DIAS_EMPRESTIMO = 14;

    private final ExemplarRepository exemplarRepository;
    private final EmprestimoRepository emprestimoRepository;
    private final UsuarioRepository usuarioRepository;

    public EmprestimoService(ExemplarRepository exemplarRepository, EmprestimoRepository emprestimoRepository, UsuarioRepository usuarioRepository) {
        this.exemplarRepository = exemplarRepository;
        this.emprestimoRepository = emprestimoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Emprestimo emprestar(Long livroId, Long usuarioId) {
        List<Exemplar> disponiveis = exemplarRepository.findDisponiveisComLock(livroId);

        if (disponiveis.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum exemplar disponível para este livro");
        }

        boolean usuarioComAtraso = emprestimoRepository
                .existsByUsuarioIdAndStatus(usuarioId, StatusEmprestimo.ATRASADO);

        if (usuarioComAtraso) {
            throw new BusinessRuleException("Usuário possui empréstimo atrasado");
        }

        Exemplar exemplar = disponiveis.get(0);
        exemplar.setStatus(StatusExemplar.EMPRESTADO);

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setExemplar(exemplar);
        emprestimo.setUsuario(usuarioRepository.getReferenceById(usuarioId));
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataPrevistaDevolucao(LocalDate.now().plusDays(DIAS_EMPRESTIMO));
        emprestimo.setStatus(StatusEmprestimo.ATIVO);

        return emprestimoRepository.save(emprestimo);
    }

}


