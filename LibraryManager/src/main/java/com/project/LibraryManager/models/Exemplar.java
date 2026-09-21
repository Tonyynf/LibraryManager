package com.project.LibraryManager.models;

import jakarta.persistence.*;

@Entity
public class Exemplar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;

    @Enumerated(EnumType.STRING)
    private StatusExemplar status;

    private String codigoTombamento;
}
