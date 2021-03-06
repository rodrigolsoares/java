create database siges;
use siges;

CREATE TABLE status_registro (
  pk_status INTEGER UNSIGNED NOT NULL ,
  nm_status VARCHAR(20) NULL,
  PRIMARY KEY(pk_status)
)
ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci;

insert into status_registro (pk_status, nm_status) values(1, 'Ativo');
insert into status_registro (pk_status, nm_status) values(2, 'Inativo');

CREATE TABLE tipo_atendimento (
  pk_tipo_atend INTEGER UNSIGNED NOT NULL ,
  nm_tipo_atend VARCHAR(20) NULL,
  PRIMARY KEY(pk_tipo_atend)
)
ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci;

insert into tipo_atendimento (pk_tipo_atend, nm_tipo_atend) values(1, 'agendamento');
insert into tipo_atendimento (pk_tipo_atend, nm_tipo_atend) values(2, 'Encaixe');

CREATE TABLE plano (
  pk_plano INTEGER UNSIGNED NOT NULL ,
  nm_plano VARCHAR(50) NULL,
  descricao VARCHAR(500) NULL,
  valor FLOAT  NULL,
  fk_status INTEGER UNSIGNED not null,
  PRIMARY KEY(pk_plano),
  FOREIGN KEY (fk_status) REFERENCES status_registro(pk_status)
)
ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci;

insert into plano(pk_plano, nm_plano, descricao, valor, fk_status) values(1, 'Basico', 'Este � o plano mais b�sico', 20.0, 1);
insert into plano(pk_plano, nm_plano, descricao, valor, fk_status) values(2, 'Intermedi�rio', 'Este � mais abrangente', 40.0, 1);
insert into plano(pk_plano, nm_plano, descricao, valor, fk_status) values(3, 'Avan�ado I', 'Este � o plano completo', 80.0, 1);
insert into plano(pk_plano, nm_plano, descricao, valor, fk_status) values(4, 'Avan�ado II', 'Este � o plano completo, mais com o portal onde seu cliente pode se cadastrar e agendar consulta pela web', 150.0, 2);
insert into plano(pk_plano, nm_plano, descricao, valor, fk_status) values(5, 'Premium', 'Este plano possui todas as funcionalidades do Avan�ado II, mas com o diferencial este plano � para clientes que possiu filial', 200.0, 2);


CREATE TABLE tipo_cobranca (
  pk_tipo_cobranca INTEGER UNSIGNED NOT NULL ,
  nm_cobranca VARCHAR(50) NULL,
  fk_status INTEGER UNSIGNED not null,
  PRIMARY KEY(pk_tipo_cobranca),
  FOREIGN KEY (fk_status) REFERENCES status_registro(pk_status)
)
ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci;

insert into tipo_cobranca values(1, 'Boleto Banc�rio', 1);
insert into tipo_cobranca values(2, 'PDF por e-mail', 1);

create table categoria_funcionario (
  pk_categoria INTEGER UNSIGNED NOT NULL ,
  nm_categoria VARCHAR(20) NOT NULL,
  fk_status INTEGER UNSIGNED not null,
  PRIMARY KEY(pk_categoria), 
  FOREIGN KEY (fk_status) REFERENCES status_registro(pk_status)
)
ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci;

insert into categoria_funcionario values(1, 'Cabeleireiro', 1);
insert into categoria_funcionario values(2, 'Manicure', 1);
insert into categoria_funcionario values(3, 'Depila��o', 1);

CREATE TABLE status_atendimento (
  pk_status_atendimento INTEGER UNSIGNED NOT NULL,
  nm_status VARCHAR(20) not NULL,
  ds_status VARCHAR(150) not NULL,
  PRIMARY KEY(pk_status_atendimento)
)
ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci;

insert into status_atendimento (pk_status_atendimento, nm_status, ds_status)Values(1,'Aguardando','Sinaliza todos os clientes que aguardam atendimento');
insert into status_atendimento (pk_status_atendimento, nm_status, ds_status)Values(2,'Em Atendimento','Sinaliza todos os clientes que est�o sendo atendidos');
insert into status_atendimento (pk_status_atendimento, nm_status, ds_status)Values(3,'Encerrado','Sinaliza todos os clientes que j� foram atendidos');
insert into status_atendimento (pk_status_atendimento, nm_status, ds_status)Values(4,'� Confirmar','Sinaliza todos os que agendaram o servi�o via smartfone, e aguardam aprova��o para mudar o status de aguardando atendimento');

CREATE TABLE forma_pagamento (
  pk_forma_pagamento INTEGER UNSIGNED NOT NULL,
  nm_forma_pagamento VARCHAR(20) not NULL,
  PRIMARY KEY(pk_forma_pagamento)
)
ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci;

insert into forma_pagamento(pk_forma_pagamento, nm_forma_pagamento)values(1, 'Dinheiro');
insert into forma_pagamento(pk_forma_pagamento, nm_forma_pagamento)values(2, 'Debito');
insert into forma_pagamento(pk_forma_pagamento, nm_forma_pagamento)values(3, 'Credito');
insert into forma_pagamento(pk_forma_pagamento, nm_forma_pagamento)values(4, 'Cheque');
insert into forma_pagamento(pk_forma_pagamento, nm_forma_pagamento)values(5, 'Pendente');
insert into forma_pagamento(pk_forma_pagamento, nm_forma_pagamento)values(6, 'N�o informado');

CREATE TABLE perfil (
  pk_Perfil INTEGER UNSIGNED NOT NULL ,
  nm_perfil VARCHAR(20) NULL,
  fk_status INTEGER UNSIGNED not null,
  PRIMARY KEY(pk_Perfil) ,
  FOREIGN KEY (fk_status) REFERENCES status_registro(pk_status)
)
ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci;

insert into perfil (pk_perfil, nm_perfil, fk_status) values (1,'Respons�vel',1);
insert into perfil (pk_perfil, nm_perfil, fk_status) values (2,'Administrador',1);
insert into perfil (pk_perfil, nm_perfil, fk_status) values (3,'Operador produto', 1);
insert into perfil (pk_perfil, nm_perfil, fk_status) values (4,'Operador', 1);
insert into perfil (pk_perfil, nm_perfil, fk_status) values (5,'Visualiza��o', 1);

CREATE TABLE menu (
  pk_menu INTEGER UNSIGNED NOT NULL ,
  nm_menu VARCHAR(50) NOT NULL,
  nm_url VARCHAR(50) NOT NULL,
  nm_icone VARCHAR(50) NOT NULL,
  id_obj VARCHAR(50) NOT NULL,
  nivel INTEGER NOT NULL,
  PRIMARY KEY(pk_menu)
)
ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci;

insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (1, 'cliente', '#', '#', 'idCli', 0);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (2, 'Cadastro', 'cadCliente.jsf', 'ui-icon-disk', 'cadCli', 1);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (3, 'Consulta', 'listaCliente.jsf', 'ui-icon-folder-open', 'consuCli', 1);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (4, 'Funcion�rio', '#', '#', 'idFunc', 0);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (5, 'Cadastro', 'cadFuncionario.jsf', 'ui-icon-disk', 'cadFunc', 4);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (6, 'Consulta', 'listaFuncionario.jsf', 'ui-icon-folder-open', 'consulFunc', 4);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (7, 'Servi�o', '#', '#', 'idServ', 0);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (8, 'Cadastro', 'cadServico.jsf', 'ui-icon-disk', 'cadServ', 7);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (9, 'Consulta', 'listaServico.jsf', 'ui-icon-folder-open', 'consulServ', 7);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (10, 'Produto', '#', '#', 'idProd', 0);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (11, 'Cadastro', 'cadProduto.jsf', 'ui-icon-disk', 'cadProd', 10);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (12, 'Consulta', 'listaProduto.jsf', 'ui-icon-folder-open', 'consulProd', 10);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (13, 'Despesa', '#', '#', 'idDespesa', 0);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (14, 'Cadastro', 'cadDespesa.jsf', 'ui-icon-disk', 'cadDesp', 13);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (15, 'Consulta', 'listaDespesa.jsf', 'ui-icon-folder-open', 'consulDesp', 13);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (17, 'Agenda', 'agenda.jsf', 'ui-icon-folder-open', 'idagenda', 1);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (18, 'Relat�rios', '#', '#', 'idRelat', 0);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (19, 'Fluxo de Caixa', 'fluxoCaixa.jsf', 'ui-icon-folder-open', 'idFluxo', 18);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (20, 'Graficos', 'graficos.jsf', 'ui-icon-folder-open', 'idGrafico', 18);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (21, 'Configura��es', '#', '#', 'idConf', 0);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (22, 'Rateio', 'rateio.jsf', 'ui-icon-disk', 'idrateio', 21);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (23, 'Encaixe', 'encaixe.jsf', 'ui-icon-folder-open', 'idmmatendimento', 1);


CREATE TABLE menu_mobile (
  pk_menu INTEGER UNSIGNED NOT NULL ,
  nm_menu VARCHAR(50) NOT NULL,
  nm_url VARCHAR(50) NOT NULL,
  nm_icone VARCHAR(50) NOT NULL,
  id_obj VARCHAR(50) NOT NULL,
  nivel INTEGER NOT NULL,
  PRIMARY KEY(pk_menu)
)
ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci;

insert into menu_mobile (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (1, 'cliente', '#', '#', 'idCli', 0);
insert into menu_mobile (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (2, 'Cadastro', 'cadClienteMobile.jsf', 'ui-icon-disk', 'cadCli', 1);
insert into menu_mobile (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (3, 'Consulta', 'listaClienteMobile.jsf', 'ui-icon-folder-open', 'consuCli', 1);
insert into menu_mobile (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (4, 'Funcion�rio', '#', '#', 'idFunc', 0);
insert into menu_mobile (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (5, 'Cadastro', 'cadFuncionarioMobile.jsf', 'ui-icon-disk', 'cadFunc', 4);
insert into menu_mobile (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (6, 'Consulta', 'listaFuncionarioMobile.jsf', 'ui-icon-folder-open', 'consulFunc', 4);
insert into menu_mobile (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (7, 'Servi�o', '#', '#', 'idServ', 0);
insert into menu_mobile (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (8, 'Cadastro', 'cadServicoMobile.jsf', 'ui-icon-disk', 'cadServ', 7);
insert into menu_mobile (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (9, 'Consulta', 'listaServicoMobile.jsf', 'ui-icon-folder-open', 'consulServ', 7);
insert into menu_mobile (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (10, 'produto', '#', '#', 'idProd', 0);
insert into menu_mobile (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (11, 'Cadastro', 'cadProdutoMobile.jsf', 'ui-icon-disk', 'cadProd', 10);
insert into menu_mobile (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (12, 'Consulta', 'listaProdutoMobile.jsf', 'ui-icon-folder-open', 'consulProd', 10);
insert into menu_mobile (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (13, 'Despesa', '#', '#', 'idDespesa', 0);
insert into menu_mobile (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (14, 'Cadastro', 'cadDespesaMobile.jsf', 'ui-icon-disk', 'cadDesp', 13);
insert into menu_mobile (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (15, 'Consulta', 'listaDespesaMobile.jsf', 'ui-icon-folder-open', 'consulDesp', 13);
insert into menu_mobile (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (17, 'Agenda', 'agendaMobile.jsf', 'ui-icon-folder-open', 'idagenda', 1);
insert into menu_mobile (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (18, 'Relat�rios', '#', '#', 'idRelat', 0);
insert into menu_mobile (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (19, 'Fluxo de Caixa', 'fluxoCaixaMobile.jsf', 'ui-icon-folder-open', 'idFluxo', 18);
insert into menu_mobile (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (20, 'Graficos', 'graficosMobile.jsf', 'ui-icon-folder-open', 'idGrafico', 18);
insert into menu_mobile (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (21, 'Configura��es', '#', '#', 'idConf', 0);
insert into menu_mobile (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (22, 'rateio', 'rateioMobile.jsf', 'ui-icon-disk', 'idrateio', 21);
insert into menu_mobile (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (23, 'Encaixe', 'encaixeMobile.jsf', 'ui-icon-folder-open', 'idmmatendimento', 1);


CREATE TABLE plano_perfil_menu (
  fk_plano INTEGER UNSIGNED not null,
  fk_menu INTEGER UNSIGNED NOT NULL ,
  fk_perfil INTEGER UNSIGNED not null,
  fk_status INTEGER UNSIGNED not null,
  FOREIGN KEY (fk_plano) REFERENCES plano(pk_plano),
  FOREIGN KEY (fk_menu) REFERENCES menu(pk_menu),
  FOREIGN KEY (fk_perfil) REFERENCES perfil(pk_Perfil),
  FOREIGN KEY (fk_status) REFERENCES status_registro(pk_status)
)
ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci;

--Operador Plano B�sico
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(1, 1, 4,1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(1, 2, 4,1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(1, 3, 4,1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(1, 23, 4,1);

--Respons�vel Plano B�sico
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(1, 1, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(1, 2, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(1, 3, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(1, 23, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(1, 4, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(1, 5, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(1, 6, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(1, 7, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(1, 8, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(1, 9, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(1, 13, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(1, 14, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(1, 15, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(1, 18, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(1, 19, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(1, 20, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(1, 21, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(1, 22, 1, 1);

--Avan�ado I Responsavel
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(3, 1, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(3, 2, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(3, 3, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(3, 4, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(3, 5, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(3, 6, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(3, 7, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(3, 8, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(3, 9, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(3, 10, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(3, 11, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(3, 12, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(3, 13, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(3, 14, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(3, 15, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(3, 17, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(3, 18, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(3, 19, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(3, 20, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(3, 21, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(3, 22, 1, 1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(3, 23, 1, 1);

delete from plano_perfil_menu where fk_menu = 24;

CREATE TABLE responsavel_salao (
  pk_responsavel_salao INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nm_Responsavel VARCHAR(50) NOT NULL,
  sexo CHAR  NULL,
  rg VARCHAR(20)  NULL,
  cpf VARCHAR(20)  NULL,
  e_mail VARCHAR(50)  NULL,
  tel_residencial VARCHAR(16)  NULL,
  tel_celular VARCHAR(16)  NULL,
  tel_comercial VARCHAR(16)  NULL,
  fk_plano  INTEGER UNSIGNED  NULL, 
  DT_NASC DATE  null,  
  DT_EXPERIENCIA_SOFT DATE Not null,
  fk_status INTEGER UNSIGNED not null,
  fk_tipo_cobranca INTEGER UNSIGNED  null,
  PRIMARY KEY(pk_responsavel_salao),
  FOREIGN KEY (fk_plano) REFERENCES plano(pk_plano),
  FOREIGN KEY (fk_status) REFERENCES status_registro(pk_status),
  FOREIGN KEY (fk_tipo_cobranca) REFERENCES tipo_cobranca(pk_tipo_cobranca)
)
ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci;

CREATE TABLE salao (
  pk_salao INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  fk_responsavel_salao INTEGER UNSIGNED NOT NULL, 
  nm_fantasia_salao VARCHAR(100) NOT NULL,
  nm_razao_social_salao VARCHAR(100) NOT NULL,
  cnpj VARCHAR(20) NULL,
  ins_estadual VARCHAR(20) NULL,
  logradouro VARCHAR(100)  NULL,
  numero VARCHAR(10)  NULL,
  bairro VARCHAR(100)  NULL,
  complemento VARCHAR(100)  NULL,
  cidade VARCHAR(100)  NULL,
  cep VARCHAR(10)  NULL,
  estado CHAR(2)  NULL,
  tel_1 VARCHAR(16) NULL,
  tel_2 VARCHAR(16) NULL,
  habilita_ajudante VARCHAR(1) not null,
  fk_status INTEGER UNSIGNED not null,
  PRIMARY KEY(pk_salao),
  FOREIGN KEY (fk_responsavel_salao) REFERENCES responsavel_salao(pk_responsavel_salao),
  FOREIGN KEY (fk_status) REFERENCES status_registro(pk_status)
  

)
ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci;

CREATE TABLE usuario (
  pk_usuario INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nm_usuario VARCHAR(50) not NULL,
  login VARCHAR(20) not NULL,
  senha VARCHAR(50) not NULL,
  fk_perfil INTEGER UNSIGNED Not NULL,
  fk_status INTEGER UNSIGNED not NULL,
  fk_responsavel_salao INTEGER UNSIGNED not NULL,
  fk_salao INTEGER UNSIGNED NULL,
  fk_plano INTEGER UNSIGNED not NULL,
  PRIMARY KEY(pk_usuario),
  FOREIGN KEY (fk_perfil) REFERENCES perfil(pk_Perfil),
  FOREIGN KEY (fk_status) REFERENCES status_registro(pk_status),
  FOREIGN KEY (fk_responsavel_salao) REFERENCES responsavel_salao(pk_responsavel_salao),
  FOREIGN KEY (fk_salao) REFERENCES salao(pk_salao),
  FOREIGN KEY (fk_plano) REFERENCES plano(pk_plano)
  

)
ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci;

CREATE TABLE usuario_android (
  pk_usuario_android INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nm_usuario VARCHAR(50) not NULL,
  no_celular VARCHAR(50) not NULL,
  e_mail VARCHAR(50) NOT NULL,
  PRIMARY KEY(pk_usuario_android)
)
ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci;

CREATE TABLE despesa (
  pk_despesa INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nm_conta VARCHAR(50) not NULL,
  valor FLOAT not NULL,
  data_vencimento DATE not NULL,
  data_pagamento DATE not NULL,
  fk_Status INTEGER UNSIGNED NULL,
  fk_responsavel_salao INTEGER UNSIGNED not NULL,
  fk_salao INTEGER UNSIGNED not NULL,
  fk_usuario INTEGER UNSIGNED not NULL,
  PRIMARY KEY(pk_despesa),
  FOREIGN KEY (fk_status) REFERENCES status_registro(pk_status),
  FOREIGN KEY (fk_responsavel_salao) REFERENCES responsavel_salao(pk_responsavel_salao),
  FOREIGN KEY (fk_salao) REFERENCES salao(pk_salao),
  FOREIGN KEY (fk_usuario) REFERENCES usuario(pk_usuario)
  
)
ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci;

CREATE TABLE produto (
  pk_produto INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nm_produto VARCHAR(50) not NULL,
  marca VARCHAR(50) not NULL,
  descricao VARCHAR(150) NULL,
  qtde_estoque INTEGER UNSIGNED  NULL,
  valor_pago FLOAT  NULL,
  valor_total FLOAT  NULL,
  qtde_minima INTEGER UNSIGNED NULL,
  fk_status INTEGER UNSIGNED NULL,
  fk_responsavel_salao INTEGER UNSIGNED not NULL,
  fk_salao INTEGER UNSIGNED not NULL,
  fk_usuario INTEGER UNSIGNED not NULL,
  PRIMARY KEY(pk_produto),
  FOREIGN KEY (fk_status) REFERENCES status_registro(pk_status),
  FOREIGN KEY (fk_responsavel_salao) REFERENCES responsavel_salao(pk_responsavel_salao),
  FOREIGN KEY (fk_salao) REFERENCES salao(pk_salao),
  FOREIGN KEY (fk_usuario) REFERENCES usuario(pk_usuario)
)
ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci;

CREATE TABLE funcionario (
  pk_funcionario INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nm_funcionario VARCHAR(50) NOT NULL,
  sexo CHAR NULL,
  rg VARCHAR(20) NULL,
  cpf VARCHAR(20) NULL,
  crt_trabalho VARCHAR(20) NULL,
  pis VARCHAR(20) NULL,
  logradouro VARCHAR(100) NULL,
  numero VARCHAR(10) NULL,
  bairro VARCHAR(100) NULL,
  complemento VARCHAR(100) NULL,
  cidade VARCHAR(100) NULL,
  cep VARCHAR(10) NULL,
  estado CHAR(2) NULL,
  tel_residencial VARCHAR(16) NULL,
  tel_celular VARCHAR(16) NULL,
  tel_recado1 VARCHAR(16) NULL,
  tel_recado2 VARCHAR(16) NULL,
  banco VARCHAR(40) NULL,
  agencia VARCHAR(10) NULL,
  conta VARCHAR(15) NULL,
  tipo_conta VARCHAR(40) NULL,
  porcentagem INTEGER UNSIGNED NULL,
  fk_status INTEGER UNSIGNED NULL,
  e_mail VARCHAR(50) NULL,
  fk_responsavel_salao INTEGER UNSIGNED not NULL,
  fk_salao INTEGER UNSIGNED not NULL,
  fk_categoria INTEGER UNSIGNED NULL,
  fk_usuario INTEGER UNSIGNED not NULL,
  PRIMARY KEY(pk_funcionario),
  FOREIGN KEY (fk_status) REFERENCES status_registro(pk_status),
  FOREIGN KEY (fk_responsavel_salao) REFERENCES responsavel_salao(pk_responsavel_salao),
  FOREIGN KEY (fk_salao) REFERENCES salao(pk_salao),
  FOREIGN KEY (fk_categoria) REFERENCES categoria_funcionario(pk_categoria),
  FOREIGN KEY (fk_usuario) REFERENCES usuario(pk_usuario)
)
ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci;

CREATE TABLE servico (
  pk_servico INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nm_servico VARCHAR(50) NOT NULL,
  valor FLOAT not NULL,
  descricao VARCHAR(150) NULL,
  sigla VARCHAR(5) NULL,
  fk_Status INTEGER UNSIGNED not NULL,
  fk_responsavel_salao INTEGER UNSIGNED not NULL,
  fk_salao INTEGER UNSIGNED not NULL,
  fk_usuario INTEGER UNSIGNED not NULL,
  PRIMARY KEY(pk_servico),
  FOREIGN KEY (fk_status) REFERENCES status_registro(pk_status),
  FOREIGN KEY (fk_responsavel_salao) REFERENCES responsavel_salao(pk_responsavel_salao),
  FOREIGN KEY (fk_salao) REFERENCES salao(pk_salao),
  FOREIGN KEY (fk_usuario) REFERENCES usuario(pk_usuario)
)
ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci;

CREATE TABLE cliente (
  pk_cliente INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nm_cliente VARCHAR(50) NOT NULL,
  sexo CHAR NULL,
  rg VARCHAR(20) NULL,
  cpf VARCHAR(20) NULL,
  logradouro VARCHAR(100) NULL,
  numero VARCHAR(10) NULL,
  bairro VARCHAR(100) NULL,
  complemento VARCHAR(100) NULL,
  cidade VARCHAR(100) NULL,
  estado CHAR(2) NULL,
  cep VARCHAR(10) NULL,
  tel_residencial VARCHAR(16) NULL,
  tel_celular VARCHAR(16) NULL,
  tel_comercial VARCHAR(16) NULL,
  tel_recado VARCHAR(16) NULL,
  e_mail VARCHAR(50) NULL,
  descricao VARCHAR(1000) NULL,
  fk_responsavel_salao INTEGER UNSIGNED not NULL,
  fk_salao INTEGER UNSIGNED not NULL,
  fk_usuario INTEGER UNSIGNED not NULL,
  PRIMARY KEY(pk_cliente),
  FOREIGN KEY (fk_responsavel_salao) REFERENCES responsavel_salao(pk_responsavel_salao),
  FOREIGN KEY (fk_salao) REFERENCES salao(pk_salao),
  FOREIGN KEY (fk_usuario) REFERENCES usuario(pk_usuario)
)
ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci;


CREATE TABLE agenda (
  pk_agenda INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  fk_cliente INTEGER UNSIGNED Not NULL,
  fk_funcionario INTEGER UNSIGNED Not NULL,
  fk_status_atendimento INTEGER UNSIGNED Not NULL,
  fk_forma_pagamento INTEGER UNSIGNED Not NULL,
  valor_servico FLOAT Not NULL,
  valor_Desconto_servico FLOAT NULL,
  valor_Total_servico FLOAT Not NULL,
  valor_Caixinha FLOAT NULL,
  data_compensa_cheque DATE NULL,
  data_agendamento DATE Not NULL,
  hora_agendamento VARCHAR(5) Not NULL,
  porcentagem INTEGER UNSIGNED Not NULL,
  descricao VARCHAR(150) NULL,
  fk_tipo_atend INTEGER UNSIGNED NOT NULL,
  fk_responsavel_salao INTEGER UNSIGNED not NULL,
  fk_salao INTEGER UNSIGNED not NULL,
  fk_usuario INTEGER UNSIGNED NULL,
  fk_usuario_android INTEGER UNSIGNED NULL,
  PRIMARY KEY(pk_agenda),
  FOREIGN KEY (fk_cliente) REFERENCES cliente(pk_cliente),
  FOREIGN KEY (fk_funcionario) REFERENCES funcionario(pk_funcionario),
  FOREIGN KEY (fk_status_atendimento) REFERENCES status_atendimento(pk_status_atendimento),
  FOREIGN KEY (fk_forma_pagamento) REFERENCES forma_pagamento(pk_forma_pagamento),
  FOREIGN KEY (fk_responsavel_salao) REFERENCES responsavel_salao(pk_responsavel_salao),
  FOREIGN KEY (fk_salao) REFERENCES salao(pk_salao),
  FOREIGN KEY (fk_tipo_atend) REFERENCES tipo_atendimento(pk_tipo_atend),
  FOREIGN KEY (fk_usuario) REFERENCES usuario(pk_usuario),
  FOREIGN KEY (fk_usuario_android) REFERENCES usuario_android(pk_usuario_android)

)
ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci;

CREATE TABLE agenda_ajudante (
  pk_agenda_ajudante INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  fk_agenda INTEGER UNSIGNED Not NULL,
  fk_funcionario INTEGER UNSIGNED Not NULL,
  porcentagem INTEGER UNSIGNED Not NULL,
  tipo_rateio INTEGER UNSIGNED NULL,
  fk_responsavel_salao INTEGER UNSIGNED not NULL,
  fk_salao INTEGER UNSIGNED not NULL,
  fk_usuario INTEGER UNSIGNED not NULL,
  PRIMARY KEY(pk_agenda_ajudante),
  FOREIGN KEY (fk_agenda) REFERENCES agenda(pk_agenda),
  FOREIGN KEY (fk_funcionario) REFERENCES funcionario(pk_funcionario),
  FOREIGN KEY (fk_responsavel_salao) REFERENCES responsavel_salao(pk_responsavel_salao),
  FOREIGN KEY (fk_salao) REFERENCES salao(pk_salao),
  FOREIGN KEY (fk_usuario) REFERENCES usuario(pk_usuario)
)
ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci;

CREATE TABLE agenda_servico (
  pk_agenda_servico INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  fk_agenda INTEGER UNSIGNED Not NULL,
  fk_servico INTEGER UNSIGNED Not NULL,
  fk_responsavel_salao INTEGER UNSIGNED not NULL,
  fk_salao INTEGER UNSIGNED not NULL,
  fk_usuario INTEGER UNSIGNED NULL,
   fk_usuario_android INTEGER UNSIGNED NULL,
  PRIMARY KEY(pk_agenda_servico),
  FOREIGN KEY (fk_agenda) REFERENCES agenda(pk_agenda),
  FOREIGN KEY (fk_servico) REFERENCES servico(pk_servico),
  FOREIGN KEY (fk_responsavel_salao) REFERENCES responsavel_salao(pk_responsavel_salao),
  FOREIGN KEY (fk_salao) REFERENCES salao(pk_salao),
  FOREIGN KEY (fk_usuario) REFERENCES usuario(pk_usuario),
  FOREIGN KEY (fk_usuario_android) REFERENCES usuario_android(pk_usuario_android)
)
ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci;

CREATE TABLE rateio (
  fk_responsavel_salao INTEGER UNSIGNED not NULL,
  fk_salao INTEGER UNSIGNED not NULL,
  valor FLOAT Not NULL,
  flag_habilita INTEGER UNSIGNED not NULL,
  fk_usuario INTEGER UNSIGNED NULL,
  FOREIGN KEY (fk_responsavel_salao) REFERENCES responsavel_salao(pk_responsavel_salao),
  FOREIGN KEY (fk_salao) REFERENCES salao(pk_salao),
  FOREIGN KEY (fk_usuario) REFERENCES usuario(pk_usuario)
)
ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci;


update menu set nm_menu= 'Servi�o' where pk_menu = 7;

############################# Executar daqui para baixo#################################




