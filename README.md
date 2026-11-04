# PetCare API

API REST para gerenciamento de clínicas veterinárias, desenvolvida em Java com Spring Boot, Spring Data JPA e banco de dados relacional em memória (H2).

## Informações do aluno
- Nome: Guilherme Fabricio Ferreira
- RA: 234843

## Objetivo
A API permite:
- cadastro de tutores e animais;
- cadastro de veterinários e espécies atendidas;
- agendamento de consultas com validação de habilitação e conflito de horários;
- cancelamento de consultas;
- registro de atendimentos e vacinas;
- consulta ao histórico de atendimento e vacinação por animal.

## Tecnologias utilizadas
- Java 17
- Spring Boot 3.3.5
- Spring Web
- Spring Data JPA
- Spring Validation
- H2 Database
- Maven

## Estrutura do projeto
- `src/main/java/com/petcare/api/model` — entidades JPA
- `src/main/java/com/petcare/api/repository` — repositórios com JpaRepository e consultas personalizadas
- `src/main/java/com/petcare/api/service` — interfaces e implementações da camada de serviço
- `src/main/java/com/petcare/api/controller` — controllers REST
- `src/main/java/com/petcare/api/exception` — tratamento de exceções
- `src/main/resources/application.properties` — configuração da aplicação e H2

## Como executar
1. Acesse a pasta do projeto:
   `cd petcare-api`
2. Execute:
   `mvn spring-boot:run`
3. A aplicação ficará disponível em:
   `http://localhost:8080`
4. Console do banco H2:
   `http://localhost:8080/h2-console`

Dados de acesso ao H2:
- URL: `jdbc:h2:mem:petcaredb`
- Usuário: `sa`
- Senha: vazia

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

## Diagrama UML
O diagrama de classes está disponível no arquivo `PetCareUML.puml` e também em imagem exportada:

![Diagrama UML PetCare](./PetCareUML.png)

<img width="710" height="595" alt="image" src="https://github.com/user-attachments/assets/c5940fbe-7559-4329-a788-9502b15d859c" />


## Observações finais
- As regras de negócio foram implementadas na camada de serviços.
- Os dados são persistidos em banco de dados relacional via JPA.
- A API fornece endpoints de criação, leitura, atualização e exclusão, além de validações de regra de negócio.

## .gitignore
```
target/
*.log
*.h2.db
*.mv.db
*.lock
.idea/
*.iml
.DS_Store
```
