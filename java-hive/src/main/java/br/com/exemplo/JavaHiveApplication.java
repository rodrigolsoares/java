package br.com.exemplo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaHiveApplication implements CommandLineRunner {

	private static String driverName = "org.apache.hive.jdbc.HiveDriver";

	public static void main(String[] args) {
		SpringApplication.run(JavaHiveApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		
		Connection con = DriverManager.getConnection("jdbc:hive2://localhost:10000/default", "hive", "");
		Statement stmt = con.createStatement();
		String tableName = "pokes";
		
		//stmt.execute("drop table if exists " + tableName);
		//stmt.execute("create table " + tableName + " (key int, value string)");

		/*String sql = ("show tables");
		ResultSet res = stmt.executeQuery(sql);
		if (res.next()) {
			System.out.println(res.getString(1));
		}*/
		
		String sql = ("select * from  pokes");
		ResultSet res = stmt.executeQuery(sql);
		while (res.next()) {
			System.out.println("Campo1: " + res.getString(1) + " campo 2: " + res.getString(2));
		}

	}

}
