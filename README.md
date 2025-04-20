# Student Data Entry with MySQL and JDBC

## Author
- Name: Akhil Chivukula  
- PRN: 23070126009  
- Batch: AIML A1  

## Overview
Java application that performs full CRUD operations on student data stored in MySQL, using JDBC. Each operation is modular, with custom exceptions to validate inputs.

## Functionalities
- Add Student (`addStudent()`)
- Display All Students (`displayAll()`)
- Search by PRN (`searchByPRN()`)
- Search by Name (`searchByName()`)
- Update Student (`updateStudent()`)
- Delete Student (`deleteStudent()`)

## Custom Exceptions
- `InvalidPercentageException`: For out-of-range marks
- `DuplicatePRNException`: If a student PRN already exists

## How to Run
1. Import the `schema.sql` into MySQL.
2. Update `DBConnection.java` with your credentials.
3. Compile and run `Main.java`.

## GitHub Repository
[Add your GitHub link here]
