# Parei na aula 4 11:30

<div id='init'/>
  
# Tutorial spring
# Indice
  
- [Comandos](#comandos)
- [Definições de nomenclaturas](#definicoes)
- [Anotações principais](#anotacoes)
- [Lembretes](#lembrete)
- [Tipos de mensageria](#mensageria)
- [Problemas e soluções](#problemas)
- [Spring profiles](#profiles)
- [Cliclo de vida dos Beans](#beans-life)
- [Spring event handler](#event-handler)
- [Classe de configurações do projeto](#poject-config)
- [JPA e Hibernate](#jpa)
- [Mapeamento objeto relacional](#mapping)
- [Adicionado dados mock com sql](#dados-mock)
- [Dropar as colunas assim que a aplicação subir](#drop)
- [DDD](#ddd)
- [Repository](#repository)
- [Many to one](#many)
- [Buscando dados por ID](#id-data)
- [Mudar nome das chaves do json apenas no json](#key-name)
- [Remover dados do json](#exclude-data)
- [Mudar nome das chaves principais](#main-keys-name)
- [Customizando os Status HTTP](#http-status)
- [Mudando o path de um endpoint e avisando o cliente sobre a mudança](#address)
- [Post](#post)
- [Put](#put)
- [Delete](#delete)
- [Domain Service](#domain-service)
- [JPA](#jpa)
- [Query methods](#query)
- [Query Custom](#query-custom)
- [Specifications do DDD](#spec)
- [Fábrica de Specifications do DDD](#spec-factory)
- [Melhorando a fábrica de Specifications do DDD](#spec-factory-increase)
- [Criando um repositorio JPA customizado](#jpa-custom)
- [One to many](#one-to-many)
- [Many to many](#many-to-many)
- [Resolvendo problema de atualização de dados no banco quando a tabela tem relacionamento](#problem-update)
- [Componentizando entidades @Embedable](#entity-component)
- [Adicionando data de cadastro e de atualização](#date)
- [Adicionando serialização sob demanda com @Lazy](#serialization-demand)
- [Pool de conexões](#pool)
- [Extressando o banco de dados para teste](#xtress)
- [Adicionando o flyway](#flyway)
- [Gerando Schemmas para o flyway automaticamente](#flyway-auto)
- [Populando tabelas com o flyway](#flyway-populate)
- [Populando tabelas com o flyway somente em dev](#flyway-populate-dev)
- [Tratando erros em migrações](#flyway-error)
- [Melhorando o tratamento de erros](#custom-errors)
- [Tratando erros com @ExceptionHandler](#error-handler)
- [Customizando body do erro](#error-class)
- [Criando erros globais](#global-error)

<div id='comandos'/>

# Comandos
* ./mvnw clean (Limpa os builds anteriores)
* ./mvnw dependency:tree (Mostra lista de dependencias)
* ./mvnw dependency:resolve (Mostra lista de dependencias resolvidas)
* ./mvnw help:effective-pom (Gera o pom efeitivo do projeto)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/ae70dd7b-ecf4-4ac5-b104-05f03bdf1295)

Esse comando a cima serve para especificar uma porta padrão quando fizer o build do projeto para .jar

<div id='definicoes'/>

# Definições de nomenclaturas
* Maven gerencia as dependencias do projeto e build
* POM (Project Object Model) é onde ficam as configurações do maven e do projeto
* Plugins podem ser adicionados nesse espaço no pom.xml
![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/5b602f94-10cd-44ee-89ee-81bf4981267b)
* Efective POM: Mostra o pom final do projeto

<div id='anotacoes'/>

[Inicio](#init)

# Anotações principais [Lista mais completa](https://github.com/devmorfeu/spring-annotations)
* @Bean (Essa anotação deve ser feita a cima de uma função e indica que ela retorna um Bean)
* @Controller (Cria um endpoint)
* @GetMapping (Usa sempre em cima de uma função, ela serve para dizer de qual tipo de requisição estamos falando, ela pode receber uma string que é o endpoint a ser acessado)
* @ResponseBody (Use para dizer que a função vai retornar uma corpo de uma resposta HTTP)
* @Component (Transforma a classe em um beam)
* @Configuration (Serve para definir outros beans, exemplo:)
  ![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/df431a4a-b2b4-48cd-aa1a-408cf4ffa824)
  No exemplo a cima eu estou criando uma classe de configuração e configurando um objeto que não é um Beam para ser um Beam do Spring
* @AutoWired (Serve para fazer injeções e também para dizer ao Spring qual construtor usar na hora da instância, exemplo:)
  ![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/dce6b2bf-258d-4260-aaf2-151ec938e803)
* @AutoWired(required = false) (Serve para fazer injeções opcionais)

<div id='lembrete'/>

[Inicio](#init)

# Lembretes
* As pastas dos arquivos da api devem estar dentro da ultima pasta, a que contem o arquivo de start da aplicação
* A forma certa de usar o equals e hashcode é apenas para o id nas entidades, com o lombook fica assim

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/08647f17-fa13-42cf-acde-9ee6af84ff44)


<div id='mensageria'/>

[Inicio](#init)

# Tipos de mensageria

* Comandos
  * A mensageria por comandos em arquiteturas de microserviços é um padrão onde os serviços se comunicam através do envio de comandos assíncronos, que instruem outros serviços a realizar uma ação específica. Em contraste com a mensageria por eventos, onde os serviços publicam eventos para informar sobre algo que aconteceu, na mensageria por comandos, um serviço emite um comando indicando ações que devem ser executadas por outros serviços.
Esses comandos geralmente contêm informações sobre a ação desejada, como uma solicitação para criar, atualizar ou excluir algum recurso, e são consumidos pelos serviços relevantes que têm a responsabilidade de executar essa ação.
Esse modelo permite uma separação clara de responsabilidades entre os serviços. O serviço que emite o comando não precisa saber como a ação será executada, apenas envia a instrução, enquanto o serviço receptor do comando é responsável por processar e realizar a ação especificada.
Assim como na mensageria por eventos, a mensageria por comandos traz benefícios de desacoplamento entre os serviços, permitindo que eles evoluam independentemente, escalabilidade, capacidade de lidar com falhas e facilitação da integração de novos serviços.
Em resumo, a mensageria por comandos é uma abordagem útil para coordenação e execução de ações entre serviços em arquiteturas de microserviços, permitindo uma comunicação assíncrona e eficaz para executar operações distribuídas dentro do sistema.
* Eventos:
  * A mensageria por eventos em microserviços é um padrão de arquitetura no qual os diferentes componentes do sistema se comunicam por meio da troca assíncrona de mensagens. Em vez de os serviços se comunicarem diretamente entre si de maneira síncrona, eles emitem e consomem eventos, que são mensagens contendo informações sobre algo que aconteceu no sistema. Esses eventos podem representar ações, atualizações de estado, solicitações de processamento ou qualquer outra informação relevante para o funcionamento do sistema. Os serviços podem publicar eventos em um barramento de mensagens ou em um sistema de filas, e outros serviços interessados podem se inscrever para receber esses eventos.
* Event notification
  * O "event notification" em microserviços refere-se ao processo de enviar notificações sobre eventos relevantes que ocorrem dentro do sistema para outros componentes ou serviços interessados. Esses eventos podem incluir ações, mudanças de estado, atualizações ou qualquer informação importante para outros serviços no ambiente de microserviços.
Essas notificações são enviadas de forma assíncrona, permitindo que os serviços reajam a eventos específicos sem depender de uma comunicação síncrona direta entre eles. Por exemplo, um serviço pode notificar outros serviços quando uma nova transação é concluída, um pedido é feito, um usuário é registrado ou qualquer outra atividade significativa no sistema.
As notificações de eventos podem ser transmitidas por meio de diferentes mecanismos, como barramentos de mensagens, sistemas de filas ou outros protocolos de comunicação assíncrona. Os serviços interessados se inscrevem para receber notificações específicas, permitindo-lhes reagir ou tomar ações com base nessas informações.
Esse padrão de "event notification" é fundamental para a arquitetura de microserviços, pois promove a desacoplagem entre os serviços, permitindo que evoluam independentemente e sejam mais flexíveis. Também ajuda na escalabilidade e na capacidade de lidar com falhas, pois os serviços podem ser adicionados, removidos ou atualizados sem interromper a comunicação baseada em eventos.
Em suma, o "event notification" nos microserviços é a prática de enviar notificações assíncronas sobre eventos relevantes no sistema para permitir uma comunicação eficiente e reativa entre os diferentes serviços
* Event transfer pattern
  * O "event transfer pattern" nos microserviços é um modelo arquitetônico que descreve a transferência e o processamento de eventos entre diferentes serviços dentro de uma arquitetura orientada a eventos.
Esse padrão trata do fluxo de eventos de um serviço para outro, geralmente usando barramentos de mensagens, sistemas de filas ou outros mecanismos de comunicação assíncrona. Ele envolve a produção, transmissão e consumo de eventos, permitindo que os serviços se comuniquem de maneira desacoplada e reativa.
Os eventos são produzidos por serviços quando algo significativo acontece, como uma alteração de estado, uma ação concluída ou qualquer outra atividade relevante. Esses eventos são então transmitidos para um barramento ou sistema de mensageria, onde outros serviços interessados podem consumi-los e reagir conforme necessário.
O "event transfer pattern" facilita a integração e a comunicação entre serviços em uma arquitetura de microserviços, permitindo uma abordagem mais flexível e resiliente. Ele ajuda na escalabilidade, uma vez que os serviços podem ser adicionados ou removidos sem interromper a troca de eventos entre eles.
Além disso, esse padrão promove a separação de preocupações entre os serviços, permitindo que cada um foque em sua funcionalidade específica sem precisar conhecer os detalhes internos dos outros serviços. Isso leva a uma arquitetura mais modular e adaptável.
Em resumo, o "event transfer pattern" nos microserviços refere-se à troca de eventos entre serviços de maneira assíncrona e desacoplada, facilitando a comunicação e a reatividade dentro de uma arquitetura orientada a eventos.

<div id='problemas'/>

[Inicio](#init)

# Problemas e soluções

## Ambiguidade de Beans

Esse problema acontece quando você tem mais de uma classe implementando uma interface e você injeta essa interface em outra classe Exemplo:
* Você tem a interface Notificador
* Você tem a classe NotificaçãoPorEmail que implementa essa interface
* Você tem a classe NotificaçãoPorSMS que implementa essa interface
* Você tem uma classe que injeta a interface Notificador, nessa hora irá acontecer um erro pois o Spring não sabe qual das implementações ele deve injetar

Resolução 1:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/e547a62c-4218-4833-a97b-137fcf7659b6)

Dessa forma eu estou dizendo que essa classe e a prioridade para ser injetada

Resolução 2:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/518473cb-8c43-4f34-a7a7-226d5a633180)

O @Qualifier é um identificador com ele eu posso escolher qual objeto será injetado. Com isso feito o erro ainda vai continuar e para resolver basta ir na classe que está injetando o objeto e fazer da seguinte forma

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/9530d085-e842-46e7-8bc6-ce7121445927)

Resolução 3:

Criando uma anotação personalizada:

* Primeiro passo: Criar uma anotação

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/4500dfcf-801e-4947-972f-0c968840952b)

* No meu caso eu vou criar um enum do nível de urgência dessa injeção

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/6df83bde-e766-4175-a0cf-6466a4aa7bfd)

* Vou adicionar na minha classe de anotation

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/89139e7c-27fb-48df-a585-b761e2756ce8)

_Esse value significa que quando eu chamar a minha anotação eu não preciso passar o value = NivelUrgencia.URGENT ficando assim:_

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/6296a06a-0310-452e-8931-40e3bcd56f13)

O meu enum vai ter dois valores

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/d4abbe6d-d3cb-43f2-95f4-7ced50b4a28e)

Depois disso voltamos na classe da anotação e adiciona a anotação @Qualifier (Isso vai servir para dizer que essa anotação e do tipo qualificadora)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/8d8b9c9d-d9f0-4373-888a-cf8d9266a560)

E adicionados a anotação @Retention

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/d6117dc3-8e60-49f8-a770-fe7055715045)

_Essa anotação serve para dizer ao spring quanto tempo essa anotação deverá ficar ativa no sistema_

Feito isso a anotação está pronta

<div id='profiles'/>

[Inicio](#init)

# Spring profiles
São como as flavors da aplicação, você pode adicionar diferentes comportamentos dependendo do ambiente

Imagine o cenário em que você precisa enviar um email para um serviço, em produção você realmente envia o email, más em desenvolvimento você apenas envia um email fake. Vamos criar a classe de envio de email fake

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/46fab3ba-b069-413c-bced-89c4ad9b5a3b)

Feito isso na nossa classe que envia o email real basta anotar com o @Profile e passar um nome para ele assim:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/d1c5df69-49d2-4f16-941b-b7ecc9402269)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/682eb135-cc48-4697-92fd-daf6454f5973)

Feito isso nos devemos dizer ao Spring qual profile usar, pode ser das seguinte formas:

* Forma1:
Por application.properties
![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/299ea99a-dfa0-49ff-91b7-f91ca7220b39)

Por linha de comando use o seguinte comando:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/82303c5e-d9ac-4bc7-8902-6c1931a6d93d)

# Chaveando configurações dependendo do Spring profile

## Criando um aplication.properties para cada profile

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/18eb7eef-5739-47cc-bbcb-50858c9d7dcf)

Com isso em cada arquivo você pode adicionar as configurações basicas de cada ambiente

Para ativar os profiles você pode fazer pelo aplication.properties

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/61a4ab1a-8307-4043-8baa-6ad705cc0357)

Ou por linha de comando (Gerando build)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/33782ec1-7ceb-4a30-915e-2c1c9d67fa34)

<div id='beans-life'/>

[Inicio](#init)

# Cliclo de vida dos Beans
* Inicialização do Bean: Essa fase acontece assim que o Bean é instânciado (Essa função é chamada depois do construtor da classe)
* Fase de uso do Bean
* Fase de destruição do Bean
Para acessar as fases do Bean você poder fazer das seguintes formas:
* Criando funções para o cliclo de vida assim:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/401919bd-036f-47b5-8abc-198d384b2154)

Feito isso basta adicionar as seguintes anotações

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/379b879d-71b8-4b8d-956d-7a85cfa8b741)

* Criando a assinatura das funções dentro do @Bean (30:47 da aula 3)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/ac6f4680-045a-484a-a898-d6927b4dd105)

<div id='event-handler'/>

[Inicio](#init)

# Spring event handler
O event handler é um observer (Design pattern).
Imagine uma situação em que você faz o envio de um email e você quer notificar o cliente que o email foi enviado, você pode fazer isso com o observer, assim:
* Primeiro passo: Criar uma classe que represente esse evento:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/3a3f810a-718f-431e-99bd-389f51572066)

* Segundo passo: Disparar o evento

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/76628f62-825b-4b02-9ae9-d1e939b75451)

Na classe que irá disparar o evento basta injetar o ApplicationEventPublisher e fazer o disparo assim

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/0e2c4957-58d0-4956-a1c9-49c1073e7542)

Você ainda pode passar atributos na classe de evento, como por exemplo qual cliente está sendo ativado.

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/f22ce8e9-1e2d-4058-916e-8d9f58dadae2)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/70061f03-6046-4a99-bd09-55392559b7ea)

* Terceiro passo: Implementar o listner.

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/26d2c5a3-f6da-4b23-a4eb-89aeebb9156c)

Vamos fazer uma função que receba a classe de evento como parâmetro

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/63497075-a260-4328-81b9-24bab394deff)

Vamos anotar a função com o @EventListner

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/bf2bf40b-4971-492a-98ad-48d00d662499)

E definir a classe como um componente Spring

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/ee542e4b-6dd3-4c82-bb16-d5476380e258)

Feito isso a nossa classe ja escuta o evento que ela recebe como parâmetro e podemos fazer algo ao receber esse evento, como por exemplo:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/87445c60-55c2-4c8d-a862-7199a8e9ebf1)

<div id='poject-config'/>

[Inicio](#init)

# Fazendo configurações

A forma mais correta de fazer configurações no seu projeto como chaves de apis e outros dados sensíveis e adicionado elas no arquivo application.properties

Algumas das principais propriedades desse arquivo podem ser encontradas aqui: [Link](https://docs.spring.io/spring-boot/docs/1.1.6.RELEASE/reference/html/common-application-properties.html)

# Criando propriedades customizadas

* Primeiro passo: Criar as propriedades no application.properties diretamente dessa forma:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/8228f97a-0298-4732-a48e-a9483979a602)

* Segundo passo: Acessar os valores na aplicação

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/8b32cace-6611-4132-a45f-b1df557177da)

OBS: O ${} È OBRIGATÓRIO

# Criando classe para configurações personalizadas

* Para fazer isso é muito simples basta criar a classe:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/f919d8bb-64c0-4ad0-81cb-12a516d3ad72)

* Adicionar as seguintes anotações:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/447e614f-f43b-4fa8-8cef-7ef88bf0b87f)

OBS: A STRING DENTRO DA ANOTAÇÃO @ConfigurationProperties SE REFERE AO PREFIXO DADO DENTRO DO ARQUIVO aplication.properties

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/490e0641-f3b7-4476-9f6b-b8c18e806388)

OBS2: O NOME DAS VARIÁVEIS DEVEM SER O MESMO NOME DAS VARIÁVEIS DENTRO DO aplication.properties COMO DA PARA VER NAS IMAGENS A CIMA
OBS: PODE SER SOLICITADO UMA NOVA DEPENDÊNCIA NA HORA DE FAZER ISSO

* Feito isso sempre que quiser usar as configurações basta injetar essa classe assim:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/12d09484-21e1-4015-aaaa-a3de3e9c5ae9)

<div id='jpa'/>

# JPA e Hibernate

JPA: Especificação usanda para persistir dados em um banco. Usa uma linguagem SQL chamada JPQL que é nada mais que um SQL mais simples usando a JPA.
HIBERNATE: É um produto da JPA ou seja uma implemetação da JPA.

Exemplo de um mapeamento usando JPA:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/29707d25-13d4-4813-b127-f980d1f40eff)

## Como usar?

- 1 Adicionar a dependência da JPA

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/6824edf0-a81b-4119-940f-e7855c8300f0)

- 2 Adicionar Driver do banco

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/26996dcf-1d1a-4d3f-891a-4787666dd4e9)

- 3 Configurar a conexão com o banco

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/4c4b528a-bf6b-4cf0-8e89-b9e47a4fd152)

<div id='mapping'/>

# Criando mapeamento objeto relacional

Normalmente as classes relacionadas ao banco são as models ou entidade, então vamos criar um model com a anotação @Entity.

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/832bb67c-bbed-4ee3-b1e4-7605b9853f6e)

Você ainda pode mudar o nome da tabela assim:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/3dc5b150-e83a-47e4-8ee8-d56b11e8d78d)

Agora vamos popular com algumas variáveis.

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/4ef17f38-3ae5-40ee-9ce4-1a42e356bf75)

É obrigatorio ter um id do tipo long anotado com a anotação @Id, ou em caso de microserviços esse id deve ser do tipo UUID também com o @Id
A anotação @Column é opcional use somente se quiser mudar o nome da coluna em questão como é o nosso caso
Feito isso basta usar o @Data do lombok para gerar os getters setters e afins

<div id='dados-mock'/>
# Para adicionar dados mockados em sql assim que a aplicaão subir basta adicionar esse arquivo em resourses
  
![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/ca292ca9-b8a1-44d1-9e1b-24769f0ee1d9)

Feito isso basta adicionar os codigos sql

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/ff0dcef8-565b-4904-b6ef-8c3884b9e84d)

Com isso feito basta rodar o projeto e o spring irá fazer toda a mágica sozinho

<div id='drop'/>

# Drop automático em dev

Basta adicionar no application.properties o seguinte comando

```spring.jpa.properties.hibernate.ddl-auto=update```

ddl-auto=update - gera alterações , mas não descarta colunas não mapeadas. Por exemplo, você pode ter uma tabela com 10 colunas e mapeamento apenas para 3 delas; nesse caso, o modo automático pode descartá-las, mas para hibernate/jpa mapeamento completo de entidade de tabela não é obrigatório. Aqui está o ticket jira Alterar e eliminar colunas com hbm2ddl.auto=update criado em novembro de 2011 e agora o status é 'não corrigido'.

Se você atualiza o banco de dados com frequência (seu modelo de domínio é alterado), você pode usar ferramentas ddl/dml como liquibase , flywaydb . Você descreve as alterações do banco de dados no arquivo xml e executa a ferramenta, todas as alterações serão aplicadas automaticamente (com controle automático do que já foi modificado antes e do que deve ser modificado agora).

Apenas recomendação: é melhor usar ferramentas ddl se você não quiser adivinhar por que algo caiu na produção. hibernate.ddl-auto usado principalmente para desenvolvimento, não para produção. Na produção você pode usar nenhum ou validar - pois são seguros.

<div id='ddd'/>

# DDD

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/5182eb8a-5093-4be4-90be-cc2c6acd7e50)

* O AgregateRoot é a Entidade principal dessa tabela sendo as outras filha dessa
* Cada pasta no desenho corresponde a um agregado ou seja um conjunto de tabelas
* Se uma tabela em outro agregado se comunicar com a outra essa deve apontar para o AgregateRoot do outro agregado
* Os repositories são um por agregado

<div id='many'/>

# Muitos para um

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/9fe04636-4efe-4c9d-9338-2aea32477ce7)

No exemplo a cima nós vemos que muitos restaurantes tem uma cozinha então na entidade basta fazer assim:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/b347f42f-0ae8-4e3a-a10b-eadf1a471144)

E para mudar o nome da coluna no banco, basta fazer dessa forma:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/4de7be6a-aeba-4582-9f9d-ee3ac9984ecd)

<div id='id-data'/>

# Buscando dados por id

Para buscar um dado por id basta fazer assim:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/90fde666-f3fa-419b-9115-ea3770a60b17)

O Optional é um tratamento de nulo com ele nós garantimos que o dado nunca seja nulo

<div id='keys-name'/>

# Mudando nome da key no json

Para mudar apenas o nome da chave no json sem alterar o nome das variáveis no spring basta fazer dessa forma:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/8482d90e-aa56-4514-b7c0-06c71dca9abd)

<div id='exclude-data'/>

# Removendo campos do json

Para remover um campo do json basta fazer dessa forma:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/a8727811-ce90-47dd-97f9-70e36619758f)

OBS: PARA FUNCIONAR TEM QUE REMOVER O @JSONPROPERTY

<div id='main-keys-name'/>

# Mudando nome das chaves de uma tabela no json

Para mudar o nome de uma chave que faz referência a outra tabela basta fazer assim:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/4a312aeb-b4bb-44d7-8c8e-22d823ecaef3)

OBS: FUNCIONA APENAS EM XML E LEMBRE-SE DE MUDAR O NOME DO ENPOINT

<div id='http-status'/>

# HTTP Status

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/7be2ac4e-a8dc-4b8e-bbec-107deb9a32ab)

Como mostrado a cima essa é a forma certa de retornar um objeto para o cliente, retornar um ResponseEntity, aqui poderiam ser feito validações como por exemplo se o dado buscado for diferente de nulo retorna HttpStatus.ok e caso não retornar uma 404 por exemplo

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/6bd76448-672c-4ec1-b969-3cd793545397)

Outra forma de fazer o mesmo e com menos código seria dessa forma:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/a1143b46-3e62-456b-a3f8-3d171225682f)

<div id='address'/>

# Mudando endereço de um url

Em caso de mudança de um path para outro endereço você deverá retornar o status 302 com headers informando o novo endereço, dessa forma.

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/11abf8f8-b009-4176-9d97-ea927f394a5b)

Fazendo assim quando uma requisição chegar ela vai ser redirecionada para o local atribuido e um header informará onde está o novo local. O resultado no postman será esse:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/b3d68302-f746-4d0e-8aaf-f6a3377e3cb2)

O cliente será informado onde fica a nova url

<div id='post'/>

# POST

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/3cdb2e31-a823-472d-8b25-b106b3f55a86)

<div id='put'/>

# Put

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/a86df9aa-39a3-407c-a5ef-7708779b606e)

O parâmetro "id" passado no BeanUtils significa o parâmetro que eu não quero copiar da classe que está sendo mandada pelo cliente

<div id='delete'/>

# Delete

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/ea15fa8a-e247-4c65-8b73-fcb22e3f5ded)

No exemplo a cima não mostra más e bom retornar um corpo mostrando o porque deu erro ao deletar o dado, nesse caso o try catch foi necessário pois existe uma relação entre a cozinha e o restaurante portanto não pode deletar essa cozinha do banco

<div id='domain-service'/>

# Domain service

Nada mais é do que a pasta service com os codigos que tem acesso direto ao repository

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/ed47a043-6997-4e60-8802-321037b1d494)

<div id='jpa'/>

# Spring data JPA

Primeiro e preciso da dependencia do JPA
E para criar o repositorio basta fazer assim:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/1f379140-c1b0-493a-b733-0e158535f76b)

<div id='query'/>

# Query methods

São usados para criar queries usando comandos como o findBy

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/108a48fa-fcfd-4555-9163-4e8f705fcef7)

Aqui se usa o findBy junto com o nome do campo no json, o jpa já reconhece e faz a busca

Nesse link se pode consultar as keywords do jpa: https://docs.spring.io/spring-data/jpa/reference/repositories/query-keywords-reference.html

exemplo de uso

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/5194718b-e4a8-4e71-90a0-f99625e8f2c9)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/08b01f9d-c9ee-4ed0-a299-1114c1afe7de)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/6ca9b03e-b72a-4dab-81b7-ac529e600318)

Nesse exemplo a cima eu estou buscando um dado de um objeto e outro dado dentro de outro objeto aninhado

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/1434c232-abef-4cc4-9811-a00d0e4ef2f8)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/6d1b79df-2ba2-4dcb-9d6e-03058be7c0e3)

No exemplo a cima eu estou buscando apenas os primeiros dois campos da busca

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/98339149-f037-4db0-8053-e8c2829aee44)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/4abda01a-01f4-409c-bde9-9b7552c09cdb)

No exemplo a cima eu estou buscando quantos restaurantes tem cadastrados nessa cozinha

No postman o resultado será esse:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/0b48f487-99a7-4377-a2cb-746878e19fd1)

<div id='query-custom'/>

# Query methods customizados

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/62837452-4029-41cf-9999-9b3479bddbdf)

A anottation @Param serve para quando o nome do parâmetro na string da consulta é diferente do nome do parâmetro recebido

<div id='spec'/>

# Implementanco Specifications do DDD

Esse padrão nada mais é do que montar uma query mais robusta baseado no padrão Specification do DDD, isso se baseia em transformar uma consulta sql em uma classe que poderá ser chamada e fazer a busca customizada, no meu caso eu vou usar como exemplo uma busca por restaurante com frete gratis e uma busca por restaurantes com nomes semelhantes

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/c25f3dac-5b0c-48eb-9f9c-9e3e63fb47c8)

Como visto a cima o padrão é o nome da classe com Spec no final e ela deve implementar uma Specification com a entidade a ser retornada, isso vai te obrigar a implementar um serialNumber e uma função

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/1f1a13b3-5b29-40c7-817e-c0f70b479acc)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/72a477d3-2770-42b9-b377-973065e65be4)

Criado os dois Specs você precisa ir no repository e herdar mais uma interface

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/fa0d3457-d694-49aa-979d-9aab497cd8a3)

Agora na controller basta instanciar as duas Spec e chamar o repository assim:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/98f5243d-f548-4f29-9c1a-a8fb33e5c1ca)

O resultado no postman será esse

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/b194aa54-bf0c-4035-aec5-4bfe505c682d)

<div id='spec-factory'/>

# Fazendo uma fábrica de Specifications do DDD

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/9f01a4ff-1232-462a-b103-4e790056a9b1)

<div id='spec-factory-increase'/>

# Melhorando a fábrica de Specifications do DDD

Como vimos a cima não é uma boa idéia ter sempre que ficar chamando a classe de spec em todos os lugares que quiser usar principalmente nas controller, então vamos melhorar isso injetando as nossas spects direto no repository

Primeiro passo, criar uma interface para esses métodos

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/eb8f945c-b2f3-4d4b-8204-98a87ab70e95)

Segundo passo implementar o método

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/ac807afc-e87d-47a3-af0a-762b028d832b)

Más antes de implementar nós vamos precisar de uma instancia do repository da JPA nessa classe

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/2cbb59f5-a460-43c4-9488-865d1342209b)

A anotação @Lazy serve para evitar dependência ciclica

E agora e só chamar os métodos

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/33f6ae4f-7e1f-428a-8983-5fbb931a2156)

<div id='jpa-custom'/>

# Criando um repository JPA custom

As vezes nós precisamos adicionar métodos a o repository jpa para fazer consultas que ele não faz por padrão e para fazer isso basta:

Criando uma interface que estenda da interface da JPA

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/ed86180f-0f87-4392-87f4-ceb35dd3289e)

A diferença aqui é que a anotação @NoRepoisitoryBean faz com que o Spring não reconheça como um repositorio JPA

Para deixar a nossa interface ainda mais customizada vamos usar os generics

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/6b239289-de0e-4e65-9a99-a0db5cb251cf)

Eu vou criar um método que vai buscar apenas a primeira ocorrência no banco

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/e80b9b16-f952-4f54-adb0-27212f2db521)

Agora no nosso repository no lugar de herdar de JPaRepository nós vamos herdade de CustomJPARepository

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/ee251964-4fc6-41ee-88a8-7c4494c22248)

Feito isso nós precisamos implementar o nosso novo método, então vamos criar a classe, lembre-se que ela deve estender de SimpleJPARepository

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/4dbc1f42-cb57-4d2e-9a29-7dec2c905349)

Depois disso nós vamos precisar do EntityManager e de um construtor que chame a classe Super do JPA

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/085124d5-54aa-4fa8-85f1-bcdfa9c9ccf1)

Como nós precisamos do EntityManager e já recebemos ele pelo consturtor então vamos criar uma variável para salvar esse EntityManager na nossa classe

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/d6661643-46b0-48dd-9728-162bf7832f12)

Feito isso vamos implementar o método

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/676172b5-24cb-47ef-be1e-f3d0f159fcfa)

Aqui vai algumas explicações, nós estamos fazendo essa interface para buscar qualquer entidade via generics então por isso que usamos a função getDomainClass() Que busca os dados da classe que está sendo passada nos generics

Com isso feito nós precisamos ativar o customRepository e isso e feito na raiz do projeto assim

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/82dbb555-7950-440b-84fb-c78a65bcc1dc)

Lembre-se que deve ser a classe de implementação a ser chamada

Feito isso basta ir na controller e chamar o método

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/429fdf13-39ab-4c42-8610-ecabd48db1e5)

<div id='one-to-many'/>

# One to many

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/4b32329a-9b99-4945-8953-0bf0bf8462c0)

Normalmente essa relação e feita no objeto oposto ao Many to one, exemplo você tem uma entidade Restaurate e ela tem uma relação Many to one com a entidade Cozinha, então você tem que ir na entidade Cozinha e fazer uma relação One To many com a Entidade restaurante

Feito isso nós precisamos dizer quem que está mapeando as cozinhas na outra entidade ou seja na entidade Restaurante

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/aa6a79df-9f4e-45fc-82f9-bf4dd81b250c)

Como vemos a cima na entidade Restaurante a variável cozinha é que está mapeando a entidade Cozinha nessa tabela, então basta fazer isso:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/aed9ad2f-8a58-4e3b-b04f-2a6e3b1b3c42)

Com isso feito nós iremos ter um problema de serialização cíclica, e para resolver isso basta

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/7ebe8e40-d85f-40b4-8829-5315ad8efb08)

Isso vai fazer com que na entidade Cozinha ela não serialize a entidade Restaurante. É importante fazer essa relação dupla pois mesmo qe não mostrado os dados de restaurante em cozinha a lista ainda é populada e você poderá precisar desses dados depois, más isso depende da regra de negócio nem sempre é preciso fazer essa relação.

<div id='many-to-many'/>

# Many to many

Como estamos trabalhando com a entidade Restaurante, muitos Restaurante pode ter muitas formas de pagamentos

Primeiro passo ir na entidade de restaurantes e fazer assim:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/42176964-c216-4ea7-9515-0ac917c18ea5)

Para mudar o nome padrão dado a tabela fazemos assim:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/de3438c1-1ac9-491a-956a-c83c44da551d)

Para renomear o nome da chave estrangeira no banco nós fazemos assim:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/f30ce9d4-639a-4953-b097-81738166e186)

Para renomear o nome da chave estrangeira que faz referencia a tabela FormaPagamento nós fazemos assim:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/ec2b591f-85a9-4cdd-bb1a-e3539dbea4d5)

<div id='problem-update'/>

# Resolvendo problemas de atualização de dados no banco, quando esses dados tem relação com outras tabelas

Quando as tabelas tem um relacionamento e sempre bom testar os metodos de atualizar dados nessa tabela, nesse caso a cima quando nós tentamos atualizar os dados de um Restaurante ele perde todas as suas formas de pagamento, esse problema é gerado no BeanUtils, quando nós copiamos um objeto para outro objeto, e nós conseguimos resolver assim:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/a410ebbf-e3dd-43d9-998d-72ee5ad31f78)

Basta adicionar o nome da variável formasPagamento para que ele não copie os dados da lista para o novo objeto. Lembrando que esse problema acontece somente se você não tiver DTOs.

<div id='entity-component'/>

# Componentizando entidades

Um bom exemplo disso é a nossa entidade Restaurante, todo restaurante tem um endereço, e para não deixar a nossa entidade muito grande nós vamos criar uma classe Endereço que será importada pela entidade Restaurante, nós fazemos isso com objetos embutidos, elas não são entidades são apenas objetos que serão incorporados por outras entidades

Criando a classe Endereço

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/7d63a689-2360-4e63-b24b-9db774a5c731)

Agora vamos anotar a classe

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/fa3aad97-c93c-4105-ad08-44230487f509)

Customizando o nome das colunas

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/f068f7b4-eda0-44c2-a343-58b225ac6c34)

Adicionando relacionamento com a tabela Cidade

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/84c4b5be-7886-420e-b594-9f2bde77039b)

Agora vamos a entidade Restaurante e incorporar essa classe de Endereço assim:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/21bce255-2578-43ab-b19f-bcdd98f470ec)

<div id='date'/>

# Adicionando data de cadastro e atualização

Primeiro vamos adicionar as propriedades

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/045bb8d6-4424-48c0-a0ef-c435d6b9eb0f)

Adicionando as anotações

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/652910f3-3714-46f1-b5b1-b6e624387a34)

Basta fazer isso para adicionar as datas, porém elas vem com milisegundos ao final da data e para remover os milisegundos basta fazer isso:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/985fcdfc-1bdd-4dd3-baaf-8ab43ced4900)

<div id='serialization-demand'/>

# Adicionando serialização sob demanda com @Lazy

Quando temos relações que terminam com ToOne o jpa sempre irá fazer vários selects no banco mesmo com a anotação @JsonIgnore, para resolver isso basta:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/015b4668-4379-4ea7-b8da-f1cf3d9bbce3)

<div id='pool'/>

# Pool de conexões

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/d8759712-8c57-435d-8534-ee1496c6fcf2)

Sem o pool o usuário faz a requisição, ela é processada e a api interage com o banco fechando a conexão com o banco logo em seguida ficando assim:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/d565aa10-08e4-4d03-93ad-2ba3075cdd36)

Agora imagine que uma segunda requisição foi feita, todo o processo será repetido

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/78e43213-6616-4255-9266-0a5d43edd7c1)

E assim por diante. Agora imagine que nós vamos receber várias requisições simultâneas

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/8c95207e-0711-4c3c-8396-8f20c8069251)

Esse processo irá acontecer para todas essas requisições. A forma de resolver isso é com um pool de conexão, ele irá manter um conjunto de conexões para reutilização

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/249b4b19-db80-4650-872b-2a1d286abeba)

No Exemplo a cima a aplicação está rodando sem requisições no momento, más ainda sim existem algumas conexões criadas

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/d670d138-e9d8-48cb-b577-4582e985309e)

Agora quando as conexões chegam ele usa as conexões aberta e cria outras caso seja necessário ou coloca na fila caso seja configurado para um número máximo de conexões

A forma de implementar isso é usando o Hiraki que é o padrão do spring e por padrão o spring já faz isso más nós podemos configurar no application.properties, assim:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/836241de-ef01-48b7-ab40-2fcd9b6e7f2a)

Também e possível criar um timeout, imagine que a api passou um tempo sem requisições, com essa configuraçaõ o Hikari mata essas conexões depois de um tempo

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/3d3491e7-c1c7-40e4-83ba-ed7ae806b1aa)

Lembrando que o tempo é em milisegundos

<div id='xtress'/>

# Estressando o banco de dados para teste

Para isso é preciso do apache benchmark https://httpd.apache.org/docs/2.4/programs/ab.html

E rodar esse comando: ab -n 1000 -c 100 http://example.com/

Isso enviará 1000 solicitações ao http://example.com/, com um máximo de 100 solicitações concorrentes.

<div id='flyway'/>

# Flyway

As queries geradas automaticamente pelo JPA não são boas de se usar em produção pois não refletem diretamente certas mudanças no banco, então temos a necessidade de usar o flyway pois com ele conseguimos versionar as nossas migrações, portanto o ideal é usar o flyway logo no inicio do projeto

Passo 1: Adicionar a dependencia

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/9b944ad8-f0c9-4a82-a4a1-0bf69ea583d4)

Se você rodar o projeto verá um erro em tela pois o spring não consegue encontrar as suas migrações, então em resources crie essas duas pastas

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/f5bd27b1-7a31-4c7d-b470-0b79f967e8f2)

Vamos criar nosso primeiro script de migração

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/8f699ea1-d341-46ff-9077-7acbacc15f8d)

O nome do arquivo deve seguir um padrão, sempre começar com V maiúsculo depois do V você deve colocar um número, depois do número dois underscore e o nome do arquivo.sql. Esse será o resultado:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/8bcbdde2-7480-49ad-afa7-f4d8a08ac0da)

<div id='flyway-auto'/>

# Gerando Schemas automáticamente

Primeiro passo, no application.properties adicione essa linha

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/bc94b569-ee11-4b19-863e-e2257dc4c469)

Com isso feito o jpa vai gerar os scripts para você sem fazer a migração. Agora nós precisamos especificar um caminho para esses scripts ficarem

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/6f39f8ff-4ef9-4c9e-8a76-6912125d060d)

Com isso feito assim que rodar o projeto ele vai criar toda a migração para nós, então basta comentar as duas linhas que adicionamos no application.properties pois o sql gerado já se refere a todas as nossas migrações e revise o sql gerado

<div id='flyway-populate'/>

# Populando dabelas com flyway

Primeiro passo criar um arquivo sql dentro da pasta migrations exatamente com esse nome

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/ef81a200-7030-4e06-b3ca-33a3a8905c19)

Feito isso basta criar o script sql para popular dados no banco, o ignore no script é obrigatório pois assim ele so insere caso não tenha dados no banco

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/4f0264d4-1e7c-4992-bd78-80f2a19f145b)

Outra forma de fazer isso é da seguinte forma:

Primeiro passo:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/21797d13-8dda-437a-95e3-a1439b9d298b)

Aqui eu estou desabilitando a checagem de chaves estrangeiras e deletando todas a tabelas

Segundo passo:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/6e52d2ee-3b15-45c1-a530-bc919951922f)

Aqui eu estou setando novamente as chaves e mantendo os dados salvos na tabela, lembre-se de não fazer isso nas tabelas de asossiação, ou seja as tabelas que guardam somente as chaves estrangeiras
Com isso feito basta fazer as inserções

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/b3a0f5ba-ee6a-47cb-9181-ec88e7bbfa0b)

<div id='flyway-populate-dev'/>

# Populando dabelas com flyway somente em desenvolvimento

Primeiro passo, em resources/db crie uma pasta, ela pode conter qualquer nomenclatura

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/f9473c48-2a9a-49df-b974-c157a789f302)

Segundo passo, mova o afterMigrate.sql para lá

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/4107f509-ba0a-46a1-8828-7ce3cecf1f52)

Agora no application.properties aponte onde estão as suas pastas de migrações e a de popular dados

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/db7d71e4-2cc1-4125-b274-1770feebaf64)

Feito isso basta criar um application.properties para cada ambiente só que no de homol e release você omite esses dados a cima que o flyway só vai procurar os dados dentro de db/migration

<div id='flyway-error'/>

# Tratando migrações com erros

Sempre que uma migração falhar você precisa ir no flyway-schemma-history e deletar do banco essa migração que falhou, caso contrário o flyway não deixar fazer essa mesma migração novamente, isso funciona bem para migrações simples más em migrações complexas você precisaria deletar as tabelas que foram criadas com sucesso.

Uma forma simples de resolver isso é criando um arquivo chamado flyway.properties e adicionando esses comandos:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/1b698816-f6ea-41c2-be6c-3fca2abbd152)

Com isso pronto rode esse comando

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/c532fa7d-7139-4150-be60-aa805f7b12be)

depois do src/ passe o caminho onde está o flyway.properties

<div id='custom-errors'/>

# Melhorando o tratamento de erros

Primero nós devemos saber que somente as controllers ou services que devem tratar os erros, portanto esses erros nós vamos fazer exclusivamente nas controllers

Então vamos criar a nossa exception dessa forma:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/6daeb537-bb1b-4a86-9189-2d03f4c2e39d)

Com isso feito vamos melhorar nosso tratamento de erro

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/26a71f5a-b629-4db8-838b-a0133f155744)

Simples assim

<div id='error-handler'/>

# Tratando erros com @ExceptionHandler

Essa anotação serve para facilitar o tratamento de erros, e ele se usa nas funções dessa forma:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/76b47cbb-fcca-4f5b-9e36-70915685e48a)

Voce pode passar mais de um tipo de exception para a anotação basta colocar entre {} e separado por virgula. Agora vamos implementar o método

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/389a82e7-582c-4c6f-b713-ab1f6e0a415e)

Basta fazer isso para que esse método capture todas as exceptions desse tipo capturadas pelo catch. E você pode criar outros métodos para tratar outros tipos de erro, exemplo:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/c02aefa4-9139-49d4-939e-3979f0b68ecc)

<div id='error-class'/>

# Criando uma classe para representar dados do erro

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/a7ed3bfb-4aa3-48f2-8e12-60523faffa87)

Uma classe simples porém que usa o builder pattern, e na hora de usar nós devemos fazer assim:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/2e4c380e-ab5e-4b44-9523-b218bee75eea)

O resultado no postman será esse

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/c93e4fb3-9117-4962-9c6b-50f0502b4aa4)

<div id='global-error'/>

# Criando erros globais

Um possível problema do @ExceptionHandler dentro de um controller e que ele só irá capturar os erros desse controlador, e para deixar isso global nós devemos fazer assim:

Primeiro vamos criar a classe global para o nosso erro

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/a90e570a-7f68-472a-bd5b-108f6f74d406)

Agora vamos implementar métodos com as nossas exceptions

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/6d0b37ce-fe83-468b-ab9f-3a4a2724f71e)

O proximo passo será anotar a nossa classe com @ControllerAdvice

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/75e1563f-2f79-4d0a-a7a0-acea98520c35)

Basta fazer isso para que a nossa classe capture globalmente os erros pelo catch e manipule o body do erro
