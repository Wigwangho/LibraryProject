package Library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.util.DBConnectionMgr;

public class LibraryLoginDAO {
	DBConnectionMgr dbMgr = null; 
	Connection con = null;
	PreparedStatement pstmt =null;
	ResultSet rs = null;
	LibraryAdvanced libraryadvanced = new LibraryAdvanced();
	int yes = 0;
	
	

	    public void Login (String ID, String password) {
	        try {
	            dbMgr = DBConnectionMgr.getInstance();
	            con = dbMgr.getConnection();

	            String SQL = "SELECT pw FROM admin WHERE ID=?";
	            pstmt = con.prepareStatement(SQL);
	            pstmt.setString(1, ID);
	            rs = pstmt.executeQuery();

	            if (rs.next()) {
	                String storedPassword = rs.getString(1);

	                if (storedPassword.equals(password)) {
	                    
	                    
	                	libraryadvanced.initDisplay();
	                } else {
	                    JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.", "로그인 실패..", JOptionPane.WARNING_MESSAGE);
	                }
	            } else {
	                JOptionPane.showMessageDialog(null, "존재하지 않는 아이디입니다.", "로그인 실패..", JOptionPane.WARNING_MESSAGE);
	            }
	        } catch (SQLException se) {
	            System.out.println(se.getMessage());
	        } finally {
	            try {
	                if (rs != null) rs.close();
	                if (pstmt != null) pstmt.close();
	                if (con != null) dbMgr.freeConnection(null, con);
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
	


