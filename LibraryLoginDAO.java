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
	LibraryManipulateServer server = new LibraryManipulateServer();
	int yes = 0;
	
	 public boolean deleteBook(int yes) {
	        // Delete the book with the given title from the database
	        // If deletion is successful, return true; otherwise, return false
	        if (yes == 1) {
	            return true;
	        } else {
	            return false;
	        }
	    }
	public LibraryLoginDAO(String title) {
	    try {
	        dbMgr = DBConnectionMgr.getInstance();
	        con = dbMgr.getConnection();
	        con.setAutoCommit(false); // 수동 커밋 모드 설정

	        String SQL = "DELETE FROM book_table WHERE book_title=?";
	        pstmt = con.prepareStatement(SQL);
	        pstmt.setString(1, title);
	        int rowsDeleted = pstmt.executeUpdate();

	        if (rowsDeleted > 0) {
	            System.out.println("삭제 성공");
	            con.commit(); // 커밋
	            yes = 1;
	        } else {
	            System.out.println("해당 도서를 찾을 수 없습니다.");
	        }

	    } catch (SQLException se) {
	        System.out.println(se.getMessage());
	        try {
	            if (con != null) {
	                con.rollback(); // 롤백
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    } finally {
	        try {
	            if (pstmt != null) pstmt.close();
	            if (con != null) {
	                con.setAutoCommit(true); // 다시 자동 커밋 모드로 변경
	                dbMgr.freeConnection(null, con);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
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

	                // 鍮꾨�踰덊샇 �씪移� �뿬遺� �솗�씤 (�빐�떆 鍮꾧탳瑜� �궗�슜�븯�뒗 寃껋씠 �븞�쟾)
	                if (storedPassword.equals(password)) {
	                    // 濡쒓렇�씤 �꽦怨�
	                    // �뿬湲곗뿉�꽌 濡쒓렇�씤 �꽦怨� �떆 �떎�쓬 �솕硫댁쑝濡� �쟾�솚�븯嫄곕굹 �옉�뾽�쓣 �닔�뻾�븷 �닔 �엳�뒿�땲�떎.
	                    //JOptionPane.showMessageDialog(null, "환영합니다.", "로그인 성공", JOptionPane.INFORMATION_MESSAGE);
	                    LibraryTable lt = new LibraryTable(server);
	                    lt.initDisplay();
	                } else {
	                    JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.", "로그인 실패..", JOptionPane.WARNING_MESSAGE);
	                }
	            } else {
	                JOptionPane.showMessageDialog(null, "존재하지 않는 아이디입니다.", "로그인 실패..", JOptionPane.WARNING_MESSAGE);
	            }
	        } catch (SQLException se) {
	            //System.out.println(SQL);
	            System.out.println(se.getMessage());
	            // �삁�쇅 泥섎━ �븘�슂
	        } finally {
	            // DB �옄�썝 �빐�젣
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
	
	


