
insert into status_registro (pk_status, nm_status) values(1, 'Ativo');
insert into status_registro (pk_status, nm_status) values(2, 'Inativo');

insert into tipo_atendimento (pk_tipo_atend, nm_tipo_atend) values(1, 'agendamento');
insert into tipo_atendimento (pk_tipo_atend, nm_tipo_atend) values(2, 'Encaixe');

insert into plano(pk_plano, nm_plano, descricao, valor, fk_status) values(1, 'Basico', 'Este é o plano mais básico', 20.0, 1);
insert into plano(pk_plano, nm_plano, descricao, valor, fk_status) values(2, 'Intermediário', 'Este é mais abrangente', 40.0, 1);
insert into plano(pk_plano, nm_plano, descricao, valor, fk_status) values(3, 'Avançado I', 'Este é o plano completo', 80.0, 1);
insert into plano(pk_plano, nm_plano, descricao, valor, fk_status) values(4, 'Avançado II', 'Este é o plano completo, mais com o portal onde seu cliente pode se cadastrar e agendar consulta pela web', 150.0, 2);
insert into plano(pk_plano, nm_plano, descricao, valor, fk_status) values(5, 'Premium', 'Este plano possui todas as funcionalidades do Avançado II, mas com o diferencial este plano é para clientes que possiu filial', 200.0, 2);

insert into tipo_cobranca values(1, 'Boleto Bancário', 1);
insert into tipo_cobranca values(2, 'PDF por e-mail', 1);


insert into categoria_funcionario values(1, 'Cabeleireiro', 1);
insert into categoria_funcionario values(2, 'Manicure', 1);
insert into categoria_funcionario values(3, 'Depilação', 1);


insert into status_atendimento (pk_status_atendimento, nm_status, ds_status)Values(1,'Aguardando','Sinaliza todos os clientes que aguardam atendimento');
insert into status_atendimento (pk_status_atendimento, nm_status, ds_status)Values(2,'Em Atendimento','Sinaliza todos os clientes que estão sendo atendidos');
insert into status_atendimento (pk_status_atendimento, nm_status, ds_status)Values(3,'Encerrado','Sinaliza todos os clientes que já foram atendidos');


insert into forma_pagamento(pk_forma_pagamento, nm_forma_pagamento)values(1, 'Dinheiro');
insert into forma_pagamento(pk_forma_pagamento, nm_forma_pagamento)values(2, 'Debito');
insert into forma_pagamento(pk_forma_pagamento, nm_forma_pagamento)values(3, 'Credito');
insert into forma_pagamento(pk_forma_pagamento, nm_forma_pagamento)values(4, 'Cheque');
insert into forma_pagamento(pk_forma_pagamento, nm_forma_pagamento)values(5, 'Pendente');

insert into perfil (pk_perfil, nm_perfil, fk_status) values (1,'Responsável',1);
insert into perfil (pk_perfil, nm_perfil, fk_status) values (2,'Administrador',1);
insert into perfil (pk_perfil, nm_perfil, fk_status) values (3,'Operador produto', 1);
insert into perfil (pk_perfil, nm_perfil, fk_status) values (4,'Operador', 1);
insert into perfil (pk_perfil, nm_perfil, fk_status) values (5,'Visualização', 1);


insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (1, 'cliente', '#', '#', 'idCli', 0);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (2, 'Cadastro', 'cadCliente.jsf', 'ui-icon-disk', 'cadCli', 1);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (3, 'Consulta', 'listaCliente.jsf', 'ui-icon-folder-open', 'consuCli', 1);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (4, 'Funcionário', '#', '#', 'idFunc', 0);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (5, 'Cadastro', 'cadFuncionario.jsf', 'ui-icon-disk', 'cadFunc', 4);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (6, 'Consulta', 'listaFuncionario.jsf', 'ui-icon-folder-open', 'consulFunc', 4);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (7, 'Serviço', '#', '#', 'idServ', 0);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (8, 'Cadastro', 'cadServico.jsf', 'ui-icon-disk', 'cadServ', 7);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (9, 'Consulta', 'listaServico.jsf', 'ui-icon-folder-open', 'consulServ', 7);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (10, 'produto', '#', '#', 'idProd', 0);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (11, 'Cadastro', 'cadProduto.jsf', 'ui-icon-disk', 'cadProd', 10);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (12, 'Consulta', 'listaProduto.jsf', 'ui-icon-folder-open', 'consulProd', 10);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (13, 'Despesa', '#', '#', 'idDespesa', 0);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (14, 'Cadastro', 'cadDespesa.jsf', 'ui-icon-disk', 'cadDesp', 13);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (15, 'Consulta', 'listaDespesa.jsf', 'ui-icon-folder-open', 'consulDesp', 13);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (17, 'agenda', 'agenda.jsf', 'ui-icon-folder-open', 'idagenda', 1);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (18, 'Relatórios', '#', '#', 'idRelat', 0);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (19, 'Fluxo de Caixa', 'fluxoCaixa.jsf', 'ui-icon-folder-open', 'idFluxo', 18);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (20, 'Graficos', 'graficos.jsf', 'ui-icon-folder-open', 'idGrafico', 18);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (21, 'Configurações', '#', '#', 'idConf', 0);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (22, 'rateio', 'rateio.jsf', 'ui-icon-disk', 'idrateio', 21);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (23, 'Encaixe', 'encaixe.jsf', 'ui-icon-folder-open', 'idmmatendimento', 1);
insert into menu (pk_menu, nm_menu, nm_url, nm_icone, id_obj, nivel) values (24, 'Layout', 'configuraThemas.jsf', 'ui-icon-disk', 'idLayout', 21);

insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(1, 1, 4,1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(1, 2, 4,1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(1, 3, 4,1);
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(1, 23, 4,1);

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
insert into plano_perfil_menu (fk_plano, fk_menu, fk_perfil, fk_status) values(3, 24, 1, 1);
