package com.wilton.agendador.tarefas.Controller;
import com.wilton.agendador.tarefas.Business.TarefasService;
import com.wilton.agendador.tarefas.Business.dto.TarefasDTO;
import com.wilton.agendador.tarefas.Infrastructute.enums.StatusNotificEnun;
import lombok.RequiredArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/tarefas")
@RestController
@RequiredArgsConstructor
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<TarefasDTO> gravarTarefa(@RequestBody TarefasDTO dto,
                                                   @RequestHeader("Authorization") String token) {


        return ResponseEntity.ok(tarefasService.gravarTarefas(token, dto));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTO>> buscarListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal) {
        return ResponseEntity.ok(tarefasService.buscarTarefasPorPeriodo(dataInicial, dataFinal));
    }
    @GetMapping
    public ResponseEntity <List<TarefasDTO>> buscaTarefasPorEmail(@RequestHeader("Authorization")  String token) {
        List<TarefasDTO> tarefas = tarefasService.buscaTarefasPorEmail(token);
    return ResponseEntity.ok(tarefas);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletaTarefaPorID (@RequestParam("id")String id){
        tarefasService.deletaTarefaPorID(id);
        return ResponseEntity.ok().build();

    }
    @PatchMapping
    public ResponseEntity<TarefasDTO> alteraStatusNotificacao(@RequestParam("status")StatusNotificEnun status,
                                                              @RequestParam ("id") String id){
        return ResponseEntity.ok(tarefasService.alteraStatus(status, id));
    }
    @PutMapping
    public ResponseEntity<TarefasDTO> upadateTarefas(@RequestBody TarefasDTO dto, @RequestParam("id") String id){
    return ResponseEntity.ok(tarefasService.updateDeTarefas(dto, id));

    }



}
