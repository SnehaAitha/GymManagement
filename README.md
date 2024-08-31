# Job Interview

# Important

- This test is a way to evaluate experience and logical thought.
- Feel free to use of ChatGPT, Gemini, xAI, StackOverflow, google or any resource you desire.
- Third party libs as Lombok and MapStruct are not only allowed but advised.

## Description

Create an Spring Boot API for Personal Trainer Session Management

### Resources

1. [https://freetestapi.com/apis](https://freetestapi.com/apis)

### Requirements

1. Fetch 25 actors and 25 actresses parse them to Customers

curl --location --request POST 'http://localhost:8080/customer/saveCustomers' \
--data ''

2. Fetch 30 users parse them to Trainers

curl --location --request POST 'http://localhost:8080/trainer/saveTrainers' \
--data ''

3. Fetch 50 US-States and Create one gym per state

curl --location --request POST 'http://localhost:8080/gym/createGymForEachState' \
--data ''

4. CRUD Gym Sessions 
    1. Book Session 
        1. Trainer and Customer must be free to be booked
        2. All Dates should be UTC

curl --location 'http://localhost:8080/gymsession/bookSession' \
--header 'Content-Type: application/json' \
--data '{
    "startTimeString": "09-30-2024 10:30:00",
    "endTimeString": "09-30-2024 14:30:00",
    "gym": {
        "id": 9
    },
    "trainer": {
        "id": 7
    },
    "customer": {
        "id": 10
    }
}'

    
    2. Search session by:
        1. Trainer
        2. Customer
        3. Time Interval 

curl --location 'http://localhost:8080/gymsession/searchSession' \
--header 'Content-Type: application/json' \
--data '{
    "startTime": "2025-01-01T16:30:00",
    "endTime": "2025-01-01T16:30:00",
    "trainer": {
        "id": 0
    },
    "customer": {
        "id": 0
    }
}'

    3. Cancel/Reschedule Booking
        1. Free of charge: +24h before booking start date
        2. Charge Customer: -24h before booking start date

Cancel:

curl --location --request DELETE 'http://localhost:8080/gymsession/cancelSession/102' \
--data ''

Reschedule:

curl --location --request PUT 'http://localhost:8080/gymsession/rescheduleSession/102' \
--header 'Content-Type: application/json' \
--data '{
    "startTimeString": "09-30-2024 22:30:00",
    "endTimeString": "09-30-2024 23:30:00"
}'
