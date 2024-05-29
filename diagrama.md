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

