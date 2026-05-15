package com.wilton.agendador.tarefas.Business;

import com.wilton.agendador.tarefas.Business.Mapper.TarefasConverter;
import com.wilton.agendador.tarefas.Business.dto.TarefasDTO;
import com.wilton.agendador.tarefas.Infrastructute.Entity.TarefasEntity;
import com.wilton.agendador.tarefas.Infrastructute.Repository.TarefasRepository;
import com.wilton.agendador.tarefas.Infrastructute.Security.JwtUtil;
import com.wilton.agendador.tarefas.Infrastructute.enums.StatusNotificEnun;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class TarefasService {
    private final TarefasRepository tarefaRepository;
    private final TarefasConverter tarefaConverter;
    private final JwtUtil jwtUtil;

    public TarefasDTO gravarTarefas(String token,TarefasDTO dto) {
        String email = jwtUtil.extrairEmaildoToken(token.substring(7));

        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificEnun(StatusNotificEnun.PENDENTE);
        dto.setEmailUsuario(email);
      TarefasEntity entity = tarefaConverter.paraTarefaEntity(dto);


        return tarefaConverter.paraTarefaDTO(
                tarefaRepository.save(entity));
    }

    public List<TarefasDTO> buscarTarefasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
          return  tarefaConverter.paraTarefaDTO(
                  tarefaRepository.findByDataEventoBetween(dataInicial, dataFinal));

    }
   public List<TarefasDTO> buscaTarefasPorEmail(String token) {
        String email = jwtUtil.extrairEmaildoToken(token.substring(7));
        List<TarefasEntity> listaTarefa = tarefaRepository.findByEmailUsuario(email);

        return tarefaConverter.paraTarefaDTO(listaTarefa);
    }

}
