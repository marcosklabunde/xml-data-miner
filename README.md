# Projeto de leitura de dados de arquivos XML
Para esse projeto foram utilizados: Java 11, Spring Boot 2.7.7, Spring Data, Hibernate, H2, Angular 15 e Angular Material.

## Execução do projeto
 - Executar DataminerApplication.java para subir a aplicação Spring;
 - No terminal, acessar a pasta do frontend (cd src/main/frontend) e executar o comando "ng serve" para subir 
a aplicação angular.

## Teste de carga

Para testar o desempenho da aplicação utilizei um arquivo XML de pouco mais de um milhão de linhas, que levou cerca de 
740ms para ser lido e convertido em suas respectivas classes (fora o tempo de gravação em banco de dados). 
Esse arquivo se encontra na pasta "main/resources" e pode ser utilizado para testes (o tempo de parse do arquivo é 
printado no log da aplicação).

## Endpoints

POST: /file/upload - Envio de arquivo xml
<br>
GET: /region/list - Consulta de regiões
<br>
GET: /agent/list - Consulta de agentes

## H2 Database

Por ser um projeto teste, optei por utilizar um banco relacional in memory, evitando a necessidade da instalação de 
algum banco de dados para a execução do projeto. Para acessar a database do projeto: 
 - Acessar: 127.0.0.1:8080/h2-ui
 - JDBC URL: jdbc:h2:mem:dataminerdb
 - Username: sa
 - Password: 123

## Melhorias para o projeto

 - Criar tela para apresentação dos dados salvos em banco;
 - Dar feedback de erro ou sucesso de envio e leitura dos arquivos;
 - Validar estrutura do arquivo XML;
 - Ao invés de enviar uma requisição por arquivo selecionado, enviar uma requisição com todos os aruivos, receber a 
lista de arquivos no backend e abrir uma thread para cada.