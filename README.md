"# database-testing-automation" 
## Overview

This project is an end-to-end automation testing project built using **Java, Selenium WebDriver, TestNG, JDBC, and MySQL**.

The project demonstrates **database-driven testing** by retrieving customer data from the **ClassicModels MySQL database** and using it to automate the user registration and login workflow on the Automation Test Store website.

---

## Technologies

- Java
- Selenium WebDriver
- TestNG
- MySQL
- JDBC
- Maven
- Apache Commons IO

---

## Project Features

- Connect to MySQL database using JDBC
- Retrieve customer data dynamically from ClassicModels database
- Generate unique usernames and emails for test execution
- Automate account registration process
- Validate successful account creation
- Perform logout functionality
- Perform login validation
- Capture screenshots after test execution
- Automatically close the browser after test execution

---

## Test Scenario

The automated test flow:

```
Read Customer Data from Database
            ↓
Generate Test Data
            ↓
Create New Account
            ↓
Verify Registration Success
            ↓
Logout
            ↓
Login with Created Account
            ↓
Validate Successful Login
```

---

## Database

This project uses the **ClassicModels MySQL sample database**.

Customer information is retrieved from the database using JDBC and used as test data during automation execution.

Retrieved data includes:

- Customer Number
- First Name
- Last Name
- Address

---

## Website Under Test

Automation Test Store

https://automationteststore.com/

---

## Project Structure

```
Database-Testing-Automation
│
├── src
│   ├── AccountRegistrationTest.java
│   └── screenshots
│
├── pom.xml
│
└── README.md
```

---

## Setup Instructions

1. Clone the repository:

```
git clone <repository-url>
```

2. Install project dependencies using Maven.

3. Install MySQL and import the ClassicModels database.

4. Update your database connection details:

```
db.url=jdbc:mysql://localhost:3306/classicmodels
db.username=YOUR_USERNAME
db.password=YOUR_PASSWORD
```

5. Run the TestNG test cases.

---

## Test Cases

| Test Case | Description |
|----------|-------------|
| Read Data | Retrieves customer data from MySQL database |
| Signup | Creates a new account using database data |
| Logout | Logs out from the created account |
| Login | Verifies login using created credentials |

---

## Author

Eng. Tasneem Tanashat

