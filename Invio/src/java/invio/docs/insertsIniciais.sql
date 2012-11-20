-- areas
insert into area (nome) values ('computacao');
insert into area (nome) values ('agronomia');

-- instituicoes
insert into instituicao (sigla, nome) values ('UFRA', 'Universidade Federal Rural da Amazonia');

-- unidades das instituicoes
insert into unidade (nome, instituicao) values ('ICIBE', 4);
insert into unidade (nome, instituicao) values ('ICA', 4);
insert into unidade (nome, instituicao) values ('ISARH', 4);