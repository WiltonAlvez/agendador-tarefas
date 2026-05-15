package com.wilton.agendador.tarefas.Business.Mapper;


import com.wilton.agendador.tarefas.Business.dto.TarefasDTO;
import com.wilton.agendador.tarefas.Infrastructute.Entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.awt.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "dataEvento", target = "dataEvento")
    @Mapping(source = "dataCriacao", target = "dataCriacao")

    TarefasEntity paraTarefaEntity(TarefasDTO dto);

    TarefasDTO paraTarefaDTO(TarefasEntity entity);


    List<TarefasEntity> paraTarefaEntity(List<TarefasDTO> dtos);

    List<TarefasDTO> paraTarefaDTO(List<TarefasEntity> entitys);
}
