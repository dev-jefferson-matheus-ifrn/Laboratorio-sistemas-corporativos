package br.edu.ifrn.usuariocrud.DTO;

import br.edu.ifrn.usuariocrud.dominio.Prioridade;

public record TaskResponseDTO(
        Long id,
        String titulo,
        boolean concluida,
        Prioridade prioridade) {}
