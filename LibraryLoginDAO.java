package Library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.util.DBConnectionMgr;

public class LibraryLoginDAO {
	DBConnectionMgr dbMgr = null; 
	Connection con = null;
	PreparedStatement pstmt =null;
	ResultSet rs = null;

	    LibraryLoginDAO(String ID, String password) {
	        try {
	            dbMgr = DBConnectionMgr.getInstance();
	            con = dbMgr.getConnection();

	            String SQL = "SELECT pw FROM admin WHERE ID=?";
	            pstmt = con.prepareStatement(SQL);
	            pstmt.setString(1, ID);
	            rs = pstmt.executeQuery();

	            if (rs.next()) {
	                String storedPassword = rs.getString(1);

	                // 비밀번호 일치 여부 확인 (해시 비교를 사용하는 것이 안전)
	                if (storedPassword.equals(password)) {
	                    // 로그인 성공
	                    // 여기에서 로그인 성공 시 다음 화면으로 전환하거나 작업을 수행할 수 있습니다.
	                    JOptionPane.showMessageDialog(null, "환영합니다.", "로그인 성공", JOptionPane.INFORMATION_MESSAGE);
	                } else {
	                    JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.", "로그인 실패", JOptionPane.WARNING_MESSAGE);
	                }
	            } else {
	                JOptionPane.showMessageDialog(null, "사용자가 아닙니다.", "로그인 실패", JOptionPane.WARNING_MESSAGE);
	            }
	        } catch (SQLException se) {
	            //System.out.println(SQL);
	            System.out.println(se.getMessage());
	            // 예외 처리 필요
	        } finally {
	            // DB 자원 해제
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
	
	


