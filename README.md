# Dont Jira

Plataforma de gestão de projetos e tarefas com visualização hierárquica em árvore (estilo Epics do Jira), autenticação de usuários e colaboração por projetos.

## Objetivo do projeto

Construir uma solução **web SPA** com foco em uso também em aplicativos móveis (via WebView/PWA/híbrido), respeitando contrato de APIs via Swagger e arquitetura em camadas no backend.

---

## Requisitos funcionais

### 1. Usuários

Cada usuário deve possuir conta com:

- E-mail
- Senha

### 2. Projetos

Cada projeto deve conter, no mínimo:

- Nome
- Descrição

Campos opcionais:

- Data de previsão de início
- Data de previsão de fim

### 3. Participação em projetos

Um usuário pode participar de um projeto de duas maneiras:

- Ser adicionado por outro usuário (ex.: criador/gestor do projeto)
- Solicitar participação no projeto

### 4. Gestão hierárquica de trabalho

As informações de planejamento devem ser representadas em **estrutura visual em árvore**, incluindo níveis como:

- Projeto
- Epic
- Tarefa
- Subtarefa

---

## Arquitetura obrigatória

## Frontend

- Framework: **Vue.js**
- UI framework: **Quasar**
- Padrão da camada web: **SPA (Single Page Application)**
- Compatibilidade alvo: uso em aplicações móveis
- Integração com backend: **exclusivamente via serviços definidos no contrato Swagger**

### Diretriz de integração

A camada web **não deve criar endpoints próprios nem consumir endpoints fora do contrato Swagger**.

## Backend

- Linguagem/plataforma: **Java 22**
- Framework: **Spring Boot 4**
- Banco de dados: relacional compatível com SQL (preferencialmente **MySQL**)

### Organização por camadas

1. **Controller**
   - Responsável apenas por receber/navegar requisições HTTP
   - Métodos devem seguir estritamente a especificação da interface Swagger

2. **Service**
   - Responsável exclusivamente por:
     - Regras de negócio
     - Validações
     - Processamento/tratamento de exceções

3. **Persistência (JPA Repository + Hibernate)**
   - Repositórios para acesso a dados
   - Customizações devem usar **SQL nativo** quando necessário
   - **Não utilizar HQL**

### Modelagem de domínio

- As classes devem representar os domínios da aplicação
- Relacionamentos devem ser implementados com chaves estrangeiras (**Foreign Keys**) entre tabelas

---

## Governança de desenvolvimento

- Em caso de dúvida funcional ou de implementação, o desenvolvimento deve ser interrompido para esclarecimento.
- A cada funcionalidade implementada ou modificada, os cenários de teste devem ser executados com sucesso antes de prosseguir.

---

## Licença

Este projeto está licenciado sob a **GNU Affero General Public License v3.0 (AGPL-3.0)**.

Consulte o arquivo [LICENSE](./LICENSE) para o texto completo.

## Contrato Swagger/OpenAPI

O contrato inicial da API está em:

- `swagger/openapi.yaml`

Este arquivo deve ser a fonte para geração de client no frontend e para alinhamento dos métodos de controller no backend.

## Docker (módulos desacoplados)

Os módulos rodam de forma desacoplada, cada um com seu próprio Dockerfile:

- `backend/Dockerfile`
- `frontend/Dockerfile`

Também foi adicionado `docker-compose.yml` para orquestração local dos módulos e do banco MySQL.

### Subir ambiente completo

```bash
docker compose up --build
```

Serviços:

- Frontend: `http://localhost:9000`
- Backend: `http://localhost:8080`
- MySQL: `localhost:3306`
