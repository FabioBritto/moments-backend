# API MomentsAPP 

Este repositório é o Backend de um projeto que visa ser uma aplicação Web Mobile para registro de fotos em molduras personalizadas. Uma vez que o cliente registra uma foto, pode seguir para o pagamento para obtê-la.

## 🚀 Começando


### Pré-requisitos
- Git
- Docker
- JDK 21
- Maven
- IDE (recomendado IntelliJ)
- Postman ou Insomnia
- 
Para poder rodar e testar o projeto em sua máquina, comece clonando o repositório

```git clone https://github.com/FabioBritto/moments-backend.git```

Na raiz do projeto, rode o seguinte comando **Maven** para compilar o projeto e gerar o artefato .jar:

```mvn package```

Agora, com o terminal aberto na raiz do projeto, suba o container docker com o comando:

```docker compose up --build```

### ⚠️ ATENÇÃO

O projeto utiliza o banco MySQL, portanto, é importante que você **pare** o seu serviço, pois se não, a **porta 3306** que o Docker espera encontrar aberta, estará fechada, impossibilitando a API de fazer acesso correto ao banco.

Agora, basta abrir a aplicação de sua preferência (Postman ou Insomnia) e realizar o uso da API.

 

⚙️ Executando os testes
Explicar como executar os testes automatizados para este sistema.

🔩 Analise os testes de ponta a ponta
Explique que eles verificam esses testes e porquê.

Dar exemplos
⌨️ E testes de estilo de codificação
Explique que eles verificam esses testes e porquê.

Dar exemplos
📦 Implantação
Adicione notas adicionais sobre como implantar isso em um sistema ativo.

AQUI EU POSSO DESCREVER COMO UM FRONT END PODERIA CONSUMIR A API 

🛠️ Construído com
Mencione as ferramentas que você usou para criar seu projeto

- Java 21
- Spring:
  - Spring Boot
  - Spring Data JPA
  - Spring Web
  - Spring Security
  - Spring Validator
- Liquibase
- Docker
- Redis
- Maven

🖇️ Colaborando
Por favor, leia o COLABORACAO.md para obter detalhes sobre o nosso código de conduta e o processo para nos enviar pedidos de solicitação.

📌 Versão
Nós usamos SemVer para controle de versão. Para as versões disponíveis, observe as tags neste repositório.

✒️ Autores
Mencione todos aqueles que ajudaram a levantar o projeto desde o seu início

Um desenvolvedor - Trabalho Inicial - umdesenvolvedor
Fulano De Tal - Documentação - fulanodetal
Você também pode ver a lista de todos os colaboradores que participaram deste projeto.

📄 Licença
Este projeto está sob a licença (sua licença) - veja o arquivo LICENSE.md para detalhes.

🎁 Expressões de gratidão
Conte a outras pessoas sobre este projeto 📢;
Convide alguém da equipe para uma cerveja 🍺;
Um agradecimento publicamente 🫂;
etc.
⌨️ com ❤️ por Armstrong Lohãns 😊

## Objetivo incial

O objetivo inicial da construção desta API REST, era a de fazer uma aplicação completa que pudesse:
- Manter o cadastro de Clientes
- Manter o cadastro de Eventos

A ideia geral da aplicação é (descrever o objetivo da aplicação).

*** 
## O que mudou:

Decidi abandonar (pelo menos inicialmente) o desenvolvimento do FrontEnd da aplicação, e focar totalmente em adicionar todas as ferramentas que eu pudesse.
É notável que o meu projeto pode ser totalmente classificado como **"Overengineering"**. Mas tudo bem! O objetivo é este mesmo. Foi por isso, que, por minha conta e risco adicionei:
- Spring Security (descrever em subtópicos o que foi adicionado)
- Exception Handlers
- Validação de entrada de dados com Spring Validator
- Arquivo .env para variáveis de ambiente
- Alteração do arquivo de configuração de .properties para .yml
- Uso do Docker
- Uso do Redis
- Migration com Liquibase

***
## Pontos de Melhoria

***
## Futuro do Projeto

**NÃO ESQUECER DE ADICIONAR A LICENÇA**