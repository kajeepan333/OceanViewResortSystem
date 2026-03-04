# Ocean View Resort - Online Room Reservation System

## 🏨 Project Overview

A complete production-level Java Web Application for managing room reservations at Ocean View Resort. This system implements modern web development practices with comprehensive features and design patterns.

## 🛠️ Technology Stack

- **IDE**: NetBeans IDE 20
- **Server**: Apache Tomcat 10.1.52
- **Database**: MySQL (XAMPP)
- **Java Version**: JDK 17
- **Architecture**: MVC (Model-View-Controller)
- **Technology**: JSP + Servlets + JDBC
- **Build Tool**: Maven
- **UI Framework**: Bootstrap 5
- **PDF Generation**: iText Library

## 🏗️ System Requirements

### Software Prerequisites
- JDK 17 or higher
- NetBeans IDE 20
- Apache Tomcat 10.1.52
- XAMPP (MySQL)
- Modern web browser

### Database Configuration
- **Database**: oceanview_resort
- **Username**: root
- **Password**: admin
- **Port**: 3306

## 📋 Features Implemented

### 🔐 Authentication & Authorization
- User login/logout with session management
- Role-based access control (Admin/Staff)
- Password validation with security requirements
- Forgot password functionality with email reset

### 🏪 Reservation Management
- Add new reservations with comprehensive guest information
- View all reservations with search and filter options
- Edit existing reservations (Admin only)
- Delete reservations (Admin only)
- Auto-generated guest IDs

### 💰 Billing System
- Automatic bill calculation with multiple strategies
- Room pricing based on type and AC preferences
- Meal plan pricing (Breakfast, Lunch, Dinner)
- VAT (18%) and Service Charge (5%) calculations
- Professional PDF bill generation

### 🎨 Modern UI/UX
- Responsive Bootstrap 5 design
- Sidebar navigation
- Admin and Staff dashboards
- Real-time form validation
- Professional color scheme and typography

## 🎯 Design Patterns Implemented

1. **MVC Pattern** - Separation of concerns
2. **DAO Pattern** - Data access abstraction
3. **Singleton Pattern** - Database connection management
4. **Factory Pattern** - Room and meal pricing
5. **Builder Pattern** - Reservation object construction
6. **Strategy Pattern** - Bill calculation strategies
7. **Front Controller Pattern** - Centralized request handling
8. **Template Method Pattern** - Base servlet functionality
9. **Role-Based Authorization Pattern** - Security implementation

## 📁 Project Structure

```
OceanViewResort/
├── pom.xml                           # Maven configuration
├── src/main/java/com/oceanview/
│   ├── model/                        # MVC Models
│   │   ├── User.java
│   │   └── Reservation.java
│   ├── dao/                          # Data Access Objects
│   │   ├── UserDAO.java
│   │   └── ReservationDAO.java
│   ├── controller/                   # MVC Controllers
│   │   ├── BaseServlet.java
│   │   ├── FrontController.java
│   │   ├── LoginServlet.java
│   │   └── [Other servlets...]
│   ├── factory/                      # Factory Pattern
│   │   └── RoomFactory.java
│   ├── builder/                      # Builder Pattern
│   │   └── ReservationBuilder.java
│   ├── strategy/                     # Strategy Pattern
│   │   ├── BillCalculationStrategy.java
│   │   ├── StandardBillCalculation.java
│   │   └── DiscountBillCalculation.java
│   ├── util/                         # Utility Classes
│   │   ├── DBConnection.java
│   │   ├── PasswordUtil.java
│   │   ├── EmailUtil.java
│   │   └── BillPDFGenerator.java
│   ├── filter/                       # Security Filters
│   │   └── AuthorizationFilter.java
│   └── listener/                     # Application Listeners
│       └── AppContextListener.java
├── src/main/webapp/
│   ├── WEB-INF/
│   │   ├── web.xml                   # Deployment descriptor
│   │   └── views/                    # JSP Pages
│   │       ├── login.jsp
│   │       ├── dashboard.jsp
│   │       └── [other JSPs...]
│   ├── admin/                        # Admin-specific pages
│   │   └── dashboard.jsp
│   ├── staff/                        # Staff-specific pages
│   │   └── dashboard.jsp
│   ├── addReservation.jsp
│   ├── viewReservations.jsp
│   ├── generateBill.jsp
│   ├── help.jsp
│   └── index.jsp
└── target/                           # Build output
```

## 🚀 Installation & Setup

### 1. Database Setup
1. Start XAMPP Control Panel
2. Start MySQL service
3. Database will be created automatically on first run

### 2. Build the Application
```bash
# Navigate to project directory
cd C:\Users\J Kenath\Desktop\Payment_Projects\OceanViewResort

# Run build script
build-project.bat

# Or use Maven commands directly
mvn clean package
```

### 3. Deploy to Tomcat
1. Start Apache Tomcat 10.1.52
2. Copy `target/OceanViewResort.war` to `tomcat/webapps/`
3. Wait for automatic deployment

### 4. Access the Application
```
http://localhost:8080/OceanViewResort/
```

## 🔑 Default Credentials

### Admin Account
- **Username**: admin
- **Password**: Admin@123
- **Role**: Administrator

### Staff Registration
- Only staff can register through the signup form
- Admin account cannot be created through UI

## 💾 Database Schema

### Users Table
```sql
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'STAFF') NOT NULL DEFAULT 'STAFF',
    email VARCHAR(100) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### Reservations Table
```sql
CREATE TABLE reservations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    guest_id VARCHAR(20) UNIQUE NOT NULL,
    guest_name VARCHAR(100) NOT NULL,
    nic_passport VARCHAR(50) NOT NULL,
    address TEXT NOT NULL,
    phone VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL,
    room_type ENUM('Standard', 'Deluxe', 'Suite') NOT NULL,
    ac_type ENUM('A/C', 'Non A/C') NOT NULL,
    adults INT NOT NULL DEFAULT 1,
    children INT NOT NULL DEFAULT 0,
    check_in DATE NOT NULL,
    check_out DATE NOT NULL,
    meal_plan ENUM('Only Breakfast', 'Breakfast + Lunch', '3 Times') NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

## 💰 Pricing Structure

### Room Prices (per night)
- **Standard**: A/C = Rs. 6,000, Non A/C = Rs. 4,000
- **Deluxe**: A/C = Rs. 10,000, Non A/C = Rs. 8,000
- **Suite**: A/C = Rs. 16,000, Non A/C = Rs. 14,000

### Meal Plans (per person per day)
- **Only Breakfast**: Rs. 500
- **Breakfast + Lunch**: Rs. 2,000
- **3 Times**: Rs. 3,500

### Taxes and Charges
- **VAT**: 18%
- **Service Charge**: 5%

## 🎯 User Roles & Permissions

### Administrator
- Full system access
- Add, view, edit, delete reservations
- Generate bills
- View system statistics
- Manage user accounts

### Staff
- Add new reservations
- View reservations (read-only)
- Generate bills
- Access help section

## 📧 Email Configuration

For password reset functionality, configure email settings in `EmailUtil.java`:
```java
private static final String SMTP_USERNAME = "your-email@gmail.com";
private static final String SMTP_PASSWORD = "your-app-password";
```

## 🔧 Configuration

### Database Connection
Update `DBConnection.java` if using different database settings:
```java
private static final String DB_USER = "root";
private static final String DB_PASSWORD = "admin";
```

### Server Configuration
- Tomcat port: 8080 (default)
- Context path: /OceanViewResort
- Session timeout: 30 minutes

## 🐛 Troubleshooting

### Common Issues

1. **Database Connection Error**
   - Ensure XAMPP MySQL is running
   - Check database credentials
   - Verify MySQL port (3306)

2. **Build Errors**
   - Ensure JDK 17 is installed
   - Check Maven dependencies
   - Verify NetBeans project configuration

3. **Deployment Issues**
   - Check Tomcat logs for errors
   - Verify WAR file integrity
   - Ensure sufficient memory allocation

### Error Logs
- Tomcat logs: `tomcat/logs/catalina.YYYY-MM-DD.log`
- Application logs: Console output during startup

## 📞 Support

For technical support:
- Email: support@oceanview.com
- Phone: +94 91 123 4567

## 📄 License

This project is developed for educational purposes as part of university assessment.

## 🎉 Acknowledgments

- Bootstrap UI Framework
- iText PDF Library
- Jakarta EE 10
- MySQL Connector/J
- Font Awesome Icons

---

**Ocean View Resort Room Reservation System**  
*Complete Production-Level Java Web Application*  
*Version 1.0.0*  
*Developed with ❤️ for educational purposes*
