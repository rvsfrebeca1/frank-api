CREATE TABLE autuacoes(
  id UUID PRIMARY KEY,
  veiculo_id UUID NOT NULL,
  descricao varchar(255) NOT NULL,
  valor_multa DECIMAL(10,2) NOT NULL,
  data_ocorrencia TIMESTAMP NOT NULL,
  
  CONSTRAINT fk_autuacoes_veiculo FOREIGN KEY (veiculo_id) REFERENCES veiculos(id) 
);