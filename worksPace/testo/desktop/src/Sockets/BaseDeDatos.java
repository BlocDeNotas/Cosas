package Sockets;
import java.awt.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

public class BaseDeDatos {
	private Connection con;
	
	public BaseDeDatos() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/test","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<ArrayList<String>> consulta(String sql) throws SQLException {
		Statement sentencia = con.createStatement();
		ResultSet rs = sentencia.executeQuery(sql);
		String s = "";
		ArrayList<ArrayList<String>> datos  = new ArrayList<ArrayList<String>>();
		while(rs.next()) {
			ArrayList<String> data = new ArrayList<String>();
	        for(int i = 1;i<=2;i++){
	        		data.add(rs.getString(i));
	        }
	        datos.add(data);
		}
		System.out.println("Resultados de la sql ("+sql+")");
		System.out.println("/////////////////////////////");
		for (Iterator<ArrayList<String>> iterator = datos.iterator(); iterator.hasNext();) {
			ArrayList<String> arrayList = (ArrayList<String>) iterator.next();
			for (Iterator<String> iterator2 = arrayList.iterator(); iterator2.hasNext();) {
				String string = (String) iterator2.next();
				System.out.println(string);
			}
		}
		System.out.println("/////////////////////////////");
		return datos;
	}

	public int modificacion(String sql) throws SQLException {
		Statement sentencia = con.createStatement();
		int rs = sentencia.executeUpdate(sql);
		return rs;
	}
}
