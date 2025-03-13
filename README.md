# Mega City Cab Service

## Project Overview
Mega City Cab Service is a dynamic web application designed to manage cab services efficiently. The project is developed using Eclipse IDE and deployed on Apache Tomcat 9. The database is managed using MySQL Workbench. The project utilizes MySQL Connector for database connectivity and JUnit for testing.

## Prerequisites
Before you begin, ensure you have the following installed:
- **Eclipse IDE** (or any preferred Java IDE)
- **Apache Tomcat 9**
- **MySQL Workbench**
- **MySQL Connector/J** (JDBC Driver for MySQL)
- **JUnit** (for testing)

## Setup Instructions

### 1. Clone the Repository
Clone the project repository to your local machine:
```bash
git clone https://github.com/Visitha2001/MegaCityCabs.git
```

### 2. Import Project into Eclipse
1. Open Eclipse IDE.
2. Go to `File` > `Import` > `General` > `Existing Projects into Workspace`.
3. Select the root directory where you cloned the repository.
4. Click `Finish`.

### 3. Database Setup
1. Open MySQL Workbench.
2. Create a new database named `signup`.
3. Import the SQL schema file located in the `DataBase/MegaCityCab_DB.sql` folder of the project to set up the necessary tables.

### 4. Configure Database Connection
1. Open the `src/main/java/com/megacity/utils` folder in Eclipse.
2. Locate the database configuration file (`DBConnection.java`).
3. Update the database connection URL, username, and password to match your MySQL setup:
   ```java
   String url = "jdbc:mysql://localhost:3306/signup";
   String user = "your_username";
   String password = "your_password";
   ```

### 5. Add Dependencies
Ensure the following libraries are added to your project's build path:
- **MySQL Connector/J**: Download the JAR file from [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/) and add it to your project.
- **JUnit**: Add JUnit to your project via Eclipse:
  1. Right-click on the project > `Build Path` > `Add Libraries` > `JUnit` > Choose `JUnit 4` or `JUnit 5`.

### 6. Deploy on Tomcat
1. Right-click on the project in Eclipse > `Run As` > `Run on Server`.
2. Select `Apache Tomcat 9` and click `Finish`.

### 7. Access the Application
Once deployed, open your browser and navigate to:
```
http://localhost:8080/MegaCityCabService/
```

## Project Structure
```
MegaCityCabs/
├── src/main/java/                # Java source files
│   ├── com/megacity/dao/        # Data Access Objects (DAO)
│   ├── com/megacity/models/     # Data models and entities
│   ├── com/megacity/serverlets/ # Servlets and controllers
│   ├── com/megacity/services/   # Business logic
│   └── com/megacity/utils/      # Utility classes (e.g., DBUtil)
├── src/test/java/               # JUnit test cases
├── webapp/                      # Web resources
│   ├── css/                     # CSS files
│   ├── fonts/                   # Font files
│   ├── images/                  # Image files
│   ├── META-INF/                # Metadata files
│   ├── role/                    # Role-specific resources
│   │   ├── admin/               # Admin resources
│   │   ├── client/              # Client resources
│   │   └── rider/               # Rider resources
│   └── WEB-INF/                 # Configuration files (web.xml)
│       ├── login.jsp            # Login page
│       ├── logout.jsp           # Logout page
│       └── registration.jsp     # Registration page
└── DataBase/                    # Database scripts
    └── MegaCityCab_DB.sql       # SQL schema file(this is only available in main branch)
```

## Running Tests
To run JUnit tests:
1. Navigate to the `src/test/java` folder.
2. Right-click on a test file > `Run As` > `JUnit Test`.

## Contributing
Contributions are welcome! Please follow these steps:
1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a pull request.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact
For any questions or feedback, please contact:
- **Visitha** -- visitha2001@gmail.com
- **GitHub:** [Visitha2001](https://github.com/Visitha2001)  

---

Thank you for using Mega City Cab Service! 🚕
