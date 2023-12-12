drop database hospital;
create database hospital;
use hospital;

create table senhaADM(senha varchar(255));

create table estado(
	-- dados baseados no IBGE
	codigo int,
    nome varchar(100) unique not null,
    uf char(2) unique not null,
    constraint Estado_PK primary key (codigo)
);

create table tipo_sanguineo(
	tipo char(3),
    constraint Tipo_Sanguineo_PK primary key (tipo)
);

create table Urgencia(
	-- protocolo mancherster
	cor varchar(8),
    classificacao varchar(20) unique not null,
    tempo int unique not null,
    constraint Urgencia_PK primary key (cor)
);

create table Convenio(
	id int auto_increment,
    convenio varchar(50) unique not null,
    descricao text,
    valor_mensal double,
	constraint Convenio_PK primary key (id)
);

create table Paciente(
	cpf varchar(14), 
	nome varchar(150),
	dataNasc date,
	convenio int,
	rua varchar(255),
	nro varchar(10),
	bairro varchar(255),
	cidade varchar(100),
	uf char(2),
	constraint Paciente_PK primary key (cpf),
    constraint Paciente_FK_Convenio foreign key (convenio) references Convenio(id),
    constraint Paciente_FK_UF foreign key (uf) references Estado(uf)
);

create table Medico(
	crm varchar(13),
	nome varchar(150),
    dataInscricao date,
	especializacao varchar(150),
	constraint Medico_PK primary key (crm)
);

create table Consulta(
	id int auto_increment,
	valor double,
	dataConsulta date,
	horario time,
	diagnostico text,
	id_medico varchar(13),
	id_paciente varchar(14),
	constraint Consulta_PK primary key (id),
	constraint Consulta_FK_Medico  foreign key (id_medico) references Medico(crm),
	constraint Consulta_FK_Paciente foreign key (id_paciente) references Paciente(cpf)
);

create table Enfermaria(
	coren varchar(13),
	nome varchar(150),
    dataInscricao date,
	constraint Enfermaria_PK primary key (coren)
);

create table Tecnico_de_Enfermagem(
	id_tecEnfer varchar(13),
	constraint Tecnicos_de_Enfermagem_PK primary key (id_TecEnfer),
    constraint Tecnicos_de_Enfermagem_FK_id foreign key (id_TecEnfer) references Enfermaria(coren)
);

create table Enfermeiro(
	id_enfer varchar(13),
	especializacao varchar(150),
	constraint Enfermeiros_PK primary key (id_Enfer),
    constraint Enfermeiros_FK_id foreign key (id_Enfer) references Enfermaria(coren)
);

create table Triagem(
	id int auto_increment,
	dataTriagem date,
	peso double,
	urgencia varchar(8),
	temperatura double,
	pressaoDia int,
    pressaoSys int,
	id_tecnico varchar(13),
	id_paciente varchar(14),
	constraint triagem_PK primary key (id),
	constraint triagem_FK_Tec foreign key (id_tecnico) references Tecnico_de_Enfermagem(id_tecEnfer),
	constraint triagem_FK_Pac foreign key (id_paciente) references Paciente(cpf),
    constraint triagem_FK_Urgencia foreign key (urgencia) references Urgencia(cor)
);

create table Ficha_Tecnica(
	prontuario int auto_increment,
	id_Paciente varchar(14) unique,
	historico_familiar text,
	tipo_Sanguineo char(3),
	constraint Ficha_Tecnica_PK primary key (prontuario, id_Paciente),
	constraint Ficha_Tecnica_FK_IDPaciente foreign key (id_Paciente) references Paciente(CPF), 
    constraint Ficha_tecnica_FK_TipoSanguineo foreign key (tipo_Sanguineo) references Tipo_sanguineo(tipo)
);

create table Receita (
	id int auto_increment,
	id_consulta int,
	descricao text,
	constraint Receita_PK primary key (id),
	constraint Receita_FK_IDConsulta foreign key (id_consulta) references Consulta(id)
);

create table Medicamentos(
	medicamento varchar(255),
	id_receita int,
	constraint Medicamentos_PK primary key (medicamento, id_receita),
	constraint Medicamentos_FK_IDReceita foreign key (id_receita) references Receita(id)
);  

create table Sintomas(
	sintoma varchar(255),
	id_triagem int,
	constraint Sintomas_PK primary key (sintoma, id_triagem),
	constraint Sintomas_FK_IDTriagem foreign key (id_triagem) references Triagem(id)
);

create table Alergias(
	alergia varchar(255),
	id_ficha int,
	constraint Alergias_PK primary key (alergia, id_ficha),
	constraint Alergias_FK_IDFichaTec foreign key (id_ficha) references Ficha_Tecnica(prontuario)
);

create table Telefones(
	telefone varchar(20),
	id_paciente varchar (14),
	constraint Telefones_PK primary key (telefone, id_paciente),
	constraint Telefones_FK_Paciente foreign key (id_paciente) references Paciente(CPF)
);

create table Auxilia(
	id_consulta int,
	id_enfer varchar(13),
	constraint Auxilia_PK primary key (id_consulta, id_enfer),
	constraint Auxilia_FK_Consultas foreign key (id_consulta) references Consulta(id),
	constraint Auxilia_FK_Enfermeiro foreign key (id_enfer) references Enfermeiro(id_Enfer)
);

insert into estado (codigo, nome, uf) values (12, 'Acre', 'AC');
insert into estado (codigo, nome, uf) values (27, 'Alagoas', 'AL');
insert into estado (codigo, nome, uf) values (13, 'Amazonas', 'AM');
insert into estado (codigo, nome, uf) values (16, 'Amapá', 'AP');
insert into estado (codigo, nome, uf) values (29, 'Bahia', 'BA');
insert into estado (codigo, nome, uf) values (23, 'Ceará', 'CE');
insert into estado (codigo, nome, uf) values (53, 'Distrito Federal', 'DF');
insert into estado (codigo, nome, uf) values (32, 'Espírito Santo', 'ES');
insert into estado (codigo, nome, uf) values (52, 'Goiás', 'GO');
insert into estado (codigo, nome, uf) values (21, 'Maranhão', 'MA');
insert into estado (codigo, nome, uf) values (31, 'Minas Gerais', 'MG');
insert into estado (codigo, nome, uf) values (50, 'Mato Grosso do Sul', 'MS');
insert into estado (codigo, nome, uf) values (51, 'Mato Grosso', 'MT');
insert into estado (codigo, nome, uf) values (15, 'Pará', 'PA');
insert into estado (codigo, nome, uf) values (25, 'Paraíba', 'PB');
insert into estado (codigo, nome, uf) values (26, 'Pernambuco', 'PE');
insert into estado (codigo, nome, uf) values (22, 'Piauí', 'PI');
insert into estado (codigo, nome, uf) values (41, 'Paraná', 'PR');
insert into estado (codigo, nome, uf) values (33, 'Rio de Janeiro', 'RJ');
insert into estado (codigo, nome, uf) values (24, 'Rio Grande do Norte', 'RN');
insert into estado (codigo, nome, uf) values (11, 'Rondônia', 'RO');
insert into estado (codigo, nome, uf) values (14, 'Roraima', 'RR');
insert into estado (codigo, nome, uf) values (43, 'Rio Grande do Sul', 'RS');
insert into estado (codigo, nome, uf) values (42, 'Santa Catarina', 'SC');
insert into estado (codigo, nome, uf) values (28, 'Sergipe', 'SE');
insert into estado (codigo, nome, uf) values (35, 'São Paulo', 'SP');
insert into estado (codigo, nome, uf) values (17, 'Tocantins', 'TO');

insert into tipo_sanguineo values ('A+');
insert into tipo_sanguineo values ('A-');
insert into tipo_sanguineo values ('B+');
insert into tipo_sanguineo values ('B-');
insert into tipo_sanguineo values ('AB+');
insert into tipo_sanguineo values ('AB-');
insert into tipo_sanguineo values ('O+');
insert into tipo_sanguineo values ('O-');

insert into urgencia (cor, classificacao, tempo) values ('Vermelho', 'Emergência', 0);
insert into urgencia (cor, classificacao, tempo) values ('Laranja', 'Muito Urgente', 10);
insert into urgencia (cor, classificacao, tempo) values ('Amarelo', 'Urgente', 50);
insert into urgencia (cor, classificacao, tempo) values ('Verde', 'Pouco Urgente', 120);
insert into urgencia (cor, classificacao, tempo) values ('Azul', 'Não Urgente', 240);

insert into convenio (id, convenio, descricao, valor_mensal) values (1,'Público', 'cobrança integral em todos os tipos de procedimento', 0);
insert into convenio (id, convenio, descricao, valor_mensal) values (2,'Empresarial', 'cobrança somente em procedimentos acima de R$2000, mas com desconto de 40% nos demais, uso individual', 395.99);
insert into convenio (id, convenio, descricao, valor_mensal) values (3,'Familiar', 'cobrança somente em procedimentos acima de R$3000, mas com desconto de 30% nos demais, disponivel para até 4 integrantes', 589.99);
insert into convenio (id, convenio, descricao, valor_mensal) values (4,'Individual', 'cobrança somente em procedimentos acima de R$1500, mas com desconto de 20% nos demais, uso individual', 199.99);
insert into convenio (id, convenio, descricao, valor_mensal) values (5,'Executivo', 'cobrança somente em procedimentos acima de R$5000, mas com desconto de 45% nos demais, uso individual', 500.99);

insert into paciente (cpf, nome, dataNasc, convenio, rua, nro, bairro, cidade, uf) values('394.066.896-69','Maria Antonia da Silva','2004-12-23',1,'Rua Regit Arabi','123','Santa Felicia','São Carlos','SP');
insert into paciente (cpf, nome, dataNasc, convenio, rua, nro, bairro, cidade, uf) values('326.182.852-80','Ana Lucia Batista','1996-10-15',2,'Rua Rubi de Souza','548B','Fagá','São Pedro','SP');
insert into paciente (cpf, nome, dataNasc, convenio, rua, nro, bairro, cidade, uf) values('801.628.270-91','Carlos Pereira dos Santos','1978-01-05',5,'Rua Das Gracas','8954','Vila Galvão','Guarulhos','SP');
insert into paciente (cpf, nome, dataNasc, convenio, rua, nro, bairro, cidade, uf) values('293.198.716-63','Pedro Gustavo Silva Almeida','2015-07-30',4,'Rua Vicente Laurito','632','Alto do Pascoal','Recife','PE');
insert into paciente (cpf, nome, dataNasc, convenio, rua, nro, bairro, cidade, uf) values('108.590.521-76','Juan Cristian Oliveira','2000-02-01',3,'Av. Bias Fortes','890','Santo Agostinho','Belo Horizonte','MG');

insert into medico (crm, nome, dataInscricao, especializacao) values ('178458/SP', 'Francisco Cutigi','2021-02-12', 'Cardiologia');
insert into medico (crm, nome, dataInscricao, especializacao) values ('245639/SP', 'Marta Gregorio de Souza','2009-08-16', 'Dermatologia');
insert into medico (crm, nome, dataInscricao, especializacao) values ('637113/MG', 'Vanessa Ramalho Cardoso Oliveira','1997-11-03', 'Urologia');
insert into medico (crm, nome, dataInscricao, especializacao) values ('363446/GO', 'Nataniel Paulo Silva Neto','2013-05-02', 'Pediatria');
insert into medico (crm, nome, dataInscricao, especializacao) values ('462671/RJ', 'Tainara Eduarda Lopes','2018-07-20', 'Oftalmologia');

insert into consulta (id, valor, dataConsulta, horario, diagnostico, id_medico, id_paciente) values (1, 345.99, '2023-09-30', '11:23:00', 'Gripe comum', '363446/GO', '293.198.716-63');
insert into consulta (id, valor, dataConsulta, horario, diagnostico, id_medico, id_paciente) values (2, 750.05, '2023-10-09', '12:34:00', 'Carcinoma basocelular na região da narina esquerda', '245639/SP', '108.590.521-76');
insert into consulta (id, valor, dataConsulta, horario, diagnostico, id_medico, id_paciente) values (3, 344.99, '2023-10-12', '09:14:00', 'Hipertensão arterial','178458/SP' , '326.182.852-80');
insert into consulta (id, valor, dataConsulta, horario, diagnostico, id_medico, id_paciente) values (4, 579.99, '2023-10-23', '15:57:00', 'Pedra Renal', '637113/MG' , '801.628.270-91');
insert into consulta (id, valor, dataConsulta, horario, diagnostico, id_medico, id_paciente) values (5, 334.99, '2023-10-23', '16:56:00', 'Ressecamento ocular', '462671/RJ' , '394.066.896-69');

insert into enfermaria (coren, nome, dataInscricao) values ('163456/SP', 'Pedro Bandeira Silva', '2007-01-08');
insert into enfermaria (coren, nome, dataInscricao) values ('895471/SP', 'Helio Moura Jr.', '2007-11-14');
insert into enfermaria (coren, nome, dataInscricao) values ('965230/RJ', 'Giovana Ferreira Lopes', '2010-03-31');
insert into enfermaria (coren, nome, dataInscricao) values ('001236/MG', 'Beatriz Souza Gregório', '2011-02-02');
insert into enfermaria (coren, nome, dataInscricao) values ('231541/SC', 'Cristina Mello Morais', '2012-12-12');
insert into enfermaria (coren, nome, dataInscricao) values ('231000/RJ', 'Vilma Resende Perreira', '2022-01-18');
insert into enfermaria (coren, nome, dataInscricao) values ('112548/MG', 'Alexandre Pires de Almeida', '2019-02-15');
insert into enfermaria (coren, nome, dataInscricao) values ('223659/SP', 'Pablo Henrique Goncalves', '2016-05-13');
insert into enfermaria (coren, nome, dataInscricao) values ('003256/SP', 'Erica Vitória Santos', '2015-04-19');
insert into enfermaria (coren, nome, dataInscricao) values ('587451/BA', 'Cleide Augusto Almeida', '2018-09-20');

insert into enfermeiro (id_Enfer, especializacao) values ('163456/SP', 'Cardiologista');
insert into enfermeiro (id_Enfer, especializacao) values ('895471/SP', 'Pediatra');
insert into enfermeiro (id_Enfer, especializacao) values ('965230/RJ', 'Urgência');
insert into enfermeiro (id_Enfer, especializacao) values ('001236/MG', 'Obstetra');
insert into enfermeiro (id_Enfer, especializacao) values ('231541/SC', 'Oncologia');

insert into tecnico_de_enfermagem (id_TecEnfer) values ('231000/RJ');
insert into tecnico_de_enfermagem (id_TecEnfer) values ('112548/MG');
insert into tecnico_de_enfermagem (id_TecEnfer) values ('223659/SP');
insert into tecnico_de_enfermagem (id_TecEnfer) values ('003256/SP');
insert into tecnico_de_enfermagem (id_TecEnfer) values ('587451/BA');

insert into ficha_tecnica (prontuario, id_paciente, historico_familiar, tipo_sanguineo) values (1, '394.066.896-69', 'Mãe é diabetica do tipo I', 'O+');
insert into ficha_tecnica (prontuario, id_paciente, historico_familiar, tipo_sanguineo) values (2, '326.182.852-80', 'Pai e Avô possui hipertensão, Avô morreu de ataque cardiaco', 'AB-');
insert into ficha_tecnica (prontuario, id_paciente, historico_familiar, tipo_sanguineo) values (3, '801.628.270-91', 'Tio Morreu de ataque cardiarco', 'O-');
insert into ficha_tecnica (prontuario, id_paciente, historico_familiar, tipo_sanguineo) values (4, '293.198.716-63', 'Desconhecido', 'A+');
insert into ficha_tecnica (prontuario, id_paciente, historico_familiar, tipo_sanguineo) values (5, '108.590.521-76', 'Mãe morreu devido ao tratamento de seu Câncer de pele', 'B-');

insert into triagem (id, dataTriagem, peso, urgencia, temperatura, pressaoDia, pressaoSys, id_tecnico, id_paciente) values (1, '2023-09-30', 47.6, 'Azul', 38.5, 79, 98, '231000/RJ', '293.198.716-63');
insert into triagem (id, dataTriagem, peso, urgencia, temperatura, pressaoDia, pressaoSys, id_tecnico, id_paciente) values (2, '2023-10-09', 78.4, 'Azul', 36.2, 78, 110, '112548/MG', '108.590.521-76');
insert into triagem (id, dataTriagem, peso, urgencia, temperatura, pressaoDia, pressaoSys, id_tecnico, id_paciente) values (3, '2023-10-12', 92.1, 'Amarelo', 36.4, 95, 150, '223659/SP', '326.182.852-80');
insert into triagem (id, dataTriagem, peso, urgencia, temperatura, pressaoDia, pressaoSys, id_tecnico, id_paciente) values (4, '2023-10-23', 65.3, 'Verde', 36.5, 77, 92, '003256/SP', '801.628.270-91');
insert into triagem (id, dataTriagem, peso, urgencia, temperatura, pressaoDia, pressaoSys, id_tecnico, id_paciente) values (5, '2023-10-23', 70.2, 'Azul', 36.1, 77, 105, '003256/SP', '394.066.896-69');

insert into receita (id, id_consulta, descricao) values (1, 1, 'Utilização do Anti Gripal de 8 em 8 horas, e Vaporizante descongestonate para congestão nasal');
insert into receita (id, id_consulta, descricao) values (2, 2, 'Tratamento do Câncer com o Oncologista, Utilização de protetor solar ao sair e evitar longa exposição ao sol');
insert into receita (id, id_consulta, descricao) values (3, 3, 'Utilização do Diazepam duas vezes por dia, ao acordar e antes de dormir');
insert into receita (id, id_consulta, descricao) values (4, 4, 'Tomar ao menos 2 litros de água por dia e utilização de Buscopan no caso de dor');
insert into receita (id, id_consulta, descricao) values (5, 5, 'Utilização do Mirugell de 12 em 12 horas');

insert into telefones (id_paciente, telefone) values ('394.066.896-69', '+55 (16) 98397-2549');
insert into telefones (id_paciente, telefone) values ('326.182.852-80', '+55 (19) 99536-6417');
insert into telefones (id_paciente, telefone) values ('801.628.270-91', '+55 (11) 99787-6345');
insert into telefones (id_paciente, telefone) values ('801.628.270-91', '+55 (11) 3505-8995');
insert into telefones (id_paciente, telefone) values ('108.590.521-76', '+55 (31) 98783-1235');

insert into sintomas (id_triagem, sintoma) values (1, 'febre');
insert into sintomas (id_triagem, sintoma) values (1, 'espirro');
insert into sintomas (id_triagem, sintoma) values (1, 'congestão nasal');
insert into sintomas (id_triagem, sintoma) values (2, 'mancha na região nasal');
insert into sintomas (id_triagem, sintoma) values (3, 'mal estar');
insert into sintomas (id_triagem, sintoma) values (3, 'desmaio');
insert into sintomas (id_triagem, sintoma) values (3, 'dor no peito');
insert into sintomas (id_triagem, sintoma) values (3, 'visão embaçada');
insert into sintomas (id_triagem, sintoma) values (4, 'colica na região pelvica');
insert into sintomas (id_triagem, sintoma) values (4, 'urina vermelha');
insert into sintomas (id_triagem, sintoma) values (5, 'dor nos olhos');

insert into medicamentos (id_receita, medicamento) values (1, 'Anti Gripal');
insert into medicamentos (id_receita, medicamento) values (1, 'Vick VapoRub');
insert into medicamentos (id_receita, medicamento) values (3, 'Diazepam');
insert into medicamentos (id_receita, medicamento) values (4, 'Buscopan');
insert into medicamentos (id_receita, medicamento) values (5, 'Mirugell');

insert into alergias (id_ficha, alergia) values (2, 'Amendoim');
insert into alergias (id_ficha, alergia) values (4, 'Pó e Polen');
insert into alergias (id_ficha, alergia) values (4, 'Perfumes e Odores fortes');
insert into alergias (id_ficha, alergia) values (5, 'Dipirona');
insert into alergias (id_ficha, alergia) values (5, 'Picada de Abelha');

insert into auxilia (id_consulta, id_enfer) values (1, '895471/SP');
insert into auxilia (id_consulta, id_enfer) values (2, '231541/SC');
insert into auxilia (id_consulta, id_enfer) values (3, '163456/SP');
insert into auxilia (id_consulta, id_enfer) values (3, '965230/RJ');
insert into auxilia (id_consulta, id_enfer) values (4, '965230/RJ');

