# Gostô?

O objetivo principal do projeto **“Gostô?”** é promover a visibilidade de pequenos e médios estabelecimentos de Belo Horizonte, fortalecendo sua competitividade no mercado digital. A plataforma busca destacar esses negócios por meio de avaliações de usuários, incentivando o engajamento e a descoberta de novas experiências. Com a organização dos estabelecimentos em categorias e tags específicas, o projeto facilita a navegação e conexão entre consumidores e empreendedores locais.

Além disso, o **“Gostô?”** visa criar uma interação justa e eficiente ao implementar recursos como rankings diferenciados, compartilhamento de fotos e reviews, e a possibilidade de reservas. Esses elementos não só ajudam a valorizar os negócios locais, mas também promovem a cultura e o turismo da cidade, equilibrando a competição digital e fortalecendo a economia regional.

## Integrantes

* Athos Marques
* Bernardo Alvim
* Gabriela Alvarenga
* João Elias
* Luísa Jardim
* Lucas de Souza
* Marcos Alberto

## Professor

Prof. Eveline Alonso Veloso

Prof. Joana Gabriela Ribeiro de Souza

Prof. Juliana Amaral Baroni de Carvalho

## Instruções de utilização

Assim que a primeira versão do sistema estiver disponível, deverá complementar com as instruções de utilização. Descreva como instalar eventuais dependências e como executar a aplicação.

## Histórico de versões
Versões do Projeto “Gostô?”
#### Versões 0.0.x
0.0.1: Início do projeto: estudo das necessidades locais e modelagem inicial do processo de rankeamento.
0.0.2: Definição dos cinco principais processos de negócio.
0.0.3 a 0.0.7: Implementação sequencial dos processos 1 a 5.

#### Versões 0.1.x
0.1.0: Implementação da funcionalidade de categorização, com a criação de categorias principais (Alimentação, Entretenimento, Serviços) e organização em tags específicas.
0.1.1: Atualização das documentações do projeto, incluindo o README no repositório. Nenhuma alteração no código.
0.1.2: Implementação do layout inicial do site, com design responsivo e definição da identidade visual.
0.1.3: Escolha das tecnologias e configuração dos ambientes de desenvolvimento e produção.
0.1.4: Modelagem detalhada dos processos de negócio e criação do diagrama ER para o banco de dados inicial.
0.1.5: Implementação dos modelos de dados para cadastro de estabelecimentos e avaliações, além dos endpoints iniciais.

#### Versão 2.0.0
Versão final do projeto, consolidando funcionalidades e processos definidos nas etapas anteriores.

Essa estrutura de versões demonstra o avanço sistemático do projeto, desde sua concepção até a entrega final.


## Como Rodar o Projeto

O projeto possui duas branches principais: main e l_app. Abaixo estão as instruções para rodar o projeto em cada uma delas.

---

### Rodando na Branch main (Frontend conectado ao backend hospedado)

1. **Pré-requisitos**:  
   - Certifique-se de que o [Node.js](https://nodejs.org/) está instalado em sua máquina.

2. **Configuração do ambiente**:  
   - Acesse a pasta src/front pelo terminal.  
     
bash
     cd src/front

   - Na pasta front, crie um arquivo chamado .env.  
     Adicione a seguinte linha ao arquivo .env:  
     
env
     PUBLIC_API_URL="https://gostoservice.onrender.com"


3. **Instalando dependências e iniciando o frontend**:  
   - Execute o comando para instalar as dependências:  
     
bash
     npm install

   - Inicie o servidor de desenvolvimento:  
     
bash
     npm run dev

   - Após a execução, um link do localhost será gerado no terminal (algo como http://localhost:3000).  
     - Copie e cole este link no navegador para acessar o aplicativo.

---

### Rodando na Branch l_app (Rodando tudo localmente)

1. **Pré-requisitos**:  
   - Certifique-se de que o [Node.js](https://nodejs.org/) e o [Java](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) estão instalados em sua máquina.
   - Certifique-se também de que você tem o [Maven](https://maven.apache.org/) configurado.

2. **Configuração do ambiente**:  
   - Acesse a pasta src/front pelo terminal.  
     
bash
     cd src/front

   - Na pasta front, crie um arquivo chamado .env.  
     Adicione a seguinte linha ao arquivo .env:  
     
env
     PUBLIC_API_URL="http://localhost:8080"


3. **Instalando dependências e iniciando o frontend**:  
   - Execute o comando para instalar as dependências:  
     
bash
     npm install

   - Inicie o servidor de desenvolvimento:  
     
bash
     npm run dev

   - Após a execução, um link do localhost será gerado no terminal (algo como http://localhost:3000).  
     - Copie e cole este link no navegador para acessar o aplicativo.

4. **Iniciando o backend (Java)**:  
   - Acesse a pasta src/back pelo terminal.  
     
bash
     cd src/back

   - Certifique-se de que as dependências do Maven estão configuradas.
   - Inicie a aplicação Java que está configurada na classe main do projeto.
      
      
  
