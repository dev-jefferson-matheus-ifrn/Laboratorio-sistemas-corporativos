package br.edu.ifrn.usuariocrud.DTO;

import br.edu.ifrn.usuariocrud.dominio.Prioridade;

public record TaskRequestDTO(String titulo, boolean concluida, Prioridade prioridade, Long iDUsuario) {
}
