<img width="1600" style="height:auto; border-radius: 12px;" alt="banner Gostô?" src="docs/images/banner.png" />

# Gostô?

> Rede social de avaliações que conecta moradores de Belo Horizonte a pequenos e médios estabelecimentos de Alimentação, Entretenimento e Serviços — com rankings, reviews, fotos e reservas de mesa online.

🔗 **Acesse o projeto:** [gostoo.vercel.app](https://gostoo.vercel.app)

---

## 🛠️ Stack principal

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-JWT-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Astro](https://img.shields.io/badge/Astro-4-BC52EE?style=for-the-badge&logo=astro&logoColor=white)
![Tailwind CSS](https://img.shields.io/badge/Tailwind_CSS-3-06B6D4?style=for-the-badge&logo=tailwindcss&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

---

## 📑 Sumário

- [Sobre o projeto](#-sobre-o-projeto)
- [Arquitetura](#️-arquitetura)
- [Estrutura de pastas](#-estrutura-de-pastas)
- [Documentação completa](#-documentação-completa)
- [Instalação e execução](#-instalação-e-execução)
- [Variáveis de ambiente](#-variáveis-de-ambiente)
- [Histórico de versões](#-histórico-de-versões)
- [Equipe](#-equipe)
- [Licença](#-licença)

---

## 📖 Sobre o projeto

O **Gostô?** é uma plataforma digital que promove a visibilidade de pequenos e médios estabelecimentos de Belo Horizonte, fortalecendo sua competitividade no mercado local. Avaliadores cadastrados avaliam estabelecimentos com notas, fotos e reviews; os negócios são organizados em categorias (**Alimentação**, **Entretenimento** e **Serviços**) e tags específicas, e classificados em rankings de destaque geral e de novos estabelecimentos.

Gerentes podem cadastrar seus negócios, gerenciar mesas e acompanhar pedidos de reserva — os avaliadores, por sua vez, descobrem novos lugares, avaliam suas experiências e reservam mesas diretamente pela plataforma, sem precisar telefonar para o estabelecimento.

O projeto foi desenvolvido como trabalho da disciplina de Engenharia de Software do curso de Engenharia de Software da PUC Minas. A documentação acadêmica completa — contextualização, modelagem dos processos de negócio, projeto da solução e indicadores — está disponível em [`docs/`](docs/).

---

## 🏛️ Arquitetura

O projeto é dividido em dois módulos independentes dentro de [`src/`](src/): um **backend** REST em Spring Boot e um **frontend** em Astro, que se comunicam via HTTP/JSON autenticado por JWT.

```
┌───────────────────────────┐        REST + JWT        ┌────────────────────────────┐
│   Frontend (Astro + SSR)  │ ───────────────────────▶ │   Backend (Spring Boot)    │
│   src/front               │ ◀─────────────────────── │   src/back                 │
│   Tailwind CSS            │        JSON / multipart  │   Spring Security · JJWT   │
└───────────────────────────┘                          └─────────────┬──────────────┘
                                                                      │  Spring Data JPA
                                                                      ▼
                                                              ┌───────────────┐
                                                              │     MySQL     │
                                                              └───────────────┘
```

**Domínios principais do backend** (`controller` / `model`): `Avaliador`, `Estabelecimento`, `Avaliacao` (com `Imagem` e `Tag`), `Mesa` e `Reserva` — cobrindo os cinco processos de negócio documentados em [`docs/`](docs/).

---

## 📁 Estrutura de pastas

```
Gostoo/
├── docs/                    # Documentação acadêmica (processos, projeto da solução, indicadores)
│   ├── images/              # Banners, diagramas e imagens dos processos
│   ├── presentations/       # Slides da apresentação final (PDF)
│   └── video/               # Vídeo de apresentação final
├── src/
│   ├── back/                # API REST — Spring Boot + MySQL + JWT
│   │   └── src/main/java/com/br/timn/Gosto/
│   │       ├── controller/  # Endpoints REST (Avaliador, Estabelecimento, Mesa, Reserva...)
│   │       ├── service/     # Regras de negócio (interfaces + implementação)
│   │       ├── model/       # Entidades JPA e DTOs (get/update/type)
│   │       ├── repository/  # Repositórios Spring Data JPA
│   │       ├── security/    # Autenticação JWT (filtro, util, login)
│   │       └── validation/  # Validação de entidades de entrada
│   └── front/               # Aplicação web — Astro + Tailwind CSS
│       └── src/
│           ├── pages/       # Rotas (login, cadastro, descubra, perfil...)
│           ├── components/  # Componentes reutilizáveis (Card, Header, Mesa, Reserva...)
│           └── layouts/     # Layout base das páginas
├── Dockerfile               # Build multi-stage do backend (Maven + JRE)
├── CITATION.cff
└── LICENSE
```

---

## 📚 Documentação completa

| Documento | Conteúdo |
|---|---|
| [`docs/README.md`](docs/README.md) | Documentação acadêmica completa: contextualização, problema, participantes, sistemas concorrentes e processos de negócio |
| [`docs/solution-design.md`](docs/solution-design.md) | Projeto da solução — modelo relacional e tecnologias |
| [`docs/performance-indicators.md`](docs/performance-indicators.md) | Indicadores de desempenho dos processos |
| [`docs/interface.md`](docs/interface.md) | Documentação da interface do sistema |
| [`docs/processo-*.md`](docs/) | Detalhamento de cada um dos 5 processos de negócio (avaliação, contas, reservas e mesas) |
| [`src/back/README.md`](src/back/README.md) | Documentação da API: endpoints, variáveis de ambiente e execução do backend |
| [`src/front/README.md`](src/front/README.md) | Documentação do frontend: páginas, comandos e execução |

---

## 🚀 Instalação e execução

### Pré-requisitos

- [Node.js](https://nodejs.org/) 18+
- [Java](https://www.oracle.com/java/technologies/downloads/) 17+ e [Maven](https://maven.apache.org/) (ou use o `mvnw` incluso)
- Uma instância MySQL acessível (local ou hospedada)

O projeto pode ser executado de duas formas: conectando o frontend a um backend já hospedado, ou rodando frontend e backend localmente.

### Opção 1 — Frontend conectado ao backend hospedado

```bash
cd src/front
```

Crie um arquivo `.env` em `src/front` com:

```env
PUBLIC_API_URL="https://gostoo-36vv.onrender.com"
```

Depois instale as dependências e suba o servidor de desenvolvimento:

```bash
npm install
npm run dev
```

Acesse o link gerado no terminal (por padrão `http://localhost:4321`).

### Opção 2 — Rodando tudo localmente

1. **Backend** — siga as instruções de configuração e execução em [`src/back/README.md`](src/back/README.md) (variáveis de ambiente do banco, JWT e `./mvnw spring-boot:run`).

2. **Frontend** — em outro terminal:

   ```bash
   cd src/front
   ```

   Crie o `.env` apontando para o backend local:

   ```env
   PUBLIC_API_URL="http://localhost:8080"
   ```

   Depois:

   ```bash
   npm install
   npm run dev
   ```

3. Acesse o link gerado no terminal (por padrão `http://localhost:4321`).

> Detalhes de cada módulo (build, testes, Docker, variáveis de ambiente) estão documentados em [`src/back/README.md`](src/back/README.md) e [`src/front/README.md`](src/front/README.md).

---

## 🔑 Variáveis de ambiente

| Módulo | Variável | Descrição |
|---|---|---|
| Frontend | `PUBLIC_API_URL` | URL base da API consumida pelo Astro (local ou hospedada) |
| Backend | `SPRING_DATASOURCE_HOST` / `_PORT` / `_DBNAME` | Conexão com o MySQL |
| Backend | `SPRING_DATASOURCE_USERNAME` / `_PASSWORD` | Credenciais do banco |
| Backend | `JWT_SECRET` | Chave usada para assinar e validar os tokens JWT |

> A lista completa, com explicações de cada variável do backend, está em [`src/back/README.md`](src/back/README.md#-variáveis-de-ambiente).

---

## 🗓️ Histórico de versões

#### Versões 0.0.x
- **0.0.1** — Início do projeto: estudo das necessidades locais e modelagem inicial do processo de rankeamento.
- **0.0.2** — Definição dos cinco principais processos de negócio.
- **0.0.3 a 0.0.7** — Implementação sequencial dos processos 1 a 5.

#### Versões 0.1.x
- **0.1.0** — Implementação da categorização: categorias principais (Alimentação, Entretenimento, Serviços) e organização em tags específicas.
- **0.1.1** — Atualização das documentações do projeto, incluindo o README do repositório (sem alteração de código).
- **0.1.2** — Layout inicial do site, com design responsivo e definição da identidade visual.
- **0.1.3** — Escolha das tecnologias e configuração dos ambientes de desenvolvimento e produção.
- **0.1.4** — Modelagem detalhada dos processos de negócio e criação do diagrama ER do banco de dados.
- **0.1.5** — Modelos de dados para cadastro de estabelecimentos e avaliações, e endpoints iniciais.

#### Versão 2.0.0
- Versão final do projeto, consolidando as funcionalidades e os processos definidos nas etapas anteriores.

---

## 👥 Equipe

### Integrantes

- Athos Marques
- Bernardo Alvim
- Gabriela Alvarenga
- João Elias
- Luísa Jardim
- Lucas de Souza
- Marcos Alberto

### Professoras

- Profa. Eveline Alonso Veloso
- Profa. Joana Gabriela Ribeiro de Souza
- Profa. Juliana Amaral Baroni de Carvalho

_Curso de Engenharia de Software — Instituto de Informática e Ciências Exatas, PUC Minas, Belo Horizonte – MG – Brasil._

---

## 📄 Licença

Distribuído sob a licença MIT — veja [`LICENSE`](LICENSE) para mais detalhes.

---

<div align="center">
  <img width="70%" alt="banner institucional PUC Minas" src="docs/images/banner-institucional.svg"/>
</div>
<p align="center">Fonte do banner: <a href="https://github.com/joaopauloaramuni">João Paulo Carneiro Aramuni</a></p>
