<div id='init'/>
  
- [Dicas](#tips)
- [Optional](#optional)
- [@Column](#column)
- [Chave composta](#key)

# Dicas

* Em listas que são importantes para a entidade (Relacionamento) e interessante mudar o fetch para eager
* Para atributos de relacionamento ToOne que são pouco usados o ideal e deixar como lazy

<div id='optional'/>

# Optional

Quando você tem um relacionamento em uma entidade e você sabe que sempre que você for salvar essa entidade o relacionamento dela é obrigatório é uma boa idéia colocar o optional dela como false

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/1983f1f5-10c0-40c5-a876-df8ed237ef51)



# @Column

Como não permitir inserções ou atualizações nos campos

![image](https://github.com/Flavio-Vieirastack/estudo_spring/assets/85948951/37285064-851d-40d4-89ec-f9aaf804675c)

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


