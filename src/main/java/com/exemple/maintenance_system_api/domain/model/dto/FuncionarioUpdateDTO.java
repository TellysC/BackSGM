package com.exemple.maintenance_system_api.domain.model.dto;

import com.exemple.maintenance_system_api.domain.model.Contato;
import com.exemple.maintenance_system_api.domain.model.Endereco;
import com.exemple.maintenance_system_api.domain.model.Usuario;

public record FuncionarioUpdateDTO  (String nome, String cargo, String cpf, Usuario usuario, Endereco endereco, Contato contato, ContatoUpdateDTO contatoUpdateDTO, EnderecoUpdateDTO enderecoUpdateDTO, UsuarioUpdateDTO usuarioUpdateDTO) {
}