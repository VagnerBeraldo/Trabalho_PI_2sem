
Drop database if exists am2cursos;

Create database if not exists am2cursos;

use am2cursos;


CREATE TABLE cursos(
    id_curso int PRIMARY KEY AUTO_INCREMENT,
    materia varchar(100) not null
);


CREATE TABLE users(
    id_user INT PRIMARY KEY AUTO_INCREMENT,
    nome varchar(50) not null,
    sobrenome varchar(100) not null,
    nome_social varchar(250),
    cpf varchar(11) not null,
    data_nascimento DATE not null,
    email varchar(250) not null,
    tipo_pagamento varchar(20) not null,
    UNIQUE(cpf)
);

-- relacao N : N entre cursos e alunos || Integridade Referencial com CASCADE 
CREATE TABLE user_cursos (
    id_user INT NOT NULL,
    id_curso INT NOT NULL,
    PRIMARY KEY (id_user, id_curso),
    FOREIGN KEY (id_user) REFERENCES users(id_user) ON DELETE CASCADE, 
    FOREIGN KEY (id_curso) REFERENCES cursos(id_curso) ON DELETE CASCADE
);


CREATE TABLE contato_users(
    id_contato INT PRIMARY KEY AUTO_INCREMENT,
    usuario_id int not null, 
    numero_telefone_celular varchar(11),
    numero_telefone_residencial varchar(8),
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

INSERT INTO cursos VALUES(NULL,"HTML");
INSERT INTO cursos VALUES(NULL,"PHP");
INSERT INTO cursos VALUES(NULL,"CSS");
INSERT INTO cursos VALUES(NULL,"JAVASCRIPT");
INSERT INTO cursos VALUES(NULL,"NODEJS");
INSERT INTO cursos VALUES(NULL,"JAVA");







