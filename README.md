# **lld3-book-my-show**

LLD implementation of a movie ticket booking system (BookMyShow) in Java Spring Boot.

**🚀 Recent Updates:** Payment feature for Booking tickets

We have successfully introduced a comprehensive payment module to handle ticket booking transactions 
within the BookMyShow application. This update simulates the integration with third-party 
payment gateways, introduces multi-layered business logic for managing transaction 
states, and ensures robust handling of booking confirmations upon successful payments.

**📁 Updated Project Structure**
The following packages were created/updated to support this feature:

**Controllers/:** Added PaymentController.java to expose RESTful endpoints for initiating and verifying payment requests, efficiently utilizing @RequestParam to capture URL parameters.

**DTOs/:** Added PaymentRequestDto.java and PaymentResponseDto.java to abstract data transfer and handle the payment request/response payloads cleanly.

**Services/:** Contains PaymentService.java (and its implementation) handling the core business logic for processing payments, communicating with simulated gateways, and verifying transaction statuses.

**Models & Enums/:** Introduced the Payment entity, alongside necessary enums like PaymentStatus (SUCCESS, FAILED, PENDING), and PaymentMode to manage the transaction lifecycle.

**Repositories/:** Added PaymentRepository.java to manage data persistence and lookups for Payment entities.

**Exceptions/:** Introduced domain-specific runtime exceptions for granular error handling regarding payment timeouts, invalid booking states, or gateway failures.

**🔄 End-to-End Payment Flow**

**Client Request:** Once a user locks in their seats (creating a PENDING booking), the client initiates a payment. The target bookingId is passed directly in the URL as a query parameter.

**Controller Layer:** PaymentController intercepts the request, extracts the bookingId using @RequestParam, validates the payload, and routes the combined data to the service layer.

**Service Layer:** PaymentService processes the request:

**Routing & Processing:** Calculates the total amount and routes the request to the Payment Gateway (e.g., Razorpay).

**State Updates:** Upon a SUCCESS callback from the gateway, the service triggers an update to change the associated Booking status to CONFIRMED. If the payment fails or times out, it triggers logic to release the locked seats.

**Data Persistence:** The service saves the final Payment record into the database using PaymentRepository.