package com.example.demo;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.hbase.HbaseConnection;

@SpringBootApplication
public class JavaHbaseApplication implements CommandLineRunner {
	
	@Autowired
	private HbaseConnection hbaseConnection;
	
	public static void main(String[] args) {
		SpringApplication.run(JavaHbaseApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Admin admin = hbaseConnection.getConnection();
		
		TableName table1 = TableName.valueOf("Table1");
		String family1 = "Family1";
		String family2 = "Family2";
		
		HTableDescriptor desc = new HTableDescriptor(table1);
		desc.addFamily(new HColumnDescriptor(family1));
		desc.addFamily(new HColumnDescriptor(family2));
		admin.createTable(desc);
	}

}
