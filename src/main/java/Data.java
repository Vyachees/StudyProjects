import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Data {


    public static void getClients() throws SQLException {

        String SQL_QUERY = "select * from client";

        try(
                Connection con = DbPool.getConnection();
                PreparedStatement pst = con.prepareStatement(SQL_QUERY);
                ResultSet rs = pst.executeQuery()

        ){
            while (rs.next()){

                List<String>stringList = new ArrayList<>();
                stringList.add(rs.getString("id"));
                stringList.add(rs.getString("last_name"));
                stringList.add(rs.getString("first_name"));
                stringList.add(rs.getString("middle_name"));
                stringList.add(rs.getString("balance"));
                System.out.println(stringList);
            }
        }

    }

    public static void addClients(long id,String lastName,String firstName,String middleName,Float balance){
        String SQL_INSERT_QUERY = String.format("insert into client values('%s','%s','%s','%s','%s')",id,lastName,firstName,middleName,balance);
        try(
                Connection con = DbPool.getConnection();
                PreparedStatement pst = con.prepareStatement(SQL_INSERT_QUERY)
        ){
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void modClientsBalance(long id, Float balance){
        String SQL_UPDATE_QUERY = String.format("update client set balance = '%s' where id='%s'",balance,id);
        try(
                Connection con = DbPool.getConnection();
                PreparedStatement pst = con.prepareStatement(SQL_UPDATE_QUERY)
        ){
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
