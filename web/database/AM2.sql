Create database if not exists am2cursos;

use asm2cursos;

CREATE TABLE user(
id_user int not null Primary Key AUTO_INCREMENT,
nome varchar(50) not null,
sobrenome varchar(100) not null,
nome_social varchar(250),
cpf int(11) not null,
data_nascimento DATE not null
email varchar(250) not null
);

CREATE TABLE contato_user(
id_registro_telefone INT AUTO_INCREMENT,
usuario_id int not null, 
telefone_celular varchar(11),
telefone_residencial varchar(8),
FOREIGN KEY (usuario_id) REFERENCES user(id_user) ON DELETE CASCADE  

);

CREATE TABLE endereco_user{
 id_endereco INT PRIMARY KEY AUTO_INCREMENT,
    usuario_id INT NOT NULL,
    cep VARCHAR(8) NOT NULL,
    rua VARCHAR(100) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    complemento VARCHAR(30),
    bairro VARCHAR(50) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    estado CHAR(2) NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES user(id_user) ON DELETE CASCADE  
}


CREATE TABLE cursos(
id_curso int PRIMARY KEY AUTO_INCREMENT,
tipo_curso varchar(100) not null
);

