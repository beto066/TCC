-- insert into Pessoa(id, nome, cpf, idade) values (nextval('hibernate_sequence'), 'Fredson', '111', 37);
-- insert into Pessoa(id, nome, cpf, idade) values (nextval('hibernate_sequence'), 'Silvano', '222', 40);
-- insert into Pessoa(id, nome, cpf, idade) values (nextval('hibernate_sequence'), 'Marco', '333', 41);

-- insert into Estado(nome, data_cadastro, sigla) values ('Tocantins', '08-25-2022', 'TO');
-- insert into Estado(nome, data_cadastro, sigla) values ('Goiás', '08-25-2022', 'GO');
-- insert into Estado(nome, data_cadastro, sigla) values ('Maranhão', '08-25-2022', 'MA');
-- insert into Estado(nome, data_cadastro, sigla) values ('Pará', '08-25-2022', 'PA');
-- insert into Estado(nome, data_cadastro, sigla) values ('Rio de Janeiro', '08-25-2022', 'RJ');
-- insert into Estado(nome, data_cadastro, sigla) values ('São Paulo', '08-25-2022', 'SP');

-- insert into Cidade(nome, data_cadastro, id_estado) values ('Palmas', '08-25-2022', 1);
-- insert into Cidade(nome, data_cadastro, id_estado) values ('Gurupi', '08-25-2022', 1);

insert into Usuario(nome, email, senha, data_cadastro) values ('Jon Snow', 'jon@gmail.com', 'AceO9r1pjFITo2FgWL4z1xynZ20NbQRVLKL7ztuXU1zH7nrZXlvkmFXIOJBB1c7eXpo6ALvWMJv1APx2QtzdkA==', '10-18-2022');
insert into Usuario(nome, email, senha, data_cadastro) values ('Vegeta', 'vegeta@gmail.com', 'AceO9r1pjFITo2FgWL4z1xynZ20NbQRVLKL7ztuXU1zH7nrZXlvkmFXIOJBB1c7eXpo6ALvWMJv1APx2QtzdkA==', '10-18-2022');

-- insert into Parceiro(nome, descricao) values ('Ponto do Açai', '25% de desconto no plano mensal');
-- insert into Parceiro(nome, descricao) values ('Açai Delícia', '10% de desconto em compras a partir de R$ 100');
-- insert into Parceiro(nome, descricao) values ('Shopping Eldorado', '10% de desconto a vista');
-- insert into Parceiro(nome, descricao) values ('Posto Petro', '25% de desconto no plano mensal');
-- insert into Parceiro(nome, descricao) values ('Centauro', '10% de desconto em compras a partir de R$ 100');
-- insert into Parceiro(nome, descricao) values ('Americanas', '10% de desconto a vista');

-- insert into Evento(nome, data, descricao) values ('CHUPA', '11-10-2022', 'Evento para maiores de 18 anos, necessario apresentação de documento pessoal');
-- insert into Evento(nome, data, descricao) values ('GREVE', '11-10-2022', 'Evento para maiores de 18 anos, necessario apresentação de documento pessoal');
-- insert into Evento(nome, data, descricao) values ('Calourada', '11-10-2022', 'Evento para maiores de 18 anos, necessario apresentação de documento pessoal');


-- insert into Endereco(cep, logradouro, numero, id_cidade, id_evento) values ('554433', 'Rua do caju', '34', 1, 2);
-- insert into Endereco(cep, logradouro, numero, id_cidade, id_evento) values ('554444', 'Rua da mangueira', '14',2, 3);
-- insert into Endereco(cep, logradouro, numero, id_cidade, id_evento) values ('554499', 'Av. do cerjiu', '25', 1, 2);

-- insert into Esporte(nome, modalidade) values ('Futebol', 'Com os pés');  
-- insert into Esporte(nome, modalidade) values ('Volei', 'Com as mãos');

-- insert into Produto(nome, quantidade, preco, desconto, descricao, marca,link) values ('camisa', 3, 2.99, 0, 'camisa legal', 'rener','https://mpago.la/1SvZ4W5');
-- insert into Produto(nome, quantidade, preco, desconto, descricao, marca) values ('caneco', 5, 349.99, 0, 'cuphed legal', 'propio');


-- insert into evento_esporte(id_envento, id_esporte) values (1, 1);
-- insert into evento_esporte(id_envento, id_esporte) values (1, 2);
-- insert into evento_esporte(id_envento, id_esporte) values (2, 1);
-- insert into evento_esporte(id_envento, id_esporte) values (2, 2);

insert into Roles(id_usuario, role) values (1, 'Admin');
insert into Roles(id_usuario, role) values (2, 'User');