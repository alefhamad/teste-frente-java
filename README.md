###
Este submódulo, contém o código em Java para a aplicação FAKEATM.

###
Tecnologias utilizadas.


##
Para executar a aplicação pelo código fonte, clone o repositório, edite o arquivo application.properties com os dados necessários para seu banco de dados MySql. 
Na pasta raíz rode o comando .\fakeatm\mvnw.cmd package, ele criará um binário dentro da pasta target, esse binário pode ser rodado utilizando o JRE >= 1.8.0 com o comando java -jar target\arquivo.jar.

##
Também já existe um binário compilado nesse repositório nas releases, caso queira utilizá-lo, você precisa editar o application.properties utilizando um gerenciar de arquivos compactados como WinRar ou similares e rode com o mesmo comando java -jar target\arquivo.jar.

##
Esse projeto teve como duração 14 dias, 4 dias a mais do estimado pois tive alguns problemas com o Framework que baixou as dependências com os nomes incompletos o que causou diversos problemas com as queries para o banco de dados, além de ter apenas duas horas por dia para realizar o projeto por conta do emprego e faculdade.

## 
Essa aplicação só tem o backend por falta de tempo para um front completo porém como todos os endpoint funcionam, então adaptar qualquer frontend não sería problema.

##
Para utilizar a aplicação você pode utilizar uma plataforma de desenvolvimento de APIs como PostMan:

Singup
~~~
post request /api/auth/signup{
    "name": "string",
    "password": "string",
    "endereco": "string",
    "username": "string",
    "dataDeNascimento": "string"
}
~~~

Signin 
~~~
post request /api/auth/sigin{
    "cpfDto": "cpf",
    "password": "password"
}
~~~
Você receberá um cookie.

Para fazer logout:
~~~
post request /api/auth/signout{
    Body pode estar vazio, a aplicação sabe que você é você e fara a remoção do cookie baseado no contexto
}
~~~
Para ver seu user e saber qual o número da sua conta.
~~~
get request /api/cliente/eu{
    você receberá um objeto Json com as suas informaçãoes.
}
~~~
Para atuliazar um usuário:
```
put request /api/cliente/atualizar{
    "name": "update",
    "password": "update",
    "endereco": "update",
    "username": "12477837826" 
}
```

Para deletar um usuario:
```
delte request /api/cliente/deletar{
    "username": "cpfstring" 
    (Se você for o usário passado no corpo do json e estiver logado, você será deletado)
}
```

Para criar transações.
```
post request /api/transacao/criar{

    "conta": "numeroconta",
    "valor": 500.00,
    "nota": 10 ou 50 ou 100
}
```

Tomei a liberdade de adicionar um número aleatório para cada conta caso exista a necessidade no futuro de transferir valores de um usuário para o outro, tanto por pacote quanto por fundos digitais.

Como os pacotes tem limite de 50 notas e as operções tem limite de 5000, não há como quebrar as operções a não ser que sejam criados novos pacotes portano caso exista uma operação que seja maior que o limite por nota, ela será quebrada em mais pacotes respeitando o limite total de 5000.


Para listar seus pacotes:

get request /api/pacote/lista/todos{
    (Retorna um Json com todos os seus pacotes)
}

get request /api/pacote/lista/ultimo{
    (Retorna apenas seus último pacotes)
}
