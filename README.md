Library System

GraphQL API

Queries: authors, books

Mutations: createAuthor, createBook

Test Cases

JUnit 5 + Mockito for REST controllers

GraphQL integration tests using GraphQlTester

Tech Stack

Java 17

Spring Boot 3

Spring Data JPA

Spring Boot GraphQL

H2 (in-memory DB for testing) / MySQL option

Maven

Setup & Run
1. Clone the Repository
git clone https://github.com/<your-username>/library-rest.git
cd library-rest

2. Build and Run
mvn spring-boot:run

3. Database Access (H2 Console)
http://localhost:8080/h2-console


GraphQL API
Endpoint
POST http://localhost:8080/graphql

Example GraphQL Query (Postman)
POST http://localhost:8080/graphql
Content-Type: application/json

{
  "query": "{ authors { id name } }"
}

Example Mutation
mutation {
  createAuthor(name: "Kiran") {
    id
    name
  }
}

Running Test Cases
mvn test


Includes:

GraphQL tests using GraphQlTester

GraphQL Schema (schema.graphqls)
type Author {
  id: ID!
  name: String!
}

type Book {
  id: ID!
  title: String!
  authorId: ID!
}

type Query {
  authors: [Author]
  books: [Book]
}

type Mutation {
  createAuthor(name: String!): Author
  createBook(title: String!, authorId: ID!): Book
}
