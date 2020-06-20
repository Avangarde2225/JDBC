import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.*;

public class Rep {
    private Statement statement;
    private Connection connection;

    @BeforeClass
    public void connect() throws SQLException {
        String url = "jdbc:mysql://database-techno.c771qxmldhez.us-east-2.rds.amazonaws.com:3306/serdar2225_rep";
        String user = "serdar2225";
        String password = "serdar2225@hotmail.com";
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement();

    }

    @AfterClass
    public void disconnect() throws SQLException {
        connection.close();
    }

    @Test(dataProvider = "reps")
    public void test(String c1,String c2,String c3,String c4) {
        System.out.println(c1 + c2 + c3 + c4 );
    }
    @DataProvider(name="reps")
    public Object[][] studentsData() throws SQLException {
        ResultSet rs = statement.executeQuery("select * from rep");
        rs.last();
        int numberOfRow = rs.getRow();
        Object[][] resultData = new Object[numberOfRow][4];
        rs.beforeFirst();

        int i = 0;
        while (rs.next()) {
            String name = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String country = rs.getString("country");
            String phone = rs.getString("phone");

            System.out.println(name + " " + lastName + " " + country + " " + phone);

            resultData[i][0]= name;
            resultData[i][1]= lastName;
            resultData[i][2]= country;
            resultData[i][3]= phone;
            i++;
        }


        return resultData;
    }
}
