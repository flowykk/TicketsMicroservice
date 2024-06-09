# ДЗ4 Сервис по покупке билетов

## Доступные запросы для микросервиса Авторизации

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
    "enabled": true,
    "credentialsNonExpired": true,
    "authorities": [],
    "username": "name",
    "accountNonExpired": true,
    "accountNonLocked": true
}
```

## Доступные запросы для микросервиса Покупки билетов

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
  "from_station": "station1_name",
  "to_station": "station2_name"
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

### Получение информации о всех заказах пользователя

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
  }
]
```
