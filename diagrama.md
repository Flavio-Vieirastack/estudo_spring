![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/dc86ef19-1551-4e28-b5f0-98d26fb1b4bc)


Símbolos de Cardinalidade
# Um

Representado por uma linha reta com uma barra vertical.
Significa que cada instância da entidade está relacionada a exatamente uma instância da outra entidade.

# Muitos

Representado por uma linha reta com uma linha em forma de pé de galinha (ou garfo) na outra extremidade.
Significa que cada instância da entidade pode estar relacionada a várias instâncias da outra entidade.

# Um (e somente um)

Representado por uma linha reta com duas barras verticais.
Significa que cada instância da entidade está relacionada a exatamente uma e somente uma instância da outra entidade.
É uma especificação mais restrita do primeiro símbolo "Um".

# Zero ou um

Representado por uma linha reta com um círculo e uma barra vertical.
Significa que uma instância da entidade pode estar relacionada a zero ou uma instância da outra entidade.

# Um ou muitos

Representado por uma linha reta com uma barra vertical e uma linha em forma de pé de galinha.
Significa que cada instância da entidade deve estar relacionada a pelo menos uma, mas possivelmente várias instâncias da outra entidade.

# Zero ou muitos

Representado por uma linha reta com um círculo e uma linha em forma de pé de galinha.
Significa que cada instância da entidade pode estar relacionada a zero ou várias instâncias da outra entidade.

# Exemplo de Aplicação
Vamos aplicar esses símbolos a alguns exemplos práticos:

1. Relacionamento Um para Um (1:1)
Entidades: Pessoa e Passaporte
Símbolo: Um (e somente um)
Explicação: Cada pessoa tem exatamente um passaporte e cada passaporte pertence a exatamente uma pessoa.
2. Relacionamento Um para Muitos (1
)
Entidades: Professor e Disciplina
Símbolo: Um (Professor) e Muitos (Disciplina)
Explicação: Um professor pode lecionar várias disciplinas, mas cada disciplina é lecionada por um único professor.
3. Relacionamento Muitos para Muitos (M
)
Entidades: Aluno e Disciplina
Símbolo: Muitos (Aluno) e Muitos (Disciplina)
Explicação: Um aluno pode se inscrever em várias disciplinas, e cada disciplina pode ter vários alunos.
Conclusão
Os símbolos de cardinalidade são essenciais para definir claramente as regras de relacionamento entre entidades em um banco de dados. Eles ajudam a garantir que o modelo de dados represente com precisão os requisitos do sistema que está sendo modelado.

# Exemplo em código

Um (Representado por uma linha reta com uma barra vertical):

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/27dae2d8-acd6-4ab9-ba71-e58117896c1f)

```java
public class Empresa {
    private String nome;
    private Departamento departamento;

    public Empresa(String nome, Departamento departamento) {
        this.nome = nome;
        this.departamento = departamento;
    }

    // Getters e setters
}

public class Departamento {
    private String nome;

    public Departamento(String nome) {
        this.nome = nome;
    }

    // Getters e setters
}
```

Muitos (Representado por uma linha reta com uma linha em forma de pé de galinha):

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/5bedc68e-6efe-4ccd-971d-6aeacde16dfd)

```java
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private List<Pedido> pedidos;

    public Cliente(String nome) {
        this.nome = nome;
        this.pedidos = new ArrayList<>();
    }

    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    // Getters e setters
}

public class Pedido {
    private int numero;

    public Pedido(int numero) {
        this.numero = numero;
    }

    // Getters e setters
}
```

Um (e somente um) (Representado por uma linha reta com duas barras verticais):

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/066f55af-f7c3-4a67-a9b2-bc27085751a2)

```java
public class Presidente {
    private String nome;
    private Pais pais;

    public Presidente(String nome, Pais pais) {
        this.nome = nome;
        this.pais = pais;
    }

    // Getters e setters
}

public class Pais {
    private String nome;

    public Pais(String nome) {
        this.nome = nome;
    }

    // Getters e setters
}
```

Zero ou um (Representado por uma linha reta com um círculo e uma barra vertical):

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/a52729ed-70ca-483f-bdaf-b0038b2a6de1)

```java
public class Estudante {
    private String nome;
    private Escola escola;

    public Estudante(String nome, Escola escola) {
        this.nome = nome;
        this.escola = escola;
    }

    // Getters e setters
}

public class Escola {
    private String nome;

    public Escola(String nome) {
        this.nome = nome;
    }

    // Getters e setters
}
```

Um ou muitos (Representado por uma linha reta com uma barra vertical e uma linha em forma de pé de galinha):

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/42e859ca-e0dd-40b1-88fa-b8080a88d8b8)

```java
public class Turma {
    private String nome;
    private Professor professor;
    private List<Aluno> alunos;

    public Turma(String nome, Professor professor) {
        this.nome = nome;
        this.professor = professor;
        this.alunos = new ArrayList<>();
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    // Getters e setters
}

public class Professor {
    private String nome;

    public Professor(String nome) {
        this.nome = nome;
    }

    // Getters e setters
}

public class Aluno {
    private String nome;

    public Aluno(String nome) {
        this.nome = nome;
    }

    // Getters e setters
}
```

Zero ou muitos (Representado por uma linha reta com um círculo e uma linha em forma de pé de galinha):

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/bccf3d5e-6118-4870-ba35-f9f53e408b41)

```java
public class Projeto {
    private String nome;
    private List<Programador> programadores;

    public Projeto(String nome) {
        this.nome = nome;
        this.programadores = new ArrayList<>();
    }

    public void adicionarProgramador(Programador programador) {
        programadores.add(programador);
    }

    // Getters e setters
}

public class Programador {
    private String nome;

    public Programador(String nome) {
        this.nome = nome;
    }

    // Getters e setters
}
```
