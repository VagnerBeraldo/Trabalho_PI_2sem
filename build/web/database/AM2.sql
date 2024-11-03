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
    nome_social VARCHAR(250) NULL,
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
    numero_telefone_celular VARCHAR(11) NULL,
    numero_telefone_residencial VARCHAR(11) NULL,
    FOREIGN KEY (usuario_id) REFERENCES users(id_user) ON DELETE CASCADE,
    UNIQUE(numero_telefone_celular, numero_telefone_residencial)
);

CREATE TABLE endereco_users(
    id_endereco INT PRIMARY KEY AUTO_INCREMENT,
    usuario_id INT NOT NULL,
    cep VARCHAR(8) NOT NULL,
    rua VARCHAR(100) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    complemento VARCHAR(30) NULL,
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
INSERT INTO cursos (id_curso, materia) VALUES (NULL, 'JAVASCRIPT');
INSERT INTO cursos (id_curso, materia) VALUES (NULL, 'NODEJS');
INSERT INTO cursos (id_curso, materia) VALUES (NULL, 'JAVA');

INSERT INTO administrador(id_admin, nome, email, senha, cargo) VALUES(NULL, 'admteste', 'adm@gmail.com', 'admin123', 'Administrador');




 /* 



  //Querie para caputurar todos os dados para atualizar na adm dps
 select users.id_user,  users.nome, users.sobrenome, users.nome_social, users.cpf, users.data_nascimento, users.senha, users.email,
endereco_users.cep ,endereco_users.rua,  endereco_users.numero , endereco_users.complemento, endereco_users.bairro, endereco_users.cidade, endereco_users.estado,
contato_users.numero_telefone_celular, contato_users.numero_telefone_residencial,
cursos.materia,
users.tipo_pagamento
FROM users 
LEFT JOIN 
    endereco_users  ON users.id_user = endereco_users.usuario_id
LEFT JOIN 
    contato_users  ON users.id_user = contato_users.usuario_id
LEFT JOIN 
    user_cursos  ON users.id_user = user_cursos.id_user
LEFT JOIN 
    cursos ON user_cursos.id_curso = cursos.id_curso
WHERE
	users.id_user = 1;
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

//Campos telefone e celular tambem aceitam nulo caso nao botem nada
ALTER TABLE contato_users MODIFY numero_telefone_celular VARCHAR(11) NULL;
ALTER TABLE contato_users MODIFY numero_telefone_residencial VARCHAR(11) NULL;

select * from users;
select * from endereco_users;
select * from cursos;
select * from contato_users;
select * from administrador;
select * from user_cursos;




SELECT 
    u.id_user,
    u.nome,
    u.sobrenome,
    u.nome_social,
    u.cpf,
    u.data_nascimento,
    u.senha,
    u.email,
    u.tipo_pagamento,
    e.rua,
    e.bairro,
    e.numero,
    e.cep,
    e.estado,
    e.cidade,
    c.numero_telefone_celular,
    c.numero_telefone_residencial,
    cu.materia AS curso
FROM 
    users u
LEFT JOIN 
    endereco_users e ON u.id_user = e.usuario_id
LEFT JOIN 
    contato_users c ON u.id_user = c.usuario_id
LEFT JOIN 
    user_cursos uc ON u.id_user = uc.id_user
LEFT JOIN 
    cursos cu ON uc.id_curso = cu.id_curso
WHERE 
    u.id_user = 1;
    
select users.id_user,  users.nome, users.sobrenome, users.nome_social, users.data_nascimento, users.email, users.tipo_pagamento,
endereco_users.rua, endereco_users.bairro, endereco_users.numero , endereco_users.cep , endereco_users.estado, endereco_users.cidade,
contato_users.numero_telefone_celular, contato_users.numero_telefone_residencial,
cursos.materia
FROM users 
LEFT JOIN 
    endereco_users  ON users.id_user = endereco_users.usuario_id
LEFT JOIN 
    contato_users  ON users.id_user = contato_users.usuario_id
LEFT JOIN 
    user_cursos  ON users.id_user = user_cursos.id_user
LEFT JOIN 
    cursos ON user_cursos.id_curso = cursos.id_curso
WHERE
	users.id_user = 1;
    


SELECT 
    users.id_user, 
    users.nome, 
    users.sobrenome, 
    users.nome_social, 
    users.data_nascimento, 
    users.email, 
    users.tipo_pagamento, 
    endereco_users.rua, 
    endereco_users.bairro, 
    endereco_users.numero, 
    endereco_users.cep, 
    endereco_users.estado, 
    endereco_users.cidade, 
    contato_users.numero_telefone_celular, 
    contato_users.numero_telefone_residencial, 
    cursos.materia
FROM 
    users
LEFT JOIN 
    endereco_users ON users.id_user = endereco_users.usuario_id
LEFT JOIN 
    contato_users ON users.id_user = contato_users.usuario_id
LEFT JOIN 
    user_cursos ON users.id_user = user_cursos.id_user
LEFT JOIN 
    cursos ON user_cursos.id_curso = cursos.id_curso;

 */
 
 

