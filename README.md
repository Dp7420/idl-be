# 📦 DigiLocker - Secure Document Storage for Organizations

A secure digital locker system built using Java Spring Boot. This system is designed to store and manage various sensitive company-related documents such as bank details, income tax, EPF, ESI, GST, KMP, professional tax, TDS, director, and MCA-related documents. It follows enterprise-grade security using JWT authentication and role-based access control.

---

## 🚀 Features

- 🔐 JWT-based Authentication & Role-based Authorization
- 🏦 Store Company Bank Details Securely
- 💰 Income Tax, TDS & GST Document Management
- 🏢 EPF, ESI, Professional Tax Handling
- 👤 Director & KMP Details Tracking
- 🧾 MCA Integration Ready (Company Master Data)
- 📂 Secure Document Upload & Access
- 🛡️ Encrypted storage practices (configurable)
- 📊 Admin Dashboard (Frontend integration-ready)

---

## 🛠️ Tech Stack

- **Backend:** Java 17, Spring Boot, Spring Security, Spring Data JPA
- **Database:** MySQL
- **Build Tool:** Maven
- **Other:** Lombok, Swagger (OpenAPI)

---

## 🧑‍💻 Getting Started

### Prerequisites

- Java 17+
- Maven
- MySQL
- Postman (for testing APIs)

### Clone the repository

```bash
git clone https://github.com/Dp7420/idl-be.git
cd idl-be
```

### Configure Database (application.properties or application.yml)

```properties
# MySQL example
spring.datasource.url=jdbc:mysql://localhost:3306/digilocker_db
spring.datasource.username=root
spring.datasource.password=yourpassword

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Build & Run

```bash
./mvnw clean install
./mvnw spring-boot:run
```

---

## 📮 API Documentation

- Swagger UI: `http://localhost:8080/swagger-ui/index.html`

---

## 🧪 Running Tests

```bash
./mvnw test
```

---

<!-- ## 🐳 Docker Support (Optional) -->

<!-- ```bash
docker build -t digilocker-app .
docker run -p 8080:8080 digilocker-app
``` -->

<!-- --- -->

## 📂 Project Structure

```
src/
├── main/
│   ├── java/com/digilocker/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── repository/
│   │   ├── entity/
│   │   ├── dto/
│   │   └── config/
│   └── resources/
│       ├── application.properties
│       └── static/
```

---

## 🙋‍♂️ Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

---

<!-- ## 📃 License -->

<!-- This project is licensed under the MIT License. -->

<!-- --- -->

## ✉️ Contact

- **Developer:** Digambar Bulbule  
- **Email:** digambarbulbule123@gmail.com  
- **LinkedIn:** [https://www.linkedin.com/in/digambar-bulbule-617515251/](https://www.linkedin.com/in/digambar-bulbule-617515251/)
