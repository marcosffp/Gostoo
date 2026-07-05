<img width="1600" style="height:auto; border-radius: 12px;" alt="banner Gostô?" src="../../docs/images/gosto-banner.png" />

# Frontend

> Aplicação web do **Gostô?** — feita em Astro com Tailwind CSS, consome a API REST do backend para cadastro, login, descoberta de estabelecimentos, avaliações e reservas.

🔗 **Deploy:** [gostoo.vercel.app](https://gostoo.vercel.app)

---

## 🛠️ Stack

![Astro](https://img.shields.io/badge/Astro-4.14-BC52EE?style=for-the-badge&logo=astro&logoColor=white)
![Tailwind CSS](https://img.shields.io/badge/Tailwind_CSS-3.4-06B6D4?style=for-the-badge&logo=tailwindcss&logoColor=white)
![TypeScript](https://img.shields.io/badge/TypeScript-3178C6?style=for-the-badge&logo=typescript&logoColor=white)
![Chart.js](https://img.shields.io/badge/Chart.js-FF6384?style=for-the-badge&logo=chartdotjs&logoColor=white)
![Node.js](https://img.shields.io/badge/Node.js-18+-339933?style=for-the-badge&logo=nodedotjs&logoColor=white)

---

## 📑 Sumário

- [Sobre o módulo](#-sobre-o-módulo)
- [Estrutura de pastas](#-estrutura-de-pastas)
- [Páginas](#-páginas)
- [Variáveis de ambiente](#-variáveis-de-ambiente)
- [Instalação e execução](#-instalação-e-execução)
- [Comandos disponíveis](#-comandos-disponíveis)

---

## 📖 Sobre o módulo

Esta é a interface web do **Gostô?**, construída com **Astro** (modo `server`, com renderização sob demanda) e estilizada com **Tailwind CSS** e ícones via `astro-icon`. A aplicação consome a API REST do [backend](../back) — autenticando o usuário com JWT (armazenado via `js-cookie`/`jwt-decode`) — para permitir cadastro e login de avaliadores e estabelecimentos, descoberta e avaliação de locais, upload de fotos, reservas de mesa e visualização de indicadores de desempenho (com gráficos via `Chart.js`).

## 📁 Estrutura de pastas

```
front/
├── public/
│   └── background/             # Imagens estáticas de fundo
├── src/
│   ├── pages/                  # Rotas (uma página por arquivo .astro)
│   │   └── perfil/
│   │       ├── avaliador/      # Perfil público de avaliador ([email].astro)
│   │       └── estabelecimento/# Perfil público de estabelecimento ([email].astro)
│   ├── components/             # Componentes reutilizáveis (Header, Footer, Card, Mesa, Reserva...)
│   ├── layouts/                # Layout base (Layout.astro)
│   └── assets/
│       ├── styles/             # Estilos globais
│       └── scripts/            # Scripts client-side
├── astro.config.mjs
├── tailwind.config.mjs
├── package.json
└── .env                        # Nunca versionar (PUBLIC_API_URL)
```

## 🧭 Páginas

| Rota | Arquivo | Descrição |
|---|---|---|
| `/` | `pages/index.astro` | Landing page com apresentação do projeto |
| `/login` | `pages/login.astro` | Autenticação de avaliadores e estabelecimentos |
| `/cadastro` | `pages/cadastro.astro` | Cadastro de novos usuários |
| `/descubra` | `pages/descubra.astro` | Busca e filtro de estabelecimentos por categoria/tag |
| `/perfil` | `pages/perfil.astro` | Perfil do usuário autenticado |
| `/perfil/avaliador/[email]` | `pages/perfil/avaliador/[email].astro` | Perfil público de um avaliador |
| `/perfil/estabelecimento/[email]` | `pages/perfil/estabelecimento/[email].astro` | Perfil público de um estabelecimento, com avaliações, mesas e indicadores |

## 🔑 Variáveis de ambiente

Crie um arquivo `.env` na raiz de `src/front/` com:

```env
PUBLIC_API_URL="http://localhost:8080"
```

> Use a URL do backend hospedado (ex.: `https://gostoo-36vv.onrender.com`) ou do backend local (`http://localhost:8080`), conforme o ambiente desejado — veja o [README raiz](../../README.md#-instalação-e-execução).

## 🚀 Instalação e execução

### Pré-requisitos

- [Node.js](https://nodejs.org/) 18+

### Passo a passo

```bash
# 1. Acesse a pasta do frontend
cd src/front

# 2. Crie o .env (ver seção acima)

# 3. Instale as dependências
npm install

# 4. Inicie o servidor de desenvolvimento
npm run dev
```

Após a execução, um link local será exibido no terminal (por padrão `http://localhost:4321`) — copie e cole no navegador para acessar a aplicação.

## 🧞 Comandos disponíveis

Todos os comandos são executados a partir da raiz de `src/front`, em um terminal:

| Comando | Ação |
|---|---|
| `npm install` | Instala as dependências |
| `npm run dev` | Inicia o servidor de desenvolvimento local |
| `npm run build` | Gera o build de produção em `./dist/` |
| `npm run preview` | Pré-visualiza o build de produção localmente |
| `npm run astro ...` | Executa comandos da CLI do Astro (`astro add`, `astro check`...) |
