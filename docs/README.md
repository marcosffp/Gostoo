# Gostô?


**Bernardo Souza Alvim, bernardo.alvim@sga.pucminas.br**

**Luísa Oliveira Jardim, lojardim@sga.pucminas.br**

**Marcos Alberto Ferreira Pinto, mafpinto@sga.pucminas.br**

**Athos Marques Ribeiro Fonseca, amrfonseca@sga.pucminas.br**

**João Paulo Fonseca Elias, joao.elias.1518018@sga.pucminas.br**

**Lucas de Souza Pereira, lucas.pereira.629726@sga.pucminas.br**

**Gabriela Alvarenga Cardoso, gabriela.cardoso.1026227@sga.pucminas.br**

---

### Professoras:

**Prof. Eveline Alonso Veloso**

**Prof. Joana Gabriela Ribeiro de Souza**

**Prof. Juliana Amaral Baroni de Carvalho**

---

_Curso de Engenharia de Software_

_Instituto de Informática e Ciências Exatas – Pontifícia Universidade Católica de Minas Gerais (PUC MINAS), Belo Horizonte – MG – Brasil_

---

O projeto **“Gostô?”** desenvolve uma plataforma digital cujo objetivo principal é aumentar a visibilidade de pequenos e médios estabelecimentos de Belo Horizonte, utilizando o rankeamento baseado em avaliações de visitantes locais. Por meio do site, os proprietários podem cadastrar seus negócios, escolhendo entre três categorias disponíveis: **Alimentação, Entretenimento ou Serviços**. Após a seleção, o estabelecimento é automaticamente organizado em tags específicas, facilitando sua descoberta pelos avaliadores.

Além disso, os avaliadores podem enriquecer a experiência na plataforma compartilhando **fotos e reviews**, destacando os locais mais bem avaliados. Os estabelecimentos são então classificados em dois rankings: **destaque geral** e **destaque de novos estabelecimentos**, garantindo uma promoção justa para os negócios emergentes, já que grandes redes de serviços tendem a reduzir a competitividade e as oportunidades dos comerciantes locais.

Outro diferencial do **“Gostô?”** é a funcionalidade de reservas. Estabelecimentos cadastrados que optarem por essa opção poderão oferecer aos avaliadores um sistema simples e eficiente para realizar reservas. Posteriormente, a plataforma fornece aos gerentes relatórios sobre o **engajamento** e a **demanda** de seus negócios, permitindo uma análise mais aprofundada e estratégica.

Por fim, um dos objetivos centrais do projeto é **promover a cultura de Belo Horizonte**. O **“Gostô?”** busca equilibrar a competição digital, incentivar a sustentabilidade dos negócios locais e conectar pessoas a novos lugares e experiências únicas. A interação entre os avaliadores e os gerente é fortalecida por meio das avaliações, que atuam como um valioso feedback, ajudando a melhorar serviços e atestar suas qualidades.

---


## 1. Introdução

Este projeto apresenta o desenvolvimento de uma solução digital voltada para a visibilidade e crescimento de pequenos e médios estabelecimentos, abordando o problema da competição desigual no ambiente online.

### 1.1 Contextualização

O setor de alimentação fora do lar é um dos mais competitivos e dinâmicos da economia, impulsionado pela crescente demanda por conveniência. No entanto, pequenos e médios estabelecimentos enfrentam desafios significativos para competir, especialmente no ambiente digital. Segundo o SEBRAE [[1.1]](#referências), cerca de 29% dos Microempreendedores Individuais (MEIs) encerram suas atividades em até cinco anos, enquanto a taxa de mortalidade para Micro Empresas (MEs) é de 21,6% e para Empresas de Pequeno Porte (EPPs) é de 17%1. A falta de visibilidade e marketing online é um dos principais fatores que contribuem para essa alta taxa de fechamento.

Nesse contexto, o projeto “Gostô?” se propõe a oferecer uma solução acessível e eficaz para aumentar a visibilidade desses estabelecimentos no mercado digital. A plataforma visa equilibrar as condições de competição, permitindo que pequenos e médios negócios no setor de alimentação se destaquem e alcancem um público maior, garantindo sua sustentabilidade e crescimento.

### 1.2 Problema

Em um mercado altamente competitivo, onde grandes redes e franquias dominam a publicidade online e o reconhecimento de marca, pequenos e médios estabelecimentos de alimentação, entretenimento e serviços enfrentam dificuldades crescentes para se manterem relevantes. Esses negócios, muitas vezes com recursos limitados para investir em marketing digital, acabam ficando à mercê de uma visibilidade orgânica insuficiente, o que dificulta a atração de novos clientes. O desafio é ainda maior para novos empreendimentos, que têm de lidar com a incerteza inicial e a necessidade urgente de construir uma base de clientes sólida.

Essas dificuldades não apenas limitam o crescimento desses estabelecimentos, mas também aumentam a probabilidade de falência nos primeiros anos de operação. Sem uma plataforma eficaz para destacar suas qualidades e se conectar com um público-alvo em potencial, muitos desses negócios se veem forçados a encerrar suas atividades antes mesmo de consolidar sua presença no mercado. O problema, portanto, reside na falta de visibilidade e nas ferramentas de marketing adequadas que permitam a esses pequenos empreendimentos competir em igualdade de condições no ambiente digital.

Além disso, na era dos smartphones, realizar reservas por meio de ligação telefônica tornou-se um processo desmotivador para muitos clientes, especialmente para as gerações mais jovens, que preferem soluções digitais rápidas e práticas. Esse comportamento acaba afastando potenciais frequentadores de restaurantes e outros estabelecimentos, que optam por alternativas mais acessíveis ou, em muitos casos, acabam permanecendo em casa por falta de opções convenientes. O site "Gostô?" também visa solucionar esse problema, oferecendo uma interface intuitiva e eficiente para reservas, tornando os estabelecimentos mais convidativos e dinâmicos aos olhos do cliente.

Adicionalmente, o site resolve outro problema enfrentado pelos consumidores: o desconhecimento de novas opções de entretenimento, gastronomia e serviços. Muitas pessoas, por falta de informação ou incentivo, acabam repetindo os mesmos destinos, gerando uma sensação de monotonia em suas atividades de lazer. Com o "Gostô?", os avaliadores terão acesso a um ranking atualizado de novos estabelecimentos e eventos, o que não apenas incentiva a descoberta de novas experiências, mas também contribui para movimentar o cenário urbano, trazendo vitalidade tanto para os negócios quanto para os clientes.

Por fim, o "Gostô?" oferece uma solução prática para aumentar a visibilidade de pequenos e médios estabelecimentos no ambiente digital, ao mesmo tempo em que facilita a interação dos clientes por meio de reservas online. O site incentiva a descoberta de novas opções de lazer, combatendo a monotonia e revitalizando o cenário urbano, contribuindo assim para a sustentabilidade dos empreendimentos e enriquecendo a experiência dos avaliadores.

### 1.3 Objetivo geral

Desenvolver um sistema web que automatize a avaliação e recomendação de estabelecimentos e eventos. O sistema permitirá o cadastro de estabelecimentos e avaliadores, a realização de avaliações e reviews por avaliadores e a reserva de mesas. O sistema organizará os estabelecimentos e eventos por categorias, sendo elas Alimentação, Entretenimento e Serviços, destacando os mais bem avaliados e facilitando a descoberta dos melhores locais e eventos para lazer em Belo Horizonte.

#### 1.3.1 Objetivos Específicos

**1. Gestão de Conta de Gerente de Estabelecimento**

**1. Avaliação e Review de Estabelecimentos**
O processo de Avaliação e Review de Estabelecimentos permite que avaliadores postem fotos, notas e comentários sobre os estabelecimentos que visitaram. Após a submissão de uma avaliação, o sistema valida o conteúdo para garantir que atenda aos requisitos e normas estabelecidas. A avaliação validada é então publicada e associada ao estabelecimento correspondente. Além disso, o sistema calcula a média das notas recebidas e exibe essas informações junto com os comentários dos avaliadores. Isso proporciona uma visão geral das avaliações gerais de cada estabelecimento, ajudando outros avaliadores a tomar decisões informadas com base no feedback da comunidade. 

**2. Gestão de Conta de Avaliador**

No processo de Gestão de Conta de Avaliador, será desenvolvido um sistema que permite aos avaliadores registrar, editar, consultar e excluir suas contas. Inicialmente, os avaliadores preencherão um formulário com informações pessoais e detalhes do perfil. Após o preenchimento, o sistema validará os dados para garantir que o cadastro esteja correto. Com a conta criada, o avaliador poderá acessar o sistema, visualizar suas informações, editá-las ou excluir sua conta quando desejar. O gerenciamento da conta também incluirá a possibilidade de consultar os dados do perfil a qualquer momento. Além disso, o sistema garantirá a atualização regular dos dados, assegurando que as permissões e perfis estejam sempre corretos e atualizados. Apenas avaliadores cadastrados poderão acessar funcionalidades como realizar avaliações e postar reviews. O sistema deve controlar e gerenciar adequadamente todas as informações da conta dos avaliadores, mantendo sua integridade, segurança e acessibilidade.

**3. Reserva de Mesas**

O processo de Reserva de Mesas envolve o desenvolvimento de um módulo que permite aos avaliadores reservar mesas em estabelecimentos de forma online. Os avaliadores selecionam a data e o horário, e a quantidade de pessoas que comparecerão, desejados para a reserva, e é feita a solicitação de reserva, que é aprovada ou não pelo gerente do estabelecimento. Após confirmar a disponibilidade, a reserva é finalizada, e o avaliador recebe uma confirmação da reserva. O sistema deve oferecer uma interface clara para que os avaliadores visualizem a disponibilidade das mesas e façam reservas de maneira prática e eficiente, garantindo uma experiência de avaliador sem complicações e rápida.


**4. Gestão de Conta de Gerente de Estabelecimento**
No processo de Gestão de Conta de Gerente de Estabelecimento, será desenvolvido um sistema que permite aos gerentes registrar, editar, consultar e excluir seus estabelecimentos. Inicialmente, os gerentes preencherão um formulário com informações detalhadas, como nome do estabelecimento, localização, categoria e horário de funcionamento. Após o preenchimento, o sistema validará os dados para garantir que o cadastro esteja correto. Com o estabelecimento cadastrado, o gerente poderá acessar o sistema, visualizar as informações, editá-las ou excluir o estabelecimento quando necessário. O gerenciamento da conta incluirá a possibilidade de consultar os dados do estabelecimento a qualquer momento, além de garantir a atualização contínua para assegurar que os dados estejam sempre corretos e atualizados. Apenas gerentes cadastrados poderão acessar funcionalidades como gerenciar os estabelecimentos e revisar indicadores de desempenho. O sistema deve controlar e gerenciar adequadamente todas as informações do estabelecimento, mantendo sua integridade, segurança e acessibilidade.


**5. Gestão de Mesa**
O processo de gestão de mesas está localizado no perfil do estabelecimento, onde são listadas as mesas que o estabelecimento possui. Este processo permite a consulta, a criação, a edição e a exclusão de mesas, para possibilitar, posteriormente, que o avaliador realize a solicitação da reserva de uma mesa.

### 1.4 Justificativas

No mercado atual, pequenos e médios estabelecimentos enfrentam dificuldades para competir com grandes redes, especialmente na divulgação e visibilidade online. Muitos negócios desse porte têm recursos limitados para investir em marketing, resultando em uma competição desigual. Estudos do SEBRAE [[1.4]](#referências) mostram que cerca de 60% dos pequenos negócios no setor de alimentação enfrentam desafios significativos na promoção online, o que afeta suas chances de sucesso e crescimento.

Nossa plataforma visa resolver essa desigualdade, criando um espaço onde todos os estabelecimentos, independentemente do tamanho, possam se destacar. Ao permitir que os gerentes de estabelecimentos interajam diretamente com os avaliadores e promovam seus negócios, oferecemos uma solução acessível e eficaz para ampliar a visibilidade e a competitividade desses empreendimentos.

## 2. Participantes do Processo

### 2.1 Avaliadores

Os avaliadores são usuários essenciais do site, responsáveis por fornecer feedback sobre os estabelecimentos através de avaliações detalhadas.Além disso, os avaliadores podem realizar reservas em estabelecimentos que desejarem. Seu papel é fundamental para a classificação e visibilidade dos estabelecimentos na plataforma, e também para a estimação do sucesso dos estabelementos individualmente e, também,  da adesão do "Gostô?" como plataforma completa.

#### 2.1.1 Características dos Avaliadores

Os avaliadores podem ser jovens e adultos de 16 anos em diante, possuindo uma variedade de níveis educacionais. Eles têm interesse em cultura, gastronomia, lazer e tecnologia, e valorizam a qualidade do serviço. Seus perfis são diversos, refletindo diferentes preferências e experiências pessoais.

#### 2.1.2 Papel dos Avaliadores

- Fornecer avaliações detalhadas e honestas sobre os estabelecimentos visitados, incluindo fotos, notas e comentários.
- Realizar reservas em seus estabelecimentos favoritos.


### 2.2 Gerentes de Estabelecimentos

Os gerentes dos estabelecimentos são responsáveis pela administração e operação dos negócios listados no site. Eles desempenham um papel crucial ao disponibilizar indicadores de desempenho sobre seus estabelecimentos e interagir com as avaliações recebidas.

#### 2.2.1 Características dos Estabelecimentos

Os estabelecimentos variam de centros urbanos a bairros periféricos de Belo Horizonte, abrangendo diferentes setores como Alimentação, Entretenimento e Serviços. Eles estão focados em melhorar o atendimento ao cliente e estão abertos ao feedback fornecido através do site.

#### 2.2.2 Papel dos Gerentes de Estabelecimentos

- Oferecer opções de reserva de mesas, permitindo aos clientes reservar com facilidade e eficiência.
- Atualizar e gerenciar as informações do estabelecimento no sistema para garantir que estejam sempre corretas e atualizadas.
 
# Modelagem do Processo de Negócio

## 3. Situação Atual

### 3.1 Sistemas Existentes

Para entender melhor o contexto e as necessidades do nosso site, é crucial analisar sistemas existentes que abordam problemas similares. A seguir, apresentamos uma visão geral dos sistemas disponíveis no mercado que oferecem funcionalidades relacionadas à avaliação e recomendação de estabelecimentos, incluindo processos de cadastro e avaliação:

**1. Yelp**
- **Descrição:** Yelp é uma plataforma amplamente conhecida para avaliações e recomendações de estabelecimentos como restaurantes, lojas e serviços locais.
- **Funcionamento:** Permite que os avaliadores postem avaliações, fotos e notas sobre suas experiências. O sistema inclui um processo de cadastro para proprietários de estabelecimentos e para avaliadores, permitindo o gerenciamento e a atualização das informações dos negócios.
- **Tecnologias Usadas:** Desenvolvido usando Ruby on Rails para o back-end e React para o front-end. Utiliza bancos de dados relacionais como MySQL para armazenar dados.
- **Dificuldades existentes:** O Yelp utiliza um modelo de negócios onde os estabelecimentos podem pagar para ter mais destaque. Pequenos e médios negócios com menos orçamento tendem a ser ofuscados por grandes marcas que podem pagar pela visibilidade.Embora o Yelp seja uma boa plataforma de avaliações, ele não foca tanto em reservas de mesas ou na venda de ingressos para eventos. Isso limita o engajamento para certos tipos de negócios que poderiam se beneficiar dessas funcionalidades.
- **Vantagens do Gostô?:** O Gostô? é projetado para aumentar a visibilidade de pequenos negócios, com uma atenção especial às suas necessidades, ao invés de competir em uma plataforma com grandes cadeias.Em vez de um modelo pago, o Gostô? destaca os estabelecimentos com base nas avaliações e no engajamento, ajudando negócios menores a se sobressaírem pela qualidade.

**2. TripAdvisor**
- **Descrição:** TripAdvisor é uma plataforma global que fornece avaliações e recomendações para hotéis, restaurantes e atrações turísticas.
- **Funcionamento:** Os avaliadores podem escrever avaliações, postar fotos e fornecer notas. Inclui um processo de cadastro para estabelecimentos e para avaliadores, facilitando a gestão e atualização das informações dos estabelecimentos e dos avaliadores.
- **Tecnologias Usadas:** Utiliza uma combinação de tecnologias, incluindo Java para o back-end e Angular para o front-end. Armazenamento de dados é gerenciado por bancos de dados como PostgreSQL.
- **Dificuldades existentes:** Embora ofereça avaliações de restaurantes e hotéis, o foco do TripAdvisor é em destinos turísticos. Pequenos estabelecimentos que atendem ao público local têm menos exposição e relevância na plataforma.A plataforma oferece muitas funcionalidades para grandes negócios turísticos, mas a gestão de pequenos estabelecimentos pode se tornar mais complicada e menos direta.
- **Vantagens do Gostô?:** O sistema de gestão de contas e avaliações no Gostô? é projetado para ser direto e acessível, otimizando o processo de cadastramento e gerenciamento para estabelecimentos de pequeno porte.O Gostô? adiciona módulos como reservas e compra de ingressos, o que o torna mais completo para pequenos negócios que precisam de ferramentas de interação direta com os clientes.

**3. Google Maps**
- **Descrição:** Google Maps oferece funcionalidades de avaliação de locais como restaurantes, lojas e pontos turísticos, além de fornecer direções e mapas.
- **Funcionamento:** Permite que os avaliadores deixem avaliações e notas sobre locais. Inclui um processo de cadastro de estabelecimentos e um sistema integrado de gestão de avaliações.
- **Tecnologias Usadas:** Desenvolvido com tecnologias como Java e C++ para o back-end e utiliza serviços de armazenamento na nuvem como Google Cloud Storage.
- **Dificuldades existentes:** A avaliação de estabelecimentos é apenas uma parte de um conjunto maior de funcionalidades centradas em mapas e navegação, o que pode diluir a visibilidade de pequenos negócios.O Google Maps não oferece ferramentas como reservas de mesas ou compra de ingressos, limitando o tipo de interação que um estabelecimento pode ter com os clientes através da plataforma.
- **Vantagens do Gostô?:** O Gostô? permite interações diretas, como reserva de mesas e compra de ingressos, que o Google Maps não oferece.O Gostô? é uma plataforma especializada em avaliação e recomendação de estabelecimentos, oferecendo uma experiência centrada nesse propósito, o que dá mais visibilidade a pequenos negócios.O Gostô? se diferencia ao promover ativamente negócios menores, ajudando-os a se destacarem em suas comunidades, algo que o Google Maps não faz de forma especializada.

### 3.2 Processos Manuais Atuais

Antes da implementação de sistemas automatizados como os mencionados acima, muitos estabelecimentos e avaliadores utilizavam processos manuais para lidar com avaliações, reservas e cadastros. A seguir, estão alguns dos processos manuais comuns atualmente utilizados:

**1. Gestão de Conta de Estabelecimento**
- **Descrição:** O cadastro de estabelecimentos no sistema manual envolvia o preenchimento de formulários físicos ou por e-mail, que eram armazenados em arquivos ou planilhas. Para consultas, edições ou exclusões, os funcionários precisavam localizar e modificar manualmente os registros, dependendo da organização dos documentos ou sistemas de arquivo.
- **Desafios:** O processo manual de gerenciamento de contas de estabelecimentos era lento, sujeito a erros humanos no preenchimento e armazenamento, e dificultava a verificação e validação dos dados, resultando em cadastros incorretos ou incompletos. A consulta e edição de registros eram demoradas, com risco de perda ou inconsistência de informações, especialmente em sistemas mal organizados. Além disso, a exclusão de estabelecimentos não era sempre feita de forma completa, comprometendo a manutenção de um cadastro limpo e organizado.

**2. Gestão de Conta de Avaliador**
- **Descrição:** A criação de contas de avaliadores no sistema manual envolvia o preenchimento de formulários físicos, que eram arquivados em fichários. Para consultas, edições ou exclusões, funcionários precisavam buscar e modificar manualmente os registros, dependendo da organização dos documentos.
- **Desafios:** O processo manual de gerenciamento de contas de avaliadores era lento, sujeito a erros humanos no preenchimento e armazenamento, e dificultava a verificação e validação de dados, resultando em cadastros incorretos ou incompletos. A consulta e edição de registros exigiam tempo, com risco de perda ou inconsistência de informações, especialmente em sistemas mal organizados, e a exclusão incompleta dificultava a manutenção de um histórico limpo e organizado.

**3. Avaliação e Review de Estabelecimentos**
- **Descrição:** Avaliações e recomendações frequentemente eram feitas por meio de palavras de boca a boca, críticas em jornais ou revistas especializadas. Avaliadores compartilhavam experiências pessoais com amigos e familiares.
- **Desafios:** A falta de um sistema centralizado tornava difícil a compilação e análise de feedback. As informações eram fragmentadas e não sistematizadas, dificultando a tomada de decisões para os consumidores.

**4. Reserva de Mesas**
- **Descrição:** Reservas eram feitas por telefone ou pessoalmente, com pouca ou nenhuma automação. Os avaliadores ligavam diretamente para os estabelecimentos para fazer reservas e confirmavam disponibilidade com os atendentes.
- **Desafios:** Esse método podia levar a erros de comunicação, falta de disponibilidade atualizada e uma experiência de reserva menos eficiente para os avaliadores.

### 3.2. Descrição Geral da Proposta de Solução

A proposta de solução do "Gostô?" é uma plataforma digital projetada para aumentar a visibilidade de pequenos e médios negócios. O objetivo principal é ajudar esses estabelecimentos a se destacarem no mercado, superando os desafios de marketing online. Os principais componentes da solução incluem seção de Novos Estabelecimentos, Perfil do Estabelecimento e Filtragem por categorias específicas, como Alimentação, Entretenimento e Serviços. Com esses recursos, o site oferece uma solução acessível e eficaz para impulsionar a competitividade e o crescimento desses negócios no ambiente digital. A solução será composta pelos seguintes componentes principais:

1. **Módulo de Gestão de Conta de Estabelecimento:**
   - Criação de uma plataforma digital para facilitar a gestão de contas de estabelecimentos, eliminando a necessidade de processos manuais.
   - Proprietários poderão preencher formulários online com informações detalhadas sobre seus negócios, como nome, endereço, categoria e horários de funcionamento, além de adicionar fotos, menus e eventos, com validação imediata dos dados.
   - Acesso à plataforma para que os proprietários consultem, atualizem ou excluam informações sobre seus estabelecimentos por meio de um sistema de autenticação seguro.
   - Somente estabelecimentos registrados terão acesso a funcionalidades específicas, como fotos e menus, bem como outras atualizações do perfil.
   - Interface intuitiva para a gestão dos perfis dos estabelecimentos e para consulta de informações de forma rápida e acessível.
   - Implementação de backups automáticos, autenticação de usuários e registro do histórico de alterações para assegurar a proteção dos dados.
   - Sistema projetado para atualizações regulares dos perfis e permissões, garantindo que as informações sejam sempre precisas e consistentes.

2. **Módulo de Gestão de Conta de Avaliador:**
   - Desenvolvimento de uma plataforma digital para o gerenciamento de contas de avaliadores, eliminando processos manuais.
   - Avaliadores preencherão formulários digitais com dados pessoais, e perfil, com validação em tempo real das informações.
   - Acesso à plataforma para consulta, edição e exclusão de dados por meio de autenticação segura.
   - Apenas avaliadores cadastrados poderão acessar funcionalidades específicas, como realizar avaliações e postar reviews.
   - Navegação simplificada para gestão de perfis e consulta de informações de forma rápida e acessível.
   - Implementação de backups automáticos, autenticação de usuários e manutenção do histórico de alterações para garantir segurança dos dados.
   - Sistema com mecanismo de atualização frequente dos perfis e permissões, garantindo informações sempre corretas e consistentes.

3. **Módulo de Avaliação e Review de Estabelecimentos:**
   - Permite aos avaliadores postar fotos, notas e comentários sobre os estabelecimentos visitados.
   - **Organização por Categoria:** Empreendimentos são organizados em categorias como Alimentação, Entretenimento e Serviços.
   - Calcula e exibe as médias das avaliações e comentários, oferecendo uma visão geral clara e acessível das opiniões dos avaliadoes.
   - **Avaliação por Nota:** Os clientes podem avaliar com uma nota de 1 a 5 estrelas, baseada na experiência geral.
   - **Avaliação por Resenha:** Resenhas detalhadas sobre a experiência no estabelecimento.
   - **Ranqueamento por Nota:** Estabelecimentos mais bem avaliados são destacados no topo das suas categorias.

4. **Módulo de Reserva de Mesas:**
   - Oferece a possibilidade de realizar reservas de mesas online, com verificação de disponibilidade e confirmação por parte do estabelecimento.
   - Os estabelecimentos poderão configurar seus horários de funcionamento, tempo de permanência por reserva, número máximo de reservas por mesa e controlar a disponibilidade de acordo com dias e horários de pico.
   -  Assim que a reserva for feita, o sistema enviará uma confirmação ao cliente, contendo os detalhes da reserva (mesa, horário, endereço do estabelecimento) e possíveis instruções adicionais (como políticas de cancelamento).
   -  Os clientes poderão cancelar ou modificar suas reservas diretamente pelo sistema, recebendo confirmação em tempo real. Os gerentes também terão controle sobre a gestão dessas alterações.
  

5. **Módulo de Gerenciamento de Mesas:**

- Os clientes conseguem visualizar a disponibilidade de mesas em tempo real e realizar reservas.
- O sistema permite filtrar mesas por capacidade, localização dentro do estabelecimento (interno, externo, próximo à janela, etc.) e horários disponíveis.
- Os proprietários têm controle total sobre a criação, edição e exclusão de mesas, podendo ajustar a disponibilidade conforme a necessidade.
- Inclui funcionalidades para gestão de reservas, notificações de confirmação para os clientes e relatórios sobre a utilização das mesas, ajudando o estabelecimento a otimizar sua operação.
- Gera relatórios detalhados sobre a taxa de ocupação das mesas.


### 3.2.1 Adequação aos Objetivos do Negócio

A solução proposta está alinhada com os objetivos do negócio ao oferecer:

- **Eficiência no Cadastro e Gerenciamento:** Melhoria na gestão e atualização dos dados dos estabelecimentos e avaliadores.
- **Transparência nas Avaliações:** Facilitação da coleta e visualização de avaliações, ajudando avaliadores a encontrar os melhores estabelecimentos.
- **Otimização da Reserva:** Melhoria na experiência de reserva de mesas, tornando os processos mais ágeis e acessíveis.

### 3.2.2 Sugestões de Melhorias

Para potencializar a solução, consideramos as seguintes melhorias:

- **Aba de Destaques:** Adicionar seção de destaques no perfil dos estabelecimentos para os mesmos divulgarem promoções, eventos ou alguma novidade.
- **Seção de novidades:** Dar maior enfoque para a seção de Novidades afim de alavancar a adesão à novos estabelecimentos.

### 3.3. Modelagem dos processos

[PROCESSO 1 - Avaliacao e Review de Estabelecimentos](processo-1-avaliacao-e-review-de-estabelecimentos.md "Detalhamento do Processo 1.")

[PROCESSO 2 - Gestão de conta de Avaliador](processo-2-gestao-de-conta-de-avaliador.md "Detalhamento do Processo 2.")

[PROCESSO 3 - Reserva de Mesas](https://github.com/ICEI-PUC-Minas-PPLES-TI/plf-es-2024-2-ti2-1372100-gostoo/blob/main/docs/processo-3-reserva-de-mesa.md)

[PROCESSO 4 - Gestão de Conta de Estabelecimento](processo-4-gestao-de-conta-de-estabelecimento.md "Detalhamento do Processo 4.")

[PROCESSO 5 - Gestão de Mesas](https://github.com/ICEI-PUC-Minas-PPLES-TI/plf-es-2024-2-ti2-1372100-gostoo/blob/main/docs/processo-5-gestao-de-mesas.md)

## 4. Projeto da solução

_O documento a seguir apresenta o detalhamento do projeto da solução. São apresentadas duas seções que descrevem, respectivamente: modelo relacional e tecnologias._

[Projeto da solução](solution-design.md "Detalhamento do projeto da solução: modelo relacional e tecnologias.")


## 5. Indicadores de desempenho

_O documento a seguir apresenta os indicadores de desempenho dos processos._

[Indicadores de desempenho dos processos](performance-indicators.md)


## 6. Interface do sistema

_A sessão a seguir apresenta a descrição do produto de software desenvolvido._ 

[Documentação da interface do sistema](interface.md)

## 7. Conclusão


Dessa forma, o projeto “Gostô?” emerge como uma plataforma revolucionária que vai além do simples papel de intermediário entre consumidores e estabelecimentos. Ele se consolida como um catalisador de transformação econômica, social e cultural, promovendo um impacto significativo na dinâmica de consumo e na relação entre os pequenos e médios negócios e a comunidade de Belo Horizonte.

Ao democratizar o acesso à visibilidade digital, o “Gostô?” atua como um instrumento de inclusão econômica, permitindo que pequenos empreendedores tenham as mesmas chances de competir com grandes redes já consolidadas. Essa iniciativa reflete um compromisso claro com a equidade de oportunidades e com a criação de um ambiente de negócios mais plural e diversificado, alinhado às necessidades de um mercado em constante evolução.

A plataforma também desempenha um papel essencial na valorização e preservação da cultura local. Belo Horizonte, reconhecida por sua rica tradição gastronômica, cultural e artística, ganha um espaço onde suas raízes são exaltadas e celebradas. Ao fomentar o consumo em estabelecimentos que carregam histórias e identidades únicas, o “Gostô?” resgata e amplifica a conexão dos moradores com sua cidade, transformando cada experiência de consumo em uma oportunidade de vivenciar a cultura local.

Além disso, o “Gostô?” transcende sua funcionalidade técnica ao integrar ferramentas avançadas que vão do rankeamento à reserva de serviços, passando pelo feedback estruturado. Essa abordagem transforma o ato de avaliar um estabelecimento em um processo dinâmico e construtivo, onde os dados coletados são utilizados para promover melhorias reais e estratégicas. Empreendedores podem adaptar seus serviços com base em insights valiosos, enquanto os consumidores encontram um ambiente mais alinhado às suas expectativas, gerando um ciclo virtuoso de crescimento e satisfação.

O impacto do projeto também se estende ao fortalecimento do senso de pertencimento e da comunidade. A participação ativa dos usuários, seja avaliando, recomendando ou explorando novos lugares, contribui para a construção de uma rede colaborativa em que todos se beneficiam. Mais do que conectar pessoas a locais, o “Gostô?” conecta histórias, promove interações humanas e cria um ambiente onde a inovação se alia à tradição para moldar um futuro mais inclusivo e integrado.

Do ponto de vista tecnológico, o projeto serve como um exemplo de como soluções digitais podem ser implementadas para resolver desafios concretos da sociedade. Sua concepção reflete um entendimento profundo das necessidades do mercado local e uma aplicação estratégica da tecnologia para atender a essas demandas. Ao unir inovação e acessibilidade, o “Gostô?” mostra que o progresso tecnológico não precisa ser excludente, mas sim um elemento que une e potencializa comunidades.

Por fim, o “Gostô?” não é apenas um guia de consumo; ele é um agente de transformação. Ele inspira um modelo de negócios sustentável que prioriza o bem-estar coletivo, promove práticas de consumo consciente e celebra a diversidade cultural. Mais do que isso, ele reafirma o papel da tecnologia como um meio de construir um futuro em que comunidades prosperem de maneira equilibrada e integrada.

Com o “Gostô?”, Belo Horizonte não apenas se torna mais conectada, mas também mais forte e orgulhosa de suas raízes, provando que o potencial de um projeto vai muito além do digital: ele está na capacidade de transformar vidas, negócios e a própria identidade de uma cidade.

### Observações pessoais  
A criação de uma solução justa para destacar novos negócios foi um desafio importante, mas essencial para equilibrar a competitividade local. Além disso, implementar futuras melhorias, como a expansão para outras cidades, algoritmos de recomendação personalizados e gamificação para engajamento, pode tornar a plataforma ainda mais eficiente. Recursos interativos, como fotos e reviews, também mostraram-se cruciais para atrair e engajar usuários, fortalecendo a conexão entre clientes e estabelecimentos. - Luísa Jardim 

Foi uma oportunidade enriquecedora trabalhar em uma solução que alia tecnologia e impacto social. Cada decisão durante o desenvolvimento evidenciou a importância de entender as necessidades reais dos usuários e como pequenas funcionalidades podem fazer uma grande diferença na experiência deles. Além disso, ficou claro como a tecnologia pode ser uma ferramenta poderosa para promover inclusão e apoiar o crescimento de empreendedores locais. - Gabriela Alvarenga

Fiquei extremamente satisfeito e feliz em ver a ideia de um projeto que tinha em mente ganhar vida com o talento e a dedicação de ótimos colegas de equipe. A colaboração de todos resultou em uma solução incrível, acredito que a mesma tem o potencial de se tornar o principal canal para a promoção da cultura e do entretenimento local, conectando pessoas e valorizando o que há de melhor na nossa comunidade. Concluo que a programação pode tornar um sonho abstrato em algo concreto e funcional, conectando e gerando um mundo melhor - Lucas Pereira

Participar do processo de documentação e implementação do Gosto? foi uma experiência extremamente enriquecedora. Durante esse período, tive a oportunidade de aprender imensamente com meus colegas e professores, o que foi fundamental para o meu crescimento profissional. Além disso, adquiri um conhecimento valioso sobre a importância da cooperação em equipe, bem como sobre o panorama atual do mercado, especialmente no setor de restaurantes, estabelecimentos e serviços. Uma das maiores descobertas foi compreender o impacto positivo de um aplicativo capaz de promover uma competitividade justa, equilibrando as oportunidades entre grandes estabelecimentos e os de menor porte. Esta experiência foi, sem dúvida, transformadora tanto para minha carreira quanto para minha visão sobre o mercado. - João Elias

Trabalhar no Gostô? foi uma experiência única e extremamente enriquecedora. Além de aprimorar minhas habilidades técnicas, como desenvolvimento front-end, back-end e design de interfaces, tive a oportunidade de enfrentar desafios reais que me ajudaram a crescer como profissional. A troca de ideias e o trabalho em equipe com meus colegas de grupo foram fundamentais para o aprendizado contínuo e para o sucesso do projeto. Essa experiência não só fortaleceu meu conhecimento técnico, mas também aprimorou minha capacidade de colaboração, comunicação e resolução de problemas, valores essenciais para qualquer desenvolvedor. - Bernardo Alvim

Gostei muito da ideia do nosso projeto e creio que, se essa ideia for levada para frente com estudos mais aprofundados e uma programação mais avançada, temos potencial para alcançar resultados incríveis. Durante o desenvolvimento, percebi o quanto a colaboração e a troca de ideias foram fundamentais para superar desafios e aprimorar o projeto.Os resultados obtidos até aqui mostram que estamos no caminho certo, mas também revelam áreas que podem ser melhor exploradas. Em resumo, acredito que este projeto não apenas nos proporcionou um aprendizado valioso, mas também tem o potencial de evoluir para algo ainda maior. - Athos Fonseca

Trabalhar no back-end do Gostô? foi um desafio instigante e uma oportunidade única de transformar ideias em soluções tangíveis, criando uma base sólida e escalável para a aplicação, capaz de atender às demandas de usuários de diferentes perfis. Cada detalhe, como a otimização de consultas e a estruturação de APIs bem definidas, demonstrou como aspectos técnicos impactam diretamente a experiência do usuário. Contribuir para uma plataforma que conecta pessoas e impulsiona pequenos negócios reforçou minha paixão por desenvolver sistemas eficientes e com propósito. Essa experiência não só ampliou minhas habilidades técnicas em arquitetura e desenvolvimento de sistemas, mas também me ensinou o valor da empatia ao criar soluções que atendam às reais necessidades de quem as utiliza. Sair do projeto com a certeza de que a tecnologia pode transformar realidades foi algo inspirador, que levarei comigo para continuar contribuindo para um impacto positivo na sociedade. - Marcos Alberto

# REFERÊNCIAS

_Como um projeto de software não requer revisão bibliográfica, a inclusão das referências não é obrigatória. No entanto, caso você deseje incluir referências relacionadas às tecnologias, padrões, ou metodologias que serão usadas no seu trabalho, relacione-as de acordo com a ABNT._

_Verifique no link abaixo como devem ser as referências no padrão ABNT:_

http://portal.pucminas.br/imagedb/documento/DOC_DSC_NOME_ARQUI20160217102425.pdf

**[1.1]** [SEBRAE, “A taxa de sobrevivência das empresas no Brasil”, 2023.](https://sebrae.com.br/sites/PortalSebrae/artigos/a-taxa-de-sobrevivencia-das-empresas-no-brasil,d5147a3a415f5810VgnVCM1000001b00320aRCRD)

**[1.2]** _COPPIN, Ben. **Inteligência artificial**. Rio de Janeiro, RJ: LTC, c2010. E-book. ISBN 978-85-216-2936-8._

**[1.3]** _CORMEN, Thomas H. et al. **Algoritmos: teoria e prática**. Rio de Janeiro, RJ: Elsevier, Campus, c2012. xvi, 926 p. ISBN 9788535236996._

**[1.4]** [SEBRAE, "Desafios e Oportunidades no Mercado de Alimentação Fora do Lar", 2022](https://sebrae.com.br/sites/PortalSebrae/artigos/oito-desafios-dos-negocios-de-alimentacao-fora-do-lar-para-2022,3c97b3d288941810VgnVCM100000d701210aRCRD#:~:text=O%20aumento%20do%20preço%20médio,que%20os%20restaurantes%20enfrentam%20hoje.)

**[1.5]** _RUSSELL, Stuart J.; NORVIG, Peter. **Inteligência artificial**. Rio de Janeiro: Elsevier, c2013. xxi, 988 p. ISBN 9788535237016._



# APÊNDICES


_Atualizar os links e adicionar novos links para que a estrutura do código esteja corretamente documentada._


## Apêndice A - Código fonte

[Código do front-end](../src/front) -- repositório do código do front-end

[Código do back-end](../src/back)  -- repositório do código do back-end


## Apêndice B - Apresentação final


[Slides da apresentação final](presentations/)


[Vídeo da apresentação final](video/)






