package com.project.LibraryManager.dto;

import jakarta.validation.constraints.*;

public record AdministradorRequestDTO(
        @NotBlank(message = "O nome não pode ser nulo!")
        String nome,

        @NotBlank(message = "O email não pode ser nulo!")
        String email,

        @NotBlank(message = "A senha não pode ser nula!")
        String senha,

        @NotBlank(message = "É Obrigatorio possuir uma KeyPass!")
        String keyPass
) {}
