
# AI-Hunt-Swag-Labs

Объект тестирования:
https://www.saucedemo.com/

Сценарии:
- Standard user login (valid credentials)
- Incorrect password
- Locked out user
- Empty credentials
- Performance glitch user

Тестирование с использованием браузеров:
- Google Chrome
- Mozilla Firefox

Тесты написаны с использованием:
- Java 17
- Selenium WebDriver
- JUnit 5
- Maven
- Allure Reports

# Запуск тестов

---
## IntellijIDEA
* Пакет tests -> SwagLabLoginTests
---
## MacOS / Ubuntu
* bash
* войти в корневую папку проекта
* mvn clean test
* mvn clean test allure:report
* файл Allure report: target/site/allure-maven-plugin
* Запуск Allure report
* cd target/site/allure-maven-plugin
  python3 -m http.server 8000
* в браузере: http://localhost:8000
