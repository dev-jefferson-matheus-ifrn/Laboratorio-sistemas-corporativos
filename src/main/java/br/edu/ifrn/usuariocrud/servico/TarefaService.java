package br.edu.ifrn.usuariocrud.servico;


import br.edu.ifrn.usuariocrud.DTO.TaskRequestDTO;
import br.edu.ifrn.usuariocrud.DTO.TaskResponseDTO;
import br.edu.ifrn.usuariocrud.dominio.Prioridade;
import br.edu.ifrn.usuariocrud.dominio.Tarefa;
import br.edu.ifrn.usuariocrud.repositorio.TarefaRepositorio;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TarefaService {

    private final TarefaRepositorio repository;


    public TarefaService(TarefaRepositorio repository) {
        this.repository = repository;
    }

    public TaskResponseDTO criar(TaskRequestDTO dto) {
        String titulo=dto.titulo();
        Tarefa tarefa = new Tarefa();
        tarefa.setConcluida(false);
        tarefa.setPrioridade(Prioridade.BAIXA);
        tarefa.setTitulo(dto.titulo());
        System.out.println("[SERVICE] Validando regra de negócio para: " +
                titulo);
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("O título da tarefa não pode ser vazio.");
        }
        Tarefa salva= repository.save(tarefa);
        return toResponseDTO(salva);
    }

    private TaskResponseDTO toResponseDTO(Tarefa tarefa) {
        return new TaskResponseDTO(
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.isConcluida(),
                tarefa.getPrioridade()
        );
    }

    public List<Tarefa> listar() {
        System.out.println("[SERVICE] Solicitando lista de tarefas ao repository");
        return repository.findAll();
    }
    public Tarefa buscarPorId(Long id) {
        System.out.println("[SERVICE] Processando busca por id: " + id);
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada: " + id));
    }

    public List<Tarefa> listarConcluidos(){
        System.out.println("[SERVICE] Solicitando lista de tarefas concluidas");
        List<Tarefa> tarefas = listar();
        List<Tarefa> tarefasConcluidas=new ArrayList<Tarefa>();

        for(Tarefa tarefa: tarefas){
            if(tarefa.isConcluida()){
                tarefasConcluidas.add(tarefa);
            }
        }
        return tarefasConcluidas;

    }

    public TaskResponseDTO atualizar(Long id, TaskRequestDTO corpo) {
        Tarefa tarefa=buscarPorId(id);
        tarefa.setTitulo(corpo.titulo());
        tarefa.setConcluida(corpo.concluida());
        tarefa.setPrioridade(corpo.prioridade());
        return toResponseDTO(repository.save(tarefa));
    }
}
