# ДЗ4 Система заказов на покупку билетов

## Содержание документации

Данный документ содержит следующие разделы:

- [Сборка сервисов и docker-compose](https://github.com/flowykk/TicketsMicroservice/blob/main/readme.md#%D1%81%D0%B1%D0%BE%D1%80%D0%BA%D0%B0-%D1%81%D0%B5%D1%80%D0%B2%D0%B8%D1%81%D0%BE%D0%B2-%D0%B8-docker-compose)
- [Скрипты создания таблиц и заполнения БД](https://github.com/flowykk/TicketsMicroservice/blob/main/readme.md#%D1%81%D0%BA%D1%80%D0%B8%D0%BF%D1%82%D1%8B-%D1%81%D0%BE%D0%B7%D0%B4%D0%B0%D0%BD%D0%B8%D1%8F-%D1%82%D0%B0%D0%B1%D0%BB%D0%B8%D1%86-%D0%B8-%D0%B7%D0%B0%D0%BF%D0%BE%D0%BB%D0%BD%D0%B5%D0%BD%D0%B8%D1%8F-%D0%B1%D0%B4)
- [Коллекция Postman](https://github.com/flowykk/TicketsMicroservice/blob/main/readme.md#%D0%BA%D0%BE%D0%BB%D0%BB%D0%B5%D0%BA%D1%86%D0%B8%D1%8F-postman)
- [Доступные запросы для микросервиса Авторизации](https://github.com/flowykk/TicketsMicroservice/blob/main/readme.md#%D0%B4%D0%BE%D1%81%D1%82%D1%83%D0%BF%D0%BD%D1%8B%D0%B5-%D0%B7%D0%B0%D0%BF%D1%80%D0%BE%D1%81%D1%8B-%D0%B4%D0%BB%D1%8F-%D0%BC%D0%B8%D0%BA%D1%80%D0%BE%D1%81%D0%B5%D1%80%D0%B2%D0%B8%D1%81%D0%B0-%D0%B0%D0%B2%D1%82%D0%BE%D1%80%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D0%B8)
- [Доступные запросы для микросервиса Покупки билетов](https://github.com/flowykk/TicketsMicroservice/blob/main/readme.md#%D0%B4%D0%BE%D1%81%D1%82%D1%83%D0%BF%D0%BD%D1%8B%D0%B5-%D0%B7%D0%B0%D0%BF%D1%80%D0%BE%D1%81%D1%8B-%D0%B4%D0%BB%D1%8F-%D0%BC%D0%B8%D0%BA%D1%80%D0%BE%D1%81%D0%B5%D1%80%D0%B2%D0%B8%D1%81%D0%B0-%D0%BF%D0%BE%D0%BA%D1%83%D0%BF%D0%BA%D0%B8-%D0%B1%D0%B8%D0%BB%D0%B5%D1%82%D0%BE%D0%B2)
- [Описание архитектуры](https://github.com/flowykk/TicketsMicroservice/blob/main/readme.md#%D0%BE%D0%BF%D0%B8%D1%81%D0%B0%D0%BD%D0%B8%D0%B5-%D0%B0%D1%80%D1%85%D0%B8%D1%82%D0%B5%D0%BA%D1%82%D1%83%D1%80%D1%8B)
- [Тесты](https://github.com/flowykk/TicketsMicroservice/blob/main/readme.md#%D1%82%D0%B5%D1%81%D1%82%D1%8B)

## Сборка сервисов и docker-compose

1. Для начала в случае необходимости необходимо перейти по-отдельности в директории, где находится каждый из микросервисов и собрать **.jar** файлы. Это делается следующим образом:

   Для `AuthMicroservice`:
   ```bash
   cd AuthMicroservice
   ./gradlew bootJar
   ```

   Для `TicketsMicroservice`:
   ```bash
   cd TicketsMicroservice
   ./gradlew bootJar
   ```

   Если команда не запускается и появляется ошибка вида `zsh: permission denied: ./gradlew`, достаточно добавить `bash` в начало строки:
   ```bash
   bash ./gradlew bootJar
   ```

   В рехультате в папках [AuthMicroservice/build/libs](AuthMicroservice/build/libs) и [TicketsMicroservice/build/libs](TicketsMicroservice/build/libs) должны появиться такие файлы:

   ![Снимок экрана 2024-06-09 в 19 25 40](https://github.com/flowykk/TicketsMicroservice/assets/71427624/d100dcb4-42e0-4ce1-ad34-90b62390e7fa)

   ![Снимок экрана 2024-06-09 в 19 26 36](https://github.com/flowykk/TicketsMicroservice/assets/71427624/e74ed0b8-3ba2-4a40-9dca-47e85236d776)

2. Теперь необходимо запустить `docker-compose` файл. Для этого необходимо перейти в директорию, где лежат два микросервиса и `docker-compose` файл и выполнить следующую команду:
   
   ```cmd
   docker-compose up -d
   ```

   Терминал должен показать такой результат:
   
   ![Снимок экрана 2024-06-09 в 19 29 06](https://github.com/flowykk/TicketsMicroservice/assets/71427624/bdbac868-e162-43c8-b100-85d37669b213)

3. Остаётся лишь запустить каждый из микросервисов любым удобным способом.

   После запуска микросервис авторизации будет доступен по `http://localhost:8081/...`, а микросервис для покупки билетов по `http://localhost:8082/tickets/...`

## Скрипты создания таблиц и заполнения БД

Скрипты создания таблиц и список миграций БД приведены в папке [db.changelog](AuthMicroservice/src/main/resources/db/changelog/) для каждого из микросервисов.

Для заполнения таблиц БД было принято решение использовать лишь скрипт для заполнения таблицы `station`, так как остальные таблицы заполняются в соответствии с действиями пользователя, касающимися процесса покупки билетов. Для заполнения таблицы `station` можно использовать скрипт, приведённый в файле [Data/stationsScript.txt](Data/stationsScript.txt), который добавляет в таблицу 50 станций различных российских вокзалов.

**P.S.** Запустить любой из скриптов можно открыв **PostgreSQL Console** прямо в IDE.

## Коллекция Postman

Подготовленную **Postman-Коллекцию** можно у себя, импортировав себе в Postman [файл](kpo-authTicketsService.postman_collection.json).

ИЛИ ЖЕ можно перейти по данной [ссылке](https://web.postman.co/workspace/kpo-authTicketsService~23094e89-c8a0-49e8-98a6-646439bc6267/request/13405344-2bb67e0a-996e-4d0f-8dbf-5d6f4de92c37), заранее авторизовавшись на сайте [postman.com](https://www.postman.com/).

## Доступные запросы для микросервиса Авторизации

**!!!** Данные запросы работают для порта `8081`

### Регистрация

URL: **POST** `../register`

Body:
```json
{
    "nickname": "nick",
    "email": "ex@ex.com",
    "password": "Pass123!"
}
```

Responses:

- `404 Bad Request`: This email is already taken
- `404 Bad Request`: This email address is invalid
- `404 Bad Request`: The password must be at least eight characters long, including uppercase and lowercase letters, digits, and special characters
- `201 Created`: User registered successfully

### Авторизация

URL: **POST** `../login`

Body:
```json
{
    "email": "ex@ex.com",
    "password": "Pass123!"
}
```

Responses:

- `404 Bad Request`: User not found
- `404 Bad Request`: Invalid password
- `200 Ok`: User authenticated successfully

### Получение информации о пользователе

URL: **GET** `../user`

Body: `-`

Responses:

- `404 Bad Request`: Unauthenticated


- `200 Ok`:
```json
{
    "id": 1,
    "email": "ex@ex.com",
    "created": "2024-12-12T12:12:12.000+00:00",
    "username": "name"
}
```

## Доступные запросы для микросервиса Покупки билетов

**!!!** Данные запросы работают для порта `8082`

### Добавление новой станции

URL: **POST** `../tickets/add-station`

Body:
```json
{
    "station": "station_name"
}
```

Responses:

- `404 Bad Request`: Station's name can't be empty
- `404 Bad Request`: Station's name must contain minimum 4 characters
- `404 Bad Request`: Can't add duplicated station
- `200 Ok`: Station added successfully

### Покупка билета

URL: **POST** `../tickets/add-station`

Body:
```json
{
    "fromStation": "station1_name",
    "toStation": "station2_name"
}
```

Responses:

- `404 Bad Request`: Unauthenticated
- `404 Bad Request`: User's session not found
- `404 Bad Request`: User's session got expired
- `404 Bad Request`: FromStation not found
- `404 Bad Request`: ToStation not found
- `404 Bad Request`: Stations can't be same
- `200 Ok`: Your order was created successfully

### Получение информации о заказе

URL: **GET** `../tickets/get-order?orderId={orderId}`

Body: `-`

Responses:

- `404 Bad Request`: Unauthenticated
- `404 Bad Request`: User's session not found
- `404 Bad Request`: User's session got expired
- `404 Bad Request`: Order not found
- `404 Bad Request`: You can't view someone's order


- `200 Ok`:
```json
{
    "id": 1,
    "userId": 1,
    "status": 1,
    "created": "2024-12-12T12:12:12.000+00:00",
    "toStationId": 2,
    "fromStationId": 1
}
```

### Получение информации о всех заказах пользователя (Дополнительный функционал)

URL: **GET** `../tickets/get-orders`

Body: `-`

Responses:

- `404 Bad Request`: Unauthenticated
- `404 Bad Request`: User's session not found
- `404 Bad Request`: User's session got expired


- `200 Ok`:
```json
[
    {
        "id": 1,
        "userId": 1,
        "status": 1,
        "created": "2024-12-12T12:12:12.000+00:00",
        "toStationId": 2,
        "fromStationId": 1
    },
    {
        "id": 2,
        "userId": 1,
        "status": 1,
        "created": "2024-13-12T12:12:12.000+00:00",
        "toStationId": 1,
        "fromStationId": 2
    },
    {
        ...
    }
]
```

### Получение информации о всех доступных станциях (Дополнительный функционал)

URL: **GET** `../tickets/get-stations

Body: `-`

Responses:

- `404 Bad Request`: Unauthenticated
- `404 Bad Request`: User's session not found
- `404 Bad Request`: User's session got expired
- `404 Bad Request`: Order not found
- `404 Bad Request`: You can't view someone's order


- `200 Ok`:
```json
[
    {
        "id": 1,
        "station": "station1"
    },
    {
        "id": 2,
        "station": "station2"
    },
    {
        ...
    }
]
```

## Описание архитектуры

Для приложения используется микросервисная архитектура. Было реализовано 2 микросервиса: `AuthMicroservice` и `TicketsMicroservice`.

В каждом из них написаны следующие `Dockerfile-ы`:

[AuthMicroservice/Dockerfile](AuthMicroservice/Dockerfile)
```Dockerfile
FROM openjdk:21

COPY build/libs/AuthMicroservice-0.0.1-SNAPSHOT.jar AuthMicroservice.jar

CMD ["java","-jar","AuthMicroservice.jar"]
```

[TicketsMicroservice/Dockerfile](TicketsMicroservice/Dockerfile)
```Dockerfile
FROM openjdk:21

COPY build/libs/TicketsMicroservice-0.0.1-SNAPSHOT.jar TicketsMicroservice.jar

CMD ["java","-jar","TicketsMicroservice.jar"]
```

Для связи двух микросервисов друг с другом используеся файл [docker-compose.yaml](docker-compose.yaml), в котором заданы используемые для микросервисов порты (**8081** для AuthMicroservice и **8082** для TicketsMicroservice), параметры БД (PostgreSQL), а также связи между микросервисами.

## Тесты 

Для проверки функционирования **Unit-ов** были написаны **Unit-тесты** для каждого из микросервисов с использованием **JUnit**.

Тесты были написаны для проверки работоспособности сущностей `User`, `Session`, `Order`, `Station`.

### AuthMicroservice - User, Session

#### Результаты запуска

<img src="https://github.com/flowykk/TicketsMicroservice/assets/71427624/ebc9bcce-3080-41ca-a4b9-41d21d6d6221" width="400"> 

#### Покрытие сущностей

<img src="https://github.com/flowykk/TicketsMicroservice/assets/71427624/44b64984-2446-4c36-8f78-49ffbe8431f8" width="400"> 

### TicketsMicroservice - Order, Station

#### Результаты запуска

<img src="https://github.com/flowykk/TicketsMicroservice/assets/71427624/dc9e728b-94a6-42db-87d9-64b3aa75a4c7" width="400"> 

#### Покрытие сущностей

<img src="https://github.com/flowykk/TicketsMicroservice/assets/71427624/6ad9cf39-71eb-41a7-b4ad-cae341aa3f86" width="400"> 
