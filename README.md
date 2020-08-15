# Desenvolvimento

Software requerido:

- JDK 8
- Maven
- IDE Java
- Mysql 8.0

# Configuração

Criar banco de dados para o projeto.

```mysql
CREATE DATABASE boot;
```

# Migrações de banco de dados

Criar arquivo SQL na pasta `src/main/resources/db/migration`, seguindo o seguinte padrão:

YYYYMMDDHHMM__descricao.sql

"YYYYMMDDHHMM" deve ser o ano, mês, data, hora e minuto de criação do arquivo. Por exemplo, no dia de hoje (7 de fevereiro
de 2018, 12:02), devemos colocar 201802071202.
Recomenda-se identificar na descrição o conteúdo do arquivo de migração.

Criar um novo arquivo de migração para cada conjunto de alterações no banco de dados.
