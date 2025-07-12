# ⚕️ CHRONUS - Microsserviço de Gerenciamento Clínico

Este repositório contém o microsserviço `ms-chronus-gerenciamento`, parte do sistema CHRONUS, responsável por **gerenciar dados clínicos** como check-ups, consultas, exames, tratamentos, enfermidades e histórico de pacientes atendidos pelo SUS.

---

## 📌 Funcionalidades

- Registro e controle de:
  - Check-ups com sinais vitais
  - Consultas médicas presenciais e remotas
  - Exames agendados e realizados
  - Tratamentos com posologias e horários
  - Histórico clínico completo (consolidado)
  - Portal de conteúdo com filtros clínicos
  - Tabela de enfermidades por CID

- Agendamento simulado via `@Scheduled`

---

## 🏗️ Arquitetura

O projeto utiliza a **Arquitetura em Camadas** aliada à **Clean Architecture**, garantindo modularidade e separação entre:

- `domain`: modelos, enums e lógica de domínio
- `application`: DTOs, mappers e serviços de caso de uso
- `infrastructure`: entidades JPA, repositórios, configs
- `web`: controllers REST e tratamento de erros

---

## ⚙️ Tecnologias

- Java 17
- Spring Boot 3
- JPA / Hibernate
- Lombok
- Swagger
- JUnit 5, Mockito, Jacoco
- Banco em memória: H2

---

## 🚀 Como Executar

### Pré-requisitos

- Java 17
- Maven 3.8+

### Passos

```bash
git clone https://github.com/CamilaLima21/ms-chronus-gerenciamento.git
cd ms-chronus-gerenciamento
mvn clean spring-boot:run
```

### Console H2

- URL: `http://localhost:8080/h2-console`
- JDBC: `jdbc:h2:mem:testdb`
- Usuário: `sa`

---

## 🧪 Testes e Cobertura

- Testes unitários com JUnit 5 e Mockito
- Cobertura superior a 80% com Jacoco

Geração dos relatórios:

```bash
mvn clean test
mvn jacoco:report
```

Visualização: `target/site/jacoco/index.html`

---

## 🧭 Melhorias Futuras

- Integração com microsserviço de notificações (SMS, WhatsApp, e-mail)
- Painel web para UBSs visualizarem filas de atendimentos
- Priorização de atendimentos por gravidade clínica
- Implementação de autenticação OAuth2

---

## 🩺 Sobre o CHRONUS

**CHRONUS** é uma plataforma de apoio ao cuidado contínuo no SUS, com foco em pacientes com condições crônicas. Utiliza automação clínica inteligente e modularidade para garantir rastreabilidade, agilidade e humanização do atendimento.

---

## 🤝 Contribuidores

- [@CamilaLima21](https://github.com/CamilaLima21)
- [@Nakandakare9](https://github.com/Nakandakare9)

---

## 📄 Licença

Distribuído sob a Licença MIT.
