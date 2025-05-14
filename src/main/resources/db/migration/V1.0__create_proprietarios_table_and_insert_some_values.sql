CREATE TABLE public.proprietarios (
    id UUID PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefone VARCHAR(20)
);

ALTER TABLE public.proprietarios
ADD CONSTRAINT uk_proprietario UNIQUE (email);


INSERT INTO public.proprietarios (id, nome, email, telefone) VALUES
('c270c708-bc41-4697-8db4-5d9217796480', 'João Silva', 'joao.silva@example.com', '(11) 91234-5678'),
('d86cb641-4dbb-4f2b-8a42-f681b11e548e', 'Maria Oliveira', 'maria.oliveira@example.com', '(21) 99876-5432'),
('924ce33f-3fb3-4454-8fa9-f838fa7b132b', 'Carlos Souza', 'carlos.souza@example.com', '(31) 98765-4321'),
('9080a1d8-ad2c-4f81-aa27-bbed6a4e66c4', 'Ana Costa', 'ana.costa@example.com', '(41) 92345-6789'),
('18a277f5-dc0e-465f-bff1-34cbecc618b7', 'Fernanda Lima', 'fernanda.lima@example.com', '(51) 93456-7890');