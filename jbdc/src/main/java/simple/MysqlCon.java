package simple;
import java.sql.*;

public class MysqlCon {
	public static void main(String args[]) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cnp", "root", "mysql");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from customer");
			while (rs.next())
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  "
						+ rs.getString(4) + "  " + rs.getString(5));
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}