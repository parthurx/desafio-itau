# 💼 Desafio Itaú - API de Transações

Este projeto é uma API desenvolvida como parte do desafio técnico do Itaú.  
A API permite gerenciar transações financeiras, calcular estatísticas baseadas nas transações realizadas e limpar os dados armazenados.  
A aplicação foi desenvolvida utilizando **Spring Boot** e segue boas práticas de desenvolvimento, como **logs** e **testes automatizados**.

---

## 🚀 Funcionalidades

### ➕ Adicionar Transação  
**Endpoint:** `POST /transacao`  
**Descrição:** Adiciona uma nova transação ao sistema.

#### ✅ Regras de Validação:
- O valor da transação deve ser **maior ou igual a 0**.
- A data/hora da transação **não pode estar no futuro**.
- A data/hora da transação **não pode ser anterior ao limite mínimo permitido**.

#### 🔁 Respostas:
- `201 Created`: Transação adicionada com sucesso.
- `422 Unprocessable Entity`: Transação inválida.
- `400 Bad Request`: Requisição inválida (ex.: JSON malformado).

---

### 🧹 Limpar Transações  
**Endpoint:** `DELETE /transacao`  
**Descrição:** Remove todas as transações armazenadas.

#### 🔁 Respostas:
- `200 OK`: Todas as transações foram removidas com sucesso.

---

### 📊 Obter Estatísticas  
**Endpoint:** `GET /estatistica`  
**Descrição:** Retorna estatísticas das transações realizadas nos últimos **N segundos** (configurável, padrão: **60 segundos**).

#### 📈 Estatísticas retornadas:
- `count`: Quantidade de transações.
- `sum`: Soma total dos valores das transações.
- `avg`: Média dos valores das transações.
- `min`: Menor valor transacionado.
- `max`: Maior valor transacionado.

#### 🔁 Respostas:
- `200 OK`: Estatísticas calculadas com sucesso.

---

## 🛠 Tecnologias Utilizadas

- Java 17  
- Spring Boot 3  
- Maven  
- JUnit 5  
- Mockito  

---

## 🧪 Como Rodar o Projeto

### ✅ Pré-requisitos

- Java 17 ou superior  
- Maven

### ▶️ Passos para rodar localmente:

```bash
# Clone o repositório
git clone https://github.com/parthurx/desafio-itau.git
cd desafio-itau

# Compile e rode a aplicação
./mvnw spring-boot:run
