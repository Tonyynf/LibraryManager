package com.project.LibraryManager.models;

import jakarta.persistence.*;
import java.time.LocalDate;

public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exemplar_id", nullable = false)
    private Exemplar exemplar;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucaoEfetiva; // null enquanto ativo

    @Enumerated(EnumType.STRING)
    private StatusEmprestimo status;
}
