package br.edu.ifrn.usuariocrud.repositorio;

import br.edu.ifrn.usuariocrud.dominio.Usuario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UsuarioRepositorioEmMemoria implements UsarioRepositorio {
    private static final Map<Long,Usuario> BANCO_EM_MEMORIS = new LinkedHashMap<>();
    private static final AtomicLong sequenciaID = new AtomicLong();

    @Override
    public Usuario criar(String nome, String email, String cargo) {
        Usuario novoUsuario = new Usuario(sequenciaID.incrementAndGet(), nome, email, cargo);
        BANCO_EM_MEMORIS.put(novoUsuario.getId(),novoUsuario);
        return novoUsuario;
    }

    @Override
    public List<Usuario> listarTodos() {

        List<Usuario> todosUsuarios = new ArrayList<>(BANCO_EM_MEMORIS.values());

        return todosUsuarios;
    }

    @Override
    public  Usuario buscarPorId(Long id) {
        Usuario usuarioBuscado = BANCO_EM_MEMORIS.get(id);

        return usuarioBuscado;
    }

    @Override
    public void atualizarUsuario(Long id, String nome, String email, String cargo) {
        Usuario usuarioAchado = buscarPorId(id);
        usuarioAchado.setCargo(cargo);
        usuarioAchado.setEmail(email);
        usuarioAchado.setNome(nome);
    }

    @Override
    public void deletarUsuario(Long id) {
        Usuario usuarioEncontrado = buscarPorId(id);
        BANCO_EM_MEMORIS.remove(id);
    }
}
