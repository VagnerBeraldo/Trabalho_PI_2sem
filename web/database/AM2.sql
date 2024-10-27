DROP DATABASE IF EXISTS am2cursos;
CREATE DATABASE IF NOT EXISTS am2cursos;
USE am2cursos;

CREATE TABLE cursos(
    id_curso INT PRIMARY KEY AUTO_INCREMENT,
    materia VARCHAR(100) NOT NULL
);

CREATE TABLE users(
    id_user INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    sobrenome VARCHAR(100) NOT NULL,
    nome_social VARCHAR(250),
    cpf VARCHAR(11) NOT NULL,
    data_nascimento DATE NOT NULL,
    email VARCHAR(250) NOT NULL,
    tipo_pagamento VARCHAR(20) NOT NULL,
    UNIQUE(cpf)
);

-- Relação N:N entre cursos e alunos || Integridade Referencial com CASCADE
CREATE TABLE user_cursos (
    id_user INT NOT NULL,
    id_curso INT NOT NULL,
    PRIMARY KEY (id_user, id_curso),
    FOREIGN KEY (id_user) REFERENCES users(id_user) ON DELETE CASCADE,
    FOREIGN KEY (id_curso) REFERENCES cursos(id_curso) ON DELETE CASCADE
);

CREATE TABLE contato_users(
    id_contato INT PRIMARY KEY AUTO_INCREMENT,
    usuario_id INT NOT NULL,
    numero_telefone_celular VARCHAR(11),
    numero_telefone_residencial VARCHAR(8),
    FOREIGN KEY (usuario_id) REFERENCES users(id_user) ON DELETE CASCADE,
    UNIQUE(numero_telefone_celular, numero_telefone_residencial)
);

CREATE TABLE endereco_users(
    id_endereco INT PRIMARY KEY AUTO_INCREMENT,
    usuario_id INT NOT NULL,
    cep VARCHAR(8) NOT NULL,
    rua VARCHAR(100) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    complemento VARCHAR(30),
    bairro VARCHAR(50) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    estado CHAR(2) NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES users(id_user) ON DELETE CASCADE
);

INSERT INTO cursos (id_curso, materia) VALUES (NULL, 'HTML');
INSERT INTO cursos (id_curso, materia) VALUES (NULL, 'PHP');
INSERT INTO cursos (id_curso, materia) VALUES (NULL, 'CSS');
INSERT INTO cursos (id_curso, materia) VALUES (NULL, 'JavaScript');
INSERT INTO cursos (id_curso, materia) VALUES (NULL, 'NodeJS');
INSERT INTO cursos (id_curso, materia) VALUES (NULL, 'Java');
