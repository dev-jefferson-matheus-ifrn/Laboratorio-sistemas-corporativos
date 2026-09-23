package br.edu.ifrn.usuariocrud.controlador;


import br.edu.ifrn.usuariocrud.DTO.UsuarioRequisisaoDTO;
import br.edu.ifrn.usuariocrud.DTO.UsuarioRespostaDTO;
import br.edu.ifrn.usuariocrud.servico.UsuarioServico;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {

    private final UsuarioServico usuarioServico;


    public UsuarioControlador(UsuarioServico usuarioServico) {
        this.usuarioServico = usuarioServico;
    }


    @GetMapping
    public ResponseEntity<List<UsuarioRespostaDTO>> listar() {
        return ResponseEntity.ok(usuarioServico.listarTodos());
    }

    @PostMapping
    public ResponseEntity<UsuarioRespostaDTO> criarUsuario(@RequestBody UsuarioRequisisaoDTO novoUsuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioServico.criar(novoUsuario));
    }

    @GetMapping("/{id}")
    public Object encontrarUsuaio(@PathVariable Long id) {
        UsuarioRespostaDTO usuarioBuscado = usuarioServico.buscarpPorId(id);

        if(usuarioBuscado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(usuarioBuscado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarUsuaio(@PathVariable Long id) {
        boolean sucesso = usuarioServico.deletarUsuario(id);

        if(sucesso) return ResponseEntity.ok().build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
