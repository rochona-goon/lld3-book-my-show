# lld3-book-my-show
LLD implementation of a movie ticket booking system (BookMyShow) in Java Spring Boot.


🚀 **Recent Updates:** Ticket Booking & Cancellation Features

We have successfully introduced core features for Ticket Booking and Ticket Cancellation to
the BookMyShow application. This update refactors the BookingController to expose clean 
RESTful endpoints, handles multi-layered business logic with robust exception validation, 
and ensures database concurrency safety.

📁 **Updated Project Structure**
The following packages were created/updated to support this feature:

**Controllers/:** Contains BookingController.java to expose endpoint logic for booking requests (bookTicket and cancelTicket).

**DTOs/:** Added BookingRequestDto.java, BookingResponseDto.java, CancelBookingRequestDto.java and CancelBookingResponseDto.java to abstract data transfer and handle request/response payloads cleanly.

**Services/:** Contains BookingService.java and BookingServiceImpl.java handling the core business logic for creating a booking and cancelling a booking.

**Repositories/:** Added BookingRepository.java, ShowRepository.java,ShowSeatRepository.java and ShowSeatTypeRepository.java to manage data persistence and lookups for Booking entities.

**Exceptions/:** Introduced domain-specific runtime exceptions for granular error handling (UserNotFoundException, ShowNotFoundException, SeatNotAvailableException, BookingNotFoundException).

🔄 **End-to-End Booking Flow**
**Client Request:** The client sends booking details (userId, showId, seats, bookingId) mapped via the BookingRequestDto and CancelBookingRequestDto.

**Controller Layer:** BookingController receives the data, initiates basic validation, and routes it to the service layer.

**Service Layer:** BookingService processes the request:
* **Validations:** Automatically validates the existence of the user, show, and seat IDs before proceeding with any state changes.
* **Dynamic Price Calculation:** Fetches configured seat type pricing via ShowSeatTypeRepository and computes the exact total dynamically during booking creation.
* **Initial Booking State:** New bookings are securely saved with an initial UNPAID status, ready for a payment gateway integration step.
* **Initial Cancellation State:** Cancelled bookings are securely saved with an initial CANCELLED status, ready for a payment gateway integration step.

**Data Persistence:** The service saves the booked seats per booking into the database using BookingRepository, ShowRepository, ShowSeatRepository.
To prevent double-booking issues in high-traffic scenarios (e.g., multiple users trying to grab the same seat simultaneously),
we introduced Pessimistic Locking:

* The ShowSeatRepository now utilizes @Lock(LockModeType.PESSIMISTIC_WRITE) when querying selected seats via findAllByIdWithLock.
* This ensures that the database rows for the requested seats are securely locked
for updates until the active transaction completes, effectively eliminating race conditions.
