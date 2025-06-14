package com.exemple.maintenance_system_api.domain.model.dto;

import com.exemple.maintenance_system_api.domain.model.Contato;
import com.exemple.maintenance_system_api.domain.model.Endereco;
import com.exemple.maintenance_system_api.domain.model.Usuario;

public record FuncionarioCreateDTO (String nome, String cargo, String cpf, UsuarioCreateDTO usuarioCreateDTO, EnderecoCreateDTO enderecoCreateDTO, ContatoCreateDTO contatoCreateDTO) {
}
