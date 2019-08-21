package com.sist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class UserDao {
	static Logger log=Logger.getLogger(UserDao.class);
	/**
	 * 사용자 추가
	 * james01_99
	 * james02_99
	 * james03_99
	 * 
	 * @param user
	 */
	public void add(User user)throws ClassNotFoundException,SQLException{
		//DB연결
		Connection c = getConnection();
		
		//query
		StringBuilder sb=new StringBuilder();
		sb.append("INSERT INTO USERS(U_ID,NAME,PASSWORD ) VALUES ( ?,?,? )");
		log.debug("query:\n"+sb.toString());
		
		//PreparedStatement
		PreparedStatement ps=c.prepareStatement(sb.toString());
		ps.setString(1, user.getU_id());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		//query run
		int flag = ps.executeUpdate();
		log.debug("flag:"+flag);
		
		//close
		ps.close();
		c.close();
		
	}
	
	/**
	 * 사용자 정보 조회(단건)
	 * @param id
	 * @return User
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public User get(String id) throws SQLException, ClassNotFoundException{
		//DB연결
		Connection c = getConnection();
		
		//query
		StringBuilder sb=new StringBuilder();
		sb.append("SELECT          \n");
		sb.append("  U_ID,         \n");
		sb.append("  NAME,         \n");
		sb.append("  PASSWORD      \n");
		sb.append(" FROM USERS     \n");
		sb.append("WHERE U_ID = ?  \n");
		
		log.debug("query:\n"+sb.toString());
		
		//PreparedStatement
		PreparedStatement ps=c.prepareStatement(sb.toString());
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();
		User user = null;
		
		if(rs.next()==true){
			user = new User();
			user.setU_id(rs.getString("U_ID"));
			user.setName(rs.getString("NAME"));
			user.setPassword(rs.getString("PASSWORD"));
			
		}
		log.debug("user:\n"+user.toString());
		
		return user;
	}
	
	/**
	 *  * IP,PORT:211.238.142.102: 1521 
     * SID: orcl
     * ID/Pass: SIST_HR/SIST1224
	 * Oracle Connection 생성
	 * @return Connection
	 */
	private Connection getConnection() throws ClassNotFoundException,SQLException{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection  connection = 
	DriverManager.getConnection("jdbc:oracle:thin:@211.238.142.102:1521:orcl","SIST_HR","SIST1224");
        return connection;		
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException,SQLException{
		UserDao userDao=new UserDao();
		
		
		//User 
		//-------------------------------------------
		//단건입력
		//-------------------------------------------
		User user=new User();
		user.setU_id("james01_99");
		user.setName("james");
		user.setPassword("0109");
		
		userDao.add(user);
		
		//User 
		//-------------------------------------------
		//단건조회
		//-------------------------------------------
		User userOne=userDao.get(user.getU_id());
		
		if(user.getU_id().equals(userOne.getU_id())  && 
		   user.getName().equals(userOne.getName())  && 
		   user.getPassword().equals(userOne.getPassword())){
			log.debug("같다.단건 입력 성공");
		}else{
			log.debug("다르다.단건 입력 실패");
		}
			
		
	}

}
