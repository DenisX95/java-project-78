# Валидатор данных

[![Actions Status](https://github.com/DenisX95/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/DenisX95/java-project-78/actions)
[![Actions Status](https://github.com/DenisX95/java-project-78/actions/workflows/sonar.yml/badge.svg)](https://github.com/DenisX95/java-project-78/actions)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=DenisX95_java-project-78&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=DenisX95_java-project-78)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=DenisX95_java-project-78&metric=bugs)](https://sonarcloud.io/summary/new_code?id=DenisX95_java-project-78)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=DenisX95_java-project-78&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=DenisX95_java-project-78)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=DenisX95_java-project-78&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=DenisX95_java-project-78)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=DenisX95_java-project-78&metric=coverage)](https://sonarcloud.io/summary/new_code?id=DenisX95_java-project-78)

## Описание

**Валидатор данных** — это Java-библиотека для валидации пользовательского ввода. Она позволяет гибко настраивать правила проверки данных, обеспечивая надежность и читаемость кода.

Библиотека построена на объектно-ориентированном подходе и поддерживает **три основные схемы**:
- `StringSchema` — для валидации строк;
- `NumberSchema` — для валидации числовых значений;
- `MapSchema` — для валидации объектов в формате Map, включая вложенные правила (shape).

Каждая схема предоставляет набор удобных методов, с помощью которых можно пошагово и интуитивно настраивать проверку конкретных значений.

## Установка

### Клонирование репозитория

```bash
git clone https://github.com/DenisX95/java-project-78.git
cd java-project-78
```

### Сборка

```bash
make setup
```

## Документация

### Основной интерфейс
Валидация начинается с создания объекта `Validator`. Он предоставляет методы для получения схем соответствующего типа:

```java
Validator v = new Validator();

StringSchema stringSchema = v.string();
NumberSchema numberSchema = v.number();
MapSchema mapSchema = v.map();
```

Объект `Validator` — это фабрика схем. Каждая схема настраивается отдельно с помощью цепочки методов и применяется к данным через `isValid(...)`.

### StringSchema

Для валидации строк (`String`):

| Метод                    | Описание                                            |
|--------------------------|-----------------------------------------------------|
| `required()`             | Значение обязательно (не `null`, не пустое)         |
| `minLength(int n)`       | Минимальная длина строки — `n` символов             |
| `contains(String text)`  | Строка должна содержать подстроку `text`            |

### NumberSchema

Для валидации чисел (`Integer`):

| Метод                    | Описание                                            |
|--------------------------|-----------------------------------------------------|
| `required()`             | Значение обязательно (не `null`)                    |
| `positive()`             | Значение должно быть больше 0                       |
| `range(int min, int max)`| Значение должно быть в диапазоне `[min, max]`       |

### MapSchema

Для валидации `Map`-объектов::

| Метод                                      | Описание                                            |
|--------------------------------------------|-----------------------------------------------------|
| `required()`                               | Значение обязательно (не `null`)                    |
| `sizeof(int size)`                         | `Map` должна содержать ровно size элементов         |
| `shape(Map<String, BaseSchema<?>> schemas)`| Задает схему для каждого ключа `Map`                |

## Пример использования

```java
var v = new Validator();

// Строка
StringSchema stringSchema = v.string();
stringSchema.required().minLength(3).contains("abc");
stringSchema.isValid("abcde"); // true

// Число
NumberSchema numberSchema = v.number();
numberSchema.required().positive().range(10, 100);
numberSchema.isValid(50); // true

// Map
MapSchema mapSchema = v.map();
mapSchema.required().sizeof(2);

Map<String, BaseSchema<?>> shape = Map.of(
    "name", validator.string().required(),
    "city", validator.string().minLength(3)
);

mapSchema.shape(shape);
mapSchema.isValid(Map.of("name", "Ivan", "city", "Moscow")); // true
```
