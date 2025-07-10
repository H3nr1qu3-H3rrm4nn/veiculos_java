-- Script SQL para criação das tabelas do Sistema de Gerenciamento de Veículos
-- Banco de Dados: MySQL/MariaDB
-- Autor: Sistema de Veículos Java
-- Data: 2024

-- Definir encoding e collation
SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;

-- ========================================
-- 1. TABELA CATEGORIA
-- ========================================
CREATE TABLE IF NOT EXISTS categoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    nome VARCHAR(100) NOT NULL UNIQUE,
    descricao TEXT,
    
    -- Constraints
    CONSTRAINT chk_categoria_nome CHECK (CHAR_LENGTH(nome) >= 2),
    
    -- Índices
    INDEX idx_categoria_nome (nome)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ========================================
-- 2. TABELA PESSOA_FISICA (Classe pai para Cliente e Vendedor)
-- ========================================
CREATE TABLE IF NOT EXISTS pessoa_fisica (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    nome VARCHAR(150) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    rg VARCHAR(20) NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL,
    
    -- Constraints
    CONSTRAINT chk_pf_nome CHECK (CHAR_LENGTH(nome) >= 2),
    CONSTRAINT chk_pf_cpf CHECK (CHAR_LENGTH(cpf) = 14), -- formato: 000.000.000-00
    CONSTRAINT chk_pf_rg CHECK (CHAR_LENGTH(rg) >= 5),
    CONSTRAINT chk_pf_email CHECK (email REGEXP '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'),
    
    -- Índices
    INDEX idx_pf_cpf (cpf),
    INDEX idx_pf_nome (nome),
    INDEX idx_pf_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ========================================
-- 3. TABELA VENDEDOR
-- ========================================
CREATE TABLE IF NOT EXISTS vendedor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    pessoa_fisica_id INT NOT NULL,
    codigo_vendedor VARCHAR(20) NOT NULL UNIQUE,
    salario_base DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    percentual_comissao DECIMAL(5,2) NOT NULL DEFAULT 0.00,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    
    -- Foreign Keys
    CONSTRAINT fk_vendedor_pessoa_fisica 
        FOREIGN KEY (pessoa_fisica_id) REFERENCES pessoa_fisica(id) 
        ON DELETE RESTRICT ON UPDATE CASCADE,
    
    -- Constraints
    CONSTRAINT chk_vendedor_salario CHECK (salario_base >= 0),
    CONSTRAINT chk_vendedor_comissao CHECK (percentual_comissao >= 0 AND percentual_comissao <= 100),
    
    -- Índices
    INDEX idx_vendedor_codigo (codigo_vendedor),
    INDEX idx_vendedor_ativo (ativo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ========================================
-- 4. TABELA CLIENTE
-- ========================================
CREATE TABLE IF NOT EXISTS cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    pessoa_fisica_id INT NOT NULL,
    codigo_cliente VARCHAR(20) NOT NULL UNIQUE,
    data_primeira_compra DATE,
    limite_credito DECIMAL(12,2) NOT NULL DEFAULT 0.00,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    
    -- Foreign Keys
    CONSTRAINT fk_cliente_pessoa_fisica 
        FOREIGN KEY (pessoa_fisica_id) REFERENCES pessoa_fisica(id) 
        ON DELETE RESTRICT ON UPDATE CASCADE,
    
    -- Constraints
    CONSTRAINT chk_cliente_limite CHECK (limite_credito >= 0),
    CONSTRAINT chk_cliente_data_primeira_compra CHECK (data_primeira_compra <= CURDATE()),
    
    -- Índices
    INDEX idx_cliente_codigo (codigo_cliente),
    INDEX idx_cliente_ativo (ativo),
    INDEX idx_cliente_data_primeira_compra (data_primeira_compra)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ========================================
-- 5. TABELA VEICULO
-- ========================================
CREATE TABLE IF NOT EXISTS veiculo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    nome VARCHAR(100) NOT NULL,
    ano INT NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    cor VARCHAR(50) NOT NULL,
    placa VARCHAR(10) NOT NULL UNIQUE,
    unico_dono BOOLEAN NOT NULL DEFAULT FALSE,
    categoria_id INT NOT NULL,
    preco DECIMAL(12,2) DEFAULT 0.00,
    vendido BOOLEAN NOT NULL DEFAULT FALSE,
    
    -- Foreign Keys
    CONSTRAINT fk_veiculo_categoria 
        FOREIGN KEY (categoria_id) REFERENCES categoria(id) 
        ON DELETE RESTRICT ON UPDATE CASCADE,
    
    -- Constraints
    CONSTRAINT chk_veiculo_ano CHECK (ano >= 1900 AND ano <= YEAR(CURDATE()) + 1),
    CONSTRAINT chk_veiculo_nome CHECK (CHAR_LENGTH(nome) >= 2),
    CONSTRAINT chk_veiculo_modelo CHECK (CHAR_LENGTH(modelo) >= 1),
    CONSTRAINT chk_veiculo_cor CHECK (CHAR_LENGTH(cor) >= 2),
    CONSTRAINT chk_veiculo_placa CHECK (CHAR_LENGTH(placa) >= 7),
    CONSTRAINT chk_veiculo_preco CHECK (preco >= 0),
    
    -- Índices
    INDEX idx_veiculo_placa (placa),
    INDEX idx_veiculo_categoria (categoria_id),
    INDEX idx_veiculo_ano (ano),
    INDEX idx_veiculo_vendido (vendido),
    INDEX idx_veiculo_nome_modelo (nome, modelo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ========================================
-- 6. TABELA VENDA_VEICULO
-- ========================================
CREATE TABLE IF NOT EXISTS venda_veiculo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_venda DATE NOT NULL,
    valor_desconto DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    valor_final DECIMAL(12,2) NOT NULL,
    vendedor_id INT NOT NULL,
    cliente_id INT NOT NULL,
    veiculo_id INT NOT NULL,
    observacoes TEXT,
    
    -- Foreign Keys
    CONSTRAINT fk_venda_vendedor 
        FOREIGN KEY (vendedor_id) REFERENCES vendedor(id) 
        ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_venda_cliente 
        FOREIGN KEY (cliente_id) REFERENCES cliente(id) 
        ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_venda_veiculo 
        FOREIGN KEY (veiculo_id) REFERENCES veiculo(id) 
        ON DELETE RESTRICT ON UPDATE CASCADE,
    
    -- Constraints
    CONSTRAINT chk_venda_valor_desconto CHECK (valor_desconto >= 0),
    CONSTRAINT chk_venda_valor_final CHECK (valor_final > 0),
    CONSTRAINT chk_venda_data CHECK (data_venda <= CURDATE()),
    
    -- Constraint única para garantir que um veículo não seja vendido duas vezes
    CONSTRAINT uk_venda_veiculo UNIQUE (veiculo_id),
    
    -- Índices
    INDEX idx_venda_data (data_venda),
    INDEX idx_venda_vendedor (vendedor_id),
    INDEX idx_venda_cliente (cliente_id),
    INDEX idx_venda_valor_final (valor_final)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ========================================
-- TRIGGERS PARA AUTOMAÇÃO
-- ========================================

-- Trigger para marcar veículo como vendido após inserir venda
DELIMITER //
CREATE TRIGGER tr_venda_veiculo_after_insert
    AFTER INSERT ON venda_veiculo
    FOR EACH ROW
BEGIN
    UPDATE veiculo 
    SET vendido = TRUE 
    WHERE id = NEW.veiculo_id;
END //
DELIMITER ;

-- Trigger para desmarcar veículo como vendido após excluir venda
DELIMITER //
CREATE TRIGGER tr_venda_veiculo_after_delete
    AFTER DELETE ON venda_veiculo
    FOR EACH ROW
BEGIN
    UPDATE veiculo 
    SET vendido = FALSE 
    WHERE id = OLD.veiculo_id;
END //
DELIMITER ;

-- Trigger para atualizar data da primeira compra do cliente
DELIMITER //
CREATE TRIGGER tr_cliente_primeira_compra
    AFTER INSERT ON venda_veiculo
    FOR EACH ROW
BEGIN
    UPDATE cliente 
    SET data_primeira_compra = LEAST(COALESCE(data_primeira_compra, NEW.data_venda), NEW.data_venda)
    WHERE id = NEW.cliente_id;
END //
DELIMITER ;

-- ========================================
-- VIEWS ÚTEIS PARA RELATÓRIOS
-- ========================================

-- View para listar vendedores com dados pessoais
CREATE OR REPLACE VIEW vw_vendedores AS
SELECT 
    v.id,
    v.codigo_vendedor,
    pf.nome,
    pf.cpf,
    pf.telefone,
    pf.email,
    v.salario_base,
    v.percentual_comissao,
    v.ativo,
    v.data_cadastro
FROM vendedor v
INNER JOIN pessoa_fisica pf ON v.pessoa_fisica_id = pf.id;

-- View para listar clientes com dados pessoais
CREATE OR REPLACE VIEW vw_clientes AS
SELECT 
    c.id,
    c.codigo_cliente,
    pf.nome,
    pf.cpf,
    pf.telefone,
    pf.email,
    c.limite_credito,
    c.data_primeira_compra,
    c.ativo,
    c.data_cadastro
FROM cliente c
INNER JOIN pessoa_fisica pf ON c.pessoa_fisica_id = pf.id;

-- View para listar veículos com categoria
CREATE OR REPLACE VIEW vw_veiculos AS
SELECT 
    v.id,
    v.nome,
    v.ano,
    v.modelo,
    v.cor,
    v.placa,
    v.unico_dono,
    v.preco,
    v.vendido,
    cat.nome AS categoria_nome,
    v.data_cadastro
FROM veiculo v
INNER JOIN categoria cat ON v.categoria_id = cat.id;

-- View para relatório de vendas
CREATE OR REPLACE VIEW vw_vendas_completas AS
SELECT 
    vv.id,
    vv.data_venda,
    vv.valor_desconto,
    vv.valor_final,
    -- Dados do vendedor
    vend.codigo_vendedor,
    pf_vend.nome AS vendedor_nome,
    -- Dados do cliente
    cli.codigo_cliente,
    pf_cli.nome AS cliente_nome,
    -- Dados do veículo
    veic.nome AS veiculo_nome,
    veic.modelo,
    veic.placa,
    cat.nome AS categoria_nome,
    vv.data_cadastro
FROM venda_veiculo vv
INNER JOIN vendedor vend ON vv.vendedor_id = vend.id
INNER JOIN pessoa_fisica pf_vend ON vend.pessoa_fisica_id = pf_vend.id
INNER JOIN cliente cli ON vv.cliente_id = cli.id
INNER JOIN pessoa_fisica pf_cli ON cli.pessoa_fisica_id = pf_cli.id
INNER JOIN veiculo veic ON vv.veiculo_id = veic.id
INNER JOIN categoria cat ON veic.categoria_id = cat.id;

-- ========================================
-- DADOS INICIAIS (OPCIONAL)
-- ========================================

-- Inserir categorias padrão
INSERT IGNORE INTO categoria (nome, descricao) VALUES
('Sedan', 'Veículos sedan de 4 portas'),
('Hatchback', 'Veículos compactos com porta-malas integrado'),
('SUV', 'Veículos utilitários esportivos'),
('Pickup', 'Caminhonetes e veículos de carga'),
('Motocicleta', 'Motos e similares'),
('Utilitário', 'Veículos comerciais e utilitários');

-- ========================================
-- COMENTÁRIOS FINAIS
-- ========================================

/*
ESTRUTURA DO BANCO DE DADOS:

1. CATEGORIA: Armazena as categorias de veículos
2. PESSOA_FISICA: Classe pai que armazena dados básicos de pessoas
3. VENDEDOR: Herda de PESSOA_FISICA, adiciona dados específicos do vendedor
4. CLIENTE: Herda de PESSOA_FISICA, adiciona dados específicos do cliente
5. VEICULO: Armazena dados dos veículos, referencia CATEGORIA
6. VENDA_VEICULO: Armazena as vendas, referencia VENDEDOR, CLIENTE e VEICULO

RELACIONAMENTOS:
- VENDEDOR -> PESSOA_FISICA (1:1)
- CLIENTE -> PESSOA_FISICA (1:1)
- VEICULO -> CATEGORIA (N:1)
- VENDA_VEICULO -> VENDEDOR (N:1)
- VENDA_VEICULO -> CLIENTE (N:1)
- VENDA_VEICULO -> VEICULO (1:1 - um veículo só pode ser vendido uma vez)

FEATURES IMPLEMENTADAS:
- Triggers para automatizar a marcação de veículos vendidos
- Views para facilitar consultas
- Constraints para garantir integridade dos dados
- Índices para otimizar performance
- Suporte a charset UTF-8 para caracteres especiais
- Dados iniciais para categorias

OBSERVAÇÕES:
- O script foi criado para MySQL/MariaDB
- Todas as tabelas usam InnoDB para suporte a foreign keys
- As datas são armazenadas considerando o timezone do servidor
- Senhas de usuários devem ser implementadas em camada de aplicação
*/
