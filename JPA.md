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


