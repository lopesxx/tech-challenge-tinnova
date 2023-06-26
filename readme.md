# Desafios de código:

### Introdução

Todos os arquivos fazem parte desse repositório, basta executar o seguinte comando para clonar em sua máquina:

```sh
git clone https://github.com/lopesxx/tech-challenge-tinnova.git
```

Após clonar o repositório basta acessar o diretório de cada desafio e rodar o comando informado no respectivo readme.md de cada pasta.

### 1 - Votos em relação ao total de eleitores

A partir tabela abaixo...

| Dados | Quantidade |
| ------ | ------ |
| Total de eleitores | 1000 |
| Votos válidos | 800 |
| Votos brancos | 150 |
| Votos nulos | 50 |

Foi criada uma classe com 3 métodos que calculam...
- o percentual de votos válidos em relação ao total de eleitores
- o percentual de brancos em relação ao total de eleitores
- o percentual de nulos em relação ao total de eleitores
  
Repo: https://github.com/lopesxx/tech-challenge-tinnova/tree/main/desafios/desafio-1

### 2 - Algoritmo Bubble Sort

A partir do vetor fornecido...

```sh
v = {5, 3, 2, 4, 7, 1, 0, 6}
```

Foi criado o algortimo para ordenação Bubble Sort

Repo: https://github.com/lopesxx/tech-challenge-tinnova/tree/main/desafios/desafio-2

### 3 - Fatorial

Programa que calcula o fatorial de um número qualquer 

Repo: https://github.com/lopesxx/tech-challenge-tinnova/tree/main/desafios/desafio-3

### 4 - Soma dos múltiplos de 3 ou 4

Programa que calcula a soma de todos os números que sejam múltiplos de 3 ou 5

Repo: https://github.com/lopesxx/tech-challenge-tinnova/tree/main/desafios/desafio-4


### 5 - Projeto Front/back veículos

Criada aplicação que lista veículos, permite edição e busca (filtragem)

##### Como executar o projeto backend:
  - na pasta do projeto execute o comando ``` docker-compose up --detach ```  no terminal
  - em seguida execute o comando ``` mvn clean package -DskipTests```
  - em seguida execute o comando  ``` java -jar target/desafio-5-0.0.1-SNAPSHOT.jar ```

Esses comandos criaram um container para o banco e executaram a aplicação via terminal, para encerrar a aplicação basta fechar o terminal.

##### Como executar o projeto frontend:
  - na pasta do projeto execute o comando ``` npm i ``` no terminal
  - em seguida execute o comando ``` npm run dev ```
  - a aplicação estará disponível no localhost:3000
    
Esses comandos criaram um container para o banco e executaram a aplicação via terminal, para encerrar a aplicação basta fechar o terminal.

API: https://github.com/lopesxx/tech-challenge-tinnova/tree/main/desafios/desafio-5-backend
Front: https://github.com/lopesxx/tech-challenge-tinnova/tree/main/desafios/desafio-5-frontend
