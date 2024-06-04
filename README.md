# Открытая школа разработки T1
# Third Task

Задание: Создание Spring Boot Starter для логирования HTTP запросов

Описание:

Ваша задача - разработать Spring Boot Starter, который предоставит возможность логировать HTTP запросы в вашем приложении на базе Spring Boot.

Требования:

Функциональность:
Ваш Spring Boot Starter должен предоставлять возможность логировать все входящие и исходящие HTTP запросы и ответы вашего приложения.
Логирование должно включать в себя метод запроса, URL, заголовки запроса и ответа, код ответа, время обработки запроса и т.д.

Реализация:
Создайте проект Maven для вашего Spring Boot Starter.
Используйте Spring Boot для автоконфигурации вашего Starter.
Реализуйте механизм перехвата и логирования HTTP запросов с помощью фильтров или интерцепторов Spring, или Spring AOP.
Обеспечьте настройку уровня логирования и формата вывода логов.

## Реализация

1) Реализована библиотека spring-boot-starter-logger, которая реализует логирование HTTP запросов.
2) Реализовано тестовое приложение BookApplication для проверки работы Spring Boot Starter.

## Сборка и запуск

1. Склонируйте репозиторий:

    ```bash
    git clone https://github.com/blesssad/SpringBootStarter
    ```

2. Установите spring-boot-starter-logger в локальный maven репозиторий:

    ```bash
    cd SpringBootStarter/SpringBootStarterLogger
    mvn clean install
    ```
   
3. Проверка работы 
   - Запустите BookApplication предварительно настроив application.properties, по умолчанию логи сохраняются в папке logs.
   - Подключите dependency к вашему проекту.
   ```
   <dependency>
           <groupId>com.openschool</groupId>
           <artifactId>spring-boot-starter-logger</artifactId>
           <version>0.0.1-SNAPSHOT</version>
   </dependency>
   ```


## Эндпоинты BookApplication

- localhost:8080/books/one - POST запрос - добавляет одну книгу.
- localhost:8080/books/many - POST запрос - добавляет несколько книг.
- localhost:8080/books - GET запрос - получить все книги.
- localhost:8080/books/{name} - GET запрос - получить книгу по имени.
- localhost:8080/books/{name} - DELETE запрос - удалить книгу.
- localhost:8080/books/{name} - PUT запрос - обновить книгу.
- localhost:8080/books/category/{category} - GET запрос - возвращает все книги по категории.