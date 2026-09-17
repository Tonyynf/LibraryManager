package com.project.LibraryManager.dto;

import com.project.LibraryManager.models.Categoria;

public record LivroResponseDTO(
   Long id,
   String titulo,
   String autor,
   String editora,
   int paginas,
   int anoPublicacao,
   Categoria categoria
) {}
