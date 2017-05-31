# domino-archetype-jogador
O Archetype Maven pra começar a implementar um Jogador de Dominó.



## Pra usar:

Ainda não está no repositório central... antes de usar, precisa instalar localmente:
```
$ git clone https://github.com/brunoabdon/domino-archetype-jogador.git
$ cd domino-archetype-jogador
$ mvn install
```

E então...

```
$ cd ..
$ mvn archetype:generate -DarchetypeGroupId=com.github.abdonia.domino \
                         -DarchetypeArtifactId=domino-archetype-jogador \
                         -DarchetypeVersion=1.0-SNAPSHOT \ 
                         -DgroupId=[groupId do seu projeto] \
                         -DartifactId=[artifactId do jogador sendo criado]
```

Editar a classe do Jogador (a única com erro de compilação no protótipo criado) e rodar:

```
$ cd myJogadorProject 
$ mvn compile exec:java
