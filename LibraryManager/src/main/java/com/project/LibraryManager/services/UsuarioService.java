package com.project.LibraryManager.services;

import com.project.LibraryManager.dto.UsuarioRequestDTO;
import com.project.LibraryManager.dto.UsuarioResponseDTO;
import com.project.LibraryManager.models.Usuario;
import com.project.LibraryManager.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario buscarPorId(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public List<Usuario> buscarTodos(){
        return usuarioRepository.findAll();
    }

    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO usuario){
        //↓ ↓ ↓ Depois criar um package ou arquivo de exceptions ↓ ↓ ↓
        boolean emailExistente = usuarioRepository.existsByEmail(usuario.email());

        if(emailExistente){
            throw new RuntimeException("Este email já está cadastrado!");
        }

        Usuario usuarioNovo = new Usuario(
                null,
                usuario.nome(),
                usuario.email(),
                usuario.senha()
        );

        return converterParaResponseDto(usuarioRepository.save(usuarioNovo));
    }

    public UsuarioResponseDTO atualizarUsuario(Long id, UsuarioRequestDTO usuarioAtualizado){
        return usuarioRepository.findById(id).map(usuarioExistente -> {
                    usuarioExistente.setNome(usuarioAtualizado.nome());
                    usuarioExistente.setEmail(usuarioAtualizado.email());
                    //Talvez essaa alteração de senha precise passar pelo HashCode
                    usuarioExistente.setSenha(usuarioAtualizado.senha());

                    return converterParaResponseDto(usuarioRepository.save(usuarioExistente));
            //↓ ↓ ↓ Depois criar um package ou arquivo de exceptions ↓ ↓ ↓
        }).orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));
    }

    public void deletarUsuario(Long id){
        if(!usuarioRepository.existsById(id)){
            //↓ ↓ ↓ Depois criar um package ou arquivo de exceptions ↓ ↓ ↓
            throw new RuntimeException("Não é possivel exclui. Usuário não foi encontrado!");
        }
        usuarioRepository.deleteById(id);
    }

    private UsuarioResponseDTO converterParaResponseDto(Usuario user) {
        return new UsuarioResponseDTO(
                user.getId(),
                user.getNome(),
                user.getEmail()
        );
    }

}
