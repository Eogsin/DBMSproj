import java.sql.*;
import java.util.Scanner;

public class Test {
    private Connection connection;
    public Statement statement;
    public ResultSet resultSet;
    private Scanner scanner; // Scanner 객체를 추가합니다.
    
    public Test() {
        try {
            // JDBC 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Connection con=DriverManager.getConnection("jdbc:mysql://192.168.56.101:4567/madang","ksj","1234");
             //데이터베이스 연결 정보 설정
            String url = "jdbc:mysql://192.168.56.101:4567/project";
            String username = "ksj";
            String password = "1234";

            // 데이터베이스 연결
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            scanner = new Scanner(System.in); // scanner 객체 초기화
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  
    private void findDataForTable(String tableName) throws SQLException {
        String query = "SELECT * FROM " + tableName;
        resultSet = statement.executeQuery(query);
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(metaData.getColumnName(i) + ": " + resultSet.getString(i) + " | ");
            }
            System.out.println();
        }
    }
    
    public void findData(String tableName) {
        try {
            String query;
            switch (tableName.toLowerCase()) {
                case "all":
                	  // 각각의 테이블에 대해 별도의 SELECT 문을 실행합니다.
                    findDataForTable("Customer");
                    findDataForTable("TravelPackage");
                    findDataForTable("Reservation");
                    findDataForTable("TravelItinerary");
                    findDataForTable("Payment");
                    break;
                case "customer":
                    System.out.print("Enter CustomerID to view its tuple (9999 = table): ");
                    int customerID = scanner.nextInt();
                    if(customerID == 9999) {
                    	findDataForTable("Customer");
                    }
                    else {
                    query = "SELECT * FROM Customer WHERE CustomerID = " + customerID;
                    resultSet = statement.executeQuery(query);
                    while (resultSet.next()) {
                    	System.out.println("CustomerID: " + resultSet.getInt("CustomerID") + ", Name: " + resultSet.getString("Name") + ", ContactInfo: " + resultSet.getString("ContactInfo"));
                    	}
                    }
                    break;
                case "travelpackage":
                    System.out.print("Enter PackageID to view its tuple (9999 = table): ");
                    int packageID = scanner.nextInt();
                    if(packageID  == 9999) {
                    	findDataForTable("TravelPackage");
                    }
                    else {
                    query = "SELECT * FROM TravelPackage WHERE PackageID = " + packageID;
                    resultSet = statement.executeQuery(query);
                    while (resultSet.next()) {
                    	System.out.println("PackageID: " + resultSet.getInt("PackageID") + ", Destination: " + resultSet.getString("Destination") + ", Schedule: " + resultSet.getString("Schedule") + ", Cost: " + resultSet.getString("Cost"));
                    	}
                    }
                    break;
                case "reservation":
                    System.out.print("Enter ReservationID to view its tuple (9999 = table): ");
                    int reservationID = scanner.nextInt();
                    if(reservationID == 9999) {
                    	findDataForTable("Reservation");
                    }
                    else {
                    query = "SELECT * FROM Reservation WHERE ReservationID = " + reservationID;
                    resultSet = statement.executeQuery(query);
                    while (resultSet.next()) {
                    	System.out.println("ReservationID: " + resultSet.getInt("ReservationID") + ", CustomerID: " + resultSet.getInt("CustomerID") + ", PackageID: " + resultSet.getInt("PackageID") + ", ReservationDate: " + resultSet.getString("ReservationDate"));
                    }
                    }
                    break;
                case "travelitinerary":
                    System.out.print("Enter ItineraryID to view its tuple (9999 = table): ");
                    int itineraryID = scanner.nextInt();
                    if(itineraryID == 9999) {
                    	findDataForTable("TravelItinerary");
                    }
                    else {
                    query = "SELECT * FROM TravelItinerary WHERE ItineraryID = " + itineraryID;
                    resultSet = statement.executeQuery(query);
                    while (resultSet.next()) {
                    	System.out.println("ItineraryID: " + resultSet.getInt("ItineraryID") + ", ReservationID: " + resultSet.getInt("ReservationID") + ", DepartureDate: " + resultSet.getString("DepartureDate") + ", ArrivalDate: " + resultSet.getString("ArrivalDate") + ", Activity: " + resultSet.getString("Activity"));
                    }
                    }
                    break;
                case "payment":
                    System.out.print("Enter PaymentID to view its tuple (9999 = table): ");
                    int paymentID = scanner.nextInt();
                    if(paymentID == 9999) {
                    	findDataForTable("Payment");
                    }
                    else {
                    query = "SELECT * FROM Payment WHERE PaymentID = " + paymentID;
                    resultSet = statement.executeQuery(query);
                    while (resultSet.next()) {
                    	 System.out.println("PaymentID: " + resultSet.getInt("PaymentID") + ", CustomerID: " + resultSet.getInt("CustomerID") + ", PaymentDate: " + resultSet.getString("PaymentDate") + ", Amount: " + resultSet.getString("Amount"));
                    }
                    }
                    break;
                default:
                    System.out.println("Invalid table name.");
                    return;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    
    
    public void insertData(String userInput) {
    	try {
            Scanner scanner = new Scanner(System.in);
            String query = "";
            
            switch(userInput.toLowerCase()) {
                case "customer":
                    System.out.print("Enter CustomerID: ");
                    String customerID = scanner.next();
                    System.out.print("Enter Name: ");
                    String name = scanner.next();
                    System.out.print("Enter ContactInfo: ");
                    String contactInfo = scanner.next();
                    
                    query = "INSERT INTO Customer (CustomerID, Name, ContactInfo) VALUES ('" + customerID + "', '" + name + "', '" + contactInfo + "')";
                    break;
                    
                case "travelpackage":
                    System.out.print("Enter PackageID: ");
                    String packageID = scanner.next();
                    System.out.print("Enter Destination: ");
                    String destination = scanner.next();
                    System.out.print("Enter Schedule: ");
                    String schedule = scanner.next();
                    System.out.print("Enter Cost: ");
                    String cost = scanner.next();
                    
                    query = "INSERT INTO TravelPackage (PackageID, Destination, Schedule, Cost) VALUES ('" + packageID + "', '" + destination + "', '" + schedule + "', '" + cost + "')";
                    break;
                    
                case "reservation":
                    System.out.print("Enter ReservationID: ");
                    String reservationID = scanner.next();
                    System.out.print("Enter CustomerID: ");
                    String resCustomerID = scanner.next();
                    System.out.print("Enter PackageID: ");
                    String resPackageID = scanner.next();
                    System.out.print("Enter ReservationDate: ");
                    String reservationDate = scanner.next();
                    
                    query = "INSERT INTO Reservation (ReservationID, CustomerID, PackageID, ReservationDate) VALUES ('" + reservationID + "', '" + resCustomerID + "', '" + resPackageID + "', '" + reservationDate + "')";
                    break;
                    
                case "travelitinerary":
                    System.out.print("Enter ItineraryID: ");
                    String itineraryID = scanner.next();
                    System.out.print("Enter ReservationID: ");
                    String itiReservationID = scanner.next();
                    System.out.print("Enter DepartureDate: ");
                    String departureDate = scanner.next();
                    System.out.print("Enter ArrivalDate: ");
                    String arrivalDate = scanner.next();
                    System.out.print("Enter Activity: ");
                    String activity = scanner.next();
                    
                    query = "INSERT INTO TravelItinerary (ItineraryID, ReservationID, DepartureDate, ArrivalDate, Activity) VALUES ('" + itineraryID + "', '" + itiReservationID + "', '" + departureDate + "', '" + arrivalDate + "', '" + activity + "')";
                    break;
                    
                case "payment":
                    System.out.print("Enter PaymentID: ");
                    String paymentID = scanner.next();
                    System.out.print("Enter CustomerID: ");
                    String payCustomerID = scanner.next();
                    System.out.print("Enter PaymentDate: ");
                    String paymentDate = scanner.next();
                    System.out.print("Enter Amount: ");
                    String amount = scanner.next();
                    
                    query = "INSERT INTO Payment (PaymentID, CustomerID, PaymentDate, Amount) VALUES ('" + paymentID + "', '" + payCustomerID + "', '" + paymentDate + "', '" + amount + "')";
                    break;
                    
                default:
                    System.out.println("Invalid table name.");
                    return;
            }
            
            statement.executeUpdate(query);
            System.out.println("Data inserted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateData(String tableName, String primaryKey) {
        try {
            Scanner scanner = new Scanner(System.in);
            String query = "";
            
            switch(tableName.toLowerCase()) {
                case "customer":
                    System.out.print("Enter new Name: ");
                    String newName = scanner.next();
                    System.out.print("Enter new ContactInfo: ");
                    String newContactInfo = scanner.next();
                    
                    query = "UPDATE Customer SET Name = '" + newName + "', ContactInfo = '" + newContactInfo + "' WHERE CustomerID = '" + primaryKey + "'";
                    break;
                    
                case "travelpackage":
                    System.out.print("Enter new Destination: ");
                    String newDestination = scanner.next();
                    System.out.print("Enter new Schedule: ");
                    String newSchedule = scanner.next();
                    System.out.print("Enter new Cost: ");
                    String newCost = scanner.next();
                    
                    query = "UPDATE TravelPackage SET Destination = '" + newDestination + "', Schedule = '" + newSchedule + "', Cost = '" + newCost + "' WHERE PackageID = '" + primaryKey + "'";
                    break;
                    
                case "reservation":
                    System.out.print("Enter new CustomerID: ");
                    String newCustomerID = scanner.next();
                    System.out.print("Enter new PackageID: ");
                    String newPackageID = scanner.next();
                    System.out.print("Enter new ReservationDate: ");
                    String newReservationDate = scanner.next();
                    
                    query = "UPDATE Reservation SET CustomerID = '" + newCustomerID + "', PackageID = '" + newPackageID + "', ReservationDate = '" + newReservationDate + "' WHERE ReservationID = '" + primaryKey + "'";
                    break;
                    
                case "travelitinerary":
                    System.out.print("Enter new ReservationID: ");
                    String newReservationID = scanner.next();
                    System.out.print("Enter new DepartureDate: ");
                    String newDepartureDate = scanner.next();
                    System.out.print("Enter new ArrivalDate: ");
                    String newArrivalDate = scanner.next();
                    System.out.print("Enter new Activity: ");
                    String newActivity = scanner.next();
                    
                    query = "UPDATE TravelItinerary SET ReservationID = '" + newReservationID + "', DepartureDate = '" + newDepartureDate + "', ArrivalDate = '" + newArrivalDate + "', Activity = '" + newActivity + "' WHERE ItineraryID = '" + primaryKey + "'";
                    break;
                    
                case "payment":
                    System.out.print("Enter new CustomerID: ");
                    String newPayCustomerID = scanner.next();
                    System.out.print("Enter new PaymentDate: ");
                    String newPaymentDate = scanner.next();
                    System.out.print("Enter new Amount: ");
                    String newAmount = scanner.next();
                    
                    query = "UPDATE Payment SET CustomerID = '" + newPayCustomerID + "', PaymentDate = '" + newPaymentDate + "', Amount = '" + newAmount + "' WHERE PaymentID = '" + primaryKey + "'";
                    break;
                    
                default:
                    System.out.println("Invalid table name.");
                    return;
            }
            
            statement.executeUpdate(query);
            System.out.println("Data updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void deleteData(String tableName, String idColumnName, String userInput) {
        try {
            String query = "DELETE FROM " + tableName + " WHERE " + idColumnName + " = '" + userInput + "'";
            statement.executeUpdate(query);
            System.out.println("Data deleted successfully from " + tableName + ".");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            // 연결 종료
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Test db = new Test();
        Scanner scanner = new Scanner(System.in);

        int userInput;
        do {
            System.out.println("------------------------------------------------------------");
            System.out.println("1. Find   2. Insert");
            System.out.println("3. Update 4. Delete");
            System.out.println("5.       6.       ");
            System.out.println("7.       8.       ");
            System.out.println("99. Quit");
            System.out.println("------------------------------------------------------------");
            System.out.print("Enter your choice: ");

            userInput = scanner.nextInt();

            switch (userInput) {
	            case 1:
	                System.out.print("Enter table name or 'all' (Customer/TravelPackage/Reservation/TravelItinerary/Payment): ");
	                String tableNameInput = scanner.next();
	                db.findData(tableNameInput);
	                break;
                case 2:
                    System.out.print("Enter value to insert: ");
                    String inputForInsert = scanner.next();
                    db.insertData(inputForInsert);
                    break;
                case 3:
                    System.out.print("Enter table name(Customer/TravelPackage/Reservation/TravelItinerary/Payment): ");
                    String oldValue = scanner.next();
                    System.out.print("Enter ID: ");
                    String newValue = scanner.next();
                    db.updateData(oldValue, newValue);
                    break;
                case 4:
                    System.out.print("Enter table name (Customer/TravelPackage/Reservation/TravelItinerary/Payment): ");
                    String tableName = scanner.next();
                    System.out.print("Enter ID value: ");
                    String inputForDelete = scanner.next();

                    switch (tableName) {
                        case "Customer":
                            db.deleteData("Customer", "CustomerID", inputForDelete);
                            break;
                        case "TravelPackage":
                            db.deleteData("TravelPackage", "PackageID", inputForDelete);
                            break;
                        case "Reservation":
                            db.deleteData("Reservation", "ReservationID", inputForDelete);
                            break;
                        case "TravelItinerary":
                            db.deleteData("TravelItinerary", "ItineraryID", inputForDelete);
                            break;
                        case "Payment":
                            db.deleteData("Payment", "PaymentID", inputForDelete);
                            break;
                        default:
                            System.out.println("Invalid table name.");
                            break;
                    }
                    break;
                case 99:
                    db.closeConnection();
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid option. Please choose a valid option.");
                    break;
            }
        } while (userInput != 99);

        scanner.close();
    }
    
}