# CodeCluster Notification Service

The **Notification Service** is a Spring Boot microservice responsible for sending email notifications for the CodeCluster platform.

It supports both **synchronous email sending through a REST API** and **asynchronous email notifications through Apache Kafka**. Emails are generated using an HTML template with dynamic title and content.

---

## Features

* Send emails through a REST API
* Consume email notification events from Kafka
* Send HTML-formatted emails using a reusable template
* Dynamic email title and content
* Request validation using Jakarta Bean Validation
* Return email delivery status to API callers
* Centralized mail-sending logic through the service layer

---

## Technology Stack

* **Java 17**
* **Spring Boot 4.1.0**
* **Spring Web**
* **Spring Kafka**
* **Spring Mail**
* **Spring Validation**
* **Jackson**
* **Maven**
* **Apache Kafka**
* **SMTP**

---

## Project Structure

```text
src/
├── main/
│   ├── java/
│   │   └── codecluster/
│   │       └── notificationservice/
│   │           ├── controller/
│   │           │   └── MailController.java
│   │           ├── dto/
│   │           │   ├── SendMailDto.java
│   │           │   └── SentMailDto.java
│   │           ├── listener/
│   │           │   └── NotificationKafkaListener.java
│   │           ├── service/
│   │           │   └── MailerService.java
│   │           └── NotificationServiceApplication.java
│   │
│   └── resources/
│       ├── application.properties
│       └── templates/
│           └── mail-template.html
│
└── test/
    └── java/
        └── codecluster/
            └── notificationservice/
                └── NotificationServiceApplicationTests.java
```

---

## Email Sending Flow

The service provides two ways to trigger an email:

### REST API

```text
Client
  │
  ▼
MailController
  │
  ▼
MailerService
  │
  ▼
HTML Template
  │
  ▼
SMTP Server
  │
  ▼
Recipient
```

### Kafka

```text
Kafka Producer
      │
      ▼
send-notification-topic
      │
      ▼
NotificationKafkaListener
      │
      ▼
MailerService
      │
      ▼
HTML Template
      │
      ▼
SMTP Server
      │
      ▼
Recipient
```

---

## REST API

### Send Email

**Endpoint**

```http
POST /api/v1/mail/send
```

**Request Body**

```json
{
  "from": "sender@example.com",
  "to": "recipient@example.com",
  "subject": "Welcome to CodeCluster",
  "title": "Welcome!",
  "content": "Your CodeCluster account has been created successfully."
}
```

### Request Fields

| Field     | Type   | Description                      |
| --------- | ------ | -------------------------------- |
| `from`    | String | Sender email address             |
| `to`      | String | Recipient email address          |
| `subject` | String | Email subject                    |
| `title`   | String | Title displayed inside the email |
| `content` | String | Main email content               |

All request fields are required and validated using `@NotBlank`.

### Response

The service returns a `SentMailDto` containing the result of the email sending operation.

Example:

```json
{
  "from": "Auto Emailer",
  "to": "recipient@example.com",
  "status": "Mail sent successfully",
  "sentAt": "2026-08-15",
  "successStatus": true
}
```

---

## Kafka Integration

The service consumes email notification events from Apache Kafka.

### Topic

```text
send-notification-topic
```

### Consumer Group

```text
notification-service-group
```

The Kafka listener receives a `SendMailDto` message and passes it to `MailerService` for processing.

```java
@KafkaListener(
    topics = "send-notification-topic",
    groupId = "notification-service-group"
)
```

This allows other CodeCluster services to publish notification events without directly handling SMTP communication.

---

## Email Template

Emails are generated using:

```text
src/main/resources/templates/mail-template.html
```

The template contains two dynamic placeholders:

```text
{{TITLE}}
{{CONTENT}}
```

Before sending the email, `MailerService` replaces these placeholders with the values received in `SendMailDto`.

The template provides a consistent CodeCluster-branded email layout containing:

* CodeCluster header
* Dynamic title
* Dynamic content
* Team CodeCluster signature
* Automated-email footer

---

## Mailer Service

`MailerService` contains the core email-sending logic.

It:

1. Creates a MIME email message.
2. Loads the HTML email template.
3. Replaces dynamic placeholders.
4. Sets the recipient and subject.
5. Sends the email through the configured SMTP server.
6. Returns a `SentMailDto` containing the delivery status.

Email sending failures are caught and returned as an unsuccessful mail operation.

---

## Running the Service

Make sure Java 17 and Maven are installed.

Using the Maven wrapper:

### Windows

```bash
./mvnw.cmd spring-boot:run
```

### Linux/macOS

```bash
./mvnw spring-boot:run
```

The service requires its mail and Kafka configuration to be provided through the application's configuration.

---

## Testing

The project contains a Spring Boot test structure under:

```text
src/test/java/
```

The service can be tested using the REST endpoint and Kafka event flow.

For REST testing, send a `POST` request to:

```text
/api/v1/mail/send
```

with a valid `SendMailDto` request body.

---

## Role in CodeCluster

The Notification Service is responsible for **centralizing email communication** within the CodeCluster microservice architecture.

Other services can either:

* Send an email directly through the Notification Service REST API, or
* Publish an email notification event to Kafka.

This keeps email/SMTP-specific logic isolated from the other CodeCluster services.