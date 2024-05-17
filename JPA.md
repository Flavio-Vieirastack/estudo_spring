<div id='init'/>
  
- [Dicas](#tips)
- [Optional](#optional)
- [@Column](#column)
- [@EntityListner](#listner)
- [Chave composta](#key)
- [Transient](#transient)
- [@PostLoad](#load)
- [Callbacks de estado](#persist)
- [@ElementCollection](#colection)
- [SecondaryTable](#second)
- [Henraça entre entidades](#entity-custom)
- [@Table](#table)
- [@JoinColumn](#join)
- [JPQL](#jpql)

# Dicas

* Em listas que são importantes para a entidade (Relacionamento) e interessante mudar o fetch para eager
* Para atributos de relacionamento ToOne que são pouco usados o ideal e deixar como lazy

<div id='optional'/>

# Optional

Quando você tem um relacionamento em uma entidade e você sabe que sempre que você for salvar essa entidade o relacionamento dela é obrigatório é uma boa idéia colocar o optional dela como false

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/1983f1f5-10c0-40c5-a876-df8ed237ef51)

<div id='column'/>

# @Column

Como não permitir inserções ou atualizações nos campos

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/37285064-851d-40d4-89ec-f9aaf804675c)

## Column definition

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/2ad42db6-049a-443a-a316-7f781f0173de)

## Propriedades do bug decimal

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/8b85a054-e506-4577-ad61-4f47a6dbd26c)

Aqui eu estou dizendo que eu posso ter um número de até 19 digitos com 2 casas decimais

<div id='key'/>

# Chave composta

Para criar uma chave composta basta fazer assim:

Crie uma classe para ela

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/87c5ff8e-6dcd-42e0-ad20-15031c7453ea)

E na entidade faça assim:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/91139fc1-8762-4f9c-a2b7-7245486b75ec)

O json será esse:

```json
{
  "id": {
    "clienteId": 123,
    "numeroPedido": "ABC123"
  },
  "produtos": [
    {
      "codigo": "P001",
      "nome": "Produto 1",
      "quantidade": 2,
      "precoUnitario": 10.0
    },
    {
      "codigo": "P002",
      "nome": "Produto 2",
      "quantidade": 1,
      "precoUnitario": 20.0
    }
  ],
  "outrosAtributos": "valor",
  "total": 40.0
}
```

<div id='transient'/>

# Propriedades transient

São propriedades ignoradas pelo JPA

<div id='load'/>

# PostLoad

O @PostLoad é uma anotação usada em frameworks de mapeamento objeto-relacional, como o Hibernate em Java. Ela é usada para marcar um método em uma entidade que será executado após a entidade ter sido carregada do banco de dados. Isso permite que você realize operações de inicialização ou processamento adicional nos dados carregados antes de usá-los em sua aplicação.

Por exemplo, se você tem uma entidade User e deseja calcular algum valor com base nos dados do usuário após ele ter sido carregado do banco de dados, você pode usar o @PostLoad para realizar essa operação.

Aqui está um exemplo básico de como usar o @PostLoad:

```java

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PostLoad;

@Entity
public class User {
    @Id
    private Long id;
    private String name;
    private int age;

    @PostLoad
    public void init() {
        // Realizar operações de inicialização ou processamento adicional
        System.out.println("Usuário carregado do banco de dados: " + name);
    }

    // Getters e Setters omitidos para brevidade
}
```

Neste exemplo, o método init() será chamado automaticamente sempre que um objeto User for carregado do banco de dados.

<div id='listner'/>

# @EntityListner

O EntityListener é uma interface no contexto da JPA (Java Persistence API) que permite que você defina métodos de callback que são acionados em resposta a eventos relacionados às entidades (objetos persistentes) em um contexto de persistência. Ele é usado principalmente para interceptar e responder a mudanças no ciclo de vida das entidades JPA, como quando uma entidade é persistida, atualizada ou removida do banco de dados.

Os métodos definidos em um EntityListener podem ser usados para executar ações personalizadas antes ou depois de certos eventos ocorrerem em uma entidade, como validações adicionais, atualizações de log, ou qualquer outra lógica de negócio específica.

Por exemplo, você pode ter um EntityListener que registra a data e hora em que uma entidade foi criada ou modificada, atualizando automaticamente esses campos em resposta a eventos de persistência.

Em resumo, o EntityListener é uma ferramenta útil para adicionar lógica personalizada relacionada ao ciclo de vida das entidades em uma aplicação JPA.

```java
import javax.persistence.*;
import java.util.Date;

// Entidade que será monitorada
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private double preco;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaAtualizacao;

    // getters e setters
}

// EntityListener para auditar as entidades
public class AuditingEntityListener {

    @PrePersist
    public void prePersist(Object entity) {
        if (entity instanceof Produto) {
            Produto produto = (Produto) entity;
            produto.setDataCriacao(new Date());
            produto.setUltimaAtualizacao(new Date());
        }
    }

    @PreUpdate
    public void preUpdate(Object entity) {
        if (entity instanceof Produto) {
            Produto produto = (Produto) entity;
            produto.setUltimaAtualizacao(new Date());
        }
    }
}

// Classe principal
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemploPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Produto produto = new Produto();
        produto.setNome("Laptop");
        produto.setPreco(1500.00);

        em.persist(produto);

        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
```

<div id='persist'/>

# @PrePersist

Executado antes de persistir dados no banco

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/e16c84d1-f01d-47bc-b962-587b169f7ccd)

# @PreUpdate

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/57758d68-e463-4f25-a255-3e328ef66059)

# @PostPersist

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/d51b34e2-d0e8-48f2-83e7-259cff22c522)

Nós ainda podemos encadear os métodos

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/63ba30ee-d462-4017-8896-aec573d115ec)


<div id='colection'/>

# @ElementCollection

A anotação @ElementCollection em Java é usada em mapeamentos de objetos relacionais para representar uma coleção de valores embutidos (embedded) ou básicos. Ela é usada em conjunto com a anotação @CollectionTable para especificar a tabela do banco de dados que armazenará os elementos da coleção.

Essa anotação é útil quando você precisa mapear uma coleção de tipos simples (como String, Integer, etc.) ou tipos embutidos (ou seja, tipos que não têm uma identidade própria no contexto do banco de dados) que são parte de uma entidade, mas não merecem uma tabela dedicada.

Aqui está um exemplo básico de como usar @ElementCollection:

```java

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Person {
    @Id
    private Long id;
    private String name;

    @ElementCollection
    private List<String> phoneNumbers;

    // Getters e Setters omitidos para brevidade
}
```

Neste exemplo, a classe Person tem uma coleção de números de telefone. Como os números de telefone não têm uma identidade própria no contexto do banco de dados, eles são armazenados como parte da tabela da entidade Person, e não em uma tabela separada. A anotação @ElementCollection indica que phoneNumbers é uma coleção de elementos que serão armazenados em uma tabela separada. O Hibernate (ou outro provedor de JPA) cuidará automaticamente da criação e mapeamento dessa tabela.

Você pode customizar o nome dessa tabela

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/709aa8aa-747e-4936-a6bf-81b795ab221c)

E também as suas colunas:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/bd95335e-e27f-45c0-9816-9c14576c4307)

Aqui eu tenho uma tabela com duas colunas, uma com o id do produto e outra com a tag que é chamada de tag

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/bd5c68a0-8ed8-4c2a-8644-c77db2a2dc2c)

O mesmo se aplica a objetos Embbedable quando eles são listas

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/ca66bcd1-dfb3-4817-926f-751eefbd573d)

<div id='second'/>

# Secondary table

è uma forma de criar uma tabela a partir dos dados de duas outras tabelas

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/d5390cb6-f2b9-43bb-9fbc-e4d5132865e3)

Com isso feito a tabela e gerada somente com a coluna id, e para gerar o restante das colunas basta:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/c75918ab-95e0-48ce-ae01-f7867a05c375)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/94d393a8-d142-464d-8f40-0f041e4a85a3)

O resultado será esse:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/244b068a-be73-4273-b514-726ef7a92fba)

<div id='entity-custom'/>

# Herança entre entidades

Supondo que nós temos entidades com atributos que podem ser duplicados entre elas, ou tenham algo em comum com seus atributos, nós podemos criar uma base exemplo:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/853052d7-5142-4a53-aaa1-05ac3dd442f1)

Aqui eu criei uma classe que abriga o id, e na hora de usar basta

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/84389362-709b-4e72-8f68-479b7fa9f8e8)

A classe também pode ser abstrata, porém somente para classes qu servem para apenas extensão

## Mesmo exemplo com simgle table

ESSE EXEMPLO E O PRÓXIMO SÃO OS MAIS INDICADOS

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/42a3265f-4d0d-4714-9cee-6e452a60d5c7)

o "dtype" é o nome da tabela que vai ser criada

Na estratégia a cima e criado uma única tabela contendo todos os dados que estão sendo estendidos de outras classes:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/8c1ae022-de82-425f-8f3d-93f11cb15d4b)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/ba994afa-2d7b-4ba2-af53-cb43726051af)

As anotações @Table estão sendo ignoradas portanto pode remove-las

O resultado será esse:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/ff6c4b57-4cf5-4f67-a384-f09ae2d7905a)

## Mesmo exemplo com Tabke per class

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/0aa29a8d-1a2e-483c-a2a9-b77a58e68f57)

Feito isso adicione as anotações @Table

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/1feecb78-899d-416c-a56d-add0a64a46f8)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/0682bff7-dd18-4d12-a35c-508884ef98af)

E não precisa dessa anotação e nem do @DiscriminatorValue e nem da anotação @Table na classe abstrata

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/7fa67960-f59e-416f-be24-92c16233c0bb)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/40298bfd-3434-4c8a-aeea-c9cf0390be08)

## Mesma coisa más com @JoinedTable

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/56e5bf59-7a76-4990-9293-9f19939f63d8)

As demais tabelas devem ficar assim:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/b7009213-9bdb-4a05-b115-df63c9898f21)

<div id='table'/>

# @Table

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/50625ce2-1507-46da-b185-fb90414dcd97)

* Schema: Serve para selecionar o banco de dados usado no momento, ou seja você poder ter no mesmo projeto entidades que acessam bancos de dados diferentes (Bancos criados dentro do mysql por exemplo)
* Unique constraints: São colunas no banco de dados que não podem se repetir (A forma a baixo é a melhor forma pois permite personalizar o nome da unique constraint ao invez de usar @Column(unique=true)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/820cdfbd-ff8b-4a39-a251-0387b56b9888)

* Indexes: Serve para facilitar o banco a encontrar dados de forma mais fácil via pesquisa

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/de5a771c-0470-4154-8c31-492a6b1e69f1)

lembrando que o "nome" deve ser o nome da coluna no banco de dados e não o da variável

<div id='join'/>

# @JoinColumn

Como customizar o nome da FK

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/cc14ac2e-10bd-4065-a0bd-ab62366c56f7)

<div id='jpql'/>

# JPQL

Sintaxe básica:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/fda563d9-571c-4f82-bf06-9c479e04e907)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/1bad1866-d3fe-472b-a04a-e46aab41a0c1)

## Selecionando um atributo da entidade como retorno

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/47a425b3-7c70-4170-b31f-78c00aa5200e)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/35fd1d57-017c-433d-9254-09d91716b200)

## Projeções

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/2af72bec-be1f-47c7-800c-1178b02318d0)

## InnerJoin

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/7c9fe6e2-80ae-4c8e-a2dc-37f105adb5c8)

Você pode deixar apenas o JOIN que funcionará

E você pode fazer projeções

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/963502f9-f3b6-4159-85f4-6565e996b5dc)

E fazer com filtro

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/5d20f4b4-4ac4-4eb2-9f3f-0f6663325e24)

## Fetch

Faz uma busca a mais nas nossas entidades

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/31df29df-18d8-4193-aade-ac492b1c17ca)

Com Joins

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/de7eaa64-a650-4f23-a18d-890e6f5f78b0)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/2061a366-ede2-47f0-8b1d-193b40a1d5a4)

## Passando parâmetros

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/b524891a-551c-4b89-a57e-a7589140d97e)

## Expessões condicionais

### Like

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/3e0447b8-2abe-4b2a-b817-634038823957)

Nesse caso a cima eu quero todos os clientes que tenham o nome igual ao que eu passei como parâmetro

Para buscar apenas sobrenome do cliente

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/f7942801-d53e-40b3-8da3-261d65cbe21e)

Para fazer uma busca mais abrangente por exemplo, todos os clientes que tem "a" no nome

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/f7fc6a65-18e4-43e6-90cf-76ab63eaf900)

### Null e IsEmpty

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/86d858cd-f2bf-404f-a1aa-be8b0dcbe0d8)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/4bb2fb70-4873-4855-b408-0157b6f6d48e)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/e2cf1871-51b6-4014-8c09-0a1c4aeb46fd)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/edff54e7-dc5c-42e2-8ca0-dd595e270ca6)

### Conicionais maior e menor

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/43f4b515-4786-4be4-be47-641244b8ff14)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/2503c8b2-3e81-4d1e-85e1-bcb8300f663d)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/d5e790ff-ee7e-40c8-b9ed-ffd53d04aeac)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/0dc7309a-f79b-46e6-a1d5-1cb8496c1c26)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/8a5d2612-54c8-4804-adde-e41596972f84)

### Between

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/45b92f85-7bc8-4cb3-ab1d-7026495bff7f)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/f158cd94-a249-4865-9b4f-cae82d837632)

### Diferente

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/cc9cfc8e-5b65-418e-816d-85e96f465364)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/9ce73a2c-6350-4fbe-b41c-2f380529b754)

### Operadores lógicos

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/de84a58a-a7bf-45a3-a1a2-9bd652048e70)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/30b61b44-2dcb-426f-8961-a9cfbd2cd191)

### Ordenação

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/a81a85e9-2f69-4b5b-9878-eb23e73c09aa)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/2bac34d8-abb0-4fcf-82bf-e2d02cd44239)

### Funções para Strings

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/54758fbb-7d30-4598-bc97-90b7a4c5f503)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/9ca4b2d0-7cc9-4d6d-806b-76149672b809)

Resultado:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/50945d80-2c0d-42f4-bda9-5c5fe6f29935)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/43e72e02-6a2a-4c52-aed3-6b0a52646537)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/b5a8141a-ec7d-498d-8677-fe6261ccdd2e)

No Exemplo a cima usamos o locate que busca o índice da string adicionada

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/cb85e5f0-d3c2-46cf-946c-7a287fd0234c)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/ab2f7238-0760-41b8-af72-69e5612c6dae)

* Upper: Deixa em caixa alta
* Trim: Remove espaços do início e do fim da String

Uso em conjunto:

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/7409ba61-ce17-4816-b697-456da8578abe)

### Funções para datas

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/9661dcb2-02f3-48dd-bb35-2aa79a0675f7)

### Funções de agregação

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/a4c8aba8-552e-4e99-a4a9-61c9844466ac)

* avg: Fazer média
* count: Conta a quantidade de registros no banco, tanto faz o parâmetro que você passar, ele sempre vai retornar o total de registros
* min: Pega o menor valor
* max: Pega o valor máximo
* sum: somar tudo

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/9ce39c90-9c2d-4c2f-9201-1759758161c5)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/11824c32-a847-4921-aa11-04224d24ce2a)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/e62307a3-e17b-4d98-b269-df0625201bab)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/f3074cec-d28b-400e-867f-ae17f383bb20)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/0c19a98a-0528-45d2-b171-376183d0f9b4)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/eafeac81-9802-4ebd-b3c6-190bf7a7c785)

### Agrupando registros

Agrupando produtos por categoria e fazendo o count delas

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/61c37219-5ab4-4586-aa98-7f47254b01d3)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/0a4dd287-124d-4b7d-bcec-00bd975e8cf3)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/8e76b290-848e-4a77-8c56-ac3fcdf8e7b8)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/aaf3dcef-bcc6-47f5-b303-3b7859695fee)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/2cd00a52-a734-47a4-af37-c5b35cb7bfe1)

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/4941e74a-f71b-438c-99f8-70643f521b99)



