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
