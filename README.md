# API GameList 🕹️
![GitHub repo size](https://img.shields.io/github/repo-size/samuelmsilva2v/gameListAPI?style=for-the-badge)
![GitHub language count](https://img.shields.io/github/languages/count/samuelmsilva2v/gameListAPI?style=for-the-badge)
![GitHub forks](https://img.shields.io/github/forks/samuelmsilva2v/gameListAPI?style=for-the-badge)
![Bitbucket open issues](https://img.shields.io/bitbucket/issues/samuelmsilva2v/gameListAPI?style=for-the-badge)
![Bitbucket open pull requests](https://img.shields.io/bitbucket/pr-raw/samuelmsilva2v/gameListAPI?style=for-the-badge)

API RESTful desenvolvida em Java com Spring Boot que permite que usuários mantenham sua própria biblioteca de jogos, com autenticação JWT, controle de acesso por roles, e organização do código seguindo os princípios de Domain-Driven Design (DDD).

## Tecnologias e Ferramentas
- Java 21
- Spring Boot
- Spring Security com JWT
- Spring Data JPA
- PostgreSQL
- MongoDB
- Maven
- Lombok
- ModelMapper
- RabbitMQ
- MailHog
- Docker (em breve)

## Estrutura DDD
O projeto segue os princípios de DDD, com separação em camadas de:
- `domain`: entidades, enums e regras de negócio
- `application`: controllers e DTOs
- `infrastructure`: repositórios e configurações técnicas

## Segurança
- Autenticação com JWT
- Autorização baseada em roles (`USER`, `ADMIN`)
- Filtros personalizados de autenticação e autorização
- Endpoints públicos e protegidos

## Funcionalidades
* Autenticação & Autorização
  *  Registro de usuário com role (USER ou ADMIN)
  *  Login com geração de token JWT
  *  Validação automática de token nos endpoints protegidos
  *  Controle de acesso baseado em permissões (role)

* Usuários

| Método | Endpoint              | Descrição                    |
|--------|-----------------------|------------------------------|
| POST   | `/api/users/register` | Registrar um novo usuário    |
| POST   | `/api/users/login`    | Autenticar e obter token JWT |

* Jogos

| Método | Endpoint          | Descrição                                 |
|--------|-------------------|-------------------------------------------|
| POST   | `/api/games`      | Cadastra um novo jogo (somente ADMIN)     |
| PUT    | `/api/games/{id}` | Edita os dados de um jogo (somente ADMIN) |
| GET    | `/api/games`      | Consulta todos os jogos                   |
| GET    | `/api/games/{id}` | Consulta um jogo através do ID            |
| DELETE | `/api/games/{id}` | Exclui um jogo (somente ADMIN)            |

* Biblioteca do Usuário (UserLibrary)

| Método | Endpoint                                          | Descrição                                     |
|--------|---------------------------------------------------|-----------------------------------------------|
| POST   | `/api/user-library/{userId}/add-game/{gameId}`    | Adicionar um jogo a biblioteca do usuário     |
| GET    | `/api/user-library/{userId}/remove-game/{gameId}` | Consultar a biblioteca de jogos de um usuário |
| DELETE | `/api/user-library/{userId}`                      | Exclui um jogo a biblioteca do usuário        |

🔒 Todas as operações com biblioteca exigem que o usuário esteja autenticado e só possa manipular sua própria biblioteca.
