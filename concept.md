# Spring Framework

Spring is a Java framework used in the development of enterprise applications.  
It provides infrastructure support and lets the developers take care of business logic.

## Core Features Of Spring

### Inversion Of Control
- this is when the control of object creation and lifecycle management is inverted to the framework/container
- it uses Dependency Injection to implement loose coupling

### Aspect Oriented Programming
- this is a programming technique where cross-cutting concerns are separated from logging, transaction, and security

### Data Access
- it simplifies working with databases by reducing boilerplate code
- supports JDBC, JPA, Hibernate, and more
- also handles transactions and exception translation

### Spring MVC
- used to build web applications following the Model-View-Controller architecture
- helps separate business logic, presentation, and navigation

### Spring Security
- provides authentication and authorization features
- helps secure applications with minimal setup

### Spring Boot
- helps in quickly setting up production-ready Spring applications
- reduces a lot of configuration and setup work

### Spring Testing
- comes with built-in support for writing unit and integration tests
- works well with JUnit and Mockito  

# SPRING IoC AND DEPENDENCY INJECTION

When we talk about the **IoC** (Inversion of Control), it is a design pattern where the creation of objects and their lifecycle is assigned to the **Framework/Container**.

## Use of the IoC
- it is responsible for object creation
- it is responsible for resolving dependencies
- it is responsible for creating dependencies

---

## The IoC Container

The **IoC container** is part of the Spring Core. It is responsible for:
- object creation
- dependency injection

### How it works

**Scanning:**
- It detects classes marked for dependency injection

**Register:**
- It registers classes and their dependencies in an internal graph

**Creation:**
- It creates objects and inserts the dependencies in them

**Lifecycle Management:**
- It manages object scope

To interact with the application container, you use the **ApplicationContext**.

---

## What the Container Handles
- Object creation
- Dependency resolving
- Lifecycle management – controls object scope
- Configuration management – handles wiring through annotation and XML

---

## Core Spring IoC Components

### BeanFactory
- Does object creation
- Basic dependency injection

### ApplicationContext
- A powerful extension of the BeanFactory
- Does everything BeanFactory does and more

#### Implementations of ApplicationContext
- `ClassPathXmlApplicationContext`
- `AnnotationConfigApplicationContext`
- `FileSystemXmlApplicationContext`
- `WebApplicationContext`

---

## Dependency Injection

**Dependency Injection** is a programming technique where an object receives its dependencies from an external source.

### Types of Dependency Injection

**Constructor Injection**
- This is when the dependency is set in the constructor
- Can be done using annotation, Java config, or XML

**Setter Injection**
- This is when a dependency is set using a setter method  

# REST Summary

REST stands for **Representational State Transfer**.  
It’s an architectural style used for designing networked applications, especially web services.

## What REST is About

- It uses **HTTP** methods like `GET`, `POST`, `PUT`, `DELETE` to perform operations.
- It's **stateless**, meaning each request from a client to the server must contain all the information needed to understand the request.
- Data is usually sent and received in **JSON** or **XML**, but most modern APIs use JSON.

## REST Principles

1. **Client-Server**
    - Separation between the client (frontend) and server (backend) so both can evolve independently.

2. **Stateless**
    - No session is stored on the server between requests. Each request is standalone.

3. **Cacheable**
    - Responses can be cached to improve performance.

4. **Uniform Interface**
    - Standard way of interacting with the server using URLs and HTTP methods.

5. **Layered System**
    - Requests can go through multiple layers (like load balancers, security filters) without the client knowing.

6. **Code on Demand** *(optional)*
    - Servers can send executable code to the client (like JavaScript), but this is rarely used in REST APIs.

## Common HTTP Methods

- `GET` – fetch data
- `POST` – create new data
- `PUT` – update existing data
- `DELETE` – remove data
- `PATCH` – update part of the data

## RESTful URL Example




