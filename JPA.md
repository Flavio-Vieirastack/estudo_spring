<div id='init'/>
  
- [Dicas](#tips)
- [Optional](#optional)
- [@Column](#column)
- [Chave composta](#key)
- [Transient](#transient)
- [@PostLoad](#load)
- [@ElementCollection](#colection)

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

