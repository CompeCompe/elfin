# Запуск
1) Перейти в директорию Docker
2) Собрать образ приложения "docker build -t elfin:latest ."
3) Запустить docker compose "docker-compose up -d" (с образом elasticksearch могут быть проблемы, может понадобиться vpn)
4) В этой же директории запустить shell скрипт preload.sh "./preload.sh" (Заполнение elasticksearch тестовыми данными)
5) В camunda modeler открыть bpьт файл diagram.bpmn и задеплоить его в camunda cockpit (localhost:8081) 
6) Запустить процесс в camunde

# Описание взаимодействия
В общем виде процесс можно разбить на 3 кейса 
1) Пользователь ввел данные для регистрации, но в базе уже существует аккаунт с таким email или username. В этом случае регистрация считается неуспешной и на почту отправляется письмо о неудаче
![alt text](https://github.com/CompeCompe/elfin/blob/master/conflict.png)
2) Пользователь ввел данные, в базе нет аккаунтов с такими же данными, домен есть в списке. Отправляется сообщение об успехе
![alt text](https://github.com/CompeCompe/elfin/blob/master/success.png)
3) Домена нет в списке. Тогда возращается пользовательская задача, оператор решает регистрировать пользователя или нет. В зависимости от выбора отправляется соответствующие письмо на почту
![alt text](https://github.com/CompeCompe/elfin/blob/master/manual_checking.png)


# Модель данных
В запросах даные пользователя передаются в виде JSON 
{
	"username":"username",
	"email":"email",
	"password":"password"
}

Пока что в Postgres есть только одна таблица user с полями 
id type="bigserial"
name="username" type="varchar(20)"
name="email" type="varchar(100)"
password type="text" (пароль хранится в зашифрованном виде)

В Elasticksearch хранятся индексы domain с полем name
