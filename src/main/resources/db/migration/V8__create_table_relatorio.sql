CREATE TABLE relatorio (
Id INT AUTO_INCREMENT PRIMARY KEY,
    Descricao VARCHAR(255) NOT NULL,
    Titulo VARCHAR(100) NOT NULL,
    Created_at TIMESTAMP NOT NULL,
    Updated_at TIMESTAMP NOT NULL,
    Deleted_at TIMESTAMP,
    fk_ordem_servico_Id INT NOT NULL,
    FOREIGN KEY (fk_ordem_servico_Id) REFERENCES ordem_servico(Id)
);

