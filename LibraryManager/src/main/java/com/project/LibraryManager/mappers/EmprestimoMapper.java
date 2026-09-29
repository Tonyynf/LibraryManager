package com.project.LibraryManager.mappers;

import com.project.LibraryManager.dto.EmprestimoResponseDTO;
import com.project.LibraryManager.models.Emprestimo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmprestimoMapper {

    // Converte a entidade Emprestimo para o DTO de resposta
    EmprestimoResponseDTO toDTO(Emprestimo emprestimo);

    // Caso precise fazer o caminho inverso no futuro
    Emprestimo toEntity(EmprestimoResponseDTO dto);
}