-- areas
insert into area (nome) values ('computacao');
insert into area (nome) values ('agronomia');

-- programas
insert into programa (nome, instituicao) values ('Pibic', '4');

-- instituicoes
insert into instituicao (sigla, nome) values ('UFRA', 'Universidade Federal Rural da Amazonia');

select * from programa;

select * from unidade;

insert into unidade (nome, instituicao) values ('ICIBE', 4);
insert into unidade (nome, instituicao) values ('ICA', 4);
insert into unidade (nome, instituicao) values ('ISARH', 4);

select * from instituicao;