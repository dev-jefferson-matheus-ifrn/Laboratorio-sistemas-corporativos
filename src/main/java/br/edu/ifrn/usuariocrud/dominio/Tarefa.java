package br.edu.ifrn.usuariocrud.dominio;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_tarefas")
@Data
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String titulo;
    private boolean concluida;
    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Usuario usuario;
}
