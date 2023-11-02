package Library;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class LibraryMain extends JFrame implements ActionListener {
    private DefaultTableModel dtm;
    private JTable jt = new JTable();;
    private Object[][] DataforTable = {};
    String[] header = {"Title", "Author", "Publisher", "Publication Year"};
    JButton jbtn_select = new JButton("조회");
    JButton jbtn_login = new JButton("로그인");
    JTextField jtxt_search = new JTextField();
    JPanel jp_south = new JPanel();
    String[] checknums = {"도서명으로 검색","작가명으로 검색","출판사명으로 검색"};
    JList jlist = new JList(checknums);
    List<Map<String, Object>> searchResult;

    LibraryLoginView libraryloginview = new LibraryLoginView();
    LibraryServer libraryserver = new LibraryServer();


	public void updateTableModel(List<Map<String, Object>> list) {
		if (list == null) {
	        return; // Avoid processing a null list
	    }
		DefaultTableModel model = (DefaultTableModel) jt.getModel();
	    // Clear the current data
	    model.setRowCount(0);
	    
	    Vector<Vector<Object>> data = new Vector<>();
	    for (Map<String, Object> item : list) {
	        Vector<Object> row = new Vector<>();
	        row.add(item.get("title"));
	        row.add(item.get("author"));
	        row.add(item.get("publisher"));
	        row.add(item.get("pubyear"));
	        data.add(row);
	    }

	    // Set the new data
	    for (Vector<Object> row : data) {
	        model.addRow(row);
	    }
	    dtm = model;
    }
    
    public void initDisplay() {
    	 jp_south.setLayout(new GridBagLayout());
    	    GridBagConstraints gbc = new GridBagConstraints();

    	    // Create a panel for each component
    	    JPanel textFieldPanel = new JPanel();
    	    jtxt_search.setPreferredSize(new Dimension(150, 30)); // Set text field size
    	    textFieldPanel.add(jtxt_search);
    	    gbc.gridx = 1;
    	    jp_south.add(textFieldPanel, gbc);

    	    JPanel buttonPanel = new JPanel();
    	    jbtn_select.setPreferredSize(new Dimension(80, 30)); // Set button size
    	    buttonPanel.add(jbtn_select);
    	    gbc.gridx = 0;
    	    jp_south.add(buttonPanel, gbc);

    	    JPanel listPanel = new JPanel();
    	    listPanel.add(jlist);
    	    gbc.gridx = 2;
    	    jp_south.add(listPanel, gbc);
    	    jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only one item to be selected

    	    JPanel loginbuttonPanel = new JPanel();
    	    jbtn_login.setPreferredSize(new Dimension(80, 30)); // Set button size
    	    loginbuttonPanel.add(jbtn_login);
    	    gbc.gridx = 3;
    	    jp_south.add(loginbuttonPanel, gbc);

    	    dtm = new DefaultTableModel(new Object[0][], header);
    	    jt = new JTable(dtm);

    	    // 이벤트 리스너 연결
    	    jbtn_select.addActionListener(this);
    	    jbtn_login.addActionListener(this);

    	    this.add(jp_south, BorderLayout.SOUTH);
    	    JScrollPane jsp = new JScrollPane(jt);
    	    Container con = this.getContentPane();
    	    con.add(jsp, BorderLayout.CENTER);
    	    this.setSize(500, 300);
    	    this.setVisible(true);
    }

  

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == jbtn_select) {
			String searchtxt = jtxt_search.getText();
	        int searchnum = jlist.getSelectedIndex();

	      
	        searchResult = libraryserver.searchInit(searchtxt, searchnum);
	        updateTableModel(searchResult);
	       
        }
		else if(obj == jbtn_login) {
			dispose();
			libraryloginview.initDisplay();
		}
		
	}
	public static void main(String[] args) {
		LibraryMain librarymain = new LibraryMain();
		librarymain.initDisplay();
	}
}