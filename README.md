# domino-archetype-jogador
Um [Archetype Maven](https://maven.apache.org/guides/introduction/introduction-to-archetypes.html) pra começar a implementar uma IA de um Jogador de [Dominó](https://github.com/brunoabdon/domino). 



## Pra usar:

Como ainda não está no [repositório central](https://search.maven.org/), antes de usar precisa instalar localmente:
```shell
$ git clone https://github.com/brunoabdon/domino-archetype-jogador.git
$ cd domino-archetype-jogador
$ mvn install
```
E então...

```shell
$ cd ..
$ mvn archetype:generate -DarchetypeGroupId=com.github.abdonia.domino \
                         -DarchetypeArtifactId=domino-archetype-jogador \
                         -DarchetypeVersion=1.0-SNAPSHOT \ 
                         -DgroupId=[groupId] \
                         -DartifactId=[artifactId]
```
...subsitituindo `[groupId]` pelo _groupId_ do seu projeto e `[artifactId]` pelo _artifactId_ do jogador sendo criado.

Vai ser criado um projecto na pasta com o nome do _artifactId_. Edite algumas linhas na classe `MeuJogador` e rode um jogo de dominó com ele:

```shell
$ cd myJogadorProject 
$ mvn compile exec:java
```
## Problemas?
[Submeta um bug, dúvida ou sugestão](https://github.com/brunoabdon/domino-archetype-jogador/issues) pra esse projeto. Informações sobre o jogo de dominó ou sobre como implementar um jogador estão na página do projeto: https://github.com/brunoabdon/domino.
