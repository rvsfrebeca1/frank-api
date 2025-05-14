CREATE TABLE public.veiculos (
    id UUID PRIMARY KEY,
    proprietario_id UUID NOT NULL,
    marca VARCHAR(20) NOT NULL,
    modelo VARCHAR(20) NOT NULL,
    placa VARCHAR(7) NOT NULL UNIQUE,
    status varchar(20) NOT NULL,
    data_cadastro TIMESTAMP NOT NULL,
    data_apreensao TIMESTAMP,

    CONSTRAINT fk_veiculo_proprietario FOREIGN KEY (proprietario_id) REFERENCES proprietarios(id),
    CONSTRAINT uk_veiculo UNIQUE (placa)
);

INSERT INTO public.veiculos (
    id,
    proprietario_id,
    marca,
    modelo,
    placa,
    status,
    data_cadastro,
    data_apreensao
) VALUES
('d7b93579-97b0-40a7-8b35-afb45ee0c154', 'c270c708-bc41-4697-8db4-5d9217796480', 'Toyota', 'Yaris', 'FGA1B23', 'Ativo', '2025-04-10 10:00:00', NULL),
('bdc09968-661a-41e3-9650-789955d967e0', 'd86cb641-4dbb-4f2b-8a42-f681b11e548e', 'Volkswagen', 'T-Cross', 'HGB2C34', 'Apreendido', '2025-03-15 08:45:00', '2025-04-05 11:00:00'),
('51725011-d441-44c6-b9bd-8adc676ac22b', '924ce33f-3fb3-4454-8fa9-f838fa7b132b', 'Chevrolet', 'Tracker', 'JKC3D45', 'Ativo', '2025-04-01 14:30:00', NULL),
('9450001c-c1d5-4095-8ee2-484b729b4b70', '9080a1d8-ad2c-4f81-aa27-bbed6a4e66c4', 'Fiat', 'Pulse', 'LMD4E56', 'Ativo', '2025-02-28 13:10:00', NULL),
('dc428a85-0ade-455e-ad51-6b2499261773', '18a277f5-dc0e-465f-bff1-34cbecc618b7', 'Renault', 'Captur', 'NEF5F67', 'Apreendido', '2025-01-18 16:20:00', '2025-03-02 09:30:00');