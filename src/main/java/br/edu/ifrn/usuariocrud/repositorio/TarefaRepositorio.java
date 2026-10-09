package br.edu.ifrn.usuariocrud.repositorio;


import br.edu.ifrn.usuariocrud.dominio.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepositorio extends JpaRepository<Tarefa, Long> {
}
