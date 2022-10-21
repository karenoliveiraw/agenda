/**
*Agenda de contatos
* @author Karen Oliveira da Silva
*/ 

-- Comando para verificar os bancos criados

show databases;

-- Comando para criar um novo banco de dados

create database agenda;

-- Comando para selecionar um banco de dados
use agenda;

-- Comando usado para excluir um banco de dados

-- drop database nome_do_banco;

-- Comando usado para criar  uma tabela
-- int (tipo de dados: (números inteiros)
-- primary key (chave primária - identificador)
-- auto_increment (numeração automatica)
-- varchar(50) (tipo de dados string - 50 caracteres)
-- not null (campo obrigatorio)
create table contatos(
	id int primary key auto_increment, 
    nome varchar(50) not null,
    fone varchar(15) not null,
    email varchar(50)

);

-- verificar tabelas do banco de dados

show tables;

-- descrever a tabela

describe contatos;
/**
*Agenda de contatos
* @author Karen Oliveira da Silva
*/ 

-- Comando para verificar os bancos criados
show databases;

-- Comando para criar um novo banco de dados
create database agenda;

-- Comando para selecionar um banco de dados
use agenda;

-- Comando usado para excluir um banco de dados

-- drop database nome_do_banco;

-- Comando usado para criar  uma tabela
-- int (tipo de dados: (números inteiros)
-- primary key (chave primária - identificador)
-- auto_increment (numeração automatica)
-- varchar(50) (tipo de dados string - 50 caracteres)
-- not null (campo obrigatorio)
create table contatos(
	id int primary key auto_increment, 
    nome varchar(50) not null,
    fone varchar(15) not null,
    email varchar(50)

);

-- verificar tabelas do banco de dados

show tables;
-- descrever a tabela

describe contatos;

-- o comando abaixo altera o nome de um campo de uma tabela (coluna)
alter table contatos change contato nome varchar (50) not null;


-- o comando abaixo adiciona uma coluna a tabela
alter table contatos add column obs varchar (250); 
-- o comando abaixo adiciona uma coluna a tabela em um local específico 
alter table contatos add column fonetwo varchar (15) after fone;
-- o comando abaixo modifica os atributos (20) de um campo da tabela 
alter table contatos modify column fone2 varchar (20);

-- o comando abaixo exclui uma coluna da tabela
alter table contatos drop column fonetwo;
alter table contatos drop column obs;

-- o comando abaixo exclui a tabela e tudo que tem nela 
-- drop table contatos 

/************** CRUD ***********/ 

-- CRUD create (insert)

-- o comando abaixo adicionar nome/fone/email 
insert into contatos (nome, fone,email)
values ('Bill Gates', '99999-1234', 'bill@outlook.com');

-- a linha abaixo seleciona todos os registros da tabela
select * from contatos;

insert into contatos (nome, fone, email)
values ('Bruno Marrone', '95865-5868', 'brunomarrone@gmail.com');

insert into contatos (nome, fone, email) 
values ('Bruna', '9856-5656', 'bruna@gmail.com');

insert into contatos (nome, fone, email)
values ('Karen', '98525-8998', 'karensilvaoliveira76@gmail.com');

insert into contatos (nome,fone)
values ('Bernando', '1195215-5151');

insert into contatos (nome, fone, email)
values ('Bianca', '114585-5525', 'bianca@bianca.com.br'); 

-- a linha abaixo criar apelidos aos campos (cabeçalho) da tabela 
select id as Código, nome as Contatos, fone as Telefone, email as Email from contatos;

-- a linha abaixo selecionar campo específicos da tabela 
select nome, fone from contatos;

-- a linha abaixo ordena os registro da tabela (de a -z)
select * from contatos order by nome;

-- a linha abaixo ordenam os registro da tabela (de z - a)
select * from contatos order by nome desc;

-- a linha filtra nomes específicos das tabela     
select * from contatos where nome like 'Bru%';

-- selecionando as linhas abaixo de acordo com um critério
select * from contatos where nome = 'Bill Gates';
select * from contatos where id = '1';

-- CRUD Update (importante usar a cláusula where associada ao Id)

 update contatos set nome =  'Willian Gates' where id = 1;


update contatos set email = 'bernado@bernado@gmail.com' where id = 5;

update contatos set email = 'williamgates@gmail.com' where id = 1;

update contatos set nome = 'Karen Oliveira', fone  = '1194219-4071', email = 'karensoliveiradasilva@gmail.com' where id  = 4; 
update contatos set nome = 'Mauricio de Souza', fone  = '119999-9999', email = 'mmsosouza@gmail.com' where id  = 30;

select * from contatos;

update contatos set nome = 'Bruna Silva', fone = '1199563211' where id =3;
update contatos set nome = 'Bernando Santos' where id = 5; 
-- CRUD Delete (!importante usar a cláusula where associada ao ID)

delete from contatos where id = 6; 
