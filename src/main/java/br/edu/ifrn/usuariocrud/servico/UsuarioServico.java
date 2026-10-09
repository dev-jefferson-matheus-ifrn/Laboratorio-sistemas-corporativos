package br.edu.ifrn.usuariocrud.servico;

import br.edu.ifrn.usuariocrud.DTO.UsuarioRequisisaoDTO;
import br.edu.ifrn.usuariocrud.DTO.UsuarioRespostaDTO;
import br.edu.ifrn.usuariocrud.dominio.Usuario;
import br.edu.ifrn.usuariocrud.repositorio.UsarioRepositorio;
import br.edu.ifrn.usuariocrud.repositorio.UsuarioRepositorioEmMemoria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServico {

    private final UsarioRepositorio usuarioRepositorio;

    public UsuarioServico(UsarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }


    public List<UsuarioRespostaDTO> listarTodos() {
        return usuarioRepositorio.findAll().stream().map(this::corverterEmUsuarioRespostaDTO).toList();
    }

    public UsuarioRespostaDTO criar(UsuarioRequisisaoDTO usuarioRequisisao) {
        if(usuarioRequisisao.email().isEmpty() || usuarioRequisisao.nome().isEmpty()) {
            throw  new IllegalArgumentException("Os campos de email e nome devem ser preenchidos.");
        }

        Usuario novoUsuario = new Usuario();

        novoUsuario.setNome(usuarioRequisisao.nome());
        novoUsuario.setEmail(usuarioRequisisao.email());
        novoUsuario.setCargo(usuarioRequisisao.cargo());

        UsuarioRespostaDTO usuarioCriado = corverterEmUsuarioRespostaDTO(usuarioRepositorio.save(novoUsuario));

        return usuarioCriado;

    }

    public UsuarioRespostaDTO buscarpPorId(Long id) {
        Usuario usuarioBuscado = usuarioRepositorio.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado"));

        return corverterEmUsuarioRespostaDTO(usuarioBuscado);
    }

    public UsuarioRespostaDTO atualizarUsuario(Long id) {
        Usuario usuarioAchado = usuarioRepositorio.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado"));

        return corverterEmUsuarioRespostaDTO(usuarioAchado);
    }

    public boolean deletarUsuario(Long id) {
        Usuario usuarioAchado = usuarioRepositorio.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado"));

        if(usuarioAchado == null) {
            return false;
        }

        usuarioRepositorio.delete(usuarioAchado);
        return true;
    }

    private UsuarioRespostaDTO corverterEmUsuarioRespostaDTO(Usuario usuario) {
        if(usuario == null) return null;
        return new UsuarioRespostaDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getCargo());
    }
}
