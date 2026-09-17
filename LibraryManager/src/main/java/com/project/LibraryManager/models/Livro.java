package com.project.LibraryManager.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_livros")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String autor;

    @Column(nullable = true)
    private String editora;

    @Column(nullable = true)
    private int paginas;

    @Column(nullable = false)
    private int anoPublicacao;

    @Column(nullable = false)
    private Categoria categoria;
}
