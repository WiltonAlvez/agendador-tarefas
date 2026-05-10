package com.wilton.agendador.tarefas.Infrastructute.Repository;

import com.wilton.agendador.tarefas.Infrastructute.Entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefasRepository extends MongoRepository<TarefasEntity, String> {

}
