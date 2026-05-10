package com.wilton.agendador.tarefas.Infrastructute.Entity;

import com.wilton.agendador.tarefas.Infrastructute.enums.StatusNotificEnun;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("tarefa")
public class TarefasEntity {
    @Id
    private String id;
    private String nomeTarefa;
    private String descricaoTarefa;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEvento;
    private String emailUsuario;
    private LocalDateTime dataAlteracao;
    private StatusNotificEnun statusNotificEnun;


}
