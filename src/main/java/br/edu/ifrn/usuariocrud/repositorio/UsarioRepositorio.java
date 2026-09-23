package br.edu.ifrn.usuariocrud.repositorio;

import br.edu.ifrn.usuariocrud.dominio.Usuario;

import java.util.List;

public interface UsarioRepositorio {
    Usuario criar(String nome, String email, String cargo);
    List<Usuario> listarTodos();
    Usuario buscarPorId(Long id);
    void atualizarUsuario(Long id, String nome, String email, String cargo);
    void deletarUsuario(Long id);
}
