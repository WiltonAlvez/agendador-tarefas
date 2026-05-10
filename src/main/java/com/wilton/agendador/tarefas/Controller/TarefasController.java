package com.wilton.agendador.tarefas.Controller;
import com.wilton.agendador.tarefas.Business.TarefasService;
import com.wilton.agendador.tarefas.Business.dto.TarefasDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/tarefas")
@RestController
@RequiredArgsConstructor
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<TarefasDTO> gravarTarefa(@RequestBody TarefasDTO dto,
                                                   @RequestHeader ("Authorization") String token) {
         return ResponseEntity.ok(tarefasService.gravarTarefas(token,dto));

    }
}
