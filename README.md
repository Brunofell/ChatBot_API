# 🎵 MusicBot Backend

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.5-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![OpenAI](https://img.shields.io/badge/OpenAI-API-blueviolet.svg)](https://openai.com/)

Spring Boot backend for MusicBot - a specialized chatbot focused exclusively on the world of music.

> 🔗 **Frontend Repository**: [MusicBot-Frontend](https://github.com/Brunofell/ChatBot)

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Configuration](#configuration)
- [API Reference](#api-reference)
- [Implementation Details](#implementation-details)
- [Contributing](#contributing)
- [License](#license)

## 🎭 Overview

The MusicBot backend is built with Spring Boot and serves as the intelligence layer for the MusicBot application. It integrates with OpenAI's API to provide music-focused chat capabilities, handling prompting, context management, and real-time streaming of AI responses to the frontend.

## ✨ Features

- **OpenAI Integration**: Leverages Spring AI to communicate with OpenAI's powerful language models
- **Music Domain Specialization**: Configured with system instructions to focus exclusively on music topics
- **Response Streaming**: Implements server-sent events for real-time streaming of AI responses
- **API Endpoints**: RESTful endpoints for chat functionality
- **CORS Configuration**: Configured for seamless communication with the React frontend
- **Environment-based Configuration**: Secure storage of API keys via environment variables

## 🛠️ Tech Stack

- **Java 21**: Latest LTS version with enhanced features
- **Spring Boot 3.4.5**: Core framework for building the application
- **Spring AI**: Spring's integration for AI models (OpenAI)
- **Spring Web**: For building RESTful API endpoints
- **Maven**: Dependency management and build tool

## 🏗️ Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           └── CHATBOT/
│   │               ├── AssistantController.java  # Handles chat endpoints
│   │               ├── ChatBotApplication.java   # Main application class
│   │               └── config/
│   │                   └── WebConfig.java        # CORS configuration
│   └── resources/
│       └── application.properties               # Configuration properties
```

### Key Components

- **AssistantController**: Configures and handles the chat endpoint with OpenAI integration
- **WebConfig**: Sets up CORS to allow communication with frontend applications
- **application.properties**: Contains configuration including OpenAI API key reference

## 🚀 Getting Started

### Prerequisites

- Java 21 JDK
- Maven
- OpenAI API key

### Installation

1. Clone the repository
   ```bash
   git clone https://github.com/yourusername/musicbot-backend.git
   cd musicbot-backend
   ```

2. Build with Maven
   ```bash
   mvn clean install
   ```

3. Set your OpenAI API key as an environment variable
   ```bash
   export API_KEY=your_openai_api_key
   ```
   
   Alternatively, for Windows:
   ```
   set API_KEY=your_openai_api_key
   ```

4. Run the Spring Boot application
   ```bash
   mvn spring-boot:run
   ```

### Configuration

The application is configured in `application.properties`:

```properties
spring.application.name=CHATBOT
spring.ai.openai.api-key=${API_KEY}
```

For production deployments, consider using more secure methods like environment-specific properties files or a secrets management service.

## 📚 API Reference

### Chat Endpoint

```
GET /chat/music?message={userMessage}
```

- **Parameters**:
  - `message`: The user's query about music (URL encoded)
- **Response**: Stream of text chunks from the AI response
- **Content Type**: `text/event-stream`
- **CORS**: Configured to allow requests from `http://localhost:5173` (React development server)

### Example Request

```bash
curl -X GET "http://localhost:8080/chat/music?message=Who%20is%20Mozart" -H "Accept: text/event-stream"
```

## 🔍 Implementation Details

### OpenAI Integration

The backend uses Spring AI's OpenAI integration with the following configuration:

1. **System Instruction**: 
   ```
   You are a member of the MusicBot platform team, a platform that talks about the world of music. 
   Respond in a relaxed but also informative way.
   ```

2. **System Restriction**:
   ```
   Only respond to things related to music. If the question is about a different topic, 
   explain that you can't answer it in a humorous way.
   ```

3. **Response Streaming**:
   The application uses `OpenAiChatModel.stream()` to receive and relay token-by-token responses from OpenAI.

### CORS Configuration

Cross-Origin Resource Sharing is configured to allow requests from the React frontend:

```java
registry.addMapping("/**")
        .allowedOrigins("http://localhost:5173")
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
        .allowedHeaders("*");
```

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

---

Created with ❤️ for music lovers everywhere.
