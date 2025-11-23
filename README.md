🧠 MindMonitor
-------
Sistema de monitoramento de bem-estar corporativo
MindMonitor é uma aplicação desenvolvida em Spring Boot, com front-end em Thymeleaf e banco de dados SQL, focada em registrar o humor diário dos colaboradores e gerar insights semanais sobre o ambiente de trabalho.
O projeto foi criado como um trabalho de Faculdade feito por:
Giovanna Laturague Bueno RM - 556242

📌 Funcionalidades
----------
🔐 Autenticação
- Login com Spring Security
- Proteção de rotas
- Senhas criptografadas com BCrypt

📋 Registros Diários
- Usuário registra:
- Emoções (1 a 10)
- Comentário opcional
- Estado geral do dia
- Validações personalizadas com i18n

📊 Relatórios Semanais
- Usuário responde perguntas sobre:
- Carga de trabalho
- Estresse
- Prazos
- Estado emocional
- Geração de insights básicos

🎨 Interface
- Front-end com Thymeleaf
- Layout limpo, responsivo e simples

🛠️ Tecnologias Utilizadas
-----
| Camada    | Tecnologia                               |
| --------- | ---------------------------------------- |
| Backend   | Spring Boot 3, Spring Web                |
| Segurança | Spring Security, JWT (ou Stateless Auth) |
| Frontend  | Thymeleaf + Tailwind + HeroUI            |
| Banco     | PostgreSQL                               |
| ORM       | Spring Data JPA (Hibernate)              |
| Build     | Maven                                    |
| Linguagem | Java 17+                                 |

🔐 Configuração de Segurança
-------
A classe SecurityConfig controla:
- Rotas públicas (/auth/**)
- Sessão stateless
- BCrypt para senhas
- Filtros e exceções JSON
