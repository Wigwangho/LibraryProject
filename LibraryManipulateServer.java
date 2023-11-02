package Library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.util.DBConnectionMgr;

public class LibraryManipulateServer {
	DBConnectionMgr dbMgr = null; 
	Connection con = null;
	PreparedStatement pstmt =null;
	ResultSet rs = null;
	Vector<Object> v = null;
	List<Map<String,Object>> list = new ArrayList<>();
	int yes = 0;
    

	
	


public void LibraryAdd(String isbn, String title, String author, String pubyear, String publisher, String imgurl) {
	String sql = "INSERT INTO BOOK_TABLE (ISBN, BOOK_TITLE, BOOK_AUTHOR, YEAR_OF_PUBLICATION, PUBLISHER, IMAGE_URL_S) VALUES (?,?,?,?,?,?)";

	dbMgr = DBConnectionMgr.getInstance();
	try {
		con = dbMgr.getConnection();
		pstmt = con.prepareStatement(sql.toString());
		pstmt.setString(1, isbn);
		pstmt.setString(2, title);
		pstmt.setString(3, author);
		pstmt.setString(4, pubyear);
		pstmt.setString(5, publisher);
		pstmt.setString(6, imgurl);

		System.out.println("데이터 삽입함");
		 int rowsInserted = pstmt.executeUpdate(); // Use rowsInserted instead of rowsDeleted
	        if (rowsInserted > 0) { // Check if rows were successfully inserted
	            JOptionPane.showMessageDialog(null, "성공적으로 추가하였습니다.", "성공!", JOptionPane.INFORMATION_MESSAGE);
	            list.clear(); // Clear the list to avoid duplicates
	        } else {
	            JOptionPane.showMessageDialog(null, "추가에 실패하였습니다.", "실패!", JOptionPane.ERROR_MESSAGE);
	        }
	}catch(SQLException se) {
		System.out.println(sql.toString());
		System.out.println(se.getMessage());
	} catch (Exception e) {
		
	}

}
public boolean deleteBook(int yes) {
    // Delete the book with the given title from the database
    // If deletion is successful, return true; otherwise, return false
    if (yes == 1) {
        return true;
    } else {
        return false;
    }
}

public void book_Delete(String title) {
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

	
}