import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.*;

public class TestCases {
    private Statement statement;
    private Connection connection;

    @BeforeClass
    public void connect() throws SQLException {
        String url = "jdbc:mysql://database-techno.c771qxmldhez.us-east-2.rds.amazonaws.com:3306/serdar2225_students";
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
        ResultSet rs = statement.executeQuery("SELECT first_name, last_name, gender, ip_address FROM students limit 10;");

        while (rs.next()) {
            String name = rs.getString(1);
            String lastName = rs.getString(2);
            String gender = rs.getString(3);
            String ip_address = rs.getString(4);
            System.out.println(name + " " + lastName + " " + gender + " " + ip_address);
        }
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE students SET fee = (fee * ?) WHERE gender = ?;");
        preparedStatement.setDouble(1, 0.95);
        preparedStatement.setString(2, "Male");
        preparedStatement.executeUpdate();

        //statement.executeUpdate("UPDATE students SET ip_address = (ip_address * 10) WHERE gender = 'Female';");

        rs = statement.executeQuery("SELECT first_name, last_name, gender, ip_address FROM students limit 10;");

        while (rs.next()) {
            String name = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String gender = rs.getString("gender");
            String ip_address = rs.getString("ip_address");
            System.out.println(name + " " + lastName + " " + gender + " " + ip_address);
        }
    }

    @Test
    public void test2() throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT first_name, gender, ip_address FROM students limit 10;");
        String name;
        String gender;
        Double ip_address;

        while (rs.next()) {
            getValuesFromCurrentRow(rs);
        }

        System.out.println("-------------------------------------------------------");
        rs.absolute(5); // gettin data from 5th row
        getValuesFromCurrentRow(rs);

        System.out.println("-------------------------------------------------------");
        rs.relative(-2); // gettin data from 3rd row
        getValuesFromCurrentRow(rs);

        System.out.println("-------------------------------------------------------");
        rs.last(); // gettin data from last row
        getValuesFromCurrentRow(rs);

        System.out.println("-------------------------------------------------------");
        rs.previous(); // gettin data from 9th row
        getValuesFromCurrentRow(rs);
    }

    private void getValuesFromCurrentRow(ResultSet rs) throws SQLException {
        String name = rs.getString(1);
        String gender = rs.getString(2);
        if (rs.wasNull()) {
            gender = "<Gender not found!>";
        }
        Double ip_address = rs.getDouble(3);
        System.out.println(name + " " + gender + " " + ip_address );
    }
}