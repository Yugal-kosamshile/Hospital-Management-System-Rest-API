# Hospital Management System API

This API provides a comprehensive set of endpoints to manage hospital details. It allows for adding, retrieving, updating, and deleting hospital records with advanced query capabilities. Built using Spring Boot, this API is robust, scalable, and easy to integrate into applications.

---

## Features

### **POST Mapping**
1. **Add New Hospital Details**
   - `POST /savedetails`: Add details of a single hospital.
2. **Add Multiple Hospital Details**
   - `POST /savebulk`: Add multiple hospital records in bulk.

### **GET Mapping**
3. **Retrieve All Hospitals**
   - `GET /getall`: Fetch details of all hospitals.
4. **Retrieve Hospital by ID**
   - `GET /getby-id/{id}`: Fetch hospital details using its ID.
5. **Retrieve Hospital by Name**
   - `GET /getby-name/{name}`: Fetch hospital details by its name.
6. **Retrieve Hospital by Location**
   - `GET /getby-location/{location}`: Fetch hospitals based on location.
7. **Retrieve Hospitals by Exact Address**
   - `GET /getby-address/{doorno}/{street}/{city}/{pincode}`: Fetch hospitals using a complete address.
8. **Retrieve Hospitals by Rating Range**
   - `GET /getby-rating/{minRating}/{maxRating}`: Fetch hospitals with ratings within a range.
9. **Retrieve Hospitals with Rating Greater Than**
   - `GET /rating/greater-than/{rating}`: Fetch hospitals with ratings above a specified value.
10. **Retrieve Hospitals with Rating Less Than**
    - `GET /rating/less-than/{rating}`: Fetch hospitals with ratings below a specified value.
11. **Find Hospitals by Location AND Name**
    - `GET /search?location=LOCATION&name=HOSPITAL-NAME`: Fetch hospitals where both location and name match.
12. **Find Hospitals by Location OR Name**
    - `GET /search-or?location=LOCATION&name=HOSPITAL-NAME`: Fetch hospitals where either location or name matches.
13. **Retrieve Hospitals by Location and Rating Range**
    - `GET /location/{location}/minRating/{minRating}/maxRating/{maxRating}`: Fetch hospitals within a location and rating range.
14. **Retrieve Hospitals Sorted by Rating**
    - `GET /sortby-rating`: Fetch hospitals in ascending order of their rating.

### **DELETE Mapping**
15. **Delete All Hospitals**
    - `DELETE /delete-all`: Remove all hospital records.
16. **Delete Hospital by ID**
    - `DELETE /deleteby-id/{id}`: Remove hospital details using its ID.
17. **Delete Hospital by Email**
    - `DELETE /deleteby-email/{email}`: Remove hospital details using its email.
18. **Delete by Rating Range**
    - `DELETE /deleteby-rating/min/{minRating}/max/{maxRating}`: Remove hospitals with ratings within a range.
19. **Delete by Rating Greater Than**
    - `DELETE /deleteby-rating-greater-than/{rating}`: Remove hospitals with ratings above a specified value.
20. **Delete by Rating and Location**
    - `DELETE /hospitals/delete-by-rating-and-location/{rating}/{location}`: Remove hospitals by rating and location.

### **PUT Mapping**
21. **Update Entire Hospital by ID**
    - `PUT /update-hospital/{id}`: Update all details of a hospital by its ID.

### **PATCH Mapping**
22. **Partially Update Hospital Name by ID**
    - `PATCH /update-name/{id}`: Update only the name of a hospital by its ID.
23. **Update Email Based on Current Email**
    - `PUT /update-email/{currentEmail}`: Update the hospital email using the current email.

### **Special API**
24. **Fetch Village Details by Pincode**
    - `GET /pincodedetails/{pincode}`: Fetch village information based on pincode.

---

## How to Use

1. Clone the repository:
   ```bash
   git clone https://github.com/Yugal-kosamshile/YourRepositoryName.git
   ```
2. Navigate to the project directory:
   ```bash
   cd YourRepositoryName
   ```
3. Run the application using Maven:
   ```bash
   mvn spring-boot:run
   ```
4. Access the API via:
   ```
   http://localhost:8084/
   ```

---

## Technologies Used
- **Framework**: Spring Boot
- **Language**: Java
- **Database**: H2 (or any supported relational database)
- **Build Tool**: Maven
- **REST API**: JSON-based

---

## Contributing
Contributions are welcome! Please fork the repository and submit a pull request.

---

## License
This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).

---

## Author
[Yugal Kosamshile](https://github.com/Yugal-kosamshile)
