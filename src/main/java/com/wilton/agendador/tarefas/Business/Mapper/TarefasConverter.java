package com.wilton.agendador.tarefas.Business.Mapper;


import com.wilton.agendador.tarefas.Business.dto.TarefasDTO;
import com.wilton.agendador.tarefas.Infrastructute.Entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    TarefasEntity paraTarefaEntity(TarefasDTO dto);

    TarefasDTO paraTarefaDTO(TarefasEntity entity);
}
