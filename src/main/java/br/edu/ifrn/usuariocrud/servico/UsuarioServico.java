package br.edu.ifrn.usuariocrud.servico;

import br.edu.ifrn.usuariocrud.DTO.UsuarioRequisisaoDTO;
import br.edu.ifrn.usuariocrud.DTO.UsuarioRespostaDTO;
import br.edu.ifrn.usuariocrud.dominio.Usuario;
import br.edu.ifrn.usuariocrud.repositorio.UsuarioRepositorioEmMemoria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServico {

    private final UsuarioRepositorioEmMemoria usuarioRepositorioEmMemoria;

    public UsuarioServico(UsuarioRepositorioEmMemoria usuarioRepositorioEmMemoria) {
        this.usuarioRepositorioEmMemoria = usuarioRepositorioEmMemoria;
    }

    public List<UsuarioRespostaDTO> listarTodos() {
        return usuarioRepositorioEmMemoria.listarTodos().stream().map(this::corverterEmUsuarioRespostaDTO).toList();
    }

    public UsuarioRespostaDTO criar(UsuarioRequisisaoDTO usuarioRequisisao) {
        if(usuarioRequisisao.email().isEmpty() || usuarioRequisisao.nome().isEmpty()) {
            throw  new IllegalArgumentException("Os campos de email e nome devem ser preenchidos.");
        }

        UsuarioRespostaDTO usuarioCriado = corverterEmUsuarioRespostaDTO(usuarioRepositorioEmMemoria.criar(usuarioRequisisao.nome(),usuarioRequisisao.email(),usuarioRequisisao.cargo()));

        return usuarioCriado;

    }

    public UsuarioRespostaDTO buscarpPorId(Long id) {
        Usuario usuarioBuscado = usuarioRepositorioEmMemoria.buscarPorId(id);

        return corverterEmUsuarioRespostaDTO(usuarioBuscado);
    }

    public UsuarioRespostaDTO atualizarUsuario(Long id) {
        Usuario usuarioAchado = usuarioRepositorioEmMemoria.buscarPorId(id);

        return corverterEmUsuarioRespostaDTO(usuarioAchado);
    }

    public boolean deletarUsuario(Long id) {
        Usuario usuarioAchado = usuarioRepositorioEmMemoria.buscarPorId(id);

        if(usuarioAchado == null) {
            return false;
        }

        usuarioRepositorioEmMemoria.deletarUsuario(id);
        return true;
    }

    private UsuarioRespostaDTO corverterEmUsuarioRespostaDTO(Usuario usuario) {
        if(usuario == null) return null;
        return new UsuarioRespostaDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getCargo());
    }
}
