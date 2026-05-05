# 📍 CepTracker

Aplicação desenvolvida como desafio técnico para consumo de API externa de CEP, com persistência de logs e arquitetura em camadas.

---

## 🚀 Objetivo

A aplicação tem como objetivo realizar consultas de CEP em uma API externa (mockada com WireMock), retornar os dados do endereço e armazenar o histórico dessas consultas em um banco de dados PostgreSQL.

---

## 🧱 Arquitetura

O projeto segue uma arquitetura em camadas:

- **Controller** → expõe endpoints HTTP
- **Service** → contém regras de negócio e orquestra o fluxo
- **Provider** → responsável pela comunicação com API externa
- **Repository** → persistência de dados
- **Entity** → representação das tabelas do banco

---

## 🔄 Fluxo da aplicação

1. Cliente realiza requisição `GET /cep/{cep}`
2. Controller recebe a requisição
3. Service processa a regra de negócio
4. Provider consulta API externa (WireMock)
5. Dados são retornados e processados
6. Log da consulta é salvo no PostgreSQL
7. Resposta é retornada ao cliente

---

## 🌐 Endpoints

### 🔎 Buscar CEP

```http
GET /cep/{cep}
```
### 📋 Listar logs

```http
GET /cep/logs
```

---
### 📦 Modelo de dados
Tabela: logs
| Campo         | Tipo      | Descrição                       |
| ------------- | --------- | ------------------------------- |
| id            | Long (PK) | Identificador único             |
| cep           | String    | CEP consultado                  |
| response_json | Text      | Resposta da API externa em JSON |
| created_at    | DateTime  | Data da consulta                |

---

### 🐳 Infraestrutura com Docker

O projeto utiliza Docker para subir:

- **PostgreSQL** (banco de dados)
- **WireMock** (mock da API externa de CEP)

Subir ambiente:
```bash
docker-compose up -d
```

---

### ⚙️ Tecnologias utilizadas
- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- WireMock
- Docker
- Maven

---

### Exemplo de resposta

```json
{
  "cep": "01001-000",
  "street": "Praça da Sé",
  "city": "São Paulo",
  "state": "SP"
}
```

---

### Desenho de solução
 
<img width="631" height="763" alt="CepTrackerSolution" src="https://github.com/user-attachments/assets/caee9695-8555-4637-9f13-9aae1a716c65" />
