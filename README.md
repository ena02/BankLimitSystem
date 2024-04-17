<h1 align="center">Bank Limit System <img src="https://github.com/blackcater/blackcater/raw/main/images/Hi.gif" height="32"/></h1>
<h3 align="center">Сервис для выставление лимитов в счете</h3>

Во время запуска миграция сама создает все таблиций.
Для таблиц account и expense_category значение даются во время миграций
У вас есть 2 аккаунта для транзакций между собой 
В expense_category есть две котегори затрат 1 - Product, 2 - Service

2 варианта запуска:

1.Запустите с перва Postgre и создай там базу bank_limit_system
Потом запустите сервис


2. Через Docker

  перейдите на директорию проекта и запустите:  docker-compose up --build -d

  сервис доступен на порту 8081
