# PetCare API

API REST para gerenciamento de clínica veterinária.

## Tecnologias
- Java 17
- Spring Boot 3.3.5
- Spring Data JPA
- H2 Database
- Maven

## Funcionalidades
- Cadastro de tutores e animais
- Cadastro de veterinários e espécies atendidas
- Agendamento de consultas com validação de horário e habilitação
- Cancelamento de consultas
- Registro de atendimentos e vacinas
- Histórico de atendimentos e vacinação por animal

## Como executar
1. Abra o terminal na pasta do projeto.
2. Execute:

```bash
mvn spring-boot:run
```

3. A API ficará disponível em:

```text
http://localhost:8080
```

4. Para acessar o banco H2:

```text
http://localhost:8080/h2-console
```

Usuário: `sa`
Senha: vazia

## Endpoints principais

### Tutores
- `POST /api/tutores`
- `GET /api/tutores`
- `GET /api/tutores/{id}`
- `PUT /api/tutores/{id}`
- `DELETE /api/tutores/{id}`

### Animais
- `POST /api/animais`
- `GET /api/animais`
- `GET /api/animais/{id}`
- `GET /api/animais/tutor/{tutorId}`
- `PUT /api/animais/{id}`
- `DELETE /api/animais/{id}`

### Veterinários
- `POST /api/veterinarios`
- `GET /api/veterinarios`
- `GET /api/veterinarios/{id}`
- `GET /api/veterinarios/especie/{especie}`
- `PUT /api/veterinarios/{id}`
- `DELETE /api/veterinarios/{id}`

### Consultas
- `POST /api/consultas`
- `GET /api/consultas`
- `GET /api/consultas/{id}`
- `GET /api/consultas/animal/{animalId}`
- `PATCH /api/consultas/{id}/cancelar`
- `DELETE /api/consultas/{id}`

### Atendimentos
- `POST /api/atendimentos`
- `GET /api/atendimentos`
- `GET /api/atendimentos/{id}`
- `GET /api/atendimentos/animal/{animalId}`
- `PUT /api/atendimentos/{id}`
- `DELETE /api/atendimentos/{id}`

### Vacinas
- `POST /api/vacinas`
- `GET /api/vacinas`
- `GET /api/vacinas/{id}`
- `GET /api/vacinas/animal/{animalId}`
- `PUT /api/vacinas/{id}`
- `DELETE /api/vacinas/{id}`
