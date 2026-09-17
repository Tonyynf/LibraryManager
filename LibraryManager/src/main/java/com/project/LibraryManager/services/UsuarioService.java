package com.project.LibraryManager.services;

import com.project.LibraryManager.dto.UsuarioRequestDTO;
import com.project.LibraryManager.dto.UsuarioResponseDTO;
import com.project.LibraryManager.models.Usuario;
import com.project.LibraryManager.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository UsuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.UsuarioRepository = usuarioRepository;
    }

    public Usuario buscarPorId(Long id){
        return UsuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public List<Usuario> buscarTodos(){
        return UsuarioRepository.findAll();
    }

    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO usuario){
        //↓ ↓ ↓ Depois criar um package ou arquivo de exceptions ↓ ↓ ↓
        boolean emailExistente = UsuarioRepository.existsByEmail(usuario.email());

        if(emailExistente){
            throw new RuntimeException("Este email já está cadastrado!");
        }

        Usuario usuarioNovo = new Usuario(
                null,
                usuario.nome(),
                usuario.email(),
                usuario.senha()
        );

        return converterParaResponseDto(UsuarioRepository.save(usuarioNovo));
    }

    public UsuarioResponseDTO atualizarUsuario(Long id, UsuarioRequestDTO usuarioAtualizado){
        return UsuarioRepository.findById(id).map(usuarioExistente -> {
                    usuarioExistente.setNome(usuarioAtualizado.nome());
                    usuarioExistente.setEmail(usuarioAtualizado.email());
                    //Talvez essaa alteração de senha precise passar pelo HashCode
                    usuarioExistente.setSenha(usuarioAtualizado.senha());

                    return converterParaResponseDto(UsuarioRepository.save(usuarioExistente));
            //↓ ↓ ↓ Depois criar um package ou arquivo de exceptions ↓ ↓ ↓
        }).orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));
    }

    public void deletarUsuario(Long id){
        if(!UsuarioRepository.existsById(id)){
            //↓ ↓ ↓ Depois criar um package ou arquivo de exceptions ↓ ↓ ↓
            throw new RuntimeException("Não é possivel exclui. Usuário não foi encontrado!");
        }
        UsuarioRepository.deleteById(id);
    }

    private UsuarioResponseDTO converterParaResponseDto(Usuario user) {
        return new UsuarioResponseDTO(
                user.getId(),
                user.getNome(),
                user.getEmail()
        );
    }

}
