package com.project.LibraryManager.services;

import com.project.LibraryManager.dto.AdministradorResponseDTO;
import com.project.LibraryManager.dto.LivroRequestDTO;
import com.project.LibraryManager.dto.LivroResponseDTO;
import com.project.LibraryManager.models.Administrador;
import com.project.LibraryManager.models.Livro;
import com.project.LibraryManager.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    public List<LivroResponseDTO> listarTodos(){
       return livroRepository.findAll()
               .stream()
               .map(this::converterParaResponseDto)
               .toList();
    }

    public Livro buscarPorId(Long id){
        return livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }

    public LivroResponseDTO criarLivro(LivroRequestDTO dados){
        Livro livro = new Livro(
                null,
                dados.titulo(),
                dados.autor(),
                dados.editora(),
                dados.paginas(),
                dados.anoPublicacao(),
                dados.categoria()
        );
        return converterParaResponseDto(livroRepository.save(livro));
    }

    public LivroResponseDTO atualizarLivro(Long id, LivroRequestDTO dados) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado!"));

        livro.setTitulo(dados.titulo());
        livro.setAutor(dados.autor());
        livro.setEditora(dados.editora());
        livro.setPaginas(dados.paginas());
        livro.setAnoPublicacao(dados.anoPublicacao());
        livro.setCategoria(dados.categoria());

        return converterParaResponseDto(livroRepository.save(livro));
    }

    public void deletarLivro(Long id){
        if(!livroRepository.existsById(id)){
            throw new RuntimeException("Livro não encontrado!");
        }
        livroRepository.deleteById(id);
    }

    private LivroResponseDTO converterParaResponseDto(Livro livro) {
        return new LivroResponseDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getEditora(),
                livro.getPaginas(),
                livro.getAnoPublicacao(),
                livro.getCategoria()
        );
    }
}
