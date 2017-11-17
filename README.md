Projeto exemplificando a implementação do header `X-Correlation-ID` em requisições HTTP, usando o Feign.

Para rodar o projeto use: `mvn spring-boot:run`

Com a aplicação rodando, teremos 2 endpoints para exemplo, `/hello`, que irá realizar uma chamada para o endpoint `/foo`
. Cada um dos endpoins gera um log com uma mensagem e o `X-Correlation-ID` para podermos acompanhar o seu funcionamento.
