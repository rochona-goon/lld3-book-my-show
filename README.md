# lld3-book-my-show
LLD implementation of a movie ticket booking system (BookMyShow) in Java Spring Boot.


🚀 **Recent Updates:** User Rating Feature for movies

We have successfully introduced additional features for Rating Movies and Fetching Movie Ratings to
the BookMyShow application. This update refactors the RatingController to expose clean 
RESTful endpoints, handles multi-layered business logic with robust exception validation.

📁 **Updated Project Structure**
The following packages were created/updated to support this feature:

**Controllers/:** Contains RatingController.java to expose endpoint logic for user rating requests (rateMovies and getRating).

**DTOs/:** Added FetchRatingRequestDto.java, FetchRatingResponseDto.java, RatingRequestDto.java and RatingResponseDto.java to abstract data transfer and handle request/response payloads cleanly.

**Services/:** Contains RatingService.java and RatingServiceImpl.java handling the core business logic for rating a movie and fetching movie rating.

**Repositories/:** Updated UserRatingRepository.java to manage data persistence and lookups for Rating entities.

**Exceptions/:** Introduced domain-specific runtime exceptions for granular error handling (UserNotFoundException, MovieNotFoundException).

🔄 **End-to-End Booking Flow**
**Client Request:** The client sends rating details (userId, movieId, rating) mapped via the RatingRequestDto.

**Controller Layer:** RatingController receives the data, initiates basic validation, and routes it to the service layer.

**Service Layer:** RatingService processes the request:
* **Validations:** Automatically validates the existence of the user, movie and if the rating is within defined standards before proceeding with any state changes.

**Data Persistence:** The service saves the user ratings for a movie into the database using UserRatingRepository.
