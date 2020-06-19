import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestCases {
    private Statement statement;
    private Connection connection;
    @Test
    public void test() throws SQLException {
    String url = "jdbc:mysql://database-techno.c771qxmldhez.us-east-2.rds.amazonaws.com:3306/daulet2030_studens_database";
    String user = "serdar2225";
    String password = "serdar2225@hotmail.com";
    connection = DriverManager.getConnection(url, user, password);
    statement = connection.createStatement();
}
}
