import java.sql.*;

class Main {
  public static void main(String[] args) throws Exception {
    String jdbc_url = "jdbc:mysql://localhost:3306/c195";
    String user = "root";
    String password = "password";

    String query = "SELECT * FROM countries";
    Class.forName("com.mysql.cj.jdbc.Driver");

    Connection conn = DriverManager.getConnection(jdbc_url, user, password);

    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(query);

    rs.next();

    String out = rs.getString("country");
    System.out.println(out);

    stmt.close();
    conn.close();
    System.out.println("closed db connection");
  }
}
