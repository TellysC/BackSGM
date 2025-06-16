CREATE TABLE funcionarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cargo ENUM('ADMINISTRADOR', 'TECNICO', 'CLIENTE') NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP ,
    deleted_at TIMESTAMP,
    fk_Usuarios_Id INT NOT NULL,
    fk_Contatos_Id INT NOT NULL,
    FOREIGN KEY (fk_Usuarios_Id) REFERENCES usuarios (id),
    FOREIGN KEY (fk_Enderecos_Id) REFERENCES enderecos(id),
    FOREIGN KEY (fk_Contatos_Id) REFERENCES contatos(id)

);
