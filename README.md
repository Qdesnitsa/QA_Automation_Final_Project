## **_Test_Automation_with_Java_Final_Project_**

Status of last deployment:<br>
<img src="https://github.com/Qdesnitsa/QA_Automation_Final_Project/workflows/My-GithubActions-Basics/badge.svg?branch=master"><br>

---
+ Разработать фреймворк для автоматизации тестирования UI и API. Например, PageObject.<br>
+ Использовать Selenium или Selenide для UI тестов, Rest Assured для API тестов.<br>
+ Тесты должны запускаться на разных браузерах, а также удаленно на selenium grid - в зависимости от передаваемого значения.<br>
+ Реализовать возможность параллельного запуска тестов на разных браузерах используя selenium grid.<br>
+ Засетапить у себя локально Jenkins, запустить тесты на Jenkins, создать Pipeline.
+ Allure report (со скринами на fail и skip) должен конфигурироваться после рана на Jenkins.
+ Сконфигурировать логи log4j2.<br>

### functional
Реализовать 3 тест кейса для онлайнера:<br>
1. Открыть раздел каталог, проверить присутствие секций "Электроника", "Компьютеры и сети", "Бытовая техника", "Стройка и ремонт", 
   "Дом и сад", "Авто и мото", "Красота и спорт", "Детям и мамам", "Работа и офис".
2. Открыть секцию каталога "Компьютеры и сети". Убедиться, что появляется вертикальный список пунктов секции и присутствуют как минимум
   пункты "Ноутбуки, компьютеры, мониторы", "Комплектующие", "Хранение данных", "Сетевое оборудование".
3. Открыть пункт "Комплектующие". Проверить, что в появившемся списке комплектующих все элементы содержат название, количество товаров
   и минимальную цену.
4. Протестировать функциональность поиска на onliner через переключение в iframe.
 - Все тесты должны быть независимы друг от друга.

### rest-api
1. Перейти на страницу https://catalog.onliner.by/sushivesla
   Воспроизвести запрос на эндпоинт https://catalog.onliner.by/sdapi/catalog.api/search/sushivesla
   с помощью Rest Assured
   Полученный ответ необходимо конвертировать в список объектов Product.
   Каждый объект содержит поля:<br>
   id<br>
   Key<br>
   name<br>
   full_name

Проверить, что каждое полученное имя не пустое.

2. На той же странице выбрать любой фильтр (например роллы), отследить запрос
   и воспроизвести его с помощью Rest Assured.
   Необходимо проверить, что все name_prefix из респонса содержат выбранный фильтр ("Роллы")
   Нет необходимости конвертироваться весь респонс в объект, достаточно по json path
   достать список этих name_prefix.

Note. Если для выполнения запроса куки не нужны, тогда в тестах должен быть только
сам REST запрос, т.е. не нужно поднимать драйвер и навигироваться на эту страницу.
