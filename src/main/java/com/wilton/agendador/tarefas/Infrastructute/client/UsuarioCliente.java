package com.wilton.agendador.tarefas.Infrastructute.client;

import com.wilton.agendador.tarefas.Business.dto.UsuarioDTO;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioCliente {
    @GetMapping
    UsuarioDTO buscaUsuarioEmail(@RequestParam("email") String email,
                                 @RequestHeader ("Authorization") String token);
}
