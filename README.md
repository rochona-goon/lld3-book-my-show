# lld3-book-my-show
LLD implementation of a movie ticket booking system (BookMyShow) in Java Spring Boot.


🚀 **Recent Updates:** User Registration Feature
We have successfully implemented the User Registration feature! 
This enhancement introduces a complete, secure flow for registering new users into the BookMyShow system, 
adhering strictly to clean code architecture and Low-Level Design (LLD) principles.

📁 **Updated Project Structure**
The following packages were created/updated to support this feature:

**Controllers/:** Contains UserController.java to expose endpoint logic for registration requests.

**DTOs/:** Added RegisterUserRequestDto.java and RegisterUserResponseDto.java to abstract data transfer and handle request/response payloads cleanly.

**Services/:** Contains UserService.java handling the core business logic, user validations, and password security.

**Repositories/:** Added UserRepository.java to manage data persistence and lookups for User entities.

**Exceptions/:** Added domain-specific exceptions (e.g., handling duplicate users or invalid inputs).

**Config/:** Updated to handle configuration beans necessary for the service and security layer (such as BCrypt password encoders).



🔄 **End-to-End Registration Flow**
**Client Request:** The client sends user details (name, email, password) mapped via the RegisterUserRequestDto.

**Controller Layer:** UserController receives the data, initiates basic validation, and routes it to the service layer.

**Service Layer:** UserService processes the request:
* Checks if the user already exists via UserRepository.
* Throws custom exceptions from the Exceptions package if data is invalid.
* Hashes/encrypts the user password for security.

**Data Persistence:** The service saves the new User model into the database using UserRepository.

**Response Delivery:** The controller transforms the saved entity into a RegisterUserResponseDto (with a success status and user ID) and sends it back to the client.