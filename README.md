# Inventory Management System 📦

A complete Core Java-based Inventory Management System that demonstrates object-oriented programming principles, file handling, multithreading, and exception handling.



## 🚀 Features

- **📊 Product Management** - Add, view, search, update, and delete products
- **🔔 Real-time Alerts** - Background thread monitoring with low stock notifications
- **👥 Supplier Management** - Complete supplier information tracking
- **💾 CSV Data Storage** - Persistent data storage using CSV files
- **📈 Reports & Analytics** - Generate inventory reports and statistics
- **🛡️ Robust Error Handling** - Comprehensive exception handling with custom exceptions
- **🎯 Input Validation** - Data integrity checks and validation

## 🛠️ Technologies Used

- **Core Java 17+** - Pure Java implementation, no external dependencies
- **File I/O** - CSV file operations using BufferedReader/Writer
- **Multithreading** - Background alert system with daemon threads
- **Collections Framework** - ArrayList, HashMap, Stream API
- **OOP Principles** - Encapsulation, Inheritance, Polymorphism, Abstraction

## 📁 Project Structure

```
InventoryManagementSystem/
├── 📂 src/
│   ├── 📂 model/
│   │   ├── Product.java
│   │   └── Supplier.java
│   ├── 📂 service/
│   │   ├── ProductService.java
│   │   ├── SupplierService.java
│   │   └── InventoryAlertThread.java
│   ├── 📂 util/
│   │   └── FileUtil.java
│   ├── 📂 exception/
│   │   ├── InventoryException.java
│   │   ├── FileFormatException.java
│   │   ├── InvalidProductException.java
│   │   └── ProductNotFoundException.java
│   └── 📂 main/
│       └── InventoryManagementSystem.java
├── 📂 data/
│   ├── products.csv
│   └── suppliers.csv
├── 📂 bin/ (auto-generated)
├── 🛠️ compile.bat
├── 🚀 run.bat
└── 📖 README.md
```

## 🏃‍♂️ Quick Start

### Prerequisites
- **Java JDK 17 or higher**
- Windows/Linux/macOS terminal

### 🎯 Quick Commands to Remember

#### **Compile the System:**
```powershell
javac -d bin src\main\InventoryManagementSystem.java src\service\*.java src\model\*.java src\exception\*.java src\util\*.java
```

#### **Run the System:**
```powershell
java -cp bin main.InventoryManagementSystem
```

#### **One-Liner Compile & Run:**
```powershell
javac -d bin src\main\*.java src\service\*.java src\model\*.java src\exception\*.java src\util\*.java && java -cp bin main.InventoryManagementSystem
```

### Using Batch Files (Windows):

#### **Compile:**
```powershell
.\compile.bat
```

#### **Run:**
```powershell
.\run.bat
```

### Manual Step-by-Step Compilation:
```powershell
# Clean previous build
Remove-Item -Path "bin\*" -Recurse -Force 2>$null

# Compile in dependency order
javac -d bin src/exception/*.java
javac -d bin src/model/*.java
javac -d bin -cp bin src/util/*.java
javac -d bin -cp bin src/service/*.java
javac -d bin -cp bin src/main/InventoryManagementSystem.java

# Run application
java -cp bin main.InventoryManagementSystem
```

## 📋 Usage Guide

### Main Menu Options:
| Option | Command | Description |
|--------|---------|-------------|
| 1️⃣ | **Add Product** | Add new products to inventory |
| 2️⃣ | **View All Products** | Display complete product list |
| 3️⃣ | **Search Product** | Find by ID, name, or category |
| 4️⃣ | **Update Product** | Modify existing product details |
| 5️⃣ | **Delete Product** | Remove products from system |
| 6️⃣ | **View Low Stock** | Products with quantity < threshold |
| 7️⃣ | **Generate Reports** | Inventory analytics & statistics |
| 8️⃣ | **Supplier Management** | View and manage suppliers |
| 9️⃣ | **Exit System** | Safe shutdown |

### Adding a Product Example:
```
Enter Product ID: P007
Enter Product Name: Gaming Mouse
Enter Category: Electronics
Enter Quantity: 25
Enter Price: 49.99
Enter Supplier ID: S001
```

## 📊 Sample Data

The system includes pre-loaded sample data:

### Products:
- **6 Products**: Laptop, Office Chair, Notebook, Wireless Mouse, Desk Lamp, Stapler
- **Multiple Categories**: Electronics, Furniture, Stationery
- **Realistic Pricing**: Market-appropriate prices

### Suppliers:
- **3 Suppliers**: TechCorp Inc., OfficeFurnish Ltd., StationeryWorld
- **Complete Contact Info**: Names, emails, phone numbers

## 💾 Data Storage

### Products CSV Format:
```csv
P001,Laptop,Electronics,15,899.99,S001
P002,Office Chair,Furniture,8,199.99,S002
P003,Notebook,Stationery,50,4.99,S003
```

### Suppliers CSV Format:
```csv
S001,TechCorp Inc.,John Doe,john@techcorp.com,555-0101
S002,OfficeFurnish Ltd.,Jane Smith,jane@officefurnish.com,555-0102
S003,StationeryWorld,Bob Johnson,bob@stationeryworld.com,555-0103
```

## 🎯 Core Java Concepts Demonstrated

### ✅ Object-Oriented Programming
- **Classes & Objects** - Proper entity modeling
- **Encapsulation** - Private fields with public getters/setters
- **Inheritance** - Custom exception hierarchy
- **Polymorphism** - Method overriding in toString()

### ✅ Exception Handling
- **Custom Exception Classes** - Domain-specific error types
- **Checked vs Unchecked** - Proper exception strategy
- **Try-Catch-Finally** - Resource management
- **Meaningful Messages** - User-friendly error information

### ✅ File I/O Operations
- **BufferedReader/Writer** - Efficient file handling
- **CSV Parsing** - String.split() for data extraction
- **File Validation** - Existence checks and auto-creation
- **Data Persistence** - Automatic save on operations

### ✅ Collections Framework
- **ArrayList** - Dynamic product/supplier storage
- **HashMap** - Category-wise grouping and counting
- **Stream API** - Functional operations on collections
- **Lambda Expressions** - Clean, concise code

### ✅ Multithreading
- **Thread Extension** - Custom InventoryAlertThread
- **Background Monitoring** - Non-blocking user experience
- **Synchronization** - Thread-safe console output
- **Daemon Threads** - Automatic JVM shutdown

### ✅ Data Validation
- **Input Validation** - All user inputs are validated
- **Business Rules** - No negative quantities/prices
- **Duplicate Prevention** - Unique ID enforcement
- **Data Integrity** - Consistent state maintenance

## 🔧 Technical Implementation

### Multithreading Architecture:
```java
public class InventoryAlertThread extends Thread {
    // Monitors inventory every 30 seconds
    // Uses synchronized blocks for thread safety
    // Implements graceful thread termination
    // Runs as daemon for automatic cleanup
}
```

### Exception Hierarchy:
```
InventoryException (Base Checked Exception)
├── InvalidProductException (Validation errors)
├── FileFormatException (CSV parsing errors)
└── ProductNotFoundException (Search failures)
```

### Service Layer Pattern:
- **ProductService** - Core product operations and business logic
- **SupplierService** - Supplier management operations
- **FileUtil** - Reusable file operation utilities

## 🚀 Advanced Features

### Real-time Alert System
- ✅ Background monitoring every 30 seconds
- ✅ Automatic low stock notifications
- ✅ Non-intrusive user experience
- ✅ Configurable threshold levels

### Comprehensive Search Capabilities
- ✅ **Exact Match** - Product ID search
- ✅ **Partial Match** - Product name search (case-insensitive)
- ✅ **Category Filter** - Category-based filtering
- ✅ **Multiple Results** - Support for bulk operations

### Robust Data Persistence
- ✅ **Auto-save** - Every operation persists immediately
- ✅ **CSV Format** - Human-readable and editable
- ✅ **Error Recovery** - Graceful handling of file issues
- ✅ **Data Backup** - Manual backup via file copying

## 📊 Sample Reports Output

### Inventory Value Report:
```
?? REPORTS & STATISTICS
Total Inventory Value: $15,274.75

Category-wise Product Count:
   Electronics: 2 product(s)
   Furniture: 2 product(s)
   Stationery: 2 product(s)

Total Products: 6
```

### Low Stock Alert:
```
?? LOW STOCK ALERT! The following products are running low:
   Office Chair (ID: P002) - Only 8 left!
   Stapler (ID: P006) - Only 5 left!
Please restock these items soon!
```

## 🐛 Troubleshooting Guide

### Common Issues & Solutions:

| Issue | Cause | Solution |
|-------|-------|----------|
| **"Product not found"** | Invalid product ID | Use Option 2 to view all products |
| **"Invalid number format"** | Non-numeric input | Enter valid numbers for quantity/price |
| **"Product ID exists"** | Duplicate ID | Use unique product identifiers |
| **Compilation errors** | Missing JDK | Ensure Java 17+ is installed and in PATH |
| **File permission errors** | Write restrictions | Run as admin or check folder permissions |

### File System Checks:
```powershell
# Verify Java installation
java -version
javac -version

# Check project structure
dir src/
dir data/

# Verify compilation output
dir bin/
```

## 📈 Future Enhancements

### Planned Features:
- 🗃️ **Database Integration** - MySQL/PostgreSQL support
- 🌐 **Web Interface** - Spring Boot REST API
- 🔐 **User Authentication** - Role-based access control
- 📊 **Advanced Analytics** - Charts and forecasting
- 📱 **Mobile App** - React Native companion app
- 🏷️ **Barcode Support** - Product scanning capabilities
- 📦 **Purchase Orders** - Complete supply chain management

### Technical Improvements:
- **Logging Framework** - SLF4J with Logback
- **Configuration Management** - External config files
- **Unit Testing** - JUnit test coverage
- **Build Automation** - Maven/Gradle support
- **Docker Support** - Containerized deployment

## 👥 Contributing

This project is designed as a comprehensive learning exercise for Core Java concepts. Contributions are welcome!

### Areas for Extension:
- Add product categories management
- Implement inventory forecasting algorithms
- Create bulk import/export functionality
- Add advanced search filters
- Implement data backup/restore features

### Development Setup:
1. Fork the repository
2. Create a feature branch
3. Implement your changes
4. Add tests if possible
5. Submit a pull request

## 📄 License

This project is open source and available under the **MIT License**.

## 🎓 Learning Outcomes

After exploring this project, you'll gain practical experience with:

### Core Java Mastery:
- Building complete Java applications from scratch
- Advanced file handling and serialization
- Multithreading and concurrency control
- Comprehensive exception handling strategies

### Software Engineering:
- Object-oriented design principles
- Service layer architecture
- Data persistence strategies
- User interface design (CLI)

### Professional Development:
- Code organization and structure
- Documentation practices
- Debugging and troubleshooting
- Project maintenance

---

**Built with 💻 using Pure Core Java | Zero External Dependencies**

---

### 🚀 Ready to Get Started?

1. **Clone or download** the project
2. **Navigate** to the InventoryManagementSystem directory
3. **Compile** using the commands above
4. **Run** and explore the features
5. **Extend** with your own improvements!

The system is production-ready and perfect for learning enterprise Java development patterns! 🎯
