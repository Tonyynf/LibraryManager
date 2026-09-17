package com.project.LibraryManager.dto;

import jakarta.validation.constraints.*;

public record UsuarioRequestDTO(
        @NotBlank(message = "O nome não pode ser nulo!")
        String nome,

        @NotBlank(message = "O email não pode ser nulo!")
        String email,

        @NotBlank(message = "A senha não pode ser nula!")
        String senha
) {}
