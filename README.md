# lld3-book-my-show
LLD implementation of a movie ticket booking system (BookMyShow) in Java Spring Boot.


🚀 **Recent Updates**
The latest commit introduces the foundational building blocks of the application:

* **Models**: Core entities like Booking, Show, Seat, Theatre, and User.

* **Services**: Implementation of BookingService to handle ticket reservation logic.

* **DTOs**: Decoupled request/response handling using BookingRequestDto and BookingResponseDto.

* **Enums**: Comprehensive domain constants for BookingStatus, SeatStatus, PaymentMode, and more.

🏗️ **Project Architecture**
The project is organized into layers to ensure a clean separation of concerns:


**src/main/java/com/scaler/bookMyshow/**

├── Controllers/   # Entry points for the API (e.g., BookingController)

├── Services/      # Core business logic and validations

├── Models/        # Database entities and domain objects

├── DTOs/          # Data Transfer Objects for client-server communication

├── Enums/         # Domain-specific constants and statuses

└── Repositories/  # (Next step) Data access layer


🛠️ **Tech Stack**

* Language: Java

* Framework: Spring Boot

* Database: (Likely MySQL/PostgreSQL via JPA)

* Build Tool: Maven

💡 Key Features (In Progress)
Booking Flow: Logic to initiate bookings, handle payments, and update statuses.

Seat Management: Tracking ShowSeat availability and handling various SeatStatus.

Extensible Enums: Ready-to-use support for multiple Language, Genre, and Feature types.