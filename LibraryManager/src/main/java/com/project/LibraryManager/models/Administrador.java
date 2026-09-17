package com.project.LibraryManager.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_administradores")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String keyPass;
}
