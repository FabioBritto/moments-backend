# 📷 AppMoments - Backend API

Uma API REST robusta para gerenciamento de fotos em molduras personalizadas. Os clientes podem registrar fotos, organizá-las em eventos e processar pagamentos para obter suas cópias.

---

## 📋 Sumário

- [Sobre o Projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Pré-requisitos](#pré-requisitos)
- [Instalação](#instalação)
- [Uso](#uso)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Tecnologias](#tecnologias)
- [Testes](#testes)
- [Documentação da API](#documentação-da-api)
- [Licença](#licença)
- [Autor](#autor)

---

## Sobre o Projeto

AppMoments é uma aplicação web/mobile que permite aos usuários:
- 📸 Registrar e armazenar fotos (futuramente, em molduras personalizadas)
- 🗂️ Organizar fotos em eventos
- 💳 Realizar pagamentos para obter cópias impressas das fotos
- 🔐 Autenticação e autorização segura

### 🏗️ Nota sobre a Arquitetura

Este projeto é um **exemplo intencional de Overengineering**. O objetivo foi aplicar e demonstrar boas práticas arquiteturais, padrões de design e tecnologias modernas, **mesmo que a complexidade de recursos seja relativamente simples**.

A intenção é servir como **material de estudo** para:
- Arquitetura em camadas (Controller → Service → Repository)
- Padrões SOLID
- Tratamento de exceções globalizado
- Validação de dados
- Segurança com Spring Security
- Versionamento de banco de dados com Liquibase
- Cache com Redis
- Containerização com Docker
- Boas práticas de desenvolvimento

Este é um **caso de uso educacional** onde a complexidade técnica foi priorizada para fins de aprendizado e demonstração de conhecimento.

---

## ✨ Funcionalidades

- **Autenticação e Autorização**: Integrado com Spring Security
- **Gerenciamento de Clientes**: CRUD para clientes
- **Gestão de Eventos**: Criar e organizar eventos com fotos
- **Armazenamento de Fotos**: Gerenciamento eficiente de imagens
- **Processamento de Pagamentos**: Integração com gateway de pagamento (**ASAAS**)
- **Validação de Dados**: Validação em camada de entrada
- **Versionamento de Banco de Dados**: Liquibase para migrations
- **Cache**: Redis para otimização de performance
- **Containerização**: Docker para deploy simplificado

---

## 🔧 Pré-requisitos

Antes de iniciar, certifique-se de ter instalado:

- **Git** (2.25+)
- **Docker** e **Docker Compose** (20.10+)
- **JDK 21** ou superior
- **Maven** (3.8+)
- **IDE**: IntelliJ IDEA recomendado
- **Cliente API**: Postman, Insomnia ou semelhante (opcional)

---

## 📦 Instalação

### 1. Clonar o Repositório

```bash
git clone https://github.com/FabioBritto/moments-backend.git
cd moments-backend
```

### 2. Compilar o Projeto

Navegue até a raiz do projeto e execute:

```bash
mvn clean package
```

Isso irá compilar o projeto e gerar o artefato `.jar`.

### 3. Configurar Banco de Dados

⚠️ **IMPORTANTE**: Se você possui um serviço MySQL rodando localmente na **porta 3306**, é necessário **parar** esse serviço antes de prosseguir, pois o Docker Compose também utiliza essa porta.

```bash
# No Linux/macOS, você pode verificar:
sudo lsof -i :3306

# Para parar o MySQL (se estiver rodando):
sudo systemctl stop mysql
```

### 4. Iniciar com Docker Compose

Na raiz do projeto, execute:

```bash
docker compose up --build
```

Esse comando irá:
- Construir a imagem da aplicação
- Iniciar um container MySQL
- Iniciar um container Redis
- Executar as migrations do Liquibase
- Disponibilizar a API em `http://localhost:8080`

---

## 🚀 Uso

### Verificar se a API está rodando

```bash
curl http://localhost:8080/actuator/health
```

### Acessar a API

Use um cliente HTTP como Postman ou Insomnia e acesse os endpoints disponíveis em `http://localhost:8080`

### Parar a aplicação

```bash
docker compose down
```

---

## 📁 Estrutura do Projeto

```
src/main/java/br/com/britto/appmoments/
├── AppmomentsApplication.java          # Classe principal da aplicação
├── advice/                             # Tratamento global de exceções
├── controller/                         # Controladores REST
│   ├── AuthController.java            # Autenticação
│   ├── ClienteController.java         # Gerenciamento de clientes
│   ├── EventoController.java          # Gerenciamento de eventos
│   ├── FotoController.java            # Gerenciamento de fotos
│   └── PagamentoController.java       # Processamento de pagamentos
├── dto/                               # Objetos de transferência de dados
├── exception/                         # Exceções customizadas
├── model/                             # Entidades JPA
├── repository/                        # Repositórios Spring Data JPA
├── security/                          # Configurações de segurança
└── service/                           # Lógica de negócio

src/main/resources/
├── application.yml                    # Configurações gerais
├── application-test.yml              # Configurações de teste
└── db/changelog/                     # Scripts de migration (Liquibase)
```

---

## 🛠️ Tecnologias

### Backend
- **Java 21** - Linguagem de programação
- **Spring Boot 4.0.1** - Framework web
- **Spring Data JPA** - Persistência de dados
- **Spring Security** - Autenticação e autorização
- **Spring Validation** - Validação de dados

### Banco de Dados
- **MySQL** - SGBD relacional
- **Liquibase** - Versionamento de banco de dados
- **Redis** - Cache em memória

### Ferramentas
- **Maven** - Gerenciador de dependências e build
- **Docker** - Containerização
- **H2 Database** - Banco em memória para testes

---

## 📚 Documentação da API

### Endpoints Principais

#### Autenticação
- `POST /auth/login` - Realizar login retornando AccessToken e RefreshToken
- `POST /auth/refresh` - Gerar um novo RefreshToken
- `POST /auth/logout` - Revogar RefreshToken

#### Clientes
- `POST /api/clientes` - Criar novo cliente
- `PUT /api/clientes` - Atualizar cliente

#### Eventos
- `GET /api/eventos` - Listar todos os eventos
- `POST /api/eventos` - Criar novo evento
- `GET /api/eventos/{uuid}` - Buscar evento por UUID
- `GET /api/eventos/cliente/{id}` - Buscar evento por ID de Cliente
- `PUT /api/eventos` - Atualizar evento

#### Fotos
- `POST /api/foto` - Fazer upload de foto
#### Pagamentos
- `POST /api/pagamento/{uuidEvento}` - Gerar link de pagamento a partir do UUID do Evento

Para documentação completa com Swagger/OpenAPI, após iniciar a aplicação, acesse:
```
http://localhost:8080/swagger-ui.html
```

---

## 🔒 Autenticação

A aplicação usa **Spring Security** com tokens JWT. Para acessar endpoints protegidos:

1. Faça login em `/auth/login`
2. Copie o token retornado
3. Adicione o header: `Authorization: Bearer {token}`

---

## 📝 Variáveis de Ambiente

Configure em `application.yml` ou via variáveis de ambiente:

```yaml
server:
  port: 8080

spring:
  datasource:
    url: jdbc:mysql://mysql:3306/appmoments
    username: root
    password: root

  redis:
    host: redis
    port: 6379
```

---

## 🎯 Próximos Passos

Melhorias e funcionalidades planejadas para versões futuras:

### 📊 Logs para Observabilidade
- Adicionar rastreamento distribuído
- Métricas com Micrometer e Prometheus
- Health checks melhorados com Spring Boot Actuator

### 💳 Mais Opções de Pagamento
- Integração com Stripe, Paypal, etc.
- Múltiplas moedas e conversão em tempo real

### 🏗️ Design Patterns para Qualidade de Código
- **Padrão Builder**: Para classes Service com construtores muito grandes
- **Factory Pattern**: Para criação de entidades e DTOs complexas
- **Strategy Pattern**: Para diferentes estratégias de processamento de pagamento
- **Observer Pattern**: Para notificações de eventos
- **Decorator Pattern**: Para adicionar funcionalidades a serviços existentes
- **Template Method**: Para operações comuns em diferentes tipos de processamento

### ⚙️ Spring Profiles Melhor Explorados
- Perfil `dev` com configurações de desenvolvimento local
- Perfil `test` com H2 e dados mock
- Perfil `staging` com ambiente pré-produção
- Perfil `prod` com otimizações de performance e segurança
- Configurações específicas por perfil para logging, cache e banco de dados

### 🧪 Testes Unitários e de Integração
- Ampliação da cobertura de testes com **JUnit 5**
- Testes unitários para Services com **Mockito**
- Testes de integração para Controllers com **@SpringBootTest**
- Testes de repositório com **@DataJpaTest**
- Mock de dependências externas (Stripe, ASAAS, etc.)
- Uso de **TestContainers** para testes com containers reais
- Fixtures e builders para dados de teste
- Testes de segurança com **@WithMockUser**
- Relatórios de cobertura com JaCoCo

---

## 🤝 Colaborando

Contribuições são bem-vindas! Por favor:

1. Faça um Fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/NovaFuncionalidade`)
3. Commit suas mudanças (`git commit -m 'Adiciona nova funcionalidade'`)
4. Push para a branch (`git push origin feature/NovaFuncionalidade`)
5. Abra um Pull Request

---

## 📌 Versionamento

Este projeto segue [SemVer](https://semver.org/lang/pt-BR/) para versionamento.

---

## ✒️ Autor

**Fabio Britto**
- GitHub: [@FabioBritto](https://github.com/FabioBritto)
- LinkedIn: [LinkedIn](https://www.linkedin.com/in/fabio-britto-399223252/)
- Email: fabio.tritono@gmail.com

---


## 🎁 Suporte

Se este projeto foi útil para você:
- ⭐ Dê uma estrela no repositório
- 🔗 Compartilhe com a comunidade
- 💬 Abra uma issue para dúvidas ou sugestões
