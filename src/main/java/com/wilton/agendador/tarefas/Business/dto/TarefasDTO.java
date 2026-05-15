package com.wilton.agendador.tarefas.Business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wilton.agendador.tarefas.Infrastructute.enums.StatusNotificEnun;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class TarefasDTO {
    private String id;
    private String nomeTarefa;
    private String descricao;
    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "dd,MM-yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;
    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "dd,MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;
    private String emailUsuario;
    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "dd,MM-yyyy HH:mm:ss")
    private LocalDateTime dataAlteracao;
    private StatusNotificEnun statusNotificEnun;



}
