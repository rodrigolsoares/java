package com.example.demo.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.springframework.stereotype.Component;

@Component
public class HbaseConnection {
	
	public Admin getConnection() throws MasterNotRunningException, ZooKeeperConnectionException, IOException {
		
		Configuration config = HBaseConfiguration.create();
		 
		String path = this.getClass().getClassLoader().getResource("hbase-site.xml").getPath();
		config.addResource(new Path(path));
		
		HBaseAdmin.available(config);
		
		Connection connection = ConnectionFactory.createConnection(config);
		return connection.getAdmin();
		
	}
}
