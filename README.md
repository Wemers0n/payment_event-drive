# Projeto: Tópicos Integradores - Microsserviço de Pagamento utilizando Event-Driven

O projeto foi baseado no desafio técnico do PicPay: [PicPay Desafio Backend](https://github.com/PicPay/picpay-desafio-backend)

## Tecnologias Utilizadas
- Arquitetura de microsserviço com event-driven
- Spring (Data, Security, Boot)
- JavaMailSender para envio de email sobre o status das transações
- Docker
- Kafka / Redpanda
- Postgres
- JWT, OAuth2

## Modo de Uso

### Requisitos
- Docker
- Java 17 ou superior
- Apache Maven 3.6.3 ou superior
- Python 3 para executar o build

### Como Executar
- Informar o e-mail e a senha de app para utilizar o serviço de notificação.
- Para executar o projeto, basta rodar o script `build.py`.

## Rotas

### Rota POST: Criar Usuário
`POST localhost:8080/api/users`

#### Exemplo de Payload:
```json
{
	"firstName" : "Free",
	"lastName" : "User",
	"document" : "12345678902",
	"email" : "degene8731@abaot.com",
	"phoneNumber" : "1212345678",
	"password" : "teste",
	"balance" : 1000.0,
	"userType" : "COMMON"
}

--> document e email devem ser únicos.
``` 

### Rota POST: Autenticação
 `POST localhost:8080/api/auth/authenticate`

Instruções:
Passe o firstName e a senha no Basic Auth para que seja retornado um token.

### Rota POST: Transferência
 `POST localhost:8080/api/transaction/transfer`
 #### Exemplo de Payload:
```json
{
	"payer" : 2,
	"payee": 4,
	"value" : 100
}

--> Passe o token gerado do payer pelo Bearer Token para liberar a rota de transferência.
```

### Rota GET: Listar Usuários
 `GET localhost:8080/api/users`

