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
* @Controller (Cria um endpoint)
* @GetMapping (Usa sempre em cima de uma função, ela serve para dizer de qual tipo de requisição estamos falando, ela pode receber uma string que é o endpoint a ser acessado)
* @ResponseBody (Use para dizer que a função vai retornar uma corpo de uma resposta HTTP)

# Lembrete
As pastas dos arquivos da api devem estar dentro da ultima pasta, a que contem o arquivo de start da aplicação

# Parei em 29 e 17 da segunda aula

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
