package com.project.LibraryManager.dto;

import com.project.LibraryManager.models.Categoria;
import com.project.LibraryManager.models.Exemplar;
import com.project.LibraryManager.models.StatusEmprestimo;
import com.project.LibraryManager.models.Usuario;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record EmprestimoRequestDTO(
        @NotBlank(message = "O nome do exemplar não pode ser nulo!")
        Exemplar exemplar,

        @NotBlank(message = "O nome do usuario não pode ser nulo!")
        Usuario usuario,

        @NotNull(message = "É necessário registrar a data de empréstimo!")
        LocalDate dataEmprestimo,

        @NotNull(message = "É necessário registrar a data prevista de devolução!")
        LocalDate dataPrevistaDevolucao,

        @NotNull(message = "É necessário registrar a data da devolução!")
        LocalDate dataDevolucaoEfetiva,

        @NotBlank(message = "É necessário registrar o status do empréstimo!")
        StatusEmprestimo  status
) {}
