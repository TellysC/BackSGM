package com.exemple.maintenance_system_api.domain.model.dto;

import com.exemple.maintenance_system_api.domain.model.Contato;
import com.exemple.maintenance_system_api.domain.model.Endereco;
import com.exemple.maintenance_system_api.domain.model.Usuario;
import com.exemple.maintenance_system_api.domain.model.enums.Cargo;

public record FuncionarioCreateDTO (String nome, Cargo cargo, String cpf, UsuarioCreateDTO usuarioCreateDTO, EnderecoCreateDTO enderecoCreateDTO, ContatoCreateDTO contatoCreateDTO) {
}
