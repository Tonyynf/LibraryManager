package com.example.LibraryManager.services;

import com.example.LibraryManager.models.Usuario;
import com.example.LibraryManager.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository UsuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.UsuarioRepository = usuarioRepository;
    }

    public Optional<Usuario> buscarPorId(Long id){
        return UsuarioRepository.findById(id);
    }

    public List<Usuario> buscarTodos(){
        return UsuarioRepository.findAll();
    }

    public Usuario criarUsuario(Usuario usuario){
        //↓ ↓ ↓ Depois criar um package ou arquivo de exceptions ↓ ↓ ↓
        boolean emailExistente = UsuarioRepository.existsByEmail(usuario.getEmail());

        if(emailExistente){
            throw new RuntimeException("Este email já está cadastrado!");
        }

        return UsuarioRepository.save(usuario);
    }

    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado){
        return UsuarioRepository.findById(id).map(usuarioExistente -> {
                    usuarioExistente.setNome(usuarioAtualizado.getNome());
                    usuarioExistente.setEmail(usuarioAtualizado.getEmail());
                    //Talvez essaa alteração de senha precise passar pelo HashCode
                    usuarioExistente.setSenha(usuarioAtualizado.getSenha());

                    return UsuarioRepository.save(usuarioExistente);
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
}
