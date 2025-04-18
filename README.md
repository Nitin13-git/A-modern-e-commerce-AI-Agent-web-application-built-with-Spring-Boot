# E-commerce Web Application

A modern e-commerce web application built with Spring Boot, featuring AI-powered chat interface for natural language interactions.

## Features

- **Product Management**
  - Browse and search products
  - View product details
  - Create, update, and delete products

- **Order Management**
  - Create and track orders
  - Order history

- **AI-Powered Chat Interface**
  - Natural language processing for product queries
  - Support for Anthropic Claude AI

## Tech Stack

- **Backend**
  - Spring Boot 3.2.3
  - Spring AI 1.0.0-M6
  - Spring Data JPA
  - H2 Database
  - Maven (Dependency Management)

- **AI Integration**
  - Anthropic Claude 3.5 Sonnet
  - Natural language processing

## Getting Started

### Prerequisites

- Java 21
- Maven 3.8 or higher
- Anthropic API key

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/ecommerce-webapp.git
   cd ecommerce-webapp
   ```

2. Configure AI provider:
   - Add your Anthropic API key to `application.properties`:
     ```properties
     spring.ai.anthropic.api-key=your-anthropic-api-key
     ```

3. Build the application:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## API Documentation

### Product Endpoints

- `GET /api/products` - Get all products
- `GET /api/products/{id}` - Get product by ID
- `POST /api/products` - Create new product
- `PUT /api/products/{id}` - Update product
- `DELETE /api/products/{id}` - Delete product

### Order Endpoints

- `GET /api/orders` - Get all orders
- `GET /api/orders/{id}` - Get order by ID
- `POST /api/orders` - Create new order

### AI Chat Endpoints

- `GET /api/chat?query=your_message` - Process user query (GET)
- `POST /api/chat` - Process user query (POST)
- `GET /api/chat/stream?query=your_message` - Stream chat response (GET)
- `POST /api/chat/stream` - Stream chat response (POST)

## AI Integration

The application uses Spring AI to provide natural language processing capabilities. The AI system can:

1. Understand user queries about products
2. Search and recommend products
3. Help with order placement
4. Provide product information

### Prompt Engineering

The application uses carefully crafted prompts to ensure accurate and helpful responses:

```java
public class EcommerceRouterPrompt {
    public static final String DEFAULT_ROUTER_PROMPT = """
        You are an AI assistant for an e-commerce store. Your role is to:
        1. Help users find products
        2. Provide product information
        3. Assist with order placement
        4. Answer customer queries
        
        Available tools:
        %s
        
        Please analyze the user's query and respond appropriately.
        """;
}
```

## Testing

The application can be tested using Postman or any HTTP client. Here are some example queries:

1. Product Queries:
   - "What products do you have?"
   - "Show me all electronics products"
   - "Tell me about product with ID abc123"

2. Order Queries:
   - "I want to order 2 units of product abc123"
   - "Show me my order history"

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- Spring Framework
- Anthropic
- H2 Database 