package com.project.LibraryManager.dto;

import com.project.LibraryManager.models.Categoria;
import jakarta.validation.constraints.*;

public record LivroRequestDTO(
   @NotBlank(message = "O nome não pode ser nulo!")
    String nome,

   @NotBlank(message = "O titulo não pode ser nulo!")
   String titulo,

   @NotBlank(message = "O nome do autor é obrigatório!")
   String autor,

   String editora,

   int paginas,

   @NotNull(message = "É necessário saber o ano de publicação do livro!")
   int anoPublicacao,

   @NotBlank(message = "A categoria não pode ser nula!")
   Categoria categoria
) {}
