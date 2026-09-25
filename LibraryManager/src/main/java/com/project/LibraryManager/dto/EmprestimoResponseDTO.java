package com.project.LibraryManager.dto;

import com.project.LibraryManager.models.Exemplar;
import com.project.LibraryManager.models.StatusEmprestimo;
import com.project.LibraryManager.models.Usuario;

import java.time.LocalDate;

public record EmprestimoResponseDTO(
    Exemplar exemplar,
    Usuario usuario,
    LocalDate dataEmprestimo,
    LocalDate dataPrevistaDevolucao,
    LocalDate dataDevolucaoEfetiva,
    StatusEmprestimo status
) {}
