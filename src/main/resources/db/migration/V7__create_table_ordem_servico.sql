CREATE TABLE ordem_servico (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Descricao VARCHAR(255) NOT NULL,
    Status VARCHAR(60) NOT NULL,
    Created_at TIMESTAMP NOT NULL,
    Updated_at TIMESTAMP NOT NULL,
    Deleted_at TIMESTAMP,
    fk_Funcionario_Id INT NOT NULL,
    fk_Equipamento_Id INT NOT NULL,
    FOREIGN KEY (fk_Funcionario_Id) REFERENCES funcionario(Id),
    FOREIGN KEY (fk_Equipamento_Id) REFERENCES equipamento(Id)
);




