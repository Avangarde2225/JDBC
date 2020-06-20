import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.*;

public class TestCasesConted {
    private Statement statement;
    private Connection connection;

    @BeforeClass
    public void connect() throws SQLException {
        String url = "jdbc:mysql://database-techno.c771qxmldhez.us-east-2.rds.amazonaws.com:3306/serdar2225_students_new";
        String user = "serdar2225";
        String password = "serdar2225@hotmail.com";
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement();

    }

    @AfterClass
    public void disconnect() throws SQLException {
        connection.close();
    }

    @Test
    public void test() throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT first_name, city, country, postal_code FROM students_new;");

        while (rs.next()) {
            String name = rs.getString("first_name");
            String country = rs.getString("country");
            String city = rs.getString("city");
            String postal_code = rs.getString("postal_code");
            System.out.println(name + " " + country + " " + city + " " + postal_code);
        }

    }

    @Test
    public void highestFeesTest() throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT first_name, email, fee, currency FROM students_new ORDER BY fee DESC limit 20;");

        while (rs.next()) {
            String name = rs.getString("first_name");
            String email = rs.getString("email");
            String fee = rs.getString("fee");
            String currency = rs.getString("currency");
            System.out.println(name + " " + email + " " + fee + " " + currency);
        }
    }
    @Test
    public void studentsInDifferentRows() throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT first_name, email, fee, currency FROM students_new ORDER BY fee DESC limit 20;");
        rs.absolute(5);
        System.out.println("Students at number 5 row: " + rs.getString("first_name")+" " + rs.getString("email") +" "+ rs.getString("fee")
        + rs.getString("currency"));

        rs.relative(3);
        System.out.println("Students at number 5 row: " + rs.getString("first_name")+" " + rs.getString("email") +" "+ rs.getString("fee")
                + rs.getString("currency"));
        rs.first();
        System.out.println("Students at number 5 row: " + rs.getString("first_name")+" " + rs.getString("email") +" "+ rs.getString("fee")
                + rs.getString("currency"));
        rs.last();
        System.out.println("Students at number 5 row: " + rs.getString("first_name")+" " + rs.getString("email") +" "+ rs.getString("fee")
                + rs.getString("currency"));

    }

    @Test
    public void averageFeeIncreaseTest() throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT first_name, country, avg(fee), currency FROM students_new GROUP BY currency, country limit 10;");
        while (rs.next()) {
            String name = rs.getString("first_name");
            String country = rs.getString("country");
            Double fee = rs.getDouble("avg(fee)");
            String currency = rs.getString("currency");
            System.out.println(name + " " + country + " " + (fee * 1.17) + " " + currency);
        }
    }



    }
