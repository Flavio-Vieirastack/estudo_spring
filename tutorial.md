# Parei em 12:06 da aula 3

# Tutorial spring
# Comandos
* ./mvnw clean (Limpa os builds anteriores)
* ./mvnw dependency:tree (Mostra lista de dependencias)
* ./mvnw dependency:resolve (Mostra lista de dependencias resolvidas)
* ./mvnw help:effective-pom (Gera o pom efeitivo do projeto)
  
# O que é cada coisa
* Maven gerencia as dependencias do projeto e build
* POM (Project Object Model) é onde ficam as configurações do maven e do projeto
* Plugins podem ser adicionados nesse espaço no pom.xml
![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/5b602f94-10cd-44ee-89ee-81bf4981267b)
* Efective POM: Mostra o pom final do projeto

# Anotações
* @Bean (São objetos que devem ser gerenciados pelo spring, ou seja sempre que uso a anotação @Bean eu estou dizendo que aquele objeto deve ser gerenciado pelo spring. Também existe a anitação @Component que transforma a classe em questão em um bean)
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


# Lembrete
* As pastas dos arquivos da api devem estar dentro da ultima pasta, a que contem o arquivo de start da aplicação

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
