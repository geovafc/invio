INSERT INTO `instituicao` (`id`, `nome`, `sigla`) VALUES
(1, 'Universidade Federal Rural da Amazônia', 'UFRA');

INSERT INTO `area` (`id`, `nome`) VALUES
(1, 'CIÊNCIAS AGRÁRIAS I'),
(2, 'ZOOTECNIA / RECURSOS PESQUEIROS'),
(3, ' MEDICINA VETERINÁRIA'),
(4, ' BIODIVERSIDADE'),
(5, 'BIOTECNOLOGIA'),
(6, 'ASTRONOMIA / FÍSICA'),
(7, 'CIÊNCIA DA COMPUTAÇÃO'),
(8, 'CIÊNCIA DE ALIMENTOS'),
(9, 'CIÊNCIA POLÍTICA E RELAÇÕESINTERNACIONAIS'),
(10, 'CIÊNCIAS AMBIENTAIS'),
(11, 'CIÊNCIAS BIOLÓGICAS I'),
(12, 'CIÊNCIAS BIOLÓGICAS II'),
(13, 'CIÊNCIAS BIOLÓGICAS III'),
(14, 'CIÊNCIAS SOCIAIS APLICADAS I'),
(15, 'ECONOMIA'),
(16, 'EDUCAÇÃO'),
(17, 'EDUCAÇÃO FÍSICA'),
(18, 'ENFERMAGEM'),
(19, 'ENGENHARIAS I'),
(20, 'ENGENHARIAS II'),
(21, 'ENGENHARIAS III'),
(22, 'ENGENHARIAS IV'),
(23, 'ENSINO'),
(24, 'FARMÁCIA'),
(25, 'FILOSOFIA/TEOLOGIA:subcomissão FILOSOFIA'),
(26, 'FILOSOFIA/TEOLOGIA:subcomissão TEOLOGIA'),
(27, 'GEOCIÊNCIAS'),
(28, 'GEOGRAFIA'),
(29, 'HISTÓRIA'),
(30, 'INTERDISCIPLINAR'),
(31, 'LETRAS / LINGUÍSTICA'),
(32, 'MATEMÁTICA / PROBABILIDADE EESTATÍSTICA'),
(33, 'MATERIAIS'),
(34, 'MEDICINA I'),
(35, 'MEDICINA II'),
(36, 'MEDICINA III'),
(37, 'NUTRIÇÃO'),
(38, 'ODONTOLOGIA'),
(39, 'PLANEJAMENTO URBANO E REGIONAL /DEMOGRAFIA'),
(40, 'PSICOLOGIA'),
(41, 'QUÍMICA'),
(42, 'SAÚDE COLETIVA'),
(43, 'SOCIOLOGIA'),
(44, 'SERVIÇO SOCIAL'),
(45, 'DIREITO'),
(46, 'ENGENHARIAS IV'),
(47, 'ADMINISTRAÇÃO, CIÊNCIAS CONTÁBEIS ETURISMO'),
(48, 'ANTROPOLOGIA / ARQUEOLOGIA'),
(49, 'ARQUITETURA E URBANISMO'),
(50, 'ARTES / MÚSICA');

INSERT INTO `programa` (`id`, `nome`, `instituicao`, `area`) VALUES
(1, ' Mestrado e Doutorado em Agronomia', 1, 1),
(2, ' Mestrado e Doutorado em Ciências Florestais', 1, 1),
(3, ' Doutorado em Ciências Agrárias', 1, 1),
(4, ' Mestrado em Aquicultura e Recursos Aquáticos Tropicais', 1, 2),
(5, ' Mestrado em Saúde e Produção Animal', 1, 3),
(6, ' Mestrado em Ciências Biológicas', 1, 4),
(7, ' Mestrado em Biotecnologia Aplicada à Agropecuária', 1, 5);

insert into login 
(email, senha, perfil) 
values 
('padrao@gmail.com', '123', 'A');