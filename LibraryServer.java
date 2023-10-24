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

import com.util.DBConnectionMgr;

public class LibraryServer{
	
	DBConnectionMgr dbMgr = null; 
	Connection con = null;
	PreparedStatement pstmt =null;
	ResultSet rs = null;
	Vector<Object> v = null;
	List<Map<String,Object>> list = new ArrayList<>();
	LibraryResult lr;
	
	public void LibraryAct(String bname, int selectedNum) {
		
		StringBuilder sql = new StringBuilder();
		
	switch (selectedNum) {
	case -1:
		sql.append("select BOOK_TITLE, BOOK_AUTHOR, YEAR_OF_PUBLICATION, PUBLISHER, IMAGE_URL_S from BOOK_TABLE where BOOK_TITLE like '%'||?||'%'");
        break;
    case 0:
        sql.append("select BOOK_TITLE, BOOK_AUTHOR, YEAR_OF_PUBLICATION, PUBLISHER, IMAGE_URL_S from BOOK_TABLE where BOOK_TITLE like '%'||?||'%'");
        break;
    case 1:
        sql.append("select BOOK_TITLE, BOOK_AUTHOR, YEAR_OF_PUBLICATION, PUBLISHER, IMAGE_URL_S from BOOK_TABLE where BOOK_AUTHOR like '%'||?||'%'");
        break;
    case 2:
        sql.append("select BOOK_TITLE, BOOK_AUTHOR, YEAR_OF_PUBLICATION, PUBLISHER, IMAGE_URL_S from BOOK_TABLE where PUBLISHER like '%'||?||'%'");
        break;
}
		//
		dbMgr = DBConnectionMgr.getInstance();
		try {
			con = dbMgr.getConnection();//물리적으로 떨어져 있는 서버와 연결통로 확보
			pstmt = con.prepareStatement(sql.toString());//쿼리문을 먼저 스캔하여 있을 지 모르는 변수의 자리를 치환할것.
			pstmt.setString(1, bname);
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
			System.out.println(list);//주소번지가 33번 출력될것이다. - 단위테스트 하자
			//메소드 설계가 리턴타입이 빠져 있으므로 화면 처리까지 여기서 해야 함.
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
			System.out.println(sql.toString());//출력된 쿼리문을 갈무리해서 토드에서 확인해 볼것.
			System.out.println(se.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}







	public List<Map<String,Object>> searchInit(String gotTxt, int checkednum) {
		this.LibraryAct(gotTxt, checkednum);
		List<Map<String,Object>> list1 = new ArrayList<Map<String,Object>>();
		if (lr == null) {
            lr = new LibraryResult(this); // 최초 호출 시에만 객체 생성
        }
		for (int s = 0; s<this.list.size(); s++) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pubyear", (int) this.list.get(s).get("pubyear"));
		map.put("author", (String) this.list.get(s).get("author"));
		map.put("publisher", (String) this.list.get(s).get("publisher"));
		map.put("title", (String) this.list.get(s).get("title"));
		map.put("imgurl", (String) this.list.get(s).get("imgurl"));
		list1.add(map);
		
	}
	return list1;
	}
		
		
	


	

}

