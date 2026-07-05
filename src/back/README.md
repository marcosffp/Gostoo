<img width="1600" style="height:auto; border-radius: 12px;" alt="banner Gostô?" src="../../docs/images/gosto-banner.png" />

# Backend

> API REST do **Gostô?** — autenticação JWT, cadastro de avaliadores e estabelecimentos, avaliações com fotos, gestão de mesas e reservas, persistidos em MySQL via Spring Data JPA.

🔗 **Deploy:** [gostoo-36vv.onrender.com](https://gostoo-36vv.onrender.com)

---

## 🛠️ Stack

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.10-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-JWT-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![JJWT](https://img.shields.io/badge/JJWT-0.11.5-000000?style=for-the-badge)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

---

## 📑 Sumário

- [Sobre o módulo](#-sobre-o-módulo)
- [Arquitetura](#️-arquitetura)
- [Estrutura de pastas](#-estrutura-de-pastas)
- [Autenticação](#-autenticação)
- [Endpoints da API](#-endpoints-da-api)
- [Variáveis de ambiente](#-variáveis-de-ambiente)
- [Instalação e execução](#-instalação-e-execução)
- [Build e testes](#-build-e-testes)
- [Docker](#-docker)

---

## 📖 Sobre o módulo

Este é o backend do **Gostô?**: uma API REST construída com **Spring Boot** que centraliza as regras de negócio dos cinco processos do projeto — gestão de contas (avaliador e estabelecimento), avaliação e review com upload de fotos, organização por categorias e tags, gestão de mesas e reservas. A persistência é feita em **MySQL** via Spring Data JPA, e o acesso é protegido por autenticação **JWT** com Spring Security.

## 🏛️ Arquitetura

A aplicação segue a separação clássica em camadas do Spring Boot — `controller` → `service` (interface + implementação) → `repository` — com pacotes dedicados para modelagem (`model`, incluindo DTOs em `model/get` e `model/update`), segurança (`security`), validação de entrada (`validation`) e tratamento de exceções (`exception`).

```
┌──────────────────────────────────────────────┐
│                  controller                   │   Endpoints REST (JSON / multipart)
└───────────────────────┬───────────────────────┘
                         │
┌───────────────────────▼───────────────────────┐
│           service (interfaces + impl)         │   Regras de negócio dos 5 processos
└───────────────────────┬───────────────────────┘
                         │
┌───────────────────────▼───────────────────────┐
│                  repository                    │   Spring Data JPA
└───────────────────────┬───────────────────────┘
                         │
                    ┌────▼────┐
                    │  MySQL  │
                    └─────────┘

security/  → JwtRequestFilter, JwtTokenUtil, LoginRequest/Response, criptografia de senha (BCrypt)
validation/→ Validação das entidades de entrada (Avaliador, Estabelecimento, Avaliação, Mesa, Reserva...)
```

**Entidades principais** (`model`): `Avaliador`, `Estabelecimento`, `Avaliacao`, `Imagem`, `Tag`, `Mesa`, `Reserva` — com enums de apoio `Status` (`PENDENTE`, `APROVADA`, `REJEITADA`) para reservas e `Tipo` (`PERFIL`, `BANNER`, `OUTRO`) para imagens.

## 📁 Estrutura de pastas

```
back/
├── src/
│   ├── main/
│   │   ├── java/com/br/timn/Gosto/
│   │   │   ├── GostoApplication.java
│   │   │   ├── controller/      # Endpoints REST
│   │   │   ├── service/
│   │   │   │   ├── interfaces/
│   │   │   │   └── implementacao/
│   │   │   ├── model/
│   │   │   │   ├── get/         # DTOs de saída
│   │   │   │   ├── update/      # DTOs de atualização
│   │   │   │   └── type/        # Enums (Status, Tipo)
│   │   │   ├── repository/      # Repositórios Spring Data JPA
│   │   │   ├── security/        # JWT (filtro, util, login, criptografia)
│   │   │   ├── validation/
│   │   │   │   └── validacaoEntidades/
│   │   │   ├── config/          # CORS e Spring Security
│   │   │   ├── indices/         # Indicadores de desempenho dos estabelecimentos
│   │   │   ├── exception/       # Tratamento de exceções
│   │   │   └── util/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
├── mvnw / mvnw.cmd
└── .env                         # Nunca versionar em produção
```

## 🔐 Autenticação

A API usa **JWT** (JSON Web Token) via Spring Security, com sessão `STATELESS` e senhas criptografadas com **BCrypt**. O fluxo é:

1. Cadastro público em `/avaliador/cadastro` ou `/estabelecimento/cadastro`.
2. Login em `POST /login` com e-mail e senha, retornando um token JWT.
3. Demais endpoints exigem o cabeçalho `Authorization: Bearer <token>` — validado pelo `JwtRequestFilter` antes de qualquer outro filtro de autenticação.

| Endpoint | Acesso |
|---|---|
| `POST /login` | Público |
| `POST /avaliador/cadastro` | Público |
| `POST /estabelecimento/cadastro` | Público |
| `OPTIONS /**` | Público (preflight CORS) |
| Todos os demais | Requer JWT válido |

## 🌐 Endpoints da API

### Login (`/login`)

| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/login` | Autentica com e-mail e senha e retorna o token JWT |

### Avaliadores (`/avaliador`)

| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/avaliador/cadastro` | Cadastrar novo avaliador |
| `GET` | `/avaliador/{avaliadorEmail}` | Buscar avaliador por e-mail |
| `PUT` | `/avaliador/{id}` | Atualizar dados do avaliador |
| `DELETE` | `/avaliador/{avaliadorId}` | Excluir avaliador |
| `GET` | `/avaliador/{avaliadorId}/avaliacoes` | Listar avaliações feitas pelo avaliador |
| `GET` | `/avaliador/{avaliadorId}/reservas` | Listar reservas do avaliador |

### Estabelecimentos (`/estabelecimento`)

| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/estabelecimento/cadastro` | Cadastrar novo estabelecimento |
| `GET` | `/estabelecimento/` | Listar estabelecimentos |
| `GET` | `/estabelecimento/{email}` | Buscar estabelecimento por e-mail |
| `PUT` | `/estabelecimento/{id}` | Atualizar dados do estabelecimento |
| `DELETE` | `/estabelecimento/{id}` | Excluir estabelecimento |
| `GET` | `/estabelecimento/{estabelecimentoId}/tags` | Listar tags do estabelecimento |
| `POST` | `/estabelecimento/{estabelecimentoId}/tags/{nomeTag}` | Adicionar tag |
| `DELETE` | `/estabelecimento/{estabelecimentoId}/tags/{nomeTag}` | Remover tag |
| `GET` | `/estabelecimento/{estabelecimentoId}/imagens` | Listar imagens (perfil/banner) |
| `POST` | `/estabelecimento/{estabelecimentoId}/imagens` | Enviar imagem (multipart) |
| `DELETE` | `/estabelecimento/{estabelecimentoId}/imagens/{imagemId}` | Remover imagem |
| `GET` | `/estabelecimento/{estabelecimentoId}/avaliacoes` | Listar avaliações recebidas |
| `GET` | `/estabelecimento/{estabelecimentoId}/mesas` | Listar mesas do estabelecimento |
| `GET` | `/estabelecimento/{estabelecimentoId}/reservas` | Listar reservas do estabelecimento |

### Avaliações (`/avaliacao`)

| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/avaliacao/cadastro` | Criar avaliação (nota + review) |
| `GET` | `/avaliacao/` | Listar avaliações |
| `GET` | `/avaliacao/{idAvaliacao}` | Detalhes de uma avaliação |
| `PUT` | `/avaliacao/{id}` | Editar avaliação |
| `DELETE` | `/avaliacao/{id}` | Excluir avaliação |
| `GET` | `/avaliacao/{avaliacaoId}/imagens` | Listar fotos da avaliação |
| `POST` | `/avaliacao/{avaliacaoId}/imagens` | Anexar foto à avaliação (multipart) |
| `DELETE` | `/avaliacao/{avaliacaoId}/imagens/{imagemId}` | Remover foto da avaliação |

### Mesas (`/mesa`)

| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/mesa/cadastro` | Cadastrar mesa |
| `GET` | `/mesa/` | Listar mesas |
| `GET` | `/mesa/{idMesa}` | Detalhes de uma mesa |
| `PUT` | `/mesa/{id}` | Atualizar mesa |
| `DELETE` | `/mesa/{id}` | Excluir mesa |
| `GET` | `/mesa/{idMesa}/reservas` | Listar reservas da mesa |
| `POST` | `/mesa/{idMesa}/reservas/{idReserva}` | Vincular reserva à mesa |
| `DELETE` | `/mesa/{idMesa}/reservas/{idReserva}` | Desvincular reserva da mesa |

### Reservas (`/reserva`)

| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/reserva/cadastro` | Criar solicitação de reserva |
| `GET` | `/reserva/` | Listar reservas |
| `GET` | `/reserva/{idReserva}` | Detalhes de uma reserva |
| `PUT` | `/reserva/{id}` | Atualizar reserva |
| `PUT` | `/reserva/{idReserva}/aprovar` | Aprovar reserva (gerente) |
| `PUT` | `/reserva/{idReserva}/rejeitar` | Rejeitar reserva (gerente) |

> O `Status` de uma reserva transita entre `PENDENTE`, `APROVADA` e `REJEITADA`.

### Indicadores (`/indices`)

| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/indices/{idEstabelecimento}` | Indicadores de desempenho do estabelecimento |
| `GET` | `/indices/{idEstabelecimento}/{indiceNome}` | Indicador específico por nome |

## 🔑 Variáveis de ambiente

Crie um arquivo `.env` (ou configure variáveis de ambiente) na raiz de `src/back/` com:

```dotenv
# ── Banco de dados (MySQL) ─────────────────────
SPRING_DATASOURCE_HOST=localhost
SPRING_DATASOURCE_PORT=3306
SPRING_DATASOURCE_DBNAME=gosto
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=sua_senha

# ── Segurança ──────────────────────────────────
JWT_SECRET=chave_jwt_minimo_256_bits
```

> `spring.jpa.hibernate.ddl-auto=update` mantém o esquema do banco sincronizado com as entidades a cada inicialização — não é necessário rodar migrations manuais em desenvolvimento.

## 🚀 Instalação e execução

### Pré-requisitos

- Java 17+
- Maven 3.9+ (ou utilize o `./mvnw` incluso)
- Uma instância MySQL acessível, com um banco criado conforme `SPRING_DATASOURCE_DBNAME`

### Passo a passo

```bash
# 1. Acesse a pasta do backend
cd src/back

# 2. Configure as variáveis de ambiente (ver seção acima)

# 3. Suba a aplicação
./mvnw spring-boot:run
```

A API ficará disponível em `http://localhost:8080`.

## 🔧 Build e testes

```bash
# Build completo (gera o JAR em target/)
./mvnw clean package

# Rodar a aplicação a partir do JAR gerado
java -jar target/Gosto-0.0.1-SNAPSHOT.jar
```

> O `maven-surefire-plugin` está configurado com `skipTests=true` — os testes não são executados durante o `package` por padrão.

## 🐳 Docker

O `Dockerfile` na raiz do repositório faz um build multi-stage (Maven + JDK para compilar, JRE para executar):

```bash
# A partir da raiz do repositório
docker build -t gostoo-backend .
docker run -p 8080:8080 --env-file src/back/.env gostoo-backend
```
