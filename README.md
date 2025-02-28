# Digio Backend
Este é um exemplo prático de como consumir a API da Digio e retornar os dados de forma mais amigável. Este micro-serviço foi desenvolvido em Java 21, utilizando o framework Spring e seu eco-sistema.


## Detalhes do projeto
- Arquitetura: hexagonal (ports and adapters)
- Resiliência: Apenas circuit breaker

### Rodando o projeto
Para rodar o projeto, você pode apenas executar o comando abaixo na raiz do projeto:
```
mvn clean compile exec:java
```
ou
```
mvn clean package
java -jar {arquivo buildado aqui}
```

Também é possível rodar pelo IntelliJ IDEA, basta abrir o projeto e executar a classe `DigioBackendApplication` que está no pacote `com.eduardoschelive.digiobackend`.