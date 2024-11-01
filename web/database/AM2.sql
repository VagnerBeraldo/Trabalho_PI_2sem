DROP DATABASE IF EXISTS am2cursos;

CREATE DATABASE IF NOT EXISTS am2cursos CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

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
    senha VARCHAR(100) NOT NULL,
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

Create Table administrador(
    id_admin INT PRIMARY KEY AUTO_INCREMENT,
    nome varchar(50) NOT NULL,
    email varchar(150) NOT NULL,
    senha varchar(50) NOT NULL,
    cargo varchar(50) NOT NULL  
);

INSERT INTO cursos (id_curso, materia) VALUES (NULL, 'HTML');
INSERT INTO cursos (id_curso, materia) VALUES (NULL, 'PHP');
INSERT INTO cursos (id_curso, materia) VALUES (NULL, 'CSS');
INSERT INTO cursos (id_curso, materia) VALUES (NULL, 'JavaScript');
INSERT INTO cursos (id_curso, materia) VALUES (NULL, 'NodeJS');
INSERT INTO cursos (id_curso, materia) VALUES (NULL, 'Java');

INSERT INTO administrador(id_admin, nome, email, senha, cargo) VALUES(NULL, 'admteste', 'adm@gmail.com', 'admin123', 'Administrador');



 /* 
 --Querry para login do usuario e admin 
 select * from users
WHERE email = "taltal@gmai.com" AND senha = "taltal";

 select * from administrador
WHERE email = "taltal@gmai.com" AND senha = "taltal";

-- Querry para retornar o usuario com o endereco dele

select users.id_user ,users.nome ,
endereco_users.id_endereco, endereco_users.cep, endereco_users.rua, endereco_users.numero , endereco_users.bairro,
 endereco_users.complemento, endereco_users.cidade, endereco_users.estado
FROM users inner join endereco_users
ON users.id_user = endereco_users.usuario_id;

select * from users;
select * from endereco_users;
select * from cursos;
select * from contato_users;
select * from administrador;
select * from user_cursos;

 */
 
 

