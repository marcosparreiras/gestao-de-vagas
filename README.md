# API - Gestão de Vagas

### Principais tecnologias

- Java

- Spring Boot

- Java Persistence API (JPA)

- PostgreSQL

- JWT

- JUnit

### Entidades

- Candidate

- Company

- Job

# Endpoints

### Candidate

| Método | Rota                         | Necessário autorização | Descrição                                                                      |
| ------ | ---------------------------- | ---------------------- | ------------------------------------------------------------------------------ |
| POST   | /candidate/                  | ❌                     | Cria um novo candidato na aplicação                                            |
| POST   | /candidate/auth              | ❌                     | Inicia a sessão de um candidato na aplicação e retorna um token de autorização |
| POST   | /candidate/jobs/:jobId/apply | ✅                     | Aplica o candidato para a vaga especificada                                    |
| GET    | /candidate/profile           | ✅                     | Retorna os dados do candidato que faz a requisição                             |
| GET    | /candidate/jobs              | ✅                     | Retorna as vagas disponíveis para o candidato                                  |

---

#### POST /candidate/

##### Body

```json
{
  "name": "John Doe",
  "description": "Enthusiastic candidate seeking junior programming opportunities, with promising skills in software development.",
  "userName": "john-doe",
  "email": "johndoe@example.com",
  "password": "123456"
}
```

---

#### POST /candidate/auth

##### Body

```json
{
  "userName": "john-doe",
  "password": "123456"
}
```

---

#### POST /candidate/jobs/:jobId/apply

##### Headers

```bash
Authorization: "Bearer token"
```

---

#### GET /candidate/profile

##### Headers

```bash
Authorization: "Bearer token"
```

---

#### GET /candidate/jobs

##### Headers

```bash
Authorization: "Bearer token"
```

##### Seacrh params

```bash
?filter=junior
```

---

### Company

| Método | Rota          | Necessário autorização | Descrição                                                                     |
| ------ | ------------- | ---------------------- | ----------------------------------------------------------------------------- |
| POST   | /company/     | ❌                     | Cria uma nova empresa na aplicação                                            |
| POST   | /company/auth | ❌                     | Inicia a sessão de uma empresa na aplicação e retorna um token de autorização |
| POST   | /company/job/ | ✅                     | Cria uma nova vaga relacionada a empresa que faz a requisição.                |

---

#### POST /company/

##### Body

```json
{
  "name": "My Company",
  "webSite": "www.mycompany.com",
  "description": "My Company is a leading software development firm known for its innovative solutions and client-centric approach",
  "userName": "my-company",
  "email": "mycompany@example.com",
  "password": "123456"
}
```

---

#### POST /company/auth

##### Body

```json
{
  "userName": "my-company",
  "password": "123456"
}
```

---

#### POST /company/job/

##### Headers

```bash
Authorization: "Bearer token"
```

##### Body

```json
{
  "description": "We are hiring a Junior Software Engineer to join our dynamic team. Responsibilities include coding, testing, and collaborating on innovative software solutions. Apply now!",
  "benefits": "Competitive salary package, Opportunities for career growth and advancement, Health insurance coverage, Flexible work schedule, Generous vacation and paid time off",
  "level": "junior"
}
```
