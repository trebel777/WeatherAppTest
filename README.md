# WeatherAppTest

Простое приложение для отображения погоды на несколько дней вперед с использованием Retrofit, RecyclerView и библиотеки Glide.


## Описание

Приложение получает данные о прогнозе погоды с помощью API и отображает их в списке на экране.


## Используемые технологии

- Kotlin
- Retrofit
- RecyclerView
- Glide


## Установка

Склонируйте репозиторий:
Откройте проект в Android Studio.
Вставьте свой API-ключ в файл local.properties:
weatherApiKey=YOUR_API_KEY
Синхронизируйте проект, чтобы загрузить зависимости.


Описание структуры проекта
MainActivity.kt - Главная активность, отображающая список погоды.
WeatherAdapter.kt - Адаптер для отображения элементов погоды в списке.
WeatherData.kt - Классы данных для представления погоды.
WeatherApiService - Интерфейс Retrofit для получения данных о погоде.