# **lld3-book-my-show**

LLD implementation of a movie ticket booking system (BookMyShow) in Java Spring Boot.

### 🚀 Recent Updates: 
**Notification Service Integration**

We have successfully introduced a flexible and extensible notification framework into the BookMyShow application using the **Observer Design Pattern**.
This enhancement enables the system to publish booking-related events and notify users through multiple communication channels without tightly coupling notification logic to the core business services.



### 📁 Updated Project Structure : 
The following packages were created/updated to support this feature:

### 1. Notification/

**NotificationObserver.java**

* Common observer contract implemented by all notification channels.

**EmailNotification.java**

* Sends booking-related notifications through email.

**WhatsappNotification.java**

* Sends booking-related notifications through WhatsApp.

**BookingEventPublisher.java**

* Maintains a collection of registered notification observers.
* Publishes booking events to all subscribed notification channels.


### 2.  Enums/

Added:

**BookingEventType**

Represents different booking lifecycle events that can trigger notifications.

Supported events:

* BOOKING_CONFIRMED
* BOOKING_CANCELLED
* PAYMENT_FAILED

### 3. Services/

**BookingService :**
Modified to publish events when:

* A booking is cancelled.

**PaymentService :**
Modified to publish events when:

* A payment succeeds and the booking is confirmed.

* A payment fails.


### 🔄 End-to-End Notification Flow

**--- Booking Confirmation ---**

When a payment is successfully processed:

1. Payment status is updated to SUCCESS.
2. Booking status is updated to CONFIRMED.
3. Seats are marked as BOOKED.
4. PaymentService publishes a BOOKING_CONFIRMED event.
5. BookingEventPublisher notifies all registered observers.
6. Email and WhatsApp notifications are sent to the customer.

**--- Payment Failure ---**

When payment processing fails:

1. Payment status is updated to FAILURE.
2. Locked seats are released.
3. Booking status is updated to CANCELLED.
4. PaymentService publishes a PAYMENT_FAILED event.
5. BookingEventPublisher notifies all registered observers.
6. Email and WhatsApp notifications are sent to the customer.

**--- Booking Cancellation ---**

When a user cancels a booking:

1. Booking status is updated to CANCELLED.
2. Seats are released and marked AVAILABLE.
3. BookingService publishes a BOOKING_CANCELLED event.
4. BookingEventPublisher notifies all registered observers.
5. Email and WhatsApp notifications are sent to the customer.

---

### 🏗️ Design Pattern Used

**Observer Pattern**

The notification system follows the Observer Pattern.

```text
BookingService / PaymentService
            ↓
     BookingEventPublisher
            ↓
     NotificationObserver
          /      \
         /        \
EmailNotification  WhatsappNotification
```

**Benefits**

* Loose coupling between business logic and notification channels.
* New notification providers can be added without modifying BookingService or PaymentService.
* Improved maintainability and extensibility.

