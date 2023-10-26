# Data Validator

[![Actions Status](https://github.com/biscof/java-project-78/workflows/hexlet-check/badge.svg)](https://github.com/biscof/java-project-78/actions)
[![Actions Status](https://github.com/biscof/java-project-78/workflows/build-and-test/badge.svg)](https://github.com/biscof/java-project-78/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/f8ef71f110ec7be5a1f7/maintainability)](https://codeclimate.com/github/biscof/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/f8ef71f110ec7be5a1f7/test_coverage)](https://codeclimate.com/github/biscof/java-project-78/test_coverage)


## Overview

The Data Validator is a Java library created to validate integers, strings, and maps against specified patterns. It is built on implementing a fluent interface and can be used to validate data entered into forms, as an example.


## Usage

#### Validating Strings

```java
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;

Validator validator = new Validator();

boolean isStringValid = validator.string()
        .required()
        .minLength(5)
        .contains("example")
        .isValid("This is an example string.");
System.out.println("String is valid: " + isStringValid); // String is valid: true
```

#### Validating Integers

```java
import hexlet.code.Validator;
import hexlet.code.schemas.IntegerSchema;

Validator validator = new Validator();

boolean isIntegerValid = validator.integer()
        .required()
        .positive()
        .isValid(42);
System.out.println("Integer is valid: " + isIntegerValid); // Integer is valid: true
```

#### Validating Maps

```java
import hexlet.code.Validator;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;

Validator validator = new Validator();
Map<String, String> map = new HashMap<>(
      Map.of("status", "new", "author", "anonymous", "length", 30)
);

// Basic map validation
boolean isMapValid = validator.map()
        .required()
        .sizeof(4)
        .isValid(map);
System.out.println("Map is valid: " + isMapValid); // Map is valid: false

// Map's inner structure validation against the custom pattern (shapeMap)
Map<String, BaseSchema> shapeMap = new HashMap<>();
shapeMap.put("name", validator.string().required());
shapeMap.put("length", validator.integer().positive());
boolean isMapValid = validator.map()
        .size(3)
        .shape(shapeMap) // checks whether "name" is present, and "length" is a positive integer
        .isValid(map);
System.out.println("Map is valid: " + isMapValid); // Map is valid: true
```

## Dependencies

Java 17
