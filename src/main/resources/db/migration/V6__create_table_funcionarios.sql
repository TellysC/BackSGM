CREATE TABLE funcionarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cargo VARCHAR(55) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL,
    update_at TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP,
    fk_Contatos_Id INT NOT NULL,
    fk_Enderecos_Id INT NOT NULL,
    fk_Usuarios_Id INT NOT NULL,
    FOREIGN KEY (fk_Contatos_Id) REFERENCES contatos (Id),
    FOREIGN KEY (fk_Enderecos_Id) REFERENCES enderecos (Id),
    FOREIGN KEY (fk_Usuarios_Id) REFERENCES contatos (Id)

);
