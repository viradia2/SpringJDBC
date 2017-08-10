package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import javax.swing.tree.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.model.Circle;

@Component
public class DbConnection {
	
	
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public DataSource getDataSource() {
		return dataSource;
	}
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int getCircleCount(){
		String sql = "SELECT COUNT(id) from Triangle";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public String getCircleName(int circleId){
		String sql = "SELECT name from Triangle where id = ?";
		return jdbcTemplate.queryForObject(sql,new Object[] {circleId}, String.class);
	}
	
	public Circle getCircleForId(int circleId){
		String sql = "SELECT * from Triangle where id = ?";
		return jdbcTemplate.queryForObject(sql,new Object[] {circleId}, new CircleMapper());
	}
	
	public List<Circle> getCircleList(){
		String sql = "SELECT * from Triangle";
		return jdbcTemplate.query(sql, new CircleMapper());
	}
	
	public void insertCircle(Circle circle){
		String sql = "INSERT INTO Triangle (ID, NAME) VALUES(?, ?)";
		jdbcTemplate.update(sql, new Object[] {circle.getId(), circle.getName()});
	}

	public void createRectangleTable(){
		String sql = "CREATE Table Rectangle (id integer, name varchar(50))";
		jdbcTemplate.execute(sql);
	}
	/****************************************************************************************
	 * Implementation with JDBC only
	 * 
	 * public Circle getCircle(int circleId) throws SQLException{
	 *	Connection con = null;
	 *
	 *	try{
	 *		con = dataSource.getConnection();
	 *		PreparedStatement ps = con.prepareStatement("SELECT * FROM circle where id = ? ");
	 *		ps.setInt(1, circleId);
	 *		
	 *		Circle circle = null;
	 *		ResultSet rs = ps.executeQuery();
	 *		if(rs.next()){
	 *			circle = new Circle(circleId, rs.getString("name"));
	 *		}
	 *		rs.close();
	 *		ps.close();
	 *		return circle;
	 *	}catch(Exception e){
	 *		throw new RuntimeException();
	 *	}finally{
	 *		con.close();
	 *	}
	 *}
	 ********************************************************************************************/
	
	private class CircleMapper implements org.springframework.jdbc.core.RowMapper<Circle>{
		@Override
		public Circle mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Circle circle = new Circle();
			circle.setId(rs.getInt("id"));
			circle.setName(rs.getString("name"));
			return circle;
		}
	}
}
