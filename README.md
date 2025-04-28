# Bank Management System 

A Java-based console application simulating basic banking operations like creating accounts, depositing, withdrawing, and checking balances.  
This project uses **Java**, **JDBC**, and **MySQL** to manage customer data securely.

---

# Features

- Account Sign Up / Login
- Deposit Money
- Withdraw Money
- Transfer Funds
- Balance Inquiry
- Account Deletion
- Persistent Data Storage via Database (SQL)

---

# Technologies Used

- Java
- MySQL / SQL Workbench
- JDBC (Java Database Connectivity)
- IntelliJ IDEA / Eclipse (any Java IDE)

---

# Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/divyanshiverma25/Bank-Management-System.git
cd Bank-Management-System

# Setup MySQL Database
CREATE DATABASE bank_db;
USE bank_db;

CREATE TABLE customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    balance DOUBLE DEFAULT 0
);

# Configure Database Connection in Java
 String url = "jdbc:mysql://localhost:3306/bank_db";
 String username = "your_mysql_username";
 String password = "your_mysql_password";

# Screenshots
![Alt Text](https://github.com/divyanshiverma25/Bank-Management-System/blob/2c7777e02025ed0e2383d45b831a56a5408314ed/login%20.png)

![Alt Text](https://github.com/divyanshiverma25/Bank-Management-System/blob/2c7777e02025ed0e2383d45b831a56a5408314ed/Signup1.png)

![Alt Text](https://github.com/divyanshiverma25/Bank-Management-System/blob/2c7777e02025ed0e2383d45b831a56a5408314ed/Signup2.png)

![Alt Text](https://github.com/divyanshiverma25/Bank-Management-System/blob/2c7777e02025ed0e2383d45b831a56a5408314ed/Signup3.png)

![Alt Text](https://github.com/divyanshiverma25/Bank-Management-System/blob/2c7777e02025ed0e2383d45b831a56a5408314ed/ATM.png)

![Alt Text](https://github.com/divyanshiverma25/Bank-Management-System/blob/2c7777e02025ed0e2383d45b831a56a5408314ed/Deposit.png)










