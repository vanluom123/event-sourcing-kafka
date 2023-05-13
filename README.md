# Java Microservices with CQRS and Event Sourcing using Kafka

This project is a sample implementation of a microservices architecture using Java, CQRS, and event sourcing with Kafka as the message broker.

## Features

- Uses Spring Boot for easy setup and configuration of microservices
- Implements CQRS (Command Query Responsibility Segregation) for a clear separation of concerns between commands and queries
- Uses event sourcing to store all changes to the system as a sequence of events
- Uses Kafka as the message broker for reliable and scalable communication between microservices

## Getting Started

To get started with this project, follow these steps:

1. Clone the repository to your local machine
2. Install Kafka and start the Kafka server
3. Start each microservice using the ./gradlew bootRun command in the respective directory
4. Use the provided REST API to interact with the system

## REST API

The following endpoints are available in the system:

- POST /orders - create a new order
- GET /orders/{orderId} - get details for a specific order
- GET /orders - get a list of all orders
- POST /payments - create a new payment
- GET /payments/{paymentId} - get details for a specific payment
- GET /payments - get a list of all payments

For more information on how to use the API, see the Swagger documentation at http://localhost:8080/swagger-ui.html.

## Contributing

If you'd like to contribute to this project, please fork the repository and submit a pull request. We welcome contributions of all kinds, including bug fixes, new features, and documentation improvements.

## License

This project is licensed under the MIT License - see the LICENSE file for details.
