import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.*;

public class DataProviderTest {
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

    @Test(dataProvider = "students")
    public void test(String c1,String c2,String c3,String c4,String c5, Double c6) {
        System.out.println(c1 + c2 + c3 + c4 + c5 + c6);
    }
    @DataProvider(name="students")
    public Object[][] studentsData() throws SQLException {
        ResultSet rs = statement.executeQuery("select * from students_new");
        rs.last();
        int numberOfRow = rs.getRow();
        Object[][] resultData = new Object[numberOfRow][6];
        rs.beforeFirst();

        int i = 0;
        while (rs.next()) {
            String name = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String email = rs.getString("email");
            String gender = rs.getString("gender");
            String country = rs.getString("country");
            Double fee = rs.getDouble("fee");
            System.out.println(name + " " + lastName + " " + email + " "+ gender + " " + country + " " + fee);

            resultData[i][0]= name;
            resultData[i][1]= lastName;
            resultData[i][2]= email;
            resultData[i][3]= gender;
            resultData[i][4]= country;
            resultData[i][5]= fee;
            i++;
        }


        return resultData;
    }
}
