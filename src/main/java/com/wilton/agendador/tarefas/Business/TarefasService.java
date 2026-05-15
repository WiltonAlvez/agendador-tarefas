package com.wilton.agendador.tarefas.Business;

import com.wilton.agendador.tarefas.Business.Mapper.TarefaUpdateConverter;
import com.wilton.agendador.tarefas.Business.Mapper.TarefasConverter;
import com.wilton.agendador.tarefas.Business.dto.TarefasDTO;
import com.wilton.agendador.tarefas.Infrastructute.Entity.TarefasEntity;
import com.wilton.agendador.tarefas.Infrastructute.Exceptions.ResourceNotFoundException;
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
    private final TarefaUpdateConverter tarefaUpdateConverter;

    public TarefasDTO gravarTarefas(String token, TarefasDTO dto) {
        String email = jwtUtil.extrairEmaildoToken(token.substring(7));

        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificEnun(StatusNotificEnun.PENDENTE);
        dto.setEmailUsuario(email);
        TarefasEntity entity = tarefaConverter.paraTarefaEntity(dto);


        return tarefaConverter.paraTarefaDTO(
                tarefaRepository.save(entity));
    }

    public List<TarefasDTO> buscarTarefasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return tarefaConverter.paraTarefaDTO(
                tarefaRepository.findByDataEventoBetween(dataInicial, dataFinal));

    }

    public List<TarefasDTO> buscaTarefasPorEmail(String token) {
        String email = jwtUtil.extrairEmaildoToken(token.substring(7));
        List<TarefasEntity> listaTarefa = tarefaRepository.findByEmailUsuario(email);

        return tarefaConverter.paraTarefaDTO(listaTarefa);
    }

    public void deletaTarefaPorID(String id) {
        try {
            tarefaRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao tentar deletar tarefa por ID: " + id, e.getCause());
        }
    }

    public TarefasDTO alteraStatus(StatusNotificEnun status, String id) {
        try {
            TarefasEntity entity = tarefaRepository.findById(id).
                    orElseThrow(() -> new ResourceNotFoundException("Tarefas não encontrata: " + id));

            entity.setStatusNotificEnun(status);
            return tarefaConverter.paraTarefaDTO(tarefaRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("ERRO ao alterar status da tarefa: " + id, e.getCause());
        }
    }

    public TarefasDTO updateDeTarefas(TarefasDTO dto, String id) {
        try {
            TarefasEntity entity = tarefaRepository.findById(id).
                    orElseThrow(() -> new ResourceNotFoundException("Tarefas não encontrata: " + id));
            tarefaUpdateConverter.updateDeTarefas(dto, entity);
            return tarefaConverter.paraTarefaDTO(tarefaRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("ERRO ao alterar status da tarefa: " + id, e.getCause());
        }
    }
}
