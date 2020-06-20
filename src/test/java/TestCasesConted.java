import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.*;

public class TestCasesConted {
    private Statement statement;
    private Connection connection;

    @BeforeClass
    public void connect() throws SQLException {
        String url = "jdbc:mysql://database-techno.c771qxmldhez.us-east-2.rds.amazonaws.com:3306/serdar2225_students_newtest";
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

}
