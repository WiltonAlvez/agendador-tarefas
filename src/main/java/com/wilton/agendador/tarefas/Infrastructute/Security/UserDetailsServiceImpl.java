package com.wilton.agendador.tarefas.Infrastructute.Security;



import com.wilton.agendador.tarefas.Business.dto.UsuarioDTO;
import com.wilton.agendador.tarefas.Infrastructute.client.UsuarioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    @Autowired
    private UsuarioCliente cliente;


    public UserDetails carregaDadosUsuario(String email, String token) {

        UsuarioDTO usuarioDTO = cliente.buscaUsuarioEmail(email, token);
        return User
                .withUsername(usuarioDTO.getEmail()) // Define o nome de usuário como o e-mail
                .password(usuarioDTO.getSenha()) // Define a senha do usuário
                .build();
        }
    }

