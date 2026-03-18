# Dont Jira

Sistema para gestão de projetos e tarefas com visualização hierárquica em árvore (estilo Epics do Jira), com autenticação de usuários e colaboração por projetos.

## Visão geral

A proposta deste projeto é permitir que equipes organizem trabalho em uma estrutura visual que conecta:

- Projeto
- Epics
- Tarefas
- Subtarefas

Tudo em um formato navegável de árvore para facilitar planejamento, acompanhamento e priorização.

## Funcionalidades principais

### 1) Contas de usuário

Cada usuário possui:

- E-mail
- Senha

Autenticação básica para acesso ao sistema e associação de atividades.

### 2) Gestão de projetos

Cada projeto deve ter no mínimo:

- Nome
- Descrição

Campos opcionais:

- Data de previsão de início
- Data de previsão de fim

### 3) Participação em projetos

Um usuário pode participar de um projeto de duas formas:

- Ser adicionado por quem criou/gerencia o projeto
- Solicitar participação no projeto

### 4) Estrutura de tarefas em árvore

O gerenciamento das atividades segue hierarquia visual, similar ao conceito de Epics no Jira, para representar dependências e agrupamentos entre itens de trabalho.

## Licença

Este projeto está licenciado sob a **GNU Affero General Public License v3.0 (AGPL-3.0)**.

Consulte o arquivo [LICENSE](./LICENSE) para o texto completo da licença.
