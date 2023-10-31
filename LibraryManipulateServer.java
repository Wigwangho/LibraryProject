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
	LibraryTable lr;
	private LibraryTable libraryTable;
	
	public void LibraryAct() {
		
		String sql = "select BOOK_TITLE, BOOK_AUTHOR, YEAR_OF_PUBLICATION, PUBLISHER, IMAGE_URL_S from BOOK_TABLE";
		
	
		dbMgr = DBConnectionMgr.getInstance();
		try {
			con = dbMgr.getConnection();//臾쇰━�쟻�쑝濡� �뼥�뼱�졇 �엳�뒗 �꽌踰꾩� �뿰寃고넻濡� �솗蹂�
			pstmt = con.prepareStatement(sql.toString());//荑쇰━臾몄쓣 癒쇱� �뒪罹뷀븯�뿬 �엳�쓣 吏� 紐⑤Ⅴ�뒗 蹂��닔�쓽 �옄由щ�� 移섑솚�븷寃�.
			rs = pstmt.executeQuery();
			
			Map<String,Object> rmap = null;
			
			while(rs.next()) {
				rmap = new HashMap<>();
				rmap.put("title", rs.getString("Book_Title"));
				rmap.put("author", rs.getString("Book_Author"));
				rmap.put("pubyear", rs.getInt("YEAR_OF_PUBLICATION"));
				rmap.put("publisher", rs.getString("PUBLISHER"));
				rmap.put("imgUrl", rs.getString("IMAGE_URL_S"));
				list.add(rmap);
			}//
			//System.out.println(list);//二쇱냼踰덉�媛� 33踰� 異쒕젰�맆寃껋씠�떎. - �떒�쐞�뀒�뒪�듃 �븯�옄
			//硫붿냼�뱶 �꽕怨꾧� 由ы꽩���엯�씠 鍮좎졇 �엳�쑝誘�濡� �솕硫� 泥섎━源뚯� �뿬湲곗꽌 �빐�빞 �븿.
			/*v = new Vector<>();
			for(int i = 0; i < list.size(); i++) {
			        Map<String, Object> map = list.get(i);
			        v.add(0, map.get("title"));
			        v.add(1, map.get("author"));
			        v.add(2, map.get("pubyear"));
			        v.add(3, map.get("publisher"));
			        v.add(4, map.get("imgUrl"));
			  
			}//*/
		} catch (SQLException se) {
			System.out.println(sql.toString());//異쒕젰�맂 荑쇰━臾몄쓣 媛덈Т由ы빐�꽌 �넗�뱶�뿉�꽌 �솗�씤�빐 蹂쇨쾬.
			System.out.println(se.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}



public void LibraryAdd(String title, String author, String pubyear, String publisher, String imgurl) {
	String sql = "INSERT INTO BOOK_TABLE (BOOK_TITLE, BOOK_AUTHOR, YEAR_OF_PUBLICATION, PUBLISHER, IMAGE_URL_S) VALUES (?,?,?,?,?)";

	dbMgr = DBConnectionMgr.getInstance();
	try {
		con = dbMgr.getConnection();//臾쇰━�쟻�쑝濡� �뼥�뼱�졇 �엳�뒗 �꽌踰꾩� �뿰寃고넻濡� �솗蹂�
		pstmt = con.prepareStatement(sql.toString());//荑쇰━臾몄쓣 癒쇱� �뒪罹뷀븯�뿬 �엳�쓣 吏� 紐⑤Ⅴ�뒗 蹂��닔�쓽 �옄由щ�� 移섑솚�븷寃�.
		pstmt.setString(1, title);
		pstmt.setString(2, author);
		pstmt.setString(3, pubyear);
		pstmt.setString(4, publisher);
		pstmt.setString(5, imgurl);
		rs = pstmt.executeQuery();
		int rowsDeleted = pstmt.executeUpdate();
		if(rowsDeleted>0) {
			JOptionPane.showMessageDialog(null, "성공적으로 추가하였습니다.", "성공!", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(null, "추가에 실패하였습니다.", "실패!", JOptionPane.ERROR_MESSAGE);
		}
	}catch(SQLException se) {
		System.out.println(sql.toString());//異쒕젰�맂 荑쇰━臾몄쓣 媛덈Т由ы빐�꽌 �넗�뱶�뿉�꽌 �솗�씤�빐 蹂쇨쾬.
		System.out.println(se.getMessage());
	} catch (Exception e) {
		
	}

}



	public List<Map<String,Object>> searchInit() {
		System.out.println("검색 시작");

	    this.list = new ArrayList<>(); // 초기화

	    if (libraryTable == null) {
	    	libraryTable = new LibraryTable(this);
	    	libraryTable.initDisplay();
	    }

	    this.LibraryAct(); // 검색 결과를 this.list에 설정
	    libraryTable.updateTableModel(this.list);
	    libraryTable.setVisible(true); // Ensure the LibraryResult window is visible

	    List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
	    for (int s = 0; s < this.list.size(); s++) {
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("pubyear", (int) this.list.get(s).get("pubyear"));
	        map.put("author", (String) this.list.get(s).get("author"));
	        map.put("publisher", (String) this.list.get(s).get("publisher"));
	        map.put("title", (String) this.list.get(s).get("title"));
	        map.put("imgurl", (String) this.list.get(s).get("imgurl"));
	        list1.add(map);
	    }
	    return this.list;
	
}
}