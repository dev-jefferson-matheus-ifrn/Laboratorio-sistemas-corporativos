package br.edu.ifrn.usuariocrud.repositorio;

import br.edu.ifrn.usuariocrud.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsarioRepositorio extends JpaRepository<Usuario, Long> {}
