package com.main;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.db.DbConnection;
import com.model.Circle;

public class JDBCDemo {
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		
		//Passing the class, So we don't have to cast it(i.e.DbConnection.class)
		DbConnection db = context.getBean("dbConnection", DbConnection.class);
		
//		System.out.println(db.getCircleForId(1).getName());
//		
//		System.out.println("List Size: " + db.getCircleList().size());
		
//		db.insertCircle(new Circle(2, "Second Trianlge"));
		
//		System.out.println(db.getCircleForId(2).getName());
		
//		db.insertCircle(new Circle(4, "Fourth Circle"));
//		
//		System.out.println("List Size: " + db.getCircleList().size());
		
		db.createRectangleTable();
	}
}
